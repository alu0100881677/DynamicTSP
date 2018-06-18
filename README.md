# Problema de Viajante de Comercio

Este repositorio contiene el código necesario para resolver una variante de los problemas de viajante de comercio. El problema que se resuelve es un problema dínamico y multiobjetivo.

## Instalación
* En primer lugar debe de clonar este repositorio
* Una vez clonado, situese en en la base del directorio y ejecute el script bash [`configurador.sh`]. Este script se encargará de instalar las dependencias necesarias del proyecto.

## Uso
El repositorio contiene tres modulos distintos los cuales pueden compenetrar su funcionamiento. Para arrancar cada uno de los modulos necesitará una terminal para cada uno de ellos.
* El primer modulo, encargado de la generación de peticiones de movilidad es ejecutado desde el directorio raiz del repositorio mediante el comando *./generadorPeticiones.sh*. A este modulo debemos de pasarle una serie de argumentos que establezcan su funcionamiento:   
   * nombre del fichero donde se generan las peticiones. Este fichero estará en la carpeta jmetalsp-examples/Datos.
   * número de ciudades que contiene el problema para el que simulamos cambios.
   * parametro númerico *k* . El coste de un arco varía entre 10000 y (10000 + k).
   * número de milisegundos entre la generación de cada cambio (5 segundos = 5000)

* El segundo modulo, es el encargado de resolver el problema, para ello hace uso del algoritmo nsga-ii. Para configurar este modulo debemos de pasar una serie de parametros a *./solucionadorProblema.sh*:
   * fichero de distancias
   * fichero de costes
   * nombre del fichero con las peticiones
   * nombre del directorio que almacena los frentes de pareto
   * periodo de lectura de nuevas peticiones en milisegundos(2000)

Todos los ficheros que se pasen como parámetro deben de estar en  jmetal-examples/Datos y solo debe indicar su nombre.
* El tercer modulo representa las soluciones obtenidas por el segundo modulo mediante un servicio web. Para ponerlo en funcionamiento se debe de ejecutar el script *./visorPareto.sh* al que se le deben de pasar una serie de argumentos:
   * nombre del directorio donde que almacena los frentes de pareto
   * periodo en milisegundos que pasa entre la traducción de cada fichero, se recomienda usar 2000.

### Ejemplo de ejecución
Tal y como se ha mencionado previamente cada modulo debe de ser ejecutado en una terminal distinta. Además para que el funcionamiento del programa sea el adecuado, se deben de ejecutar los modulos en orden.
* **Primer modulo** --> ./generadorPeticiones.sh generacion.txt 10 2000 5000

* **Segundo modulo** --> ./solucionadorProblema.sh distanceFile.prbd costFile.prbd generacion.txt DirectorioSalida 2000

* **Tercer modulo** --> ./visorPareto.sh DirectorioSalida 2000
