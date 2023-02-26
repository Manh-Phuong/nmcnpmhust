package model;

/**
 *
 * @author Hai
 */
public class UserModel {
    private int ID;
    private String userName;
    private String passwd;
    private String role;
    
    public UserModel(String role, String userName) {
        this.role = role;
        this.userName = userName;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
