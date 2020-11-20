package com.android.alfazvohrapractical.core;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Telephony;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ContentLoadingProgressBar;

import com.android.alfazvohrapractical.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.LOCATION_SERVICE;
import static android.media.MediaFormat.KEY_PROFILE;

public class CommonUtils {

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static String SHARED_PREF_NAME = "SHARED_PREFS_ACE";
    private static AlertDialog alert;

    public static void makeToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void setSharedPreference(Context p_context) {
        if (Constant.m_sharedPreference == null)
            Constant.m_sharedPreference = p_context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getTime(int hr, int min) {
        Time tme = new Time(hr, min, 0);//seconds by default set to zero
        Format formatter;
//        formatter = new SimpleDateFormat( "hh:mm a" );
        formatter = new SimpleDateFormat("hh:mm");
        return formatter.format(tme);
    }

    /**
     * Initializes {@link android.content.SharedPreferences.Editor}.
     *
     * @param p_context activity context
     */
    public static void setEditor(Context p_context) {

        setSharedPreference(p_context);
        if (Constant.m_sharedPrefEditor == null)
            Constant.m_sharedPrefEditor = Constant.m_sharedPreference.edit();
    }

    /**
     * Adds {@link Integer} value of <code>p_spKey</code> into {@link android.content.SharedPreferences}.
     * If value of <code>p_spKey</code> is already set then overrides its previous value.
     *
     * @param p_context activity context
     * @param p_spKey   KeyName
     */

    public static void setIntSharedPref(Context p_context, String p_spKey, Integer p_value) {
        setEditor(p_context);
        Constant.m_sharedPrefEditor.putInt(p_spKey, p_value);
        Constant.m_sharedPrefEditor.commit();
    }

    /**
     * Adds {@link String} value of <code>p_spKey</code> into {@link android.content.SharedPreferences}.
     * If value of <code>p_spKey</code> is already set then overrides its previous value.
     *
     * @param p_context activity context
     * @param p_spKey   KeyName
     */

    public static void setStringSharedPref(Context p_context, String p_spKey, String p_value) {

        setEditor(p_context);
        Constant.m_sharedPrefEditor.putString(p_spKey, p_value);
        Constant.m_sharedPrefEditor.commit();
    }

    /*public static void saveProfile(Context c, User tutor) {
        setEditor(c);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        setStringSharedPref(c, KEY_PROFILE, gsonBuilder.create().toJson(tutor));
    }

    public static User getProfile(Context c) {
        setSharedPreference(c);
        if (Constant.m_sharedPreference.contains(KEY_PROFILE)) {
            String json = getStringSharedPref(c, KEY_PROFILE, null);
            Gson gson = new Gson();
            User taxSetting = gson.fromJson(json, User.class);
            return taxSetting;
        } else
            return null;
    }*/

    /*public static void showFullImageDialog(Activity mActivity, final String imageUrl) {
        final Dialog dialog = new Dialog(mActivity, RelativeLayout.LayoutParams.MATCH_PARENT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.dialog_full_item_image);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        final ImageView btnClose = dialog.findViewById(R.id.btnClose);
        final ImageView imgFullImage = (TouchImageView) dialog.findViewById(R.id.img_full_image);
        final ContentLoadingProgressBar itemPgb = dialog.findViewById(R.id.item_pgb);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (!isEmpty(imageUrl)) {
            Glide.with(mActivity).
                    load(imageUrl)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.placeholder_image)
//                            .fitCenter()
//                            .centerCrop()
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(imgFullImage);
        } else {
            itemPgb.setVisibility(View.GONE);
//            imgFullImage.setImageResource(R.drawable.);
        }
        dialog.show();
    }*/

    public static void setBooleanPref(Context p_context, String p_spKey, boolean p_value) {
        setEditor(p_context);
        Constant.m_sharedPrefEditor.putBoolean(p_spKey, p_value);
        Constant.m_sharedPrefEditor.commit();
    }

    public static void redirectToActivity(Context c, Activity from, Class to, boolean isFinish, Bundle b, int flag) {

        if (from != null) {
            Intent i = new Intent(from, to);
            if (b != null) {
                i.putExtras(b);
            }
            if (flag != 0) {
                i.addFlags(flag);
            }
            if (isFinish) {
                from.startActivity(i);
                from.finish();
                // from.overridePendingTransition(R.animator.enter_from_right, R.animator.exit_to_left);
            } else {
                from.startActivity(i);
                // from.overridePendingTransition(R.animator.enter_from_right, R.animator.exit_to_left);
            }

        } else {
            Intent i = new Intent(c, to);
            if (b != null) {
                i.putExtras(b);
            }
            if (flag != 0) {
                i.addFlags(flag);
            }

            if (isFinish) {
                c.startActivity(i);
                ((Activity) c).finish();
                // ((Activity) c).overridePendingTransition(R.animator.enter_from_right, R.animator.exit_to_left);

            } else {
                c.startActivity(i);
                //((Activity) c).overridePendingTransition(R.animator.enter_from_right, R.animator.exit_to_left);
            }
        }
    }

    /**
     * Gets {@link Integer} value of <code>p_spKey</code> from {@link android.content.SharedPreferences}.
     *
     * @param p_context activity context
     * @param p_spKey   KeyName
     * @return {@link Integer} value of <code>p_spKey</code>
     */

    public static int getIntSharedPref(Context p_context, String p_spKey, int p_value) {

        setSharedPreference(p_context);
        return Constant.m_sharedPreference.getInt(p_spKey, p_value);
    }

    public static boolean getBooleanSharedPref(Context p_context, String p_spKey, boolean p_value) {

        setSharedPreference(p_context);
        return Constant.m_sharedPreference.getBoolean(p_spKey, p_value);
    }

    /**
     * Gets {@link String} value of <code>p_spKey</code> from {@link android.content.SharedPreferences}.
     *
     * @param p_context activity context
     * @param p_spKey   KeyName
     * @return {@link String} value of <code>p_spKey</code>
     */

    public static String getStringSharedPref(Context p_context, String p_spKey, String p_value) {

        setSharedPreference(p_context);
        return Constant.m_sharedPreference.getString(p_spKey, p_value);
    }

    public static void copyToClipboard(Context activity, String content, String toast) {
        if (activity == null)
            return;
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", content);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
        }
        CommonUtils.makeToast(activity, toast + " copied to Clipboard");
    }

