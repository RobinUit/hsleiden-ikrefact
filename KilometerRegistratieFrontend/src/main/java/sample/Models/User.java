package sample.Models;

public class User {

    private static int userID;
    private String email;
    private String username;

    private String password;

    /**
     * Constructor
     */
    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public static int getUserID() {
        return userID;
    }
    public static void setUserID(int ID){
        User.userID = ID;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailadress() {
        return email;
    }


    public String getUsername() {
        return username;
    }

    public void setEmailadress(String emailadress) {
        this.email = emailadress;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}