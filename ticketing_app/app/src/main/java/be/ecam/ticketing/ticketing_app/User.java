package be.ecam.ticketing.ticketing_app;

/**
 * Created by Juan Barrera on 28-03-17.
 * Time: 10:26.
 */

public class User {
    private String nom, prenom, mail;
    private int ID, age, pswd, droit;
    private Compte compte;

    public User(String nom, String prenom, String mail,
                int ID, int age, int pswd, int droit, Compte compte){
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.ID = ID;
        this.age = age;
        this.pswd = pswd;
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

    public void changePassword(int pswd){
        this.pswd = pswd;
    }
}
