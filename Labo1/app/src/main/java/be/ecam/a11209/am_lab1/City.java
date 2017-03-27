package be.ecam.a11209.am_lab1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static org.json.JSONArray.*;

/**
 * Created by 11209 on 07-02-17.
 */

public class City {
    private String name;
    private String coord;
    private String country;
    private static ArrayList<City> city_list = new ArrayList<>();
    private String tmpt_min;
    private String tmpt_max;

    public City(String name, String coord, String country,String tmpt_min,String tmpt_max)
    {
        this.name=name;
        this.coord=coord;
        this.country=country;
        this.tmpt_min=tmpt_min;
        this.tmpt_max=tmpt_max;
    }

    public static void parse(String data) throws JSONException
    {
        JSONObject jsonCity = new JSONObject(data);
        DecimalFormat df = new DecimalFormat("#####.00");

        JSONObject city = jsonCity.getJSONObject("city");
            String name = city.getString("name");
            String country = city.getString("country");

            JSONObject coord = city.getJSONObject("coord");
            Double lon = Double.parseDouble(coord.getString("lon"));
            Double lat = Double.parseDouble(coord.getString("lat"));
            String s_lon = df.format(lon);
            String s_lat = df.format(lat);
            String coord_str ="Lon: "+ s_lon+" Lat: "+s_lat;

            JSONArray list= jsonCity.getJSONArray("list");
            for (int i = 0; i < list.length(); i++)
            {
                JSONObject tmptList = list.getJSONObject(i);
                JSONObject temp = tmptList.getJSONObject("temp");
                Double dtmpt_min = Double.parseDouble(temp.getString("min"));
                Double dtmpt_max = Double.parseDouble(temp.getString("max"));
                String tmpt_max = df.format(dtmpt_max);
                String tmpt_min = df.format(dtmpt_min);

                City details = new City(name,coord_str,country,tmpt_min,tmpt_max);
                city_list.add(details);
            }
    }

    public static City find(int index)
    {
        return city_list.get(index);
    }

    public String  getName(){return name;}
    public String  getCoord(){return coord;}
    public String  getCountry(){return country;}
    public String  getTmptMin(){return tmpt_min;}
    public String  getTmptMax(){return tmpt_max;}
    public static  ArrayList<City> getList(){return city_list;}
}
