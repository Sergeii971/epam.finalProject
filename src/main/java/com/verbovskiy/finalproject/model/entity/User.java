package com.verbovskiy.finalproject.model.entity;

public class User {
    private String email;
    private String name;
    private String surname;
    private Account account;

    public User(Account account, String email, String name, String surname) {
        this.account = account;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        User user = (User) o;

        if (email == null) {
            if (user.email != null) {
                return false;
            }
        } else {
            if (!email.equals(user.email)) {
                return false;
            }
        }
        if (name == null) {
            if (user.name != null) {
                return false;
            }
        } else {
            if (!name.equals(user.name)) {
                return false;
            }
        }
        if (surname == null) {
            if (user.surname != null) {
                return false;
            }
        } else {
            if (!surname.equals(user.surname)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31  * result + (account == null ? 0 : account.hashCode());
        result = 31  * result + (email == null ? 0 : email.hashCode());
        result = 31  * result + (name == null ? 0 : name.hashCode());
        result = 31  * result + (surname == null ? 0 : surname.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(account.toString());
        builder.append(email);
        builder.append(name);
        builder.append(surname);
        return builder.toString();
    }
}