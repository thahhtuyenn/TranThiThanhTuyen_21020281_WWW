package vn.edu.iuh.fit.tranthithanhtuyen_session01.connect;

import vn.edu.iuh.fit.tranthithanhtuyen_session01.POJO.User;

import java.sql.*;

public class UserDAO {
    private static final String url = "jdbc:mysql://localhost:3306/login";
    private static final String username = "root";
    private static final String password = "sapassword";

    public  static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public User findByUserName(User user) {
        try (Connection connect = getConnection()){
            PreparedStatement state = connect.prepareStatement("SELECT * FROM users WHERE user_name = ? AND pass_word = ?");
            state.setString(1, user.getUsername());
            state.setString(2, user.getPassword());
            ResultSet rs = state.executeQuery();
            User userLogin = null;
            while (rs.next()){
                String us, pw;
                us = rs.getString("user_name");
                pw = rs.getString("pass_word");
                userLogin = new User(us, pw);
            }
            return userLogin;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
