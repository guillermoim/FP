/*
 * centro.h
 *
 *  Created on: 14 de may. de 2016
 *      Author: Guillermo
 */

#ifndef INCLUDES_CENTRO_H_
#define INCLUDES_CENTRO_H_

#include "cadena.h"
#include "espacio.h"
#include "logico.h"
#include <stdio.h>
#include <string.h>

typedef struct{
	Cadena nombre;
	Cadena direccion;
	int numeroPlantas;
	int numeroSotanos;
	ArrayEspacios espacios;
	int numEspacios;
}Centro;
typedef Centro* PCentro;

int inicializacENTRO(PCentro, const Cadena, const Cadena, int, int, const ArrayEspacios, int);
void muestraCentro(Centro);

Logico checkPlantas(int);
Logico checkSotanos(int);
void copiaArray( ArrayEspacios,const ArrayEspacios, int);

#endif /* INCLUDES_CENTRO_H_ */
