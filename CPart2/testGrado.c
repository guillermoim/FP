/*
 * testGrado.c
 *
 *  Created on: 14 de may. de 2016
 *      Author: Guillermo
 */

#include <stdio.h>
#include "grado.h"

void testInicializaGrado1();
void testInicializaGrado2();
void testInicializaGrado3();
void testInicializaGrado( const Cadena, const Cadena, const ArrayAsignaturas, int, const ArrayAsignaturas, int,	double);

int main(void){

	testInicializaGrado1();
	testInicializaGrado2();
	testInicializaGrado3();

	return 0;
}

void testInicializaGrado1(){
	printf("*** testInicializaGrado1 ***\n");
	ArrayAsignaturas obligatorias;
	int numObligatorias;
	ArrayAsignaturas optativas;
	int numOptativas;

	numObligatorias=leeAsignaturasFichero("./res/asignaturas.txt",obligatorias);
	numOptativas=leeAsignaturasFichero("./res/asignaturasOptativas.txt",optativas);

	testInicializaGrado("Test","Test",obligatorias,numObligatorias,optativas,numOptativas,120.0);

}

void testInicializaGrado2(){
	printf("*** testInicializaGrado2 ***\n");
	ArrayAsignaturas obligatorias;
	int numObligatorias;
	ArrayAsignaturas optativas;
	int numOptativas;

	numObligatorias=leeAsignaturasFichero("./res/asignaturas.txt",obligatorias);
	numOptativas=leeAsignaturasFichero("./res/asignaturasOptativas.txt",optativas);

	optativas[0].creditos=15.0; /* Para violar la restricción */

	testInicializaGrado("Test","Test",obligatorias,numObligatorias,optativas,numOptativas,12.0);
}

void testInicializaGrado3(){
	printf("*** testInicializaGrado3 ***\n");
	ArrayAsignaturas obligatorias;
	int numObligatorias;
	ArrayAsignaturas optativas;
	int numOptativas;

	numObligatorias=leeAsignaturasFichero("./res/asignaturas.txt",obligatorias);
	numOptativas=leeAsignaturasFichero("./res/asignaturasOptativas.txt",optativas);

	testInicializaGrado("Test","Test",obligatorias,numObligatorias,optativas,numOptativas,12.0);
}

void testInicializaGrado( const Cadena nombre, const Cadena centro,
		const ArrayAsignaturas obligatorias, int numObligatorias,
		const ArrayAsignaturas optativas, int numOptativas,
		double minimoCreditosOptativas){
	Grado g;
	int res;

	res=inicializaGrado(&g,nombre,centro,obligatorias,numObligatorias,optativas,numOptativas,minimoCreditosOptativas);
	if(res==-1){
		printf("Hubo un problema al inicializar el grado.\n");
	}
	else{
		muestraGrado(g);
	}
}

