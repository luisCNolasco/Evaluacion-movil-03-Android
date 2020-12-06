package com.example.evaluacion_03_carranza.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

	private int idCurso;
	private String codigo;
	private String nombre;
	private String estado;
	private SistemaEvaluacion sistemaevaluacion;

	
}
