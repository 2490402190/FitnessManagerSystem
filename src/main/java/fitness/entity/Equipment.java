package fitness.entity;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Equipment {
    private String id;
    private String name;
    private String place;
    private String type;
    private String price;
    private ImageView picture;
    private String status;
    private String renderId;
    private String remark;

    public Equipment() {
    }

    public void initImageSize() {
        picture.setFitWidth(50);
        picture.setFitHeight(50);
    }

    public Equipment(String id, String name, String place, String type, String price, ImageView picture, String status, String renderId, String remark) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.type = type;
        this.price = price;
        this.picture = picture;
        this.status = status;
        this.renderId = renderId;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", picture='" + picture + '\'' +
                ", status='" + status + '\'' +
                ", renderId='" + renderId + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ImageView getPicture() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRenderId() {
        return renderId;
    }

    public void setRenderId(String renderId) {
        this.renderId = renderId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
