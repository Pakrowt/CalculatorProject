package dao;

import entity.Users;
import servlets.DBConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDao {
    private static final UsersDao INSTANCE = new UsersDao();
    public static final String INSERT_SQL = "INSERT INTO users (login, password, role) VALUES (?,?,?);";
    public static final String DELETE_SQL = "DELETE FROM users WHERE login = ?;";
    public static final String UPDATE_SQL = "UPDATE users SET  password = ? WHERE login =?;";
    public static final String SELECT_SQL = "SELECT * FROM users WHERE login=?";
    public static final String SELECT_ALL_SQL = "SELECT * FROM users;";
    public static final String SELECT_BY_LOGIN_AND_PASSWORD_SQL = "SELECT * FROM users WHERE login=? AND password = ?;";
    public static final String SELECT_ROLE = "SELECT * FROM users WHERE login=?;";

    private UsersDao() {
    }

    public Optional<Users> checkLoginAndPassword(String login, String password) {
        Users users = null;
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD_SQL)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                users = new Users(
                        resultSet.getString("login"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(users);
    }

    public Users insert(Users user) {
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void delete(String login) {
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Users user) {
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Users> select(String login) {
        Users users = new Users();
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                users = new Users(
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(users);
    }

    public List<Users> selectAll() {
        List<Users> usersList = new ArrayList<>();
        Users users = null;
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users = new Users(
                        resultSet.getString("login"),
                        resultSet.getString("password"));
                usersList.add(users);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public String checkRole(String login) {
//        Users users = null;
        String role = "";
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("role");
//                users = new Users(
//                        resultSet.getString("login"),
//                        resultSet.getString("password"),
//                        resultSet.getString("role")
//                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public static UsersDao getInstance() {
        return INSTANCE;
    }
}
