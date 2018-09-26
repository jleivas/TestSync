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
- Tener instalado java 1.8 o superior
Para instalar:
- No requiere de otro software adicional, la instalación será más sencilla y no será necesario tener un mayor conocimiento 
técnico para la puesta en marcha del sistema.

 # Current Working

 ## 25.09.2018-17.09.2018
 ### TestSync US Convenios 2° Etapa
 *Objetivo*
 Plan de trabajo segunda etapa para generar convenios
 
 ####Validar Convenios (CON-001)
 **Reglas de negocio**
 - Validar si existen convenios en estado 1 en la BD cada vez que se listen los convenios o se cree una nueva ficha
 - Validar fecha de término, Si la fecha de término caduca el convenio debe pasar a estado 2.
 - Validar si existen fichas registradas con el convenio a generar, si no existen, se debe modificar la fecha de término en el día actual, si la fecha de término es igual o superior a la fecha de cobro, se debe sumar un día a la fecha de cobro y dejar el convenio en estado 1.
 - Generar todas las cuotas a partir del saldo pendiente total dividido en la cantidad de cuotas y guardar cuotas en la BD con estado 1

 ####Generar una entidad convenio nuevo (CON-002)
 **Reglas de negocio**
 - Fecha de inicio debe ser día actual, fecha de termino puede ser actual o superior.
 - Fecha de pago debe ser superior a la fecha de termino

  ####Modificar una entidad convenio (CON-003)
 **Reglas de negocio**
 - Solo se puede modificar si la fecha de término no ha caducado
 - Fecha de inicio debe ser inferior a fecha de termino
 - Fecha de termino puede ser actual o superior
 - Fecha de pago debe ser superior a fecha de termino
 - La institución y porcentaje adicional se pueden modificar
 - El convenio debe quedar en estado 1
 - Validar si existen fichas registradas y si se modifica la institución preguntar para confirmar con aviso de cambio irreversible en las fichas generadas.
 - Si el convenio no se puede modificar (fecha de término caducada) se mostrarán los datos, pero no se podrán modificar, el botón guardar no debe estar visible o mostrar un mensaje de error al presionar, debe aparecer un botón para visualizar las cuotas pagadas y fechas de pago.
 - Si el convenio tiene estado 2 no se puede anular

 ####Generar convenios (CON-004)
 **Reglas de negocio**
 - Crear un botón para generar un reporte de convenio solo cuando se filtren fichas por convenio
 - Validar si la fecha de termino ha caducado, de lo contrario se deberá preguntar si desea cerrar el convenio para futuras fichas y de ser afirmativo se modifica la fecha de término en un día menos de la fecha actual y se valida con el método booleano interno. 
 - Validar convenio

 ####Generar reporte (CON-005)
 **Reglas de negocio**
 - Generar reportes solo de convenios finalizados en estado 2
 - Generar una clase reporte con todos los datos
 - Generar reporte Jasper a partir de la clase reporte donde se listen las fichas generadas y las cuotas

 ####Pago de cuotas (CON-006)
 **Reglas de negocio**
 - Crear un botón para registrar el pago de cuotas en la visualización de los convenios
 - Preguntar cantidad de cuotas pagadas y fecha de pago, confirmar datos y guardar
 - Seleccionar primeras x cuotas pagadas y cambiar estado de 1 a 2
 - Crear un botón para resetear cuotas pagadas de estado 2 a 1 con confirmación de usuario tipo jefatura
 - Crear botón para visualizar cuotas pagadas con fecha, fecha pagada, monto y saldo pendiente




