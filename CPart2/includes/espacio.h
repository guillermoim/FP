/*
 * espacio.h
 *
 *  Created on: 14 de may. de 2016
 *      Author: Guillermo
 */

#ifndef INCLUDES_ESPACIO_H_
#define INCLUDES_ESPACIO_H_

#include "cadena.h"
#include "logico.h"
#include <stdio.h>
#define MAXESPACIOS 200

typedef struct{
	Cadena nombre;
	int planta;
	int capacidad;
}Espacio;

typedef Espacio* PEspacio;
typedef Espacio ArrayEspacios[MAXESPACIOS];

int inicializaEspacio(PEspacio, const Cadena, int, int);
void muestraEspacio(Espacio);
void muestraEspacios(const ArrayEspacios, int);
int leeEspaciosFichero(const Cadena , ArrayEspacios);


Logico checkCapacidad(int);
void leeEspacioFichero(FILE*, PEspacio);

#endif /* INCLUDES_ESPACIO_H_ */
