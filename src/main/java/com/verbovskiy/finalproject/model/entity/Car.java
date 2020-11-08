package com.verbovskiy.finalproject.model.entity;

import java.time.LocalDate;

public class Car extends Entity {
    private long carId;
    private CarBrand brand;
    private String model;
    private int manufactureYear;
    private double price;
    private String description;
    private String imageName;
    private LocalDate addedDate;
    private boolean isAvailable;
    private BoxType boxType;
    private Engine engineType;
    private CarColor color;

    public Car(long carId, CarBrand brand, String model, int manufactureYear, double price, String description,
               String imageName, LocalDate addedDate, boolean isAvailable, CarColor color, BoxType boxType, Engine engineType) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.price = price;
        this.description = description;
        this.imageName = imageName;
        this.addedDate = addedDate;
        this.isAvailable = isAvailable;
        this.color = color;
        this.boxType = boxType;
        this.engineType = engineType;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public boolean getIsAvailable() {
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public CarColor getColor() {
        return color;
    }

    public void setColor(CarColor color) {
        this.color = color;
    }

    public BoxType getBoxType() {
        return boxType;
    }

    public void setBoxType(BoxType boxType) {
        this.boxType = boxType;
    }

    public Engine getEngineType() {
        return engineType;
    }

    public void setEngineType(Engine engineType) {
        this.engineType = engineType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Car car = (Car) o;

        if (brand == null) {
            if (car.brand != null) {
                return false;
            }
        } else {
            if (!brand.equals(car.brand)) {
                return false;
            }
        }
        if (model == null) {
            if (car.model != null) {
                return false;
            }
        } else {
            if (!model.equals(car.model)) {
                return false;
            }
        }
        if (color == null) {
            if (car.color != null) {
                return false;
            }
        } else {
            if (!color.equals(car.color)) {
                return false;
            }
        }
        if (engineType == null) {
            if (car.engineType != null) {
                return false;
            }
        } else {
            if (!engineType.equals(car.engineType)) {
                return false;
            }
        }
        if (boxType == null) {
            if (car.boxType != null) {
                return false;
            }
        } else {
            if (!boxType.equals(car.boxType)) {
                return false;
            }
        }
        if (description == null) {
            if (car.description != null) {
                return false;
            }
        } else {
            if (!description.equals(car.description)) {
                return false;
            }
        }
        if (addedDate == null) {
            if (car.addedDate != null) {
                return false;
            }
        } else {
            if (!addedDate.equals(car.addedDate)) {
                return false;
            }
        }
        if (imageName == null) {
            if (car.imageName != null) {
                return false;
            }
        } else {
            if (!imageName.equals(car.imageName)) {
                return false;
            }
        }
        return ((carId == car.carId) && (price == car.price) && (isAvailable == car.isAvailable)
        && (manufactureYear == car.manufactureYear));
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += 31 * result + (brand == null ? 0 : brand.hashCode());
        result += 31 * result + (description == null ? 0 : description.hashCode());
        result += 31 * result + (imageName == null ? 0 : imageName.hashCode());
        result += 31 * result + (addedDate == null ? 0 : addedDate.hashCode());
        result += 31 * result + (color == null ? 0 : color.hashCode());
        result += 31 * result + (boxType == null ? 0 : boxType.hashCode());
        result += 31 * result + (engineType == null ? 0 : engineType.hashCode());
        result += 31 * result + (model == null ? 0 : model.hashCode());
        result += 31 * result + manufactureYear;
        result += 31 * result + Long.hashCode(carId);
        result += 31 * result + Double.hashCode(price);
        result += 31 * result + Boolean.hashCode(isAvailable);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(carId);
        builder.append(" ");
        builder.append(model);
        builder.append(" ");
        builder.append(manufactureYear);
        builder.append(" ");
        builder.append(brand);
        builder.append(" ");
        builder.append(color);
        builder.append(" ");
        builder.append(boxType);
        builder.append(" ");
        builder.append(engineType);
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
