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

    /*this method allows the user's account refill*/
    public void refill(double add){
        balance += add;
        db.Recharge(add, getID());
    }
    /*This method allows the user to pay*/
    public void pay(double withdraw, String product){
        balance -= withdraw;
        db.Pay(withdraw, getID(), product);
    }

    public  double getBalance()
    {
        return this.balance;
    }
}
