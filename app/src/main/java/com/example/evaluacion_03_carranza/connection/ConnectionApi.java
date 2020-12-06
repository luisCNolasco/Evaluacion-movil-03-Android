package com.example.evaluacion_03_carranza.connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionApi {

    private  static Retrofit retrofit = null;
    private static final String  RUTA_API = "https://rest-examen-movil-03.herokuapp.com/api/rest/";


    public static Retrofit getConnection(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(RUTA_API).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
