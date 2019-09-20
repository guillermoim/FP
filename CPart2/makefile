C_COMPILER=gcc

ifeq ($(OS),Windows_NT)
	RM=cmd \/C del
else
	RM=rm -f 
endif

INCLUDES_ROOT=./includes
INC_DIRS=-I . -I$(INCLUDES_ROOT)
BIN_DIR=

SRC_FILES1=cadena.c testCadena.c
TARGET1=$(BIN_DIR)testCadena.exe

SRC_FILES2=asignatura.c cadena.c testAsignatura.c
TARGET2=$(BIN_DIR)testAsignatura.exe

SRC_FILES3=grado.c asignatura.c cadena.c testGrado.c
TARGET3=$(BIN_DIR)testGrado.exe

SRC_FILES4=espacio.c cadena.c testEspacio.c
TARGET4=$(BIN_DIR)testEspacio.exe

SRC_FILES5=espacio.c centro.c cadena.c testCentro.c
TARGET5=$(BIN_DIR)testCentro.exe

all: clean default
	
clean:
	-$(RM) $(TARGET1)
	-$(RM) $(TARGET2)
	-$(RM) $(TARGET3)
	-$(RM) $(TARGET4)
	-$(RM) $(TARGET5)	
	 
testCadena:
	-$(C_COMPILER) $(INC_DIRS) $(SRC_FILES1) -o $(TARGET1)

testAsignatura:
	-$(C_COMPILER) $(INC_DIRS) $(SRC_FILES2) -o $(TARGET2)

testGrado:
	-$(C_COMPILER) $(INC_DIRS) $(SRC_FILES3) -o $(TARGET3)

testEspacio:
   #Descomente la siguiente línea una vez implementado testEspacio
	-$(C_COMPILER) $(INC_DIRS) $(SRC_FILES4) -o $(TARGET4)
	
testCentro:
#   Descomente la siguiente línea una vez implementado testCentro
	-$(C_COMPILER) $(INC_DIRS) $(SRC_FILES5) -o $(TARGET5)
	
default: testCadena testAsignatura testGrado testEspacio testCentro