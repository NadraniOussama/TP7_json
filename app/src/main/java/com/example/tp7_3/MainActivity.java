package com.example.tp7_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> elt = getPays("pays.json");
        ArrayList<String> elt1 = getPays("filieres.json");
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.ligne,R.id.txt,elt);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.ligne,R.id.txt,elt1);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
    }

    private ArrayList<String> getPays(String fileName) {
        JSONArray jsonArray = null;
        ArrayList<String>cList = new ArrayList<String>();

        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {

                for (int i = 0; i<jsonArray.length(); i++)
                    cList.add(jsonArray.getJSONObject(i).getString("cnom"));

            }

        }catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException je){
            je.printStackTrace();
        }
        return cList;
    }
}