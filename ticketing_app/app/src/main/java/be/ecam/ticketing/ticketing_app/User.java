package be.ecam.ticketing.ticketing_app;

/**
 * Created by Juan Barrera on 28-03-17.
 * Time: 10:26.
 */

public class User {
    private String nom, prenom, mail, password;
    private int ID, age, access;

    public User(String nom, String prenom, String mail,
                int ID, int age, String password, int access){
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.ID = ID;
        this.age = age;
        this.password = password;
        this.access = access;
    }

    public String getFirstName(){
        return prenom;
    }

    public String getForeName(){
        return nom;
    }

    public String getMail(){
        return mail;
    }

    public int getID(){
        return ID;
    }

    public int getAge(){
        return age;
    }

    protected String getPassword() { return password; }

    public void changePassword(String pswd){
        this.password = password;
    }

    public int getAccess() { return this.access; }
}
