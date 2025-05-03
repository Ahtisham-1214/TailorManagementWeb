package Backend;
import Database.UserDatabase;

import java.sql.SQLException;

public class User {
    private String userName;
    private String password;
    private String role;
    private final UserDatabase userDatabase;

    public User(String userName, String password) throws SQLException {
        this.setUserName(userName);
        this.setPassword(password);
        userDatabase = new UserDatabase();
    }


    public String authenticateUser() throws Exception {
        this.setRole( userDatabase.authenticateUser(this.getUserName(), this.getPassword()));
        return this.getRole();
    }


    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }else{
            this.userName = userName;
        }
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }else{
            this.password = password;
        }
    }

    public String getRole() {
        return role;
    }

    private void setRole(String role) {
        this.role = role;
    }

    // For Testing
    public static void main(String[] args) throws Exception {

//        User user = new User("ahtisham", "13");
//        System.out.println(user.authenticateUser());


    }
}
