package be.ecam.ticketing.ticketing_app;

import android.provider.ContactsContract;

/**
 * Created by Juan Barrera on 25-04-17.
 * Time: 09:13.
 */

public class Compte {
    private double montant;

    public Compte(){
        this.montant = 0;
    }

    public Compte(double montant){
        this.montant = montant;
    }

    public void refill(double add){
        montant += add;
    }

    public void pay(){

    }

    public void update(){

    }
}
