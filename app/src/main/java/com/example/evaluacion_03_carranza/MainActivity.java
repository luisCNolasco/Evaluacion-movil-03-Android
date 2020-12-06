package com.example.evaluacion_03_carranza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evaluacion_03_carranza.api.CursoApi;
import com.example.evaluacion_03_carranza.connection.ConnectionApi;
import com.example.evaluacion_03_carranza.entity.Curso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    Button btnFiltrar;
    ListView lstData;

    CursoApi api;

    ArrayAdapter<String> adapter;
    ArrayList<String> data = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        btnFiltrar = (Button) findViewById(R.id.btnFiltrar);
        lstData = (ListView) findViewById(R.id.lstData);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        lstData.setAdapter(adapter);

        api = ConnectionApi.getConnection().create(CursoApi.class);

        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filtro = txtNombre.getText().toString().trim();

                if (filtro.equals("")){
                    mensaje("Escriba el curso a consultar");
                }else{
                    lista(filtro);
                }
            }
        });
    }

    public void lista(String filtro){

        Call<List<Curso>> call = api.listaPorNombre(filtro);
        call.enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {

                if(response.isSuccessful()){
                    List<Curso> salida =  response.body();
                    String item = null;

                    data.clear();

                    for(Curso x : salida){
                        item = "Codigo: "+x.getCodigo() + " - Nombre: " + x.getNombre();
                        data.add(item);
                    }
                    adapter.notifyDataSetChanged();

                }else{
                    mensaje("ERROR en onResponse");
                }
            }
            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {
                mensaje("-> onFailure");
                mensaje("ERROR" +   t.getMessage());

            }
        });
    }

    void mensaje(String msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }
}