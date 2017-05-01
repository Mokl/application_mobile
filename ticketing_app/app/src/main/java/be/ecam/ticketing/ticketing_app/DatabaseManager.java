package be.ecam.ticketing.ticketing_app;

import android.provider.BaseColumns;

import java.sql.*;

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

    public boolean ConnectEmployee(String[] info)
    {
      try
      {
          conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
          Statement stmt = conn.createStatement();
          String query = "SELECT * FROM employee WHERE id='"+info[0]+"' AND password='"+info[1]+"';";
          ResultSet result = stmt.executeQuery(query);

          if(result != null)
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

    public boolean CreateAdmin(String[] info)
    {
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO employee VALUES('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+");";
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

    public boolean DeleteUser(String id)
    {
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM user WHERE id='"+id+"';";
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

    public boolean DeleteAdmin(String id, Admin admin)
    {
        if(admin.getAccess() <= 7)
        {
            try
            {
                conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
                Statement stmt = conn.createStatement();
                String query = "DELETE FROM employee WHERE id='"+id+"';";
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
        else
        {
            return false;
        }
    }

    public boolean Pay(double montant, String[] info,String[] product)
    {
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            PreparedStatement stmt = null;
            String montant_str = Double.toString(montant);
            String query = "UPDATE user SET solde = 'solde-"+montant_str+"' WHERE id='"+info[0]+"' AND password='"+info[1]+"';";
            String query2 ="UUDATE product SET quantity = quantity - 1 WHERE product ='?' ;";

            conn.setAutoCommit(false);

            int i;
            conn.prepareStatement(query2);
            stmt.execute();
            conn.prepareStatement(query);
            for(i=0;i< product.length-1;i++)
            {
                stmt.setObject(1,product,Types.VARCHAR);
                stmt.execute();
            }
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

    public boolean Recharge(double montant, String[] info)
    {
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            Statement stmt = conn.createStatement();
            String montant_str = Double.toString(montant);
            String query = "UPDATE user SET solde = 'solde+"+montant_str+"' WHERE id='"+info[0]+"' AND password='"+info[1]+"';";
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

    public boolean SetProduct(String[][] info,int nb_product)
    {
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            PreparedStatement stmt = null;
            int i =0 ;
            conn.setAutoCommit(false);
            String query = "INSERT INTO product VALUES(?,?,?,?,?);";

            conn.prepareStatement(query);
            for(i=0;i<nb_product-1;i++)
            {
                stmt.setObject(1,info[i][0],Types.VARCHAR);
                stmt.setObject(2,info[i][1],Types.VARCHAR);
                stmt.setObject(3,info[i][2],Types.FLOAT);
                stmt.setObject(4,info[i][3],Types.INTEGER);
                stmt.setObject(5,info[i][4],Types.INTEGER);
                stmt.execute();
            }
            conn.commit();

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

    public boolean SetPrice(String name, double prix)
    {
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            Statement stmt = conn.createStatement();
            String query = "UPDATE product SET prix ='"+Double.toString(prix)+"' WHERE name='"+name+"';";
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

    public boolean DeleteProduct(String name)
    {
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM product WHERE name='"+name+"';";
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

}
