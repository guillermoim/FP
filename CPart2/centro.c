/*
 * centro.c
 *
 *  Created on: 21 de may. de 2016
 *      Author: Guillermo
 */

#include "centro.h"

int inicializaCentro(PCentro res, const Cadena nombre, const Cadena direccion, int numeroPlantas,
		int numeroSotanos, const ArrayEspacios espacios, int numEspacios){
	int i = 0;
	if(!checkPlantas(numeroPlantas) || !checkSotanos(numeroSotanos)){
		i = -1;
		printf("Error el numero de plantas o de sotanos es incorrecto.\n");
	}
	strcpy(res->nombre, nombre);
	strcpy(res->direccion, direccion);
	res->numeroPlantas=numeroPlantas;
	res->numeroSotanos=numeroSotanos;
	copiaArray(res->espacios,espacios, numEspacios);
	res->numEspacios = numEspacios;

	return i;
}

void muestraCentro(Centro c){
	printf("Nombre: %s\n", c.nombre);
	fflush(stdout);
	printf("Dirección: %s\n", c.direccion);
	fflush(stdout);
	printf("Número de plantas: %d\n", c.numeroPlantas);
	fflush(stdout);
	printf("Número de sótanos: %d\n", c.numeroSotanos);
	fflush(stdout);
	printf("Espacios:\n***\n");
	muestraEspacios(c.espacios, c.numEspacios);
	printf("***");
	fflush(stdout);
}

Logico checkPlantas(int plantas){
	Logico res = CIERTO;
	if(plantas<1){
		res = FALSO;
	}
	return res;
}

Logico checkSotanos(int sotanos){
	Logico res = CIERTO;
	if(sotanos<0){
		res = FALSO;
	}
	return res;
}

void copiaArray(ArrayEspacios destino, const ArrayEspacios fuente, int numEspacios ){
	int i;
	for(i=0; i<numEspacios; i++){
		destino[i]=fuente[i];
	}
}
