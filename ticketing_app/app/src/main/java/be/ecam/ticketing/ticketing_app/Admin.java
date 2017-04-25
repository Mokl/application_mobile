package be.ecam.ticketing.ticketing_app;

/**
 * Created by Juan Barrera on 25-04-17.
 * Time: 08:59.
 */

public class Admin extends User {

    public Admin(String nom, String prenom, String mail,
                int ID, int age, int pswd, Compte compte){
        super(nom, prenom, mail, ID, age, pswd, 1, compte);
    }

    public void setProduct(){

    }

    public void readStat(){

    }

    public void controlUsed(int ID){

    }

    public void addUser(String nom, String prenom, String mail,
                   int ID, int age, int pswd, Compte compte){
        new User(nom, prenom, mail, ID, age, pswd, compte);
    }

    public void addAdmin(String nom, String prenom, String mail,
                    int ID, int age, int pswd, Compte compte){
        new Admin(nom, prenom, mail, ID, age, pswd, compte);
    }



}
