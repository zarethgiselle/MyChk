package com.example.deathmau.mychk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RegistrarFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{
    RequestQueue rq;
    JsonRequest jrq;
    EditText cajaUser, cajaPwd, cajaName, cajaTel;
    Button btnConsultar, btnRegistro;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);
        View vista = inflater.inflate(R.layout.fragment_registrar, container, false);
        cajaName = (EditText) vista.findViewById(R.id.txtName);
        cajaTel = (EditText) vista.findViewById(R.id.txtNoCell);
        cajaUser = (EditText) vista.findViewById(R.id.txtUser);
        cajaPwd= (EditText) vista.findViewById(R.id.txtPwd);

        btnConsultar = (Button) vista.findViewById(R.id.btnSesion);
        btnRegistro = (Button) vista.findViewById(R.id.btnRegistrar);
        rq = Volley.newRequestQueue(getContext());

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();

            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();

            }
        });


        return vista;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No Se pudó registrar el usuario " + error.toString(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onResponse(JSONObject response) {
        User usuario = new User();
        Toast.makeText(getContext(), "Se ha registrado el usuario" + cajaUser.getText().toString(), Toast.LENGTH_SHORT).show();
        limpiarCajas();
    }

    void limpiarCajas() {
        cajaName.setText("");
        cajaTel.setText("");
        cajaUser.setText("");
        cajaPwd.setText("");
    }


    void iniciarSesion() {

        SesionFragment fr=new SesionFragment();
        //fr.setArguments(bn);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.escenario,fr)
                .addToBackStack(null)
                .commit();
    }

    void registrarUsuario(){
        //192.168.1.66(172.29.243.3
        String url = "https://dethmauws.000webhostapp.com/registrar.php?user="+cajaUser.getText().toString()+"&pwd="+cajaPwd.getText().toString()+"&name=" +cajaName.getText().toString()+"&tel=" +cajaTel.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
    }

}
