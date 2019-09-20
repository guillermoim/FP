/*
 * asignatura.c
 *
 *  Created on: 14 de may. de 2016
 *      Author: Guillermo
 */

#include "asignatura.h"
#include <string.h>
#define MAXCAR 250

int inicializaAsignatura(PAsignatura res, const Cadena nombre, const Codigo
codigo, double creditos, int curso, const Cadena departamento) {
	int status = 0;
	if (!checkCodigo(codigo)) {
		fprintf(stderr, "Error en código de asignatura: %s\n", codigo);
		status = -1;
	} else if (!checkCreditos(creditos)) {
		fprintf(stderr, "Error en créditos de asignatura: %lf\n", creditos);
		status = -1;
	}else if (!checkCurso(curso)) {
		fprintf(stderr, "Error en curso de asignatura: %d\n", curso);
		status = -1;
	} else {
		strcpy(res->nombre, nombre);
		strcpy(res->codigo, codigo);
		res->curso = curso;
		res->creditos = creditos;
		strcpy(res->departamento, departamento);
	}
	return status;
}

void muestraAsignatura(Asignatura a) {
	printf("Nombre: %s\n", a.nombre);
	printf("Código: %s\n", a.codigo);
	printf("Créditos: %lf\n", a.creditos);
	printf("Curso: %d\n", a.curso);
	printf("Departamento: %s\n", a.departamento);
}

void muestraAsignaturas(const ArrayAsignaturas res, int nAsig) {
	int i;
	for (i = 0; i < nAsig; i++) {
		printf("Asignatura número %d.\n", i + 1);
		muestraAsignatura(res[i]);
		printf("============================\n");
	}
}

Logico checkCodigo(const Codigo codigo) {
	Logico res = FALSO;
	if(strlen(codigo) == CODIGOLEN-1){
		res = CIERTO;
	}
	return res;
}

Logico checkCurso(int curso) {
	Logico res = FALSO;
	if(curso >= 1 && curso <= 4){
		res = CIERTO;
	}
	return res;
}

Logico checkCreditos(double creditos) {
	Logico res = FALSO;
	if(creditos>0.0){
		res = CIERTO;
	}
	return res;
}

int leeAsignaturasFichero(const Cadena nombreFichero, ArrayAsignaturas res) {
	int i = 0;
	int n;
	FILE *f;
	f = fopen(nombreFichero, "r");
	if (f == NULL) {
		printf("Fichero %s no encontrado", nombreFichero);
		n=0;
	} else {
		leeAsignaturaFichero(f,&res[i]);
		while (!feof(f) && i < MAXASIG - 1) {
			i++;
			leeAsignaturaFichero(f,&res[i]);
		}
	n = i;
	fclose(f);
	}
return n;
}

void leeAsignaturaFichero(FILE *f, PAsignatura a){
	char c;
	fgets(a->nombre,MAXCAR,f);
	quitaSaltoDeLinea(a->nombre);
	fgets(a->codigo,CODIGOLEN,f);
	quitaSaltoDeLinea(a->codigo);
	fscanf(f,"%lf%c",&a->creditos,&c);
	fscanf(f,"%d%c",&a->curso,&c);
	fgets(a->departamento,MAXCAR,f);
	quitaSaltoDeLinea(a->departamento);
}
