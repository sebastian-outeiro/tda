# TDA - Grupo Capybaras salvajes
2024 1° Cuatrimestre - TDA - Capybaras salvajes

## Introducción

(Requerido) Java Version: Java 17

(Opcional) Maven - Versión: Apache Maven 3.8.1 

## Ejercicio 1 

### Ejecución - JAR

Se adjunta en los archivos un JAR pre-compilado para su rápida ejecución. En caso de que su ejecución no funcione, se debera proceder a compilar el mismo nuevamente.

1°) Ir a la carpeta:  

`/tp1/ej1`

2°) Abrir una consola en la ubicacion y ejecutar el comando

`java -jar ej1.jar <archivo>`

3°) Ejemplos

`java -jar ej1.jar ejemplo.txt`


### Compilación - JAR

En caso de que sea requerido re-compilar el JAR se pueden seguir los siguientes pasos:

1°) Ir a la carpeta:  

`/tp1/ej1`

2°) Compilar con maven:

`mvn compile`

3°) Armar el package con maven

`mvn package`

4°) El .jar ejecutable se creara en la carpeta /target. Se podria ejecutar el mismo con el siguiente comando:

`java -jar target/*.jar ejemplo.txt`


## Ejercicio 3

### Ejecución - JAR

Se adjunta en los archivos un JAR pre-compilado para su rápida ejecución. En caso de que su ejecución no funcione, se debera proceder a compilar el mismo nuevamente.

1°) Ir a la carpeta:  

`/tp1/ej3`

2°) Abrir una consola en la ubicacion y ejecutar el comando

`java -jar ej3.jar <tamaño campo> <columna silo> <fila silo>`

3°) Ejemplos

`java -jar ej3.jar 8 0 0`


### Compilación - JAR

En caso de que sea requerido re-compilar el JAR se pueden seguir los siguientes pasos:

1°) Ir a la carpeta:  

`/tp1/ej3`

2°) Compilar con maven:

`mvn compile`

3°) Armar el package con maven

`mvn package`

4°) El .jar ejecutable se creara en la carpeta /target. Se podria ejecutar el mismo con el siguiente comando:

`java -jar target/*.jar 8 0 0`
