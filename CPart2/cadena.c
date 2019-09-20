/*
 * cadena.c
 *
 *  Created on: 14 de may. de 2016
 *      Author: Guillermo
 */

#include "cadena.h"
#include <string.h>

void quitaSaltoDeLinea(Cadena c){
	int i;
	i = strlen(c);
	if(c[i-1]=='\n'){
		c[i-1]='\0';
	}
}
