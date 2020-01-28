package sample.Models;

public class User {

    private static int userID;
    private String email;
    private String username;
    private String password;

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

    public String getEmailadress() {
        return email;
    }

    public String getUsername() {
        return username;
    }

}