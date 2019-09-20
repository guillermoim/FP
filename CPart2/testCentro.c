/*
 * testCentro.c
 *
 *  Created on: 14 de may. de 2016
 *      Author: Guillermo
 */
#include "centro.h"

void testInicializaCentro(const Cadena nombre, const Cadena direccion, int numeroPlantas,
		int numeroSotanos, const ArrayEspacios espacios, int numEspacios);
void testInicializaCentro1();
void testInicializaCentro2();
void testInicializaCentro3();

int main(void){
	testInicializaCentro1();
	testInicializaCentro2();
	testInicializaCentro3();
	return 0;
}

void testInicializaCentro(const Cadena nombre, const Cadena direccion, int numeroPlantas,
		int numeroSotanos, const ArrayEspacios espacios, int numEspacios){
	int res;
	Centro c;
	res = inicializaCentro(&c, nombre, direccion, numeroPlantas, numeroSotanos, espacios, numEspacios);
	if(res<0){
		printf("Error en la inicialización del centro.\n");
	}else{
		printf("El centro se ha inicializado correctamente.\n");
		muestraCentro(c);
	}
}

void testInicializaCentro1(){
	printf("*** testInicializaCentro 1 ***\n");
	ArrayEspacios espacios;
	leeEspaciosFichero("./res/espacios.txt", espacios);
	testInicializaCentro("ETSII", "Avd. Reina Mercedes", 4, 2, espacios, 13);
}

void testInicializaCentro2(){
	printf("*** testInicializaCentro 2 ***\n");
	ArrayEspacios espacios;
	leeEspaciosFichero("./res/espacios.txt", espacios);
	testInicializaCentro("ETSII", "Avd. Reina Mercedes", 0, 2, espacios, 13);
}

void testInicializaCentro3(){
	printf("*** testInicializaCentro 3 ***\n");
	ArrayEspacios espacios;
	leeEspaciosFichero("./res/espacios.txt", espacios);
	testInicializaCentro("ETSII", "Avd. Reina Mercedes", 4, -1, espacios, 13);
}
