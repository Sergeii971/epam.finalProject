package com.verbovskiy.finalproject.model.entity;

import java.time.LocalDate;

public class Good extends Entity {
    private long goodId;
    private String goodName;
    private double price;
    private String description;
    private String imagePath;
    private LocalDate addedDate;
    private final User seller;
    private boolean isAvailable;

    public Good(long goodId, String goodName, double price, String description, String imagePath,
                LocalDate addedDate, User seller, boolean isAvailable) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
        this.addedDate = addedDate;
        this.seller = seller;
        this.isAvailable = isAvailable;
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }

    public User getSeller() {
        return seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Good good = (Good) o;

        if (goodName == null) {
            if (good.goodName != null) {
                return false;
            }
        } else {
            if (!goodName.equals(good.goodName)) {
                return false;
            }
        }
        if (seller == null) {
            if (good.seller != null) {
                return false;
            }
        } else {
            if (!seller.equals(good.seller)) {
                return false;
            }
        }
        if (description == null) {
            if (good.description != null) {
                return false;
            }
        } else {
            if (!description.equals(good.description)) {
                return false;
            }
        }
        if (addedDate == null) {
            if (good.addedDate != null) {
                return false;
            }
        } else {
            if (!addedDate.equals(good.addedDate)) {
                return false;
            }
        }
        if (imagePath == null) {
            if (good.imagePath != null) {
                return false;
            }
        } else {
            if (!imagePath.equals(good.imagePath)) {
                return false;
            }
        }
        return ((goodId == good.goodId) && (price == good.price) && (isAvailable == good.isAvailable));
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += 31 * result + (goodName == null ? 0 : goodName.hashCode());
        result += 31 * result + (description == null ? 0 : description.hashCode());
        result += 31 * result + (imagePath == null ? 0 : imagePath.hashCode());
        result += 31 * result + (addedDate == null ? 0 : addedDate.hashCode());
        result += 31 * result + (seller == null ? 0 : seller.hashCode());
        result += 31 * result + Long.hashCode(goodId);
        result += 31 * result + Double.hashCode(price);
        result += 31 * result + Boolean.hashCode(isAvailable);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(goodId);
        builder.append(" ");
        builder.append(seller.toString());
        builder.append(" ");
        builder.append(goodName);
        builder.append(" ");
        builder.append(price);
        builder.append(" ");
        builder.append(description);
        builder.append(" ");
        builder.append(addedDate);
        builder.append(" ");
        builder.append(description);
        builder.append(" ");
        builder.append(isAvailable);
        return builder.toString();
    }
}
