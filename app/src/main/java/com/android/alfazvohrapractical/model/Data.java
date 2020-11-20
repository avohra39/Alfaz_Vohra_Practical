package com.android.alfazvohrapractical.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("availability_status")
    @Expose
    private String availabilityStatus;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avg_rating")
    @Expose
    private Float avgRating;
    @SerializedName("avg_rating_by_consumer")
    @Expose
    private Float avgRatingByConsumer;
    @SerializedName("avg_rating_by_delivery_boy")
    @Expose
    private Float avgRatingByDeliveryBoy;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("cover_photo")
    @Expose
    private String coverPhoto;
    @SerializedName("discount_type")
    @Expose
    private String discountType;
    @SerializedName("discount_value")
    @Expose
    private Integer discountValue;
    @SerializedName("range")
    @Expose
    private Integer range;
    @SerializedName("preparation_time")
    @Expose
    private String preparationTime;
    @SerializedName("delivery_charge")
    @Expose
    private Integer deliveryCharge;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("restaurant_longitude")
    @Expose
    private Double restaurantLongitude;
    @SerializedName("restaurant_latitude")
    @Expose
    private Double restaurantLatitude;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("est_order_time")
    @Expose
    private Integer estOrderTime;
    @SerializedName("total_item")
    @Expose
    private Integer totalItem;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Float avgRating) {
        this.avgRating = avgRating;
    }

    public Float getAvgRatingByConsumer() {
        return avgRatingByConsumer;
    }

    public void setAvgRatingByConsumer(Float avgRatingByConsumer) {
        this.avgRatingByConsumer = avgRatingByConsumer;
    }

    public Float getAvgRatingByDeliveryBoy() {
        return avgRatingByDeliveryBoy;
    }

    public void setAvgRatingByDeliveryBoy(Float avgRatingByDeliveryBoy) {
        this.avgRatingByDeliveryBoy = avgRatingByDeliveryBoy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Integer getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Integer discountValue) {
        this.discountValue = discountValue;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Integer getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Integer deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getRestaurantLongitude() {
        return restaurantLongitude;
    }

    public void setRestaurantLongitude(Double restaurantLongitude) {
        this.restaurantLongitude = restaurantLongitude;
    }

    public Double getRestaurantLatitude() {
        return restaurantLatitude;
    }

    public void setRestaurantLatitude(Double restaurantLatitude) {
        this.restaurantLatitude = restaurantLatitude;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getEstOrderTime() {
        return estOrderTime;
    }

    public void setEstOrderTime(Integer estOrderTime) {
        this.estOrderTime = estOrderTime;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }
}
