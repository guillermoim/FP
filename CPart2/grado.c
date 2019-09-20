/*
 * grado.c
 *
 *  Created on: 14 de may. de 2016
 *      Author: Guillermo
 */
#include "grado.h"
#include "asignatura.h"
#include <stdio.h>
#include <string.h>


int inicializaGrado(PGrado res, const Cadena nombre, const Cadena centro, const ArrayAsignaturas obligatorias, int numObligatorias,
const ArrayAsignaturas optativas, int numOptativas, double minimoCreditosOptativas) {

	int status = 0;
	if (!checkMinimoCreditosOptativas(minimoCreditosOptativas, optativas, numOptativas)) {
		fprintf(stderr, "Error con el número de créditos mínimo de las asignaturas optativas.\n");
		status = -1;
	} else if (!checkOptativasTodasIgualesCreditos(optativas, numOptativas)) {
		fprintf(stderr, "Error con el número de créditos de las asignaturas optativas.\n");
		status = -1;
	} else{
		strcpy(res->nombre, nombre);
		strcpy(res->centro, centro);
		copiaArray(res->obligatorias, obligatorias, numObligatorias);
		res->numObligatorias = numObligatorias;
		copiaArray(res->optativas, optativas, numOptativas);
		res->numOptativas = numOptativas;
		res->minimoCreditosOptativas = minimoCreditosOptativas;
	}

	return status;

}
void muestraGrado(Grado g){
	printf("Nombre: %s\n", g.nombre);
	printf("Centro: %s\n", g.centro);
	printf("Mínimo créditos optativas: %lf\n",g.minimoCreditosOptativas);
	printf("Asignaturas obligatorias:\n");
	muestraAsignaturas(g.obligatorias,g.numObligatorias);
	printf("Asignaturas optativas:\n");
	muestraAsignaturas(g.optativas,g.numOptativas);
}

Logico checkMinimoCreditosOptativas(double minimoCreditosOptativas,	const ArrayAsignaturas optativas, int numOptativas) {
	Logico res = FALSO;
	double suma = sumaCreditos(optativas, numOptativas);
	if(minimoCreditosOptativas >= 0 && minimoCreditosOptativas <= suma){
		res = CIERTO;
	}
	return res;
}

double sumaCreditos(const ArrayAsignaturas optativas, int numOptativas) {
	double suma = 0.0;
	int i;
	for (i = 0; i < numOptativas; i++) {
		suma += optativas[i].creditos;
	}
	return suma;
}

Logico checkOptativasTodasIgualesCreditos(const ArrayAsignaturas optativas, int numOptativas){

	Logico res = CIERTO;
	int i;
	for(i=1;i<numOptativas;i++){
		if(optativas[i].creditos!=optativas[0].creditos){
			res = FALSO;
			break;
		}
	}
	return res;
}

void copiaArray(ArrayAsignaturas destino, const ArrayAsignaturas origen, int numElem){
	int i;
	for(i=0;i<numElem;i++){
		destino[i]=origen[i];
	}
}
