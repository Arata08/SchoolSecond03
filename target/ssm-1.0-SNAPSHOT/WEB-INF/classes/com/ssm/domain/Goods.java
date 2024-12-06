package com.ssm.domain;

public class Goods {
    private Integer price;
    private Integer id;
    private String type;
    private String name;
    private String goodsPic;
    private String description;
    private Integer uid;
    private Integer bid;
    private String delivery;
    private String username;
    private String businessname;

    @Override
    public String toString() {
        return "Goods{" +
                "price=" + price +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", goodsPic='" + goodsPic + '\'' +
                ", description='" + description + '\'' +
                ", uid=" + uid +
                ", bid=" + bid +
                ", delivery='" + delivery + '\'' +
                ", username='" + username + '\'' +
                ", businessname='" + businessname + '\'' +
                '}';
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }
}
