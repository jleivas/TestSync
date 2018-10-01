# Desarrollo de aplicacion Opcentra

## Introducción

Opcentra es una aplicación de escritorio basada en OptiData version 3 utilizado para registrar recetas 
oftalmológicas y almacenamiento de datos de clientes en las ópticas, tiene la capacidad tanto de imprimir 
el comprobante para el cliente como de enviarlo por correo, envía reporte de recetas efectuadas y 
obtiene reportes de ventas por periodo, actualmente se encuentra disponible la tercera versión del 
sistema en softdirex.cl, Opcentra es la cuarta versión y se encuentra en desarrollo.

## Mejoras

- Genera la receta oftalológica automáticamente, a diferencia de las versiones anteriores, no necesita un formulario pre-impreso
- Envía reportes de ventas por filtro al correo, ahora en formato HTML
- Tiene la capacidad de administrar mas de un inventario
- Almacenamiento de datos centralizado en una base de datos remota
- Puede registrar distintas oficinas o locales y trabajar en forma paralela conectado al mismo centro de datos
- Acceso de usuarios con permisos específicos para cada tipo de usuario
- Envío automático de reporte de errores
- Mejora en la interfaz gráfica
- Base de datos local embebida, no requiere software adicional a diferencia de las versiones anteriores

## Instalación

Requisitos previos:
- Tener instalado [java 1.8 o superior](https://www.java.com/es/download/)
Para instalar:
- No requiere de otro software adicional, la instalación será más sencilla y no será necesario tener un mayor conocimiento 
técnico para la puesta en marcha del sistema.

 # Current Working
 ## 05.10.2018-15.10.2018
 ### Licencias
 *Objetivo*
 Ajustar los planes de sincronización de la base de datos

 #### Paneles de información (CON-012)
 
 -Botón de ayuda en cada ventana
 -Panel de selección de planes actualizado remotamente
 -Link de pago actualizable desde la red
 
 #### Generar licencia gratuita (CON-013)
 
 -No tendrá acceso a bases de datos remota
 -Impresión de fichas con publicidad de la empresa actualizada remotamente
 -Sólo podrá generar un reporte mensual de cada tipo

 #### Genenerar licencia 2x (CON-014)
 
 -Acceso a base de datos remota
 -Impresión de fichas sin envío de correos a los clientes
 -Generación de reportes limitadas a un día por tipo
 -Sincronización manual con base de datos remota dos veces al día

 #### Genenerar licencia 4x (CON-015)
 
 -Acceso a base de datos remota
 -Impresión de fichas con envío de correos a los clientes
 -Generación ilimitada de reportes
 -Sincronización automática con base de datos remota tres veces al día
 -Sincronización manual con base de datos hasta 4 veces al día

 #### Genenerar licencia 6x (CON-016)
 
 -Acceso a base de datos remota
 -Impresión de fichas con envío de correos a los clientes
 -Generación ilimitada de reportes
 -Sincronización manual con base de datos hasta seis veces al día

 #### Genenerar licencia Full Data (CON-017)
 
 -Acceso a base de datos remota
 -Impresión de fichas con envío de correos a los clientes
 -Generación ilimitada de reportes
 -Sincronización manual con base de datos ilimitada

 #### Encriptar datos de lectura xml (CON-018)
 
 -encriptar escritura xml
 -desencriptar lectura xml

 #### crear ventana de configuracion de parametros xml (CON-019)
 
 -Leer, editar y escribir datos xml con acceso restringidos

 #### restringir acceso de usuarios (CON-020)
 
 -Restringir acceso de usuarios por tipo y rango a cada funcionalidad del sistema

 #### Marcar Fichas como entregadas (CON-021)
 
 -Que el sistema permita cambiar el estado a entregadas con despacho sin registro
 
 ## 01.10.2018-04.10.2018
 ### Sincronización
 *Objetivo*
 Mejorar el proceso de sincronización de las bases de datos, reducir el tiempo de espera y reparar errores
 
 #### [Abortar proceso de sincronización (CON-008)](https://github.com/softdirex/TestSync/commit/a9e9a57539a68daac30e151ad1941f82d3784621)

 - Cerrar proceso si demora más del tiempo estimado en un mismo porcentage

 #### Respaldo automático (CON-009)

 - generar respaldo de datos automáticamente al momento de cerrar y validar coneccion a internet
 - comprobar licencia cada 30 minutos

 #### Importación de base de datos (CON-010)

 - Crear opción en herramientas para importar todos los datos desde los respaldos en excel

 #### Importación de inventario (CON-011)

 - Crear opción para importar inventario desde archivo en excel

 ## 25.09.2018-27.09.2018
 ### TestSync US Convenios 2° Etapa
 *Objetivo*
 Plan de trabajo segunda etapa para generar convenios
 
 #### [Validar Convenios (CON-001)](https://github.com/softdirex/TestSync/commit/c50f7b601da7c71932d6656eb60f7d63244e0e13)
 
 - Validar si existen convenios en estado 1 en la BD cada vez que se listen los convenios o se cree una nueva ficha
 - Validar fecha de término, Si la fecha de término caduca el convenio debe pasar a estado 2.
 - Validar si existen fichas registradas con el convenio a generar, si no existen, se debe modificar la fecha de término en el día actual, si la fecha de término es igual o superior a la fecha de cobro, se debe sumar un día a la fecha de cobro y dejar el convenio en estado 1.
 - Generar todas las cuotas a partir del saldo pendiente total dividido en la cantidad de cuotas y guardar cuotas en la BD con estado 1

 #### [Generar una entidad convenio nuevo (CON-002)](https://github.com/softdirex/TestSync/commit/2577e3e12a08ac5315a5e6f3c563656dc7ebc0c9)
 
 - Fecha de inicio debe ser día actual, fecha de termino puede ser actual o superior.
 - Fecha de pago debe ser superior a la fecha de termino

 #### [Modificar una entidad convenio (CON-003)](https://github.com/softdirex/TestSync/commit/2b03ab6de9de8d4f2de1ec5a27c4f43190c6e624)
 
 - Solo se puede modificar si la fecha de término no ha caducado
 - Fecha de inicio debe ser inferior a fecha de termino
 - Fecha de termino puede ser actual o superior
 - Fecha de pago debe ser superior a fecha de termino
 - La institución y porcentaje adicional se pueden modificar
 - El convenio debe quedar en estado 1
 - Validar si existen fichas registradas y si se modifica la institución preguntar para confirmar con aviso de cambio irreversible en las fichas generadas.
 - Si el convenio no se puede modificar (fecha de término caducada) se mostrarán los datos, pero no se podrán modificar, el botón guardar no debe estar visible o mostrar un mensaje de error al presionar, debe aparecer un botón para visualizar las cuotas pagadas y fechas de pago.
 - Si el convenio tiene estado 2 no se puede anular

 #### [Generar convenios (CON-004)](https://github.com/softdirex/TestSync/commit/fb55dece0ddc8655a768d31e6da539f867689a1b)
 
 - Crear un botón para generar un reporte de convenio solo cuando se filtren fichas por convenio
 - Validar si la fecha de termino ha caducado, de lo contrario se deberá preguntar si desea cerrar el convenio para futuras fichas y de ser afirmativo se modifica la fecha de término en un día menos de la fecha actual y se valida con el método booleano interno. 
 - Validar convenio

 #### [Generar reporte (CON-005)](https://github.com/softdirex/TestSync/commit/0b1d9caeb650ebbb5320645e4e361e0731aedc6f)
 - [Generar reportes solo de convenios finalizados en estado 2](https://github.com/softdirex/TestSync/commit/a8f88b25dbff5ef1bb5fb7a3d87705f0f3d04bcc)
 - [Generar una clase reporte con todos los datos](https://github.com/softdirex/TestSync/commit/a8f88b25dbff5ef1bb5fb7a3d87705f0f3d04bcc)
 - [Generar reporte Jasper a partir de la clase reporte donde se listen las fichas generadas y las cuotas](https://github.com/softdirex/TestSync/commit/3d082add22adbe3e7f92cf07c7cb6898ce03c131)

 #### [Agregar Rut a empresas (CON-006)](https://github.com/softdirex/TestSync/commit/8abf54941a15ef5202a76e387e4d229794928899)
 - Crear atributo para almacenar el rut de las empresas, dato importante para generar los reportes

 #### [Pago de cuotas (CON-007)](https://github.com/softdirex/TestSync/commit/60a082750075722cdbf89a4f762ca3dfcb510f92)
 - Crear un botón para registrar el pago de cuotas en la visualización de los convenios
 - Preguntar cantidad de cuotas pagadas y fecha de pago, confirmar datos y guardar solo en Convenio
 - Seleccionar primeras x cuotas pagadas y cambiar estado de 1 a 2
 - Crear botón para visualizar cuotas pagadas con fecha, fecha pagada, monto y saldo pendiente
 - En las fichas con convenio no se pueden agregar abonos, solo abono inicial al crear nueva ficha





