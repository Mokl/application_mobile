package be.ecam.ticketing.ticketing_app;

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
        conn_string[0] = "jdbc:mysql://localhost/"+dbInfo[0];
        conn_string[1] = dbInfo[2];
        conn_string[2] = dbInfo[3];
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
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM user WHERE id='"+info[0]+"' AND password='"+info[1]+"';";
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

    public boolean CreateUser(String[] info)
    {
        try
        {
            conn = DriverManager.getConnection(conn_string[0],conn_string[1],conn_string[2]);
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO user VALUES('"+info[0]+"','"+info[1]+"','"+info[2]+"','"+info[3]+"','"+info[4]+"','"+info[5]+"','"+info[6]+"');";
            ResultSet result = stmt.executeQuery(query);

            result.close();
            stmt.close();
            conn.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
