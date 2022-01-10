package dao;

import entity.Calculation;
import servlets.DBConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CalculationDao {
    private static final CalculationDao INSTANCE = new CalculationDao();
    public static final String INSERT_SQL = "INSERT INTO calculations (user_id, first_side, second_side, result) VALUES (?,?,?,?);";
    public static final String DELETE_SQL = "DELETE FROM calculations WHERE result_id= ?;";
    public static final String DELETE_ALL_USER_CALCULATIONS_SQL = "DELETE from calculations WHERE user_id= ?;";
    public static final String UPDATE_SQL = "UPDATE calculations SET  first_side = ?,second_side = ?,result = ? WHERE result_id=?;";
    public static final String SELECT_SQL = "SELECT * FROM calculations WHERE user_id=?";
    public static final String SELECT_ALL_SQL = "SELECT * FROM calculations;";
    public static final String GET_USER_AND_HIS_CALCULATIONS = "SELECT user_id, result_id, first_side, second_side, result FROM users u INNER JOIN calculations ca ON u.login = ca.user_id;";


    private CalculationDao() {

    }

    ;

    public Calculation insert(Calculation calculation) {
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, calculation.getUserId());
            preparedStatement.setDouble(2, calculation.getFirstSide());
            preparedStatement.setDouble(3, calculation.getSecondSide());
            preparedStatement.setDouble(4, calculation.getResult());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            calculation.setUserId(generatedKeys.getString("resultId"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return calculation;
    }

    public void delete(Integer resultId) {
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, resultId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllUserCalculations(String userId) {
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_USER_CALCULATIONS_SQL)) {
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Calculation calculation) {
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setDouble(1, calculation.getFirstSide());
            preparedStatement.setDouble(2, calculation.getSecondSide());
            preparedStatement.setDouble(3, calculation.getResult());
            preparedStatement.setInt(4, calculation.getResultId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    public Optional<Calculation> select(String userId) {
//        Calculation calculation = new Calculation();
//        try (Connection connection = DBConnectionManager.open();
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)) {
//            preparedStatement.setString(1, userId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                calculation = new Calculation(
//                        resultSet.getInt("resultId"),
//                        resultSet.getString("userId"),
//                        resultSet.getDouble("firstSide"),
//                        resultSet.getDouble("secondSide"),
//                        resultSet.getDouble("result")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return Optional.ofNullable(calculation);
//    }
    public List<Calculation> select(String userId) {
        List<Calculation> calculationList = new ArrayList<>();
        Calculation calculation = null;
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)) {
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                calculation = new Calculation(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5)
                );
                calculationList.add(calculation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return calculationList;
    }

    public List<Calculation> selectAll() {
        List<Calculation> calculationList = new ArrayList<>();
        Calculation calculation = null;
        try (Connection connection = DBConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                calculation = new Calculation(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5)
                );
                calculationList.add(calculation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return calculationList;
    }

    //    public List<Calculation> getAllCalculations () {
//        List<Calculation> resultList = new ArrayList<>();
//        Calculation calculation = null;
////        Users users = null;
////        Calculation calculation = null;
//        try (Connection connection = new DBConnectionManager().getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_AND_HIS_CALCULATIONS)){
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                calculation = new Calculation(
//                        resultSet.getInt("resultId"),
//                        resultSet.getString("userId"),
//                        resultSet.getDouble("firstSide"),
//                        resultSet.getDouble("secondSide"),
//                        resultSet.getDouble("result")
//                );
//                resultList.add(calculation);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return resultList;
//    }
    public String getAll() {
        String s1 = "";
        try (Connection connection = DBConnectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_USER_AND_HIS_CALCULATIONS);
            while (resultSet.next()) {
                String s2;

                s2 = (
                        "Login - " + resultSet.getString(1) + ", " +
                                "Result id - " + resultSet.getString(2) + ", " +
                                "First side - " + resultSet.getString(3) + ", " +
                                "Second side - " + resultSet.getString(4) + ", " +
                                "Result - " + resultSet.getString(5)
                );
                s1 += s2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s1;
    }

    public static CalculationDao getInstance() {
        return INSTANCE;
    }


}
