package io.github.netgeek.asciiwarehouse.model;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String type;
    private String id;
    private Integer size;
    private Integer price;
    private String face;
    private Integer stock;
    private List<String> tags = new ArrayList<>();

    private int primaryBackgroundColor;
    private int secondaryBackgroundColor;
    private int primaryTextColor;
    private int secondaryTextColor;

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size The size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return The price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return The face
     */
    public String getFace() {
        return face;
    }

    /**
     * @param face The face
     */
    public void setFace(String face) {
        this.face = face;
    }

    /**
     * @return The stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock The stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * @return The tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @param tags The tags
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * @return The primary background color
     */
    public int getPrimaryBackgroundColor() {
        return primaryBackgroundColor;
    }

    /**
     * @param primaryBackgroundColor The primary background color
     */
    public void setPrimaryBackgroundColor(int primaryBackgroundColor) {
        this.primaryBackgroundColor = primaryBackgroundColor;
    }

    /**
     * @return The secondary background color
     */
    public int getSecondaryBackgroundColor() {
        return secondaryBackgroundColor;
    }

    /**
     * @param secondaryBackgroundColor The secondary background color
     */
    public void setSecondaryBackgroundColor(int secondaryBackgroundColor) {
        this.secondaryBackgroundColor = secondaryBackgroundColor;
    }

    /**
     * @return The primary text color
     */
    public int getPrimaryTextColor() {
        return primaryTextColor;
    }

    /**
     * @param primaryTextColor The primary text color
     */
    public void setPrimaryTextColor(int primaryTextColor) {
        this.primaryTextColor = primaryTextColor;
    }

    /**
     * @return The secondary text color
     */
    public int getSecondaryTextColor() {
        return secondaryTextColor;
    }

    /**
     * @param secondaryTextColor The secondary text color
     */
    public void setSecondaryTextColor(int secondaryTextColor) {
        this.secondaryTextColor = secondaryTextColor;
    }
}