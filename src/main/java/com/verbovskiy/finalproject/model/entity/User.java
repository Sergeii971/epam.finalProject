package com.verbovskiy.finalproject.model.entity;

public class User {
    private String login;
    private String password;
    private boolean isAdmin;

    public User(String login, String password, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
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

        if (login == null) {
            if (user.login != null) {
                return false;
            }
        } else {
            if (!login.equals(user.login)) {
                return false;
            }
        }
        if (password == null) {
            if (user.password != null) {
                return false;
            }
        } else {
            if (!password.equals(user.password)) {
                return false;
            }
        }
        return (isAdmin == user.isAdmin);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 31 * login.hashCode();
        result += 31 * password.hashCode();
        result += 31 * Boolean.hashCode(isAdmin);

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", login, password);
    }
}
