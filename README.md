# PetCare

Este proyecto corresponde a una aplicación de consola desarrollada en Kotlin para simular la gestión de boxes de una clínica veterinaria.

El sistema permite registrar distintos tipos de pacientes, asignarlos a boxes disponibles, calcular el valor de la atención y generar un reporte al finalizar el turno.

## Qué hace el programa

El programa trabaja con pacientes Canino, Felino y Exotico.

También permite:

- Registrar la entrada de pacientes.
- Buscar un box libre.
- Cambiar el estado de los boxes.
- Registrar la salida de un paciente.
- Calcular el valor de la atención.
- Aplicar IVA y descuentos.
- Generar tickets.
- Guardar un historial de atenciones.
- Mostrar consultas del sistema.
- Manejar algunos errores sin cerrar el programa.
- Simular la espera de los sensores utilizando corrutinas.

## Cómo ejecutar

1. Abrir el proyecto en IntelliJ IDEA.
2. Esperar que Gradle termine de cargar las dependencias.
3. Abrir el archivo `Main.kt`.
4. Ejecutar el programa.
5. Los resultados se mostrarán en la consola.

## Organización del proyecto

La carpeta `model` contiene las clases relacionadas con pacientes, boxes, estados y tickets.

La carpeta `service` contiene la clase `PetCare`, donde se encuentra la mayor parte de la lógica del sistema.

El archivo `Main.kt` contiene los datos utilizados para probar el funcionamiento del programa.

## Tecnologías utilizadas

- Kotlin
- Gradle
- IntelliJ IDEA
- Kotlin Coroutines