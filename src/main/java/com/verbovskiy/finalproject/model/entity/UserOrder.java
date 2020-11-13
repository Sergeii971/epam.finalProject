package com.verbovskiy.finalproject.model.entity;

import java.time.LocalDate;

public class UserOrder extends Entity {
    private long orderId;
    private LocalDate date;
    private final User user;
    private final Car car;
    private boolean inProcessing;

    public UserOrder(long orderId, LocalDate date, User user, Car car, boolean inProcessing) {
        this.orderId = orderId;
        this.date = date;
        this.user = user;
        this.car = car;
        this.inProcessing = inProcessing;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public boolean isInProcessing() {
        return inProcessing;
    }

    public void setInProcessing(boolean inProcessing) {
        this.inProcessing = inProcessing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        UserOrder order = (UserOrder) o;

        if (date == null) {
            if (order.date != null) {
                return false;
            }
        } else {
            if (!date.equals(order.date)) {
                return false;
            }
        }
        if (user == null) {
            if (order.user != null) {
                return false;
            }
        } else {
            if (!user.equals(order.user)) {
                return false;
            }
        }
        if (car == null) {
            if (order.car != null) {
                return false;
            }
        } else {
            if (!car.equals(order.car)) {
                return false;
            }
        }
        return (orderId == order.orderId && inProcessing == order.inProcessing);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result += 31 * result + (date == null ? 0 : date.hashCode());
        result += 31 * result + (user == null ? 0 : user.hashCode());
        result += 31 * result + (car == null ? 0 : car.hashCode());
        result += 31 * result + Boolean.hashCode(inProcessing);
        result += 31 * result + Long.hashCode(orderId);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(orderId);
        builder.append(" ");
        builder.append(user);
        builder.append(" ");
        builder.append(car);
        builder.append(" ");
        builder.append(date);
        builder.append(" ");
        builder.append(inProcessing);
        return builder.toString();
    }
}
