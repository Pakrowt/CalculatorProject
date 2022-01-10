package entity;

import dao.UsersDao;

import java.util.Objects;

public class Users {

    private String login;
    private String password;
    private String role;

    public Users() {
    }

    public Users(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(login, users.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "Пользователь(login - " + login + " ,password - " + password + " )";
    }


}
