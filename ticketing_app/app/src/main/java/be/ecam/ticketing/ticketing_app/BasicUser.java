package be.ecam.ticketing.ticketing_app;

/**
 * Created by Juan Barrera on 27-04-17.
 * Time: 11:44.
 */

public class BasicUser extends User {
    private double balance;
    private static DatabaseManager db;

    public BasicUser(String nom,String mail,
                String ID, int age, String password, double balance){
        super(nom,mail, ID, age, password,0);
        this.balance = balance;
    }

    public void refill(double add){
        balance += add;
        String[] info = {this.getID(), this.getPassword()};
        db.Recharge(add, info);
    }

    public void pay(double withdraw, String[] product){
        balance -= withdraw;
        String[] info = {this.getID(), this.getPassword()};
        db.Pay(withdraw, info, product);
    }

    public void update(){

    }

    public  double getBalance()
    {
        return this.balance;
    }
}
