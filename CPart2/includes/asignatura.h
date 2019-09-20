/*
 * asignatura.h
 *
 *  Created on: 14 de may. de 2016
 *      Author: Guillermo
 */
#ifndef ASIGNATURA_H_
#define ASIGNATURA_H_
#include "cadena.h"
#include "logico.h"
#include <stdio.h>

#define MAXASIG 100
#define CODIGOLEN 8

typedef char Codigo[CODIGOLEN];
typedef struct{
	Cadena nombre;
	Codigo codigo;
	double creditos;
	int curso;
	Cadena departamento;
} Asignatura;

typedef Asignatura *PAsignatura;
typedef Asignatura ArrayAsignaturas[MAXASIG];

int inicializaAsignatura(PAsignatura res, const Cadena nombre, const Codigo codigo, double creditos, int curso, const Cadena departamento);
void muestraAsignatura(Asignatura a);
void muestraAsignaturas(const ArrayAsignaturas res, int nAsig);
int leeAsignaturasFichero(const Cadena nombreFichero, ArrayAsignaturas res);


/* Funciones auxiliares */
Logico checkCodigo(const Codigo);
Logico checkCurso(int curso);
Logico checkCreditos(double creditos);
void leeAsignaturaFichero(FILE *f, PAsignatura a);

#endif /* ASIGNATURA_H_ */
