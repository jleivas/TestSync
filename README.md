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

 # System Issues
 ## Registro de errores del sistema para su corrección
 ### Reporte de errores
 *Objetivo*
 Refactorizar códigos con error de implementación

 #### [Cursor en espera (ISS-019)](https://github.com/softdirex/TestSync/search?q=ISS-019&type=Commits)
 
 - No aparece el cursor en espera cuando el sistema está trabajando

 #### [Error al generar reporte de ventas(ISS-018)](https://github.com/softdirex/TestSync/search?q=ISS-018&type=Commits)
 
 - Arreglos de string fuera del rango.
 - Validacion de resumen con datos nulos

 #### [Validaciones de licencia(ISS-017)](https://github.com/softdirex/TestSync/search?q=ISS-017&type=Commits)
 
 - Validar accesos con licencia activa.

 #### [Presionar enter para entrar(ISS-016)](https://github.com/softdirex/TestSync/search?q=ISS-016&type=Commits)
 
 - Logearse al presionar enter.

 #### El sistema es lento al inicio(ISS-015)
 
 - El sistema demora mucho al cargar despues de logearse

 #### [Eliminar caracteres especiales de los mensajes(ISS-014)](https://github.com/softdirex/TestSync/search?q=ISS-014&type=Commits)
 
 - El sistema no logra enviar mensajes con comillas

 #### [Yo como usuario deseo modificar mis datos(ISS-013)](https://github.com/softdirex/TestSync/search?q=ISS-013&type=Commits)
 
 - El sistema no logra modificar los datos

 #### [Error al cargar tabla vacía de mensajes(ISS-012)](https://github.com/softdirex/TestSync/search?q=ISS-012&type=Commits    )
 
 - Aparece un mensaje de error en vez de indicar que no se encuentran mensajes disponibles

 #### [Error de visualización al cargar tabla de fichas vacía(ISS-011)](https://github.com/softdirex/TestSync/search?q=ISS-011&type=Commits)
 
 - El panel de notificaciones aparece detrás del panel principal

 #### [Ventana de sincronización se actualiza despues de 10 segundos (ISS-009)](https://github.com/softdirex/TestSync/search?q=ISS-009&unscoped_q=ISS-009&type=Commits)
 
 - Actualizar ventana de sincronización para mostrar porcentaje de progreso actualizado al inicio

 #### [No se agregan usuarios por defecto cuando ya existe una base de datos remota creada (ISS-008)](https://github.com/softdirex/TestSync/search?q=ISS-008&unscoped_q=ISS-008&type=Commits)
 
 - Sincronizar datos cuando no existen usuarios por defecto creados en la basse de datos local

 #### [El sistema no reconoce equipos duplicados (ISS-010)](https://github.com/softdirex/TestSync/search?q=ISS-010&type=Commits)
 
 - Validar existencia de equipos duplicados para evitar perdida irreversible de la información

 #### [Modificar tabla fichas (ISS-007)](https://github.com/softdirex/TestSync/search?q=ISS-007&unscoped_q=ISS-007)
 
 - Agregar columna vendedores en tabla de fichas

 #### [Error al filtrar fichas por vendedor (ISS-006)](https://github.com/softdirex/TestSync/search?q=ISS-006&type=Commits)
 
 - El sistema arroja un error sql (Encountered "ORDER" at line 1, column 10820.) en (Local.java:1159).

 #### [Botón de sincronización manual (ISS-001)](https://github.com/softdirex/TestSync/search?q=ISS-001&type=Commits)
 
 - Desabilitar cuando ya se está ejecutando una sincronización

 #### [CRUD usuarios (ISS-002)](https://github.com/softdirex/TestSync/search?q=ISS-002&type=Commits)
 
 - Mostrar mensaje de aviso de cambio de contraseña a usuario modificado
 - Mostrar mensaje de aviso de confirmación de usuario creado
 - No aparece el tipo de usuario al cargar mis datos [PENDIENTE]
 - El sistema permite la entrada a usuarios anulados [PENDIENTE]

 #### Respaldo automático de datos (ISS-003)
 
 - Respaldar los datos automáticamente al iniciar en versiones locales

 #### [Exportar correos de clientes morosos (ISS-004)](https://github.com/softdirex/TestSync/search?q=ISS-004&type=Commits)
 
 - El sistema no comprueba si quedan saldos pendientes para exportar

 #### [Error al iniciar sistema cuando no tiene acceso a la base de datos remota (ISS-005)](https://github.com/softdirex/TestSync/search?q=ISS-005&type=Commits)
 
 - El sistema no puede depender de internet para ejecutarse
 - El sistema debe crear usuarios por defecto sin actualizar su id inicial


 # Current Working
 ## 05.10.2018-15.10.2018
 ### Licencias
 *Objetivo*
 Ajustar los planes de sincronización de la base de datos

 #### [Ajuste de reportes (CON-024)](https://github.com/softdirex/TestSync/search?q=CON-024&type=Commits)
 
 - Borrar datos internos de la solicitud de lente a laboratorio
 - Crear reporte de ventas por fecha y agruparlar por vendedor

 #### Paneles de información (CON-012)
 
 - Botón de ayuda en cada ventana
 - Panel de selección de planes actualizado remotamente
 - Link de pago actualizable desde la red
 
 #### Generar licencia gratuita VERSION LOCAL (CON-013)
 
 - No tendrá acceso a bases de datos remota
 - No podrá enviar mensajes internos
 - Sin funciones de envío de correos
 - Impresión de fichas con publicidad de la empresa actualizada remotamente
 - Sólo podrá ver reportes pero no exportarlos (no libs)
 - Respaldar los datos automáticamente al iniciar (Sin pregunar al cerrar)

 #### Generar licencia básica VERSION LOCAL (CON-023)
 
 - No tendrá acceso a bases de datos remota
 - No podrá enviar mensajes internos
 - Con funciones de envío de correos
 - Impresión de fichas sin publicidad
 - Generación de reportes y exportación (con libs)
 - Respaldar los datos automáticamente al cerrar (Inicio mas rápido)

 #### [Genenerar licencia 2x (CON-014)](https://github.com/softdirex/TestSync/search?q=con-014&type=Commits)
 
 - Acceso a base de datos remota
 - No podrá enviar mensajes internos
 - Sin funciones de envío de correos
 - Generación de reportes ilimitados
 - Sincronización manual con base de datos remota dos veces al día

 #### Genenerar licencia 4x (CON-015) OK
 
 - Acceso a base de datos remota
 - Podrá enviar mensajes internos
 - Impresión de fichas con envío de correos a los clientes
 - Generación ilimitada de reportes
 - Sincronización manual con base de datos hasta 4 veces al día

 #### Genenerar licencia 6x (CON-016) OK
 
 - Acceso a base de datos remota
 - Podrá enviar mensajes internos
 - Impresión de fichas con envío de correos a los clientes
 - Generación ilimitada de reportes
 - Sincronización manual con base de datos hasta seis veces al día

 #### [Genenerar licencia Full Data (CON-017)](https://github.com/softdirex/TestSync/commit/459dfbe43baa4e4388ca5eff2e73c009470f6cad)
 
 - Acceso a base de datos remota
 - Podrá enviar mensajes internos
 - Impresión de fichas con envío de correos a los clientes
 - Generación ilimitada de reportes
 - Sincronización manual con base de datos ilimitada

 #### [Encriptar datos de lectura xml (CON-018)](https://github.com/softdirex/TestSync/commit/d42f7d3373629ddbae85d4f1548770574c05ab49)
 
 - Encriptar escritura xml
 - Desencriptar lectura xml

 #### crear ventana de configuracion de parametros xml (CON-019)
 
 - Leer, editar y escribir datos xml con acceso restringidos

 #### [Restringir acceso de usuarios (CON-020)](https://github.com/softdirex/TestSync/search?q=con-020&type=Commits)
 
 - Restringir acceso de usuarios por tipo y rango a cada funcionalidad del sistema

 #### [Marcar Fichas como entregadas (CON-021)](https://github.com/softdirex/TestSync/search?q=con-021&unscoped_q=con-021&type=Commits)
 
 - Que el sistema permita cambiar el estado a entregadas con despacho sin registro

 #### [Validar xml (CON-022)](https://github.com/softdirex/TestSync/search?q=con-022&type=Commits)
 
 - Validar xml al inicio del sistema e ingreasar nombre de equipo en primera instancia
 - Que el sistema permita reconocer si los arcivos xml fueron copiados de un pc a otro
 
 ## 01.10.2018-04.10.2018
 ### Sincronización
 *Objetivo*
 Mejorar el proceso de sincronización de las bases de datos, reducir el tiempo de espera y reparar errores
 
 #### [Abortar proceso de sincronización (CON-008)](https://github.com/softdirex/TestSync/search?q=con-008&type=Commits)

 - Cerrar proceso si demora más del tiempo estimado en un mismo porcentage

 #### [Respaldo automático y licencia (CON-009)](https://github.com/softdirex/TestSync/search?q=con-009&type=Commits)

 - generar respaldo de datos automáticamente al momento de cerrar y validar coneccion a internet
 - comprobar licencia cada 5 minutos

 #### [Importación de base de datos (CON-010)](https://github.com/softdirex/TestSync/search?q=CON-010&type=Commits)

 - Crear opción en herramientas para importar todos los datos desde los respaldos en excel
 - Generar archivos Excel para migrar datos antiguos

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





