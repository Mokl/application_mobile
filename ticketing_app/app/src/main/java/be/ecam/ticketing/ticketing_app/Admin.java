package be.ecam.ticketing.ticketing_app;

/**
 * Created by Juan Barrera on 25-04-17.
 * Time: 08:59.
 */

public class Admin extends User {

    public Admin(String nom, String prenom, String mail,
                String ID, int age, String password){
        super(nom, mail, ID, age, password, 1);
    }

    public void setProduct(){

    }

    public void readStat(){

    }

    public void controlUser(int ID){

    }

    public void addUser(String nom, String prenom, String mail,
                   String ID, int age, String password){
        new User(nom,mail, ID, age, password, 0);
    }

    public void addAdmin(String nom, String prenom, String mail,
                    String ID, int age, String password){
      //  new Admin(nom,mail,ID,age,password);
    }
}
