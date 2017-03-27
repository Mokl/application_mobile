package be.ecam.a11209.am_lab1;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tmpt_max = (TextView) findViewById(R.id.tmpt_max);
        TextView tmpt_min = (TextView) findViewById(R.id.tmpt_min);
        TextView eve = (TextView) findViewById(R.id.eve);
        TextView day = (TextView) findViewById(R.id.day);
        TextView morn = (TextView) findViewById(R.id.morn);
        TextView humidity = (TextView) findViewById(R.id.humidity);
        TextView pressure = (TextView) findViewById(R.id.pressure);
        TextView main = (TextView) findViewById(R.id.main);
        TextView wind_speed = (TextView) findViewById(R.id.wind_speed);
        TextView night = (TextView) findViewById(R.id.night);
        TextView title = (TextView) findViewById(R.id.title);


        Intent intent = getIntent();

        Data data = Data.find(intent.getIntExtra(Intent.EXTRA_INDEX,0));
        String str_title = "Prévision pour le "+data.getDate();
        title.setText(str_title);
        tmpt_max.setText(data.getTmptmax());
        tmpt_min.setText(data.getTmptmin());
        eve.setText(data.getEve());
        day.setText(data.getDay());
        morn.setText(data.getMorning());
        humidity.setText(data.getHumidty());
        pressure.setText(data.getPressure());
        main.setText(data.getMain_str());
        wind_speed.setText(data.getSpeed());
        night.setText(data.getNight());
    }

}
