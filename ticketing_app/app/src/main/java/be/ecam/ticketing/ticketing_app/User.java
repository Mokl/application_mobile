package be.ecam.ticketing.ticketing_app;

/**
 * Created by Juan Barrera on 28-03-17.
 * Time: 10:26.
 */

public class User {
    private String nom,mail,password,ID;
    private int age, access;

    public User(String nom,String mail,
                String ID, int age, String password, int access){
        this.nom = nom;
       // this.prenom = prenom;
        this.mail = mail;
        this.ID = ID;
        this.age = age;
        this.password = password;
        this.access = access;
    }

    public String getForeName(){
        return nom;
    }

    public String getMail(){
        return mail;
    }

    public String getID(){
        return ID;
    }

    public int getAge(){
        return age;
    }

    protected String getPassword() { return password; }

    public int getAccess() { return this.access; }
}
