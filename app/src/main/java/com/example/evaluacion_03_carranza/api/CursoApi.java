package com.example.evaluacion_03_carranza.api;

import com.example.evaluacion_03_carranza.entity.Curso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CursoApi {

    @GET("curso/porNombre/{param}")
    public Call<List<Curso>> listaPorNombre(@Path("param") String filtro);
}
