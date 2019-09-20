/*
 * testEspacio.c
 *
 *  Created on: 14 de may. de 2016
 *      Author: Guillermo
 */
#include <stdio.h>
#include <string.h>
#include "cadena.h"
#include "espacio.h"

void testLeeEspaciosFichero(const Cadena nombre);
void testInicializaEspacio( const Cadena nombre, int planta, int capacidad);
void testInicializaEspacio1();
void testInicializaEspacio2();


int main(void){
	testLeeEspaciosFichero("./res/espacios.txt");
	testInicializaEspacio1();
	testInicializaEspacio2();


	return 0;
}

void testInicializaEspacio(const Cadena nombre, int planta, int capacidad){
	int res;
	Espacio e1;
	res = inicializaEspacio(&e1,nombre, planta, capacidad);
	if(res<0){
		printf("Hubo un problema al inicializar el espacio.\n");
	}else{
		printf("El espacio se inicializó correctamente.\n");
		muestraEspacio(e1);
	}
}

void testInicializaEspacio1(){

	printf("*** testInicializaEspacio 1 ***\n");
	testInicializaEspacio( "A2.13", 2, 0);
}

void testInicializaEspacio2(){

	printf("*** testInicializaEspacio 2 ***\n");
	testInicializaEspacio("A2.13", 2, 1);
}


void testLeeEspaciosFichero(const Cadena nombre){
	printf("*** testLeeEspaciosFichero ***\n");
	ArrayEspacios espacios;
	int numEspacios;

	numEspacios = leeEspaciosFichero(nombre, espacios);
	muestraEspacios(espacios, numEspacios);
}

