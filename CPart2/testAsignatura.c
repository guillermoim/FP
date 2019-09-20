/*
 * testAsignatura.c
 *
 *  Created on: 16 de may. de 2016
 *      Author: Guillermo
 */

#include <stdio.h>
#include <string.h>
#include "cadena.h"
#include "asignatura.h"

void testLeeAsignaturasFichero(const Cadena nombreFichero);
void testInicializaAsignatura1();
void testInicializaAsignatura2();
void testInicializaAsignatura3();
void testInicializaAsignatura4();
void testInicializaAsignatura(const Cadena, const Codigo, double, int, const Cadena);

int main(void){

	testLeeAsignaturasFichero("./res/asignaturas.txt");
	testInicializaAsignatura1();
	testInicializaAsignatura2();
	testInicializaAsignatura3();
	testInicializaAsignatura4();

	return 0;
}

void testLeeAsignaturasFichero(const Cadena nombreFichero) {
	printf("*** testLeeAsignaturasFichero ***\n");
	ArrayAsignaturas asignaturas;
	int nAsig;

	nAsig=leeAsignaturasFichero(nombreFichero,asignaturas);
	muestraAsignaturas(asignaturas,nAsig);
}

void testInicializaAsignatura1(){
	printf("*** testInicializaAsignatura1 ***\n");
	testInicializaAsignatura("Fundamentos de Programación",
			"12345767", 12.0, 1, "LSI");
}

void testInicializaAsignatura2(){
	printf("*** testInicializaAsignatura2 ***\n");
	testInicializaAsignatura("Fundamentos de Programación",
				"1234567", -1.0, 1, "LSI");
}

void testInicializaAsignatura3(){
	printf("*** testInicializaAsignatura3 ***\n");
	testInicializaAsignatura("Fundamentos de Programación",
				"1234567", 12.0, 6, "LSI");
}

void testInicializaAsignatura4(){
	printf("*** testInicializaAsignatura4 ***\n");
	testInicializaAsignatura("Fundamentos de Programación",
				"1234567", 12.0, 1, "LSI");
}

void testInicializaAsignatura(const Cadena nombre, const Codigo codigo, double creditos, int curso, const Cadena departamento){
	Asignatura a1;

	int res = inicializaAsignatura(&a1, nombre, codigo, creditos, curso, departamento);

	if (res == -1) {
		printf("Hubo algún problema con la inicialización.\n");
	} else {
		printf("Se inicializó la asignatura correctamente.\n");
		muestraAsignatura(a1);
	}
}
