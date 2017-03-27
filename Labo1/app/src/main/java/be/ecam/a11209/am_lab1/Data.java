package be.ecam.a11209.am_lab1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by hp on 14/02/2017.
 */

public class Data
{
    private String tmpt_max;
    private String tmpt_min;
    private String tmpt_night;
    private String tmpt_eve;
    private String tmpt_morn;
    private String humidity;
    private String pressure;
    private String main_str;
    private String wind_speed;
    private String day;
    private String date;
    private static ArrayList<Data> data_list= new ArrayList<>();

    public Data (String tmpt_max, String tmpt_min, String tmpt_eve, String tmpt_night
                , String tmpt_morn, String humidity, String pressure, String main
                ,String wind_speed,String day,String date)
    {
        this.tmpt_max = tmpt_max;
        this.tmpt_min = tmpt_min;
        this.tmpt_eve = tmpt_eve;
        this.tmpt_night = tmpt_night;
        this.tmpt_morn = tmpt_morn;
        this.humidity = humidity;
        this.pressure = pressure;
        this.main_str = main;
        this.wind_speed = wind_speed;
        this.day = day;
        this.date = date;
    }

    public static void parse(String data) throws JSONException
    {
        data_list= new ArrayList<>();
        String[] days_of_week = new String[7];
        days_of_week[0] = "Dimanche";
        days_of_week[1] = "Lundi";
        days_of_week[2] = "Mardi";
        days_of_week[3] = "Mercredi";
        days_of_week[4] = "Jeudi";
        days_of_week[5] = "Vendredi";
        days_of_week[6] = "Samedi";

        JSONObject jsonCity = new JSONObject(data);
        DecimalFormat df = new DecimalFormat("#####.00");


        JSONArray list= jsonCity.getJSONArray("list");
        int j =0;
        for (int i = 0; i < list.length(); i++)
        {
            JSONObject tmptList = list.getJSONObject(i);
            JSONObject temp = tmptList.getJSONObject("temp");
            Double dtmpt_min = temp.getDouble("min");
            Double dtmpt_max = temp.getDouble("max");
            Double dday = temp.getDouble("day");
            Double dnight = temp.getDouble("night");
            Double deve = temp.getDouble("eve");
            Double dmorn = temp.getDouble("morn");

            Double dpressure = tmptList.getDouble("pressure");
            Double dhumidity = tmptList.getDouble("humidity");

            JSONArray arr_weather = tmptList.getJSONArray("weather");
            JSONObject weather = arr_weather.getJSONObject(0);

            String str_main = "Etat général : "+weather.getString("main");
            Double dspeed = tmptList.getDouble("speed");

            String tmpt_max = "Maxima: "+df.format(dtmpt_max)+" °C";
            String tmpt_min = "Minima: "+df.format(dtmpt_min)+" °C";
            String day = "En journée : " + df.format(dday)+" °C";
            String night = "La nuit: "+df.format(dnight)+" °C";
            String eve = "En soirée: "+df.format(deve)+" °C";
            String morn = "En matinée: "+df.format(dmorn)+" °C";
            String pressure = "Pression: "+df.format(dpressure)+" bar";
            String humidity = "Humidité: "+df.format(dhumidity)+"%";
            String speed = "Vitesse du vent: "+df.format(dspeed)+" km/h";
            String date="no data";

            if(j< 7)
            {
                date = days_of_week[j];
                if( j >= 6)
                {
                    j=0;
                }
                else
                {
                    j++;
                }
            }
            else
            {
                date ="error day";
            }



            Data data_object = new Data(tmpt_max,tmpt_min,eve,night,morn,humidity
                                ,pressure,str_main,speed,day,date);
            data_list.add(data_object);
        }
    }

    public String getTmptmax(){return tmpt_max;}
    public String getTmptmin(){return tmpt_min;}
    public String getDay(){return day;}
    public String getNight(){return tmpt_night;}
    public String getEve(){return tmpt_eve;}
    public String getMorning(){return tmpt_morn;}
    public String getPressure(){return pressure;}
    public String getHumidty(){return humidity;}
    public String getSpeed(){return wind_speed;}
    public String getMain_str(){return main_str;}
    public String getDate(){return date; }

    public static ArrayList<Data> DataList()
    {
        return data_list;
    }

    public static int ListSize()
    {
        return data_list.size();
    }

    public static Data find(int index)
    {
        return data_list.get(index);
    }



}
