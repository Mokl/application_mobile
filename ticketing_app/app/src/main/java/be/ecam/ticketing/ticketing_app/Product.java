package be.ecam.ticketing.ticketing_app;

import java.util.ArrayList;

/**
 * Created by hp on 01/05/2017.
 */

public class Product {
    private String nom;
    private String description;
    private double prix;
    private static ArrayList<Product> product_list;

    public Product(String nom_in,String description_in,double prix_in)
    {
        nom=nom_in;
        description=description_in;
        prix=prix_in;
    }


    public String Nom()
    {
        return nom;
    }
    public String Description()
    {
        return description;
    }
    public double Prix()
    {
        return prix;
    }

    public static void setList(ArrayList<Product> data)
    {
        product_list = new ArrayList<>();
        product_list.addAll(data);
    }

    public  static  Product find(int index)
    {
        return product_list.get(index);
    }
}
