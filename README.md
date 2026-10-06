# HelpDesk

Proyecto de gestión de incidencias desarrollado en Java para 2º DAW.

## Ejecución

Para ejecutar la aplicación se debe iniciar la clase:

`AplicacionHelpDesk`

La aplicación muestra un menú desde el que se pueden realizar las siguientes operaciones:

1. Crear incidencia
2. Listar incidencias
3. Buscar incidencia por identificador
4. Cerrar incidencia
5. Mostrar estadísticas
6. Guardar incidencias
0. Salir

Las incidencias se guardan en el archivo `tickets.txt`.

Al iniciar la aplicación, se intenta recuperar automáticamente la información guardada anteriormente.

## Estructura de las clases

### Ticket

Representa una incidencia.

Se encarga de:

- Mantener el identificador.
- Mantener la descripción.
- Mantener el estado de la incidencia.
- Validar los datos.
- Permitir cerrar una incidencia.

### GestorTickets

Se encarga de gestionar la colección de incidencias.

Sus responsabilidades son:

- Crear incidencias.
- Asignar identificadores consecutivos.
- Buscar incidencias.
- Mantener la colección.
- Calcular las estadísticas.
- Recuperar incidencias cargadas desde el archivo.

### ArchivoTickets

Se encarga de la persistencia.

Sus responsabilidades son:

- Guardar las incidencias en `tickets.txt`.
- Leer las incidencias del archivo.
- Recuperar identificadores, descripciones y estados.
- Utilizar UTF-8 para trabajar con el archivo.

### AplicacionHelpDesk

Es la clase principal de la aplicación.

Se encarga de:

- Mostrar el menú.
- Leer los datos introducidos por teclado.
- Mostrar mensajes al usuario.
- Coordinar las operaciones entre el gestor y el archivo.

## Persistencia

Las incidencias se almacenan en `tickets.txt`.

Cada incidencia ocupa una línea con el siguiente formato:

`identificador;estado;descripcion`

Por ejemplo:

`1;false;Falla el teclado`

`2;true;No funciona el ratón`

El estado `false` representa una incidencia abierta y `true` una incidencia cerrada.

El identificador siguiente se calcula a partir del identificador más alto recuperado.

## Pruebas

El proyecto utiliza JUnit para comprobar el funcionamiento de las clases.

Las pruebas comprueban, entre otras cosas:

- Creación de incidencias.
- Estado inicial abierto.
- Cierre de incidencias.
- Validación de identificadores.
- Validación de descripciones.
- Búsqueda de incidencias.
- Identificadores consecutivos.
- Protección de la colección interna.
- Estadísticas de incidencias.

Para ejecutar las pruebas con Maven:

`mvn test`

## Limitaciones conocidas

La aplicación utiliza un archivo de texto local para guardar las incidencias.

No utiliza una base de datos ni un sistema de usuarios.

Las descripciones se almacenan en una sola línea.

Si el archivo `tickets.txt` contiene datos inválidos o identificadores repetidos, la aplicación informa del error y no inicia la carga de las incidencias.

## Git

El proyecto utiliza Git para controlar las versiones.

Se han realizado varios commits durante el desarrollo del proyecto para registrar los principales avances.

## Resultado de las pruebas

El proyecto dispone de 19 pruebas automatizadas con JUnit.

Todas las pruebas pasan correctamente al ejecutar:

`mvn test`
