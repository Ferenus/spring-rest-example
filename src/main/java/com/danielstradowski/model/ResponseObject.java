package com.danielstradowski.model;

import com.google.gson.annotations.SerializedName;

public final class ResponseObject {

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("description")
    private String description;

    @SerializedName("clone_url")
    private String cloneUrl;

    @SerializedName("stargazers_count")
    private int stars;

    @SerializedName("created_at")
    private String createdAt;

    public ResponseObject(String fullName, String description, String cloneUrl, int stars, String createdAt) {
        this.fullName = fullName;
        this.description = description;
        this.cloneUrl = cloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public int getStars() {
        return stars;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
