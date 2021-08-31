insert into usuario (
	primer_nombre ,
	segundo_nombre ,
	primer_apellido ,
	segundo_apellido ,
	tipo_de_documento ,
	documento ,
	administradora_salud ,
	fecha_afiliacion_salud,
	administradora_de_pension ,
	fecha_afiliacion_pension 
) 
values
(
	:primerNombre, 
	:segundoNombre, 
	:primerApellido,
	:segundoApellido,
	:tipoDeDocumento,
	:documento,
	:administradoraSalud,
	:fechaAfiliacionASalud,
	:administradoraDePension,
	:fechaAfiliacionAPension
)

