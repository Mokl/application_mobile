package be.ecam.ticketing.ticketing_app;

/**
 * Created by Juan Barrera on 28-03-17.
 * Time: 10:26.
 */

public class User {
    private String nom, prenom, mail, password;
    private int ID, age, droit;
    private Compte compte;

    // Constructor for a basic user account (no-admin)
    public User(String nom, String prenom, String mail,
                int ID, int age, String password, Compte compte){
        new User(nom, prenom, mail, ID, age, password, 0, compte);
    }

    public User(String nom, String prenom, String mail,
                int ID, int age, String password, int droit, Compte compte){
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.ID = ID;
        this.age = age;
        this.password = password;
        this.droit = droit;
        this.compte = compte;
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

    public void changePassword(String pswd){
        this.password = password;
    }

    public int getRights() { return this.droit; }
}