    public static Bitmap handleSamplingAndRotationBitmapForUpload(String selectedImage) {

        int MAX_HEIGHT = 2048;
        int MAX_WIDTH = 2048;

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImage, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH, MAX_HEIGHT);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap img = BitmapFactory.decodeFile(selectedImage, options);
        if (selectedImage != null)
            img = rotateImageIfRequired(img, selectedImage);
        return img;
    }


    public static Bitmap handleSamplingAndRotationBitmap(String selectedImage) {
        int MAX_HEIGHT = 400;
        int MAX_WIDTH = 400;

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImage, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, MAX_WIDTH, MAX_HEIGHT);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap img = BitmapFactory.decodeFile(selectedImage, options);
        if (selectedImage != null)
            img = rotateImageIfRequired(img, selectedImage);
        return img;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee a final image
            // with both dimensions larger than or equal to the requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).

            final float totalPixels = width * height;

            // Anything more than 2x the requested pixels we'll sample down further
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }

    private static Bitmap rotateImageIfRequired(Bitmap img, String selectedImage) {

        try {
            ExifInterface ei = new ExifInterface(selectedImage);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotateImage(img, 90);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotateImage(img, 180);
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotateImage(img, 270);
                default:
                    return img;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    public static String saveImage(Context context, Bitmap bitmap, String dirName) {
        String filePah = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + context.getPackageName() + File.separator + dirName;
        if (!new File(filePah).exists()) {
            new File(filePah).mkdirs();
        }

        Calendar calendar = Calendar.getInstance();
        String filename = File.separator + "ACE_IMG__" + calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH) + 1) + "_" + calendar.get(Calendar.DAY_OF_MONTH) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "_" + calendar.get(Calendar.MINUTE) + "_" + calendar.get(Calendar.SECOND) + ".jpg";
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePah + filename);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
            return filePah + filename;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePah + File.separator + filename;
    }

    public static String moveFile(String inputPath, String inputFile, Context context, String dirName) {

        String filePah = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + context.getPackageName() + dirName;
        Calendar calendar = Calendar.getInstance();
        String filename = File.separator + "photo_" + calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH) + 1) + "_" + calendar.get(Calendar.DAY_OF_MONTH) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "_" + calendar.get(Calendar.MINUTE) + "_" + calendar.get(Calendar.SECOND) + ".jpg";
        if (!new File(filePah).exists()) {
            new File(filePah).mkdirs();
        }

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File(filePah);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(filePah + filename);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;

            // delete the original file
            new File(inputPath + inputFile).delete();
        } catch (FileNotFoundException fnfe1) {
            CommonUtils.printLog("tag", fnfe1.getMessage());
        } catch (Exception e) {
            CommonUtils.printLog("tag", e.getMessage());
        }
        return filePah + filename;
    }

    public static boolean deleteFile(String path) {
        File mFile = new File(path);
        if (mFile.exists()) {
            return mFile.delete();
        } else {
            return false;
        }
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static int getDeviceHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return height;
    }

    public static int getDeviceWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return width;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }
    }

    /*A string that uniquely identifies this build.*/
    public static String getDeviceFingerPrint() {
        return Build.FINGERPRINT;
    }

    public static int getTabletSize(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        /*float scaleFactor = metrics.density;

        float widthDp = widthPixels / scaleFactor;
        float heightDp = heightPixels / scaleFactor;

        float smallestWidth = Math.min(widthDp, heightDp);

        if (smallestWidth > 720) {
            //Device is a 10" tablet
        }
        else if (smallestWidth > 600) {
            //Device is a 7" tablet
        }*/

        float widthDpi = metrics.xdpi;
        float heightDpi = metrics.ydpi;

        float widthInches = widthPixels / widthDpi;
        float heightInches = heightPixels / heightDpi;

        double diagonalInches = Math.sqrt(
                (widthInches * widthInches)
                        + (heightInches * heightInches));

        if (diagonalInches >= 10) {
            return 10;
        } else if (diagonalInches >= 9) {
            return 9;
        } else if (diagonalInches >= 7) {
            return 7;
        } else {
            return 7;
        }
    }

    /**
     * Method to request focus of view
     *
     * @param p_view
     */
    public static void requestFocus(View p_view, Context p_Context) {
        if (p_view.requestFocus()) {
            ((Activity) p_Context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean validateForEmpty(EditText p_editText, Context p_context, String p_string) {
        if (p_editText.getText().toString().trim().isEmpty()) {
            p_editText.setError(p_string);
            requestFocus(p_editText, p_context);
            return false;
        } else {
            p_editText.setError(null);
        }

        return true;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static final boolean isValidURL(CharSequence url) {
        String webUrl = "^((ftp|http|https):\\\\/\\\\/)?(www.)?(?!.*(ftp|http|https|www.))[a-zA-Z0-9_-]+(\\\\.[a-zA-Z]+)+((\\\\/)[\\\\w#]+)*(\\\\/\\\\w+\\\\?[a-zA-Z0-9_]+=\\\\w+(&[a-zA-Z0-9_]+=\\\\w+)*)?$";
        if (url == null) {
            return false;
        } else {
            return Patterns.WEB_URL.matcher(url).matches();
        }
    }


    public static void showKeyBoard(View view) {
        if (view != null) {
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                editText.setSelection(editText.length());
            }
            InputMethodManager imm = (InputMethodManager)
                    view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void hideKeyBoard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public static String getMimeType(Context context, Uri uri) {
        String mimeType = null;
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            ContentResolver cr = context.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }


    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static String copyFile(String inputPath, String fileName, Context context, File destination, String extension) {

        //Calendar calendar = Calendar.getInstance();
        //String filename = "file" + calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH) + 1) + "_" + calendar.get(Calendar.DAY_OF_MONTH) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "_" + calendar.get(Calendar.MINUTE) + "_" + calendar.get(Calendar.SECOND) + extension;


        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist

            if (!destination.exists()) {
                destination.mkdirs();
            }

            in = new FileInputStream(inputPath);
            out = new FileOutputStream(destination.getAbsolutePath() + File.separator + fileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;
        } catch (FileNotFoundException fnfe1) {
            CommonUtils.printLog("tag", fnfe1.getMessage());
        } catch (Exception e) {
            CommonUtils.printLog("tag", e.getMessage());
        }
        return fileName;
    }

    public static void printLog(String tag, String message) {
        if (Constant.IS_DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void printErrorLog(String tag, String message, Throwable throwable) {
        if (Constant.IS_DEBUG) {
            Log.e(tag, message, throwable);
        }
    }

    public static String getDefaultSmsAppPackageName(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            return Telephony.Sms.getDefaultSmsPackage(context);
        else {
            Intent intent = new Intent(Intent.ACTION_VIEW)
                    .addCategory(Intent.CATEGORY_DEFAULT).setType("vnd.android-dir/mms-sms");
            final List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(intent, 0);
            if (resolveInfos != null && !resolveInfos.isEmpty())
                return resolveInfos.get(0).activityInfo.packageName;
            return "";
        }
    }


    /**
     * @param root usually Activity.getWindow().getDecorView() or your custom Toolbar
     */
    public static
    View findActionBarTitle(View root) {
        return findActionBarItem(root, "action_bar_title", "mTitleTextView");
    }

    /**
     * @param root usually Activity.getWindow().getDecorView() or your custom Toolbar
     */

    public static
    View findActionBarSubTitle(View root) {
        return findActionBarItem(root, "action_bar_subtitle", "mSubtitleTextView");
    }

    private static
    View findActionBarItem(View root,
                           String resourceName, String toolbarFieldName) {
        View result = findViewSupportOrAndroid(root, resourceName);

        if (result == null) {
            View actionBar = findViewSupportOrAndroid(root, "action_bar");
            if (actionBar != null) {
                result = reflectiveRead(actionBar, toolbarFieldName);
            }
        }
        if (result == null && root.getClass().getName().endsWith("widget.Toolbar")) {
            result = reflectiveRead(root, toolbarFieldName);
        }
        return result;
    }

    @SuppressWarnings("ConstantConditions")
    private static @Nullable
    View findViewSupportOrAndroid(View root, String resourceName) {
        Context context = root.getContext();
        View result = null;
        if (result == null) {
            int supportID = context.getResources().getIdentifier(resourceName, "id", context.getPackageName());
            result = root.findViewById(supportID);
        }
        if (result == null) {
            int androidID = context.getResources().getIdentifier(resourceName, "id", "android");
            result = root.findViewById(androidID);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T reflectiveRead(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(object);
        } catch (Exception ex) {
            CommonUtils.printLog("HACK", "Cannot read " + fieldName + " in " + object + " " + ex);
        }
        return null;
    }


    public static void openDialer(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        context.startActivity(intent);
    }

    /**
     * Convert byte array to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sbuf = new StringBuilder();
        for (int idx = 0; idx < bytes.length; idx++) {
            int intVal = bytes[idx] & 0xff;
            if (intVal < 0x10) sbuf.append("0");
            sbuf.append(Integer.toHexString(intVal).toUpperCase());
        }
        return sbuf.toString();
    }

    /**
     * Get utf8 byte array.
     *
     * @param str
     * @return array of NULL if error was found
     */
    public static byte[] getUTF8Bytes(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Load UTF8withBOM or any ansi text file.
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static String loadFileAsString(String filename) throws IOException {
        final int BUFLEN = 1024;
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(filename), BUFLEN);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(BUFLEN);
            byte[] bytes = new byte[BUFLEN];
            boolean isUTF8 = false;
            int read, count = 0;
            while ((read = is.read(bytes)) != -1) {
                if (count == 0 && bytes[0] == (byte) 0xEF && bytes[1] == (byte) 0xBB && bytes[2] == (byte) 0xBF) {
                    isUTF8 = true;
                    baos.write(bytes, 3, read - 3); // drop UTF8 bom marker
                } else {
                    baos.write(bytes, 0, read);
                }
                count += read;
            }
            return isUTF8 ? new String(baos.toByteArray(), "UTF-8") : new String(baos.toByteArray());
        } finally {
            try {
                is.close();
            } catch (Exception ex) {
            }
        }
    }

    /*set scrollView inside editText*/
    @SuppressLint("ClickableViewAccessibility")
    public static void makeEditTextScrollable(EditText editText) {
        editText.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_SCROLL:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                }
                return false;
            }
        });
    }

    /**
     * Returns MAC address of the given interface name.
     *
     * @param interfaceName eth0, wlan0 or NULL=use first interface
     * @return mac address or empty string
     */
    public static String getMACAddress(String interfaceName) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (interfaceName != null) {
                    if (!intf.getName().equalsIgnoreCase(interfaceName)) continue;
                }
                byte[] mac = intf.getHardwareAddress();
                if (mac == null) return "";
                StringBuilder buf = new StringBuilder();
                for (int idx = 0; idx < mac.length; idx++)
                    buf.append(String.format("%02X:", mac[idx]));
                if (buf.length() > 0) buf.deleteCharAt(buf.length() - 1);
                return buf.toString();
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
        /*try {
            // this is so Linux hack
            return loadFileAsString("/sys/class/net/" +interfaceName + "/address").toUpperCase().trim();
        } catch (IOException ex) {
            return null;
        }*/
    }

    /**
     * Get IP address from first non-localhost interface
     *
     * @param useIPv4 true=return ipv4, false=return ipv6
     * @return address or empty string
     */
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }


    public static void closeInternetAlert() {
        if (alert != null)
            alert.dismiss();
    }

    //Checking for loaction enbaled or disabled
    public static boolean isLocationEnabled(Context mContext) {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    //returns distance in kilometers (km)
    public static double distanceInKms(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage & camera permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static String parseDate(String time) {
        String inputPattern = "dd/MM/yyyy HH:mm:ss";
        String outputPattern = "HH:mm:ss dd-MMM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String formatDateAndTime(long yourmilliseconds) {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy hh:mm aa");
        Date resultDate = new Date(yourmilliseconds);
        String date = format.format(resultDate);
        System.out.println(format.format(resultDate));
        return date;
    }

    public static String formattedDate(long yourmilliseconds) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        Date resultdate = new Date(yourmilliseconds);
        String date = sdf.format(resultdate);
        System.out.println(sdf.format(resultdate));
        return date;
    }

    public static String formattedDateAndTime(long yourmilliseconds) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        Date resultdate = new Date(yourmilliseconds);
        String date = sdf.format(resultdate);
        System.out.println(sdf.format(resultdate));
        return date;
    }

    public static String yearFromDateString(String releaseDate) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd",
                Locale.getDefault());

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
            Date date = simpleDateFormat.parse(releaseDate);
            gregorianCalendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(gregorianCalendar.get(Calendar.YEAR));

    }

   /* public static String getTime(int hr, int min) {
        Time tme = new Time(hr, min, 0);//seconds by default set to zero
        Format formatter;
        formatter = new SimpleDateFormat("hh:mm a");
//        formatter = new SimpleDateFormat( "hh:mm" );
        return formatter.format(tme);
    }*/

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        if (darkness < 0.5) {
            return false; // It's a light color
        } else {
            return true; // It's a dark color
        }
    }

    public static int getContrastColor(int bgColor) {
        return Color.rgb(255 - Color.red(bgColor),
                255 - Color.green(bgColor),
                255 - Color.blue(bgColor));
    }

    public static String getFormattedNumber(double number) {
        DecimalFormat df = new DecimalFormat(".##");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(number);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void loadImage(Context context, ImageView imageView, String imageUrl) {
        Glide.with(context).
                load(imageUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.placeholder_image)
                        .fitCenter()
                        .centerCrop()
                        .skipMemoryCache(true))
                .into(imageView);
    }

    public static boolean isEmpty(final CharSequence s) {
        return s == null || s.length() == 0;
    }

    private String getDeviceBoard() {
        return Build.BOARD;
    }

}
