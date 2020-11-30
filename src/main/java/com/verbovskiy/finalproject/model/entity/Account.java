package com.verbovskiy.finalproject.model.entity;

/**
 * The type Account.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class Account implements Entity {
    private String login;
    private boolean isBlocked;
    private boolean isAdmin;
    private boolean isConfirmed;

    public Account(String login, boolean isAdmin, boolean isBlocked, boolean isConfirmed) {
        this.login = login;
        this.isAdmin = isAdmin;
        this.isBlocked = isBlocked;
        this.isConfirmed = isConfirmed;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Sets blocked.
     *
     * @param blocked the block status
     */
    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    /**
     * Sets admin.
     *
     * @param admin the admin
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * is blocked.
     *
     * @return is blocked
     */
    public boolean isBlocked() {
        return isBlocked;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * is admin.
     *
     * @return is admin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * is confirmed.
     *
     * @return is confirmed
     */
    public boolean isConfirmed() {
        return isConfirmed;
    }

    /**
     * Sets confirmed.
     *
     * @param confirmed the confirmed
     */
    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
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
        return ((isAdmin == account.isAdmin) && (isBlocked == account.isBlocked) && (isConfirmed == account.isConfirmed));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 31 * login.hashCode();
        result += 31 * Boolean.hashCode(isAdmin);
        result += 31 * Boolean.hashCode(isBlocked);
        result += 31 * Boolean.hashCode(isConfirmed);
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
        builder.append(isConfirmed);
        return builder.toString();
    }
}
