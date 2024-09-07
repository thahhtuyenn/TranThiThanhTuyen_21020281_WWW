package vn.edu.iuh.fit.tranthithanhtuyen_session01.beans;

import vn.edu.iuh.fit.tranthithanhtuyen_session01.POJO.User;
import vn.edu.iuh.fit.tranthithanhtuyen_session01.connect.UserDAO;

public class LoginBean {
    private UserDAO userDAO;

    public LoginBean() {
        userDAO = new UserDAO();
    }

    public boolean login(User user) {
        User userExists = userDAO.findByUserName(user);
        return userExists != null;
    }
}
