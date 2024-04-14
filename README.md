# TDA - Grupo Capybaras salvajes
2024 1° Cuatrimestre - TDA - Capybaras salvajes

## Introducción

(Requerido) Java Version: Java 17
(Opcional) Maven - Versión: Apache Maven 3.8.1 

## Ej 1 

### Ejecución - JAR

1°) Ir a la carpeta:  /tp1/ej1

2°) Abrir una consola en la ubicacion y ejecutar el comando

   java -jar ej1.jar <archivo>

3°) Ejemplos

  java -jar ej1.jar ejemplo.txt


### Compilación - JAR

En caso de que sea requerido re-compilar el JAR se pueden seguir los siguientes pasos:

1°) Ir a la carpeta:  /tp1/ej1

2°) Compilar con maven:

   mvn compile

3°) Armar el package con maven

  mvn package

4°) El .jar ejecutable se creara en la carpeta /target. Se podria ejecutar el mismo con el siguiente comando:

	java -jar target/*.jar ejemplo.txt