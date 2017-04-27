package be.ecam.ticketing.ticketing_app;

/**
 * Created by Juan Barrera on 27-04-17.
 * Time: 11:44.
 */

public class BasicUser extends User {
    private double balance;
    private static DatabaseManager db;

    public BasicUser(String nom, String prenom, String mail,
                int ID, int age, String password, double balance){
        super(nom, prenom, mail, ID, age, password, 0);
        this.balance = balance;
    }

    public void refill(double add){
        balance += add;
        String[] info = {Integer.toString(this.getID()), this.getPassword()};
        db.Recharge(add, info);
    }

    public void pay(double withdraw){
        balance -= withdraw;
        String[] info = {Integer.toString(this.getID()), this.getPassword()};
        db.Pay(withdraw, info);
    }

    public void update(){

    }
}
