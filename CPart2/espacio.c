/*
 * espacio.c
 *
 *  Created on: 21 de may. de 2016
 *      Author: Guillermo
 */

#include "espacio.h"
#include <string.h>
#define MAXCAR 200

int inicializaEspacio(PEspacio res, const Cadena nombre, int planta, int capacidad){
	int i =0;
	if(!checkCapacidad(capacidad)){
		i =-1;
		printf("ERROR: La capadidad no puede ser menor o igual que 0");
	}
	strcpy(res->nombre, nombre);
	res->planta = planta;
	res->capacidad = capacidad;

	return i;
}

void muestraEspacio(Espacio e){

	printf("Nombre: %s\n", e.nombre);
	fflush(stdout);
	printf("Planta: %d\n", e.planta);
	fflush(stdout);
	printf("Capadidad: %d\n", e.capacidad);
	fflush(stdout);
}

void muestraEspacios(const ArrayEspacios res, int numEspacios){
	int i;
	for(i=0; i<numEspacios; i++){
		printf("Espacio número %d.\n", i + 1);
				muestraEspacio(res[i]);
				printf("============================\n");
	}
}

int leeEspaciosFichero(const Cadena nombreFichero, ArrayEspacios res){
	FILE* f = fopen(nombreFichero, "r");
	int i = 0;
	if(f == NULL){
		printf("Error en la lectura del fichero");
	}else{
		leeEspacioFichero(f, &res[i]);
		while(!feof(f) && i<MAXESPACIOS-1){
			i++;
			leeEspacioFichero(f, &res[i]);
		}
		fclose(f);
	}
	return i;
}

void leeEspacioFichero(FILE* f, PEspacio res){
	char c;
	fgets(res->nombre, MAXCAR, f);
	quitaSaltoDeLinea(res->nombre);
	fscanf(f, "%d%c", &res->planta, &c);
	fscanf(f, "%d%c", &res->capacidad, &c);

}


Logico checkCapacidad(int capacidad){
	Logico res = CIERTO;
	if(capacidad<=0){
		res = FALSO;
	}
	return res;
}
