package com.example.deathmau.mychk;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private ListView lvItems;
    private Adaptador adaptador;
    private ArrayList<Entidad> arrayEntidad = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lvItems = (ListView) findViewById(R.id.lvItems);
        llenarItems();



        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0){
                    Intent intencion = new Intent(getApplicationContext(), Main3Activity.class);
                    startActivity(intencion);
                }

                if (position == 1){
                    Intent intencion = new Intent(getApplicationContext(), Main3Activity.class);
                    startActivity(intencion);
                }

                if (position == 2){
                    Intent intencion = new Intent(getApplicationContext(), Main3Activity.class);
                    startActivity(intencion);
                }

                if (position == 3){
                    Toast.makeText(getApplicationContext(),"No disponible. ", Toast.LENGTH_LONG).show();

                }

                if (position == 4){
                    Toast.makeText(getApplicationContext(),"No disponible. ", Toast.LENGTH_LONG).show();

                }

            }
        });
    }




    private void llenarItems(){
        arrayEntidad.add(new Entidad(R.drawable.walmart, "WALLMART", "Diagonal Ignacio Zaragoza, San Manuel"));
        arrayEntidad.add(new Entidad(R.drawable.aurrera, "AURRERA", "Valsequillo, Cdad. Universitaria"));
        arrayEntidad.add(new Entidad(R.drawable.comercial, "LA COMERCIAL MEXICANA", "Sn. Pedro, Blvd. Norte Heroes 5 de Mayo"));
        arrayEntidad.add(new Entidad(R.drawable.chedraui, "CHEDRAUI", "Cruz del Sur, Blvd. Forjadores de Puebla"));
        arrayEntidad.add(new Entidad(R.drawable.soriana, "SORIANA", "Av. Francisco I.Madero Col.Las Redes "));

        adaptador = new Adaptador(this, arrayEntidad);
        lvItems.setAdapter(adaptador);
    }








}