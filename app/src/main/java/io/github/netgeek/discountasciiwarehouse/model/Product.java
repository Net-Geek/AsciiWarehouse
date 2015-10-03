package io.github.netgeek.discountasciiwarehouse.model;

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

}