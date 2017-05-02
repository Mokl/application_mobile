package be.ecam.ticketing.ticketing_app;

import android.provider.BaseColumns;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by hp on 09/04/2017.
 */

public class DatabaseManager
{
    private Connection conn;
    private String[] conn_string;

    public DatabaseManager(String dbInfo[])
    {
        conn_string = new String[3];
        conn_string[0] = "jdbc:mysql://192.168.137.222:3306/"+dbInfo[0];//DB name
        conn_string[1] = dbInfo[1];//username
        conn_string[2] = dbInfo[2];//password
    }


    /*This method allows the verification of the user information*/
    public boolean ConnectUser(String[] info)
    {
        boolean state =false;
        try
        {
            //conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            conn = DriverManager.getConnection("jdbc:mysql://192.168.137.222:3306/ticketing_app","root","root");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM user WHERE id='"+info[0]+"' AND password='"+info[1]+"';";
            ResultSet result = stmt.executeQuery(query);

            while(result.next())
            {
                if(result.getString("id")==info[0] && result.getString("password") == info[1] )
                {
                    state = true;
                    break;
                }
            }

            if(state == true)
            {
                result.close();
                stmt.close();
                conn.close();
                return true;
            }
            else
            {
                stmt.close();
                conn.close();
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /*This method Allows the creation of a new user*/
    public boolean CreateUser(String[] info)
    {
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO user VALUES('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','" +
                    info[4]+"','"+info[5]+"','"+info[6]+"');";
            //ResultSet result = stmt.executeQuery(query);

            //result.close();
            stmt.close();
            conn.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    /*This method process the purchase transaction*/
    public boolean Pay(double montant, String info,String product)
    {
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            PreparedStatement stmt = null;
            String montant_str = Double.toString(montant);
            String query = "UPDATE user SET solde = 'solde-"+montant_str+"' WHERE id='"+info+"';";
            String query2 ="UUDATE product SET quantity = quantity - 1 WHERE name='?' ;";

            conn.setAutoCommit(false);

            int i;
            conn.prepareStatement(query2);
            stmt.execute();
            conn.prepareStatement(query);

            stmt.setObject(1,product,Types.VARCHAR);
            stmt.execute();

            conn.commit();

            //stmt.executeQuery(query);

            stmt.close();
            conn.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /*this method process the refill transaction*/
    public boolean Recharge(double montant, String info)
    {
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            Statement stmt = conn.createStatement();
            String montant_str = Double.toString(montant);
            String query = "UPDATE user SET solde = 'solde+"+montant_str+"' WHERE id='"+info+"';";
            stmt.executeQuery(query);

            stmt.close();
            conn.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /*This method retrieve the user information*/
    public String[] getInfo(String id)
    {
        String[] data= new String[7];
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM user WHERE id='"+id+"';";
            ResultSet result = stmt.executeQuery(query);

            if(result != null)
            {
                while(result.next())
                {
                    data[0]=result.getString("nom");
                    data[1]=result.getString("adresse");
                    data[2]=result.getString("age");
                    data[3]=result.getString("id");
                    data[4]=result.getString("password");
                    data[5]=result.getString("email");
                    data[6]=result.getString("solde");
                }



                result.close();
                stmt.close();
                conn.close();
                return data;
            }
            else
            {
                stmt.close();
                conn.close();
                return null;
            }
        }
        catch (Exception e)
        {
            return null;
        }
    }
    /*This method retrieve all the product from the database*/
    public ArrayList<Product> getProduct()
    {
        ArrayList<Product> product = new ArrayList<>();
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            Statement stmt = conn.createStatement();
            String query = "SELECT nom,description,prix FROM product;";
            ResultSet result = stmt.executeQuery(query);

            if(result != null)
            {
                while(result.next())
                {
                    Product in_product = new Product(result.getString("nom"),result.getString("description"),Double.parseDouble(result.getString("prix")));
                    product.add(in_product);
                }
                result.close();
                stmt.close();
                conn.close();
                return product;
            }
            else
            {
                stmt.close();
                conn.close();
                return null;
            }
        }
        catch (Exception e)
        {
            return null;
        }
    }
}


