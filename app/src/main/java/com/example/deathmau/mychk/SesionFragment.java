package com.example.deathmau.mychk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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


public class SesionFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{
    RequestQueue rq;
    JsonRequest jrq;
    EditText cajaUser, cajaPwd;
    Button btnConsultar, btnRegistro;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);
        View vista = inflater.inflate(R.layout.fragment_sesion, container, false);
        cajaUser = (EditText) vista.findViewById(R.id.txtUser);
        cajaPwd = (EditText) vista.findViewById(R.id.txtPwd);
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
        Toast.makeText(getContext(), "No se encontro el usuario " + error.toString(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onResponse(JSONObject response) {
        User usuario = new User();
        Toast.makeText(getContext(), "Se ha encontrado el usuario" + cajaUser.getText().toString(), Toast.LENGTH_SHORT).show();
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setUser(jsonObject.optString("user"));
            usuario.setPwd(jsonObject.optString("pwd"));
            usuario.setName(jsonObject.optString("name"));
            usuario.setTel(jsonObject.optString("tel"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intencion = new Intent(getContext(), Main2Activity.class);
        startActivity(intencion);


    }
    void registrarUsuario(){
        RegistrarFragment fr=new RegistrarFragment();
        //fr.setArguments(fr);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.escenario,fr)
                .addToBackStack(null)
                .commit();

    }


    private void iniciarSesion(){
        String url = "https://dethmauws.000webhostapp.com/sesion.php?user="+cajaUser.getText().toString()+"&pwd="+cajaPwd.getText().toString();
        //String url = "http://192.168.0.10/login/sesion.php?user="+cajaUser.getText().toString()+"&pwd="+cajaPwd.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);

    }
}
