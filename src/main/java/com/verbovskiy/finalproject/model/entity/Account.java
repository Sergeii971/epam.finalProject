package com.verbovskiy.finalproject.model.entity;

public class Account extends Entity{
    private String login;
    private boolean isBlocked;
    private boolean isAdmin;
    private boolean isActive;

    public Account(String login, boolean isAdmin, boolean isBlocked) {
        this.login = login;
        this.isAdmin = isAdmin;
        this.isBlocked = isBlocked;
    }

    public Account(String login, boolean isBlocked, boolean isAdmin, boolean isActive) {
        this.login = login;
        this.isBlocked = isBlocked;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public String getLogin() {
        return login;
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
        Account account = (Account) o;

        if (login == null) {
            if (account.login != null) {
                return false;
            }
        } else {
            if (!login.equals(account.login)) {
                return false;
            }
        }
        return ((isAdmin == account.isAdmin) && (isBlocked == account.isBlocked) && (isActive == account.isActive));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 31 * login.hashCode();
        result += 31 * Boolean.hashCode(isAdmin);
        result += 31 * Boolean.hashCode(isBlocked);
        result += 31 * Boolean.hashCode(isActive);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(login);
        builder.append(" ");
        builder.append(isBlocked);
        builder.append(" ");
        builder.append(isAdmin);
        builder.append(" ");
        builder.append(isActive);
        return builder.toString();
    }
}
