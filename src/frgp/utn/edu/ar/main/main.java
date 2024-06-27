package frgp.utn.edu.ar.main;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import frgp.utn.edu.ar.entidad.Especialidad;
import frgp.utn.edu.ar.entidad.Estado;
import frgp.utn.edu.ar.entidad.Medico;
import frgp.utn.edu.ar.entidad.Paciente;
import frgp.utn.edu.ar.entidad.Turno;
import frgp.utn.edu.ar.entidad.Usuario;
import frgp.utn.edu.ar.negocioImp.EspecialidadNegocioImp;
import frgp.utn.edu.ar.negocioImp.MedicoNegocioImp;
import frgp.utn.edu.ar.negocioImp.PacienteNegocioImp;
import frgp.utn.edu.ar.negocioImp.TurnoNegocio;
import frgp.utn.edu.ar.negocioImp.UsuarioNegocioImp;

public class main {
/*
	private final static String MENSAJE_AGREGADO = "AGREGADO CORRECTAMENTE";
	private final static String MENSAJE_YA_EXISTE = "YA EXISTE EN LA BASE DE DATOS";
	private final static String MENSAJE_MODIFICADO = "MODIFICADO CORRECTAMENTE";
	private final static String MENSAJE_MODIFICADO_ERROR = "NO SE PUDO MODIFICAR";
	private final static String MENSAJE_BORRADO = "BORRADO CORRECTAMENTE";
	private final static String MENSAJE_BORRADO_ERROR = "NO SE PUDO BORRAR";
	private final static String MENSAJE_LISTADO_UN_USUARIO= "LA INFORMACIÓN ES: ";
	private final static String MENSAJE_LISTADO_TODOS_LOS_USUARIOS = "LA INFORMACIÓN ES: ";
	private final static String MENSAJE_LISTADO_UN_ESPECIALIDAD= "LA INFORMACIÓN ES: ";
	private final static String MENSAJE_LISTADO_TODAS_LAS_ESPECIALIDADES = "LA INFORMACIÓN ES: ";
	
	public static void main(String[] args) {
			System.out.println("-------------Especialidades-----------------");
			alEspecialidad();
			System.out.println("-------------Usuarios-----------------");
			abmlUsuarios();
			System.out.println("-------------Medicos-----------------");
		    abmlMedicos();
		    System.out.println("-------------Pacientes-----------------");
			abmlPacientes();
			System.out.println("-------------Turnos-----------------");
			abmlTurnos();
		}
	     
	public static void abmlUsuarios() {
		 boolean estado = false;
			
			// Se inicializan las variables junto con los beans
			ApplicationContext appContext = new ClassPathXmlApplicationContext("frgp/utn/edu/ar/resources/Beans.xml");
			UsuarioNegocioImp usuarioNegocio = (UsuarioNegocioImp) appContext.getBean("beanUsuarioNegocio");
			Usuario usuario1 = (Usuario) appContext.getBean("beanUsuario");
			Usuario usuario2 = (Usuario) appContext.getBean("beanUsuario");
			Usuario usuario3 = (Usuario) appContext.getBean("beanUsuario");
			Usuario usuarioModificado = (Usuario) appContext.getBean("beanUsuario");
			Usuario usuarioNuevo = (Usuario) appContext.getBean("beanUsuario");
			
		    usuario1.setUser("NombreUsuario1");
		    usuario1.setPass("123");
		    usuario1.setActivo(true);
		    
		    usuario2.setUser("NombreUsuario2");
		    usuario2.setPass("453");
		    usuario2.setActivo(true);
		    
		    usuario3.setUser("NombreUsuario3");
		    usuario3.setPass("678");
		    usuario3.setActivo(true);
		    	    
		    
		    //VERIFICAR QUE NO EXISTE EL USUARIO PARA AGREGARLO
		     estado = usuarioNegocio.Exist("NombreUsuario1");
		     if(estado == false)
		     {
		    	  	usuarioNegocio.Add(usuario1);
		 	      	System.out.println(MENSAJE_AGREGADO);	 
		     }
		     else
		    		System.out.println(MENSAJE_YA_EXISTE);
		
		     
		     //VERIFICAR QUE NO EXISTE EL USUARIO PARA AGREGARLO
			  estado = usuarioNegocio.Exist("NombreUsuario2");
			  if(estado == false)
			  {
			      //No existe entonces lo agrego
			      estado= usuarioNegocio.Add(usuario2);
			      if(estado)
			    	  System.out.println(MENSAJE_AGREGADO);
			     
			  }
			  else
		    		System.out.println(MENSAJE_YA_EXISTE);
			  
			  estado = usuarioNegocio.Exist("NombreUsuario3");
			     if(estado == false)
			     {
			    	  	usuarioNegocio.Add(usuario3);
			 	      	System.out.println(MENSAJE_AGREGADO);	 
			     }
			     else
			    		System.out.println(MENSAJE_YA_EXISTE);
		 
			  
		    //MODIFICAR dato de NombreUsuario1 - cambio de contraseña
		    
			usuarioModificado.setId(1);//Cargo el ID
		    usuarioModificado.setUser("NombreUsuario1");
		    usuarioModificado.setPass("Test"); //Cambio el dato
		    usuarioModificado.setActivo(true);
		    
		     estado = usuarioNegocio.Update(usuarioModificado);
		   
		     if(estado)
			    	System.out.println(MENSAJE_MODIFICADO);
			    else
			    	System.out.println(MENSAJE_MODIFICADO_ERROR);
		     

		     //LEE el dato de la bd: NombreUsuario1 modificado
		    
		     usuarioNuevo = usuarioNegocio.ReadOne("NombreUsuario1");
		     System.out.println(MENSAJE_LISTADO_UN_USUARIO+ usuarioNuevo);
		    
		     
		     //BORRA NombreUsuario2
		     
		     estado = usuarioNegocio.Delete(usuario2);
		     if(estado)	 
			    	System.out.println(MENSAJE_BORRADO);
			 else
				 	System.out.println(MENSAJE_BORRADO_ERROR);
		     
		     //LEE TODOS:
		     List<Usuario> usuarios = usuarioNegocio.ReadAll();
		     for (Usuario usuario : usuarios) {
		    	if(usuario.isActivo())
				System.out.println(MENSAJE_LISTADO_TODOS_LOS_USUARIOS+usuario.toString());
				
		     }
	 }
	
	public static void abmlMedicos() {

		boolean estado = false;

	    // Inicialización del contexto de la aplicación y beans
	    ApplicationContext appContext = new ClassPathXmlApplicationContext("frgp/utn/edu/ar/resources/Beans.xml");
	    MedicoNegocioImp medicoNegocio = (MedicoNegocioImp) appContext.getBean("beanMedicoNegocio");
	    Medico medico1 = (Medico) appContext.getBean("beanMedico");
	    Medico medico2 = (Medico) appContext.getBean("beanMedico");
	    Medico medico1Nuevo = (Medico) appContext.getBean("beanMedico");	    
	    UsuarioNegocioImp usuarioNegocio = (UsuarioNegocioImp) appContext.getBean("beanUsuarioNegocio");
	    Usuario usuario1 = usuarioNegocio.ReadOne("NombreUsuario1");
	    Usuario usuario3 = usuarioNegocio.ReadOne("NombreUsuario3");
	    
	    //Especialidad especialidadC = (Especialidad) appContext.getBean("beanEspecialidad");
		Especialidad especialidadN = (Especialidad) appContext.getBean("beanEspecialidad");
		Especialidad especialidadP = (Especialidad) appContext.getBean("beanEspecialidad");
		EspecialidadNegocioImp especialidadNegocio = (EspecialidadNegocioImp) appContext.getBean("beanEspecialidadNegocio");
		
		especialidadP = especialidadNegocio.ReadOne(3);
		especialidadN = especialidadNegocio.ReadOne(2);
	    
	    // Configurar los datos del médico1
	    medico1.setLegajo(111);
	    medico1.setNombre("NombreMedico1");
	    medico1.setApellido("ApellidoMedico1");
	    medico1.setSexo('M');
	    medico1.setFechaNacimiento(Date.valueOf("1925-03-01"));
	    medico1.setDireccion("Dirección del médico");
	    medico1.setLocalidad("Localidad del médico");
	    medico1.setCorreoElectronico("correo@ejemplo.com");
	    medico1.setTelefono("123456789");
	    medico1.setActivo(true);
	    medico1.setEspecialidad(especialidadP);
	    medico1.setDiasDeTrabajo(new HashSet<>(Arrays.asList("Lunes", "Miércoles", "Viernes")));
	    medico1.setHorariosDeTrabajo(new HashSet<>(Arrays.asList("08:00-12:00", "14:00-18:00")));

	    medico1.setUsuario(usuario1);
	    
	    
	    medico2.setLegajo(222);
	    medico2.setNombre("NombreMedico2");
	    medico2.setApellido("ApellidoMedico2");
	    medico2.setSexo('M');
	    medico2.setFechaNacimiento(Date.valueOf("1953-10-10"));
	    medico2.setDireccion("Dirección del médico2");
	    medico2.setLocalidad("Localidad del médico2");
	    medico2.setCorreoElectronico("cocoroco@ejemplo.com");
	    medico2.setTelefono("123456789");
	    medico2.setActivo(true);
	    medico2.setEspecialidad(especialidadN);
	    medico2.setDiasDeTrabajo(new HashSet<>(Arrays.asList("Lunes", "Miércoles", "Viernes")));
	    medico2.setHorariosDeTrabajo(new HashSet<>(Arrays.asList("08:00-12:00", "14:00-18:00")));
	    medico2.setUsuario(usuario3);

	    // Verificar si el médico ya existe
	    estado = medicoNegocio.Exist(medico1.getLegajo());
	    if (!estado) {
	        medicoNegocio.Add(medico1); // Agregar el médico si no existe
	        System.out.println("Médico agregado correctamente");
	    } else {
	        System.out.println("El médico ya existe");
	    }
	    	    
	    estado = medicoNegocio.Exist(medico2.getLegajo());
	    if (!estado) {
	        medicoNegocio.Add(medico2); // Agregar el médico si no existe
	        System.out.println("Médico agregado correctamente");
	    } else {
	        System.out.println("El médico ya existe");
	    }
	    
	    
	     //Modificar el médico1
	    medico1Nuevo.setId(1);
	    medico1Nuevo.setCorreoElectronico("nuevisimo_correo@ejemplo.com");
	    medico1Nuevo.setLegajo(111);
	    medico1Nuevo.setNombre("NombreMedico1");
	    medico1Nuevo.setApellido("ApellidoMedico1");
	    medico1Nuevo.setSexo('M');
	    medico1Nuevo.setFechaNacimiento(Date.valueOf("1925-03-01"));
	    medico1Nuevo.setDireccion("Dirección del médico");
	    medico1Nuevo.setLocalidad("Localidad del médico");
	    medico1Nuevo.setTelefono("123456789");
	    medico1Nuevo.setActivo(true);
	    medico1Nuevo.setDiasDeTrabajo(new HashSet<>(Arrays.asList("Lunes", "Miércoles", "Viernes")));
	    medico1Nuevo.setHorariosDeTrabajo(new HashSet<>(Arrays.asList("08:00-12:00", "14:00-18:00")));
	    medico1Nuevo.setUsuario(usuario1);
	    medico1Nuevo.setEspecialidad(especialidadP);

	    // Actualizar el médico
	    estado = medicoNegocio.Update(medico1Nuevo);
	    if (estado) {
	        System.out.println("Médico modificado correctamente");
	    } else {
	        System.out.println("Error al modificar el médico");
	    }
			    
	    //Elimino un medico:
	     estado = medicoNegocio.Delete(medico2);
	     if(estado)	 
		    	System.out.println(MENSAJE_BORRADO);
		 else
			 	System.out.println(MENSAJE_BORRADO_ERROR);
	    
		//leo 1
	    System.out.println("LEO SOLO UN MEDICO");
	    Medico medicoLeido = (Medico) appContext.getBean("beanMedico");	    
		medicoLeido = medicoNegocio.ReadOne(111);
		if (medicoLeido != null) {
		    System.out.println("Médico encontrado:");
		    System.out.println(medicoLeido.toString());
		} else {
		    System.out.println("No se encontró el médico");
		}
		
		//Leo Todos
		 List<Medico> medicos = medicoNegocio.ReadAll();
	     for (Medico medico : medicos) {
	    	 if(medico.isActivo())
			System.out.println(MENSAJE_LISTADO_TODOS_LOS_USUARIOS+ medico.toString());
			
	     }
	 }	     
	
	public static void alEspecialidad() {
		boolean estado = false;

		// Se inicializan las variables junto con los beans
		ApplicationContext appContext = new ClassPathXmlApplicationContext("frgp/utn/edu/ar/resources/Beans.xml");
		EspecialidadNegocioImp especialidadNegocio = (EspecialidadNegocioImp) appContext.getBean("beanEspecialidadNegocio");
		Especialidad especialidadC = (Especialidad) appContext.getBean("beanEspecialidad");
		Especialidad especialidadN = (Especialidad) appContext.getBean("beanEspecialidad");
		Especialidad especialidadP = (Especialidad) appContext.getBean("beanEspecialidad");

		especialidadC.setNombre("Cardiología");
		especialidadC.setActivo(true);
		// Verificación y agregado de especialidades
		estado = especialidadNegocio.ExistByName(especialidadC.getNombre());
		if (!estado) {
		    especialidadNegocio.Add(especialidadC);
		    System.out.println(MENSAJE_AGREGADO);	 
		} else {
		    System.out.println(MENSAJE_YA_EXISTE);
		}

		especialidadN.setNombre("Neurología");
		especialidadN.setActivo(true);
		estado = especialidadNegocio.ExistByName(especialidadN.getNombre());
		if (!estado) {
		    especialidadNegocio.Add(especialidadN);
		    System.out.println(MENSAJE_AGREGADO);	 
		} else {
		    System.out.println(MENSAJE_YA_EXISTE);
		}
		
		especialidadP.setNombre("Pediatría");
		especialidadP.setActivo(true);
		estado = especialidadNegocio.ExistByName(especialidadP.getNombre());
		if (!estado) {
		    especialidadNegocio.Add(especialidadP);
		    System.out.println(MENSAJE_AGREGADO);	 
		} else {
		    System.out.println(MENSAJE_YA_EXISTE);
		}

		// Listar todas las especialidades
		List<Especialidad> especialidades = especialidadNegocio.ReadAll();
		for (Especialidad especialidad : especialidades) {
		    System.out.println(MENSAJE_LISTADO_TODAS_LAS_ESPECIALIDADES + especialidad.toString());
		}

	 }	     
	
	public static void abmlPacientes() {
		boolean estado = false;
		
		ApplicationContext appContext = new ClassPathXmlApplicationContext("frgp/utn/edu/ar/resources/Beans.xml");
		PacienteNegocioImp pacienteNegocio = (PacienteNegocioImp) appContext.getBean("beanPacienteNegocio");
		Paciente paciente1 = (Paciente) appContext.getBean("beanPaciente");
		Paciente paciente2 = (Paciente) appContext.getBean("beanPaciente");
		Paciente paciente3 = (Paciente) appContext.getBean("beanPaciente");
		Paciente pacienteModificado = (Paciente) appContext.getBean("beanPaciente");
		
		paciente1.setNombre("Pepe");
		paciente1.setApellido("Hongo");
		paciente1.setDNI(1236);
		paciente1.setActivo(true);
		paciente1.setCorreoElectronico("hongopepe@gmail.com");
		paciente1.setDireccion("Avenida siempre hongo 123");
		paciente1.setFechaNacimiento(Date.valueOf("1925-03-01"));
		paciente1.setLocalidad("Hongolandia");
		paciente1.setProvincia("Pepenova");
		paciente1.setSexo('M');
		paciente1.setTelefono("43232423");
	    
		paciente2.setNombre("Pepe2");
		paciente2.setApellido("Hongo2");
		paciente2.setDNI(222);
		paciente2.setActivo(true);
		paciente2.setCorreoElectronico("hongopepe2@gmail.com");
		paciente2.setDireccion("Avenida siempre hongo 123");
		paciente2.setFechaNacimiento(Date.valueOf("1955-03-01"));
		paciente2.setLocalidad("Hongolandia");
		paciente2.setProvincia("Pepenova");
		paciente2.setSexo('M');
		paciente2.setTelefono("43232423");
		
		paciente3.setNombre("Pepe3");
		paciente3.setApellido("Hongo3");
		paciente3.setDNI(3333);
		paciente3.setActivo(true);
		paciente3.setCorreoElectronico("hongopepe3@gmail.com");
		paciente3.setDireccion("Avenida siempre hongo 123");
		paciente3.setFechaNacimiento(Date.valueOf("1943-03-01"));
		paciente3.setLocalidad("Hongolandia");
		paciente3.setProvincia("Pepenova");
		paciente3.setSexo('M');
		paciente3.setTelefono("43232423");
		
		
		//ALTA
		System.out.println("ALTAAAAAA");
	     estado = pacienteNegocio.Exist(paciente1.getDNI());
	     if(estado == false)
	     {
	    	  pacienteNegocio.Add(paciente1);
	 	      System.out.println(MENSAJE_AGREGADO);	 
	     }
		  
	     estado = pacienteNegocio.Exist(paciente2.getDNI());
	     if(estado == false)
	     {
	    	  pacienteNegocio.Add(paciente2);
	 	      System.out.println(MENSAJE_AGREGADO);	 
	     }
	     
	     estado = pacienteNegocio.Exist(paciente3.getDNI());
	     if(estado == false)
	     {
	    	  pacienteNegocio.Add(paciente3);
	 	      System.out.println(MENSAJE_AGREGADO);	 
	     }
	     
	     //Modificacion
	     pacienteModificado.setId(3);
	     pacienteModificado.setNombre("Pepe3 Mod");
	     pacienteModificado.setApellido("Hongo3 Mod");
	     pacienteModificado.setDNI(3333);
	     pacienteModificado.setActivo(true);
	     pacienteModificado.setCorreoElectronico("hongopepe3Mod@gmail.com");
	     pacienteModificado.setDireccion("Avenida siempre hongo 123");
	     pacienteModificado.setFechaNacimiento(Date.valueOf("1943-03-01"));
	     pacienteModificado.setLocalidad("Hongolandia Mod");
	     pacienteModificado.setProvincia("Pepenova");
	     pacienteModificado.setSexo('M');
	     pacienteModificado.setTelefono("43232423");
		 
		 estado = pacienteNegocio.Update(pacienteModificado);
		   
	     if(estado)
		    	System.out.println(MENSAJE_MODIFICADO);
		    else
		    	System.out.println(MENSAJE_MODIFICADO_ERROR); 
	     
	     //Elimino paciente 2
	     estado = pacienteNegocio.Delete(paciente2);
	     if(estado)	 
		    	System.out.println(MENSAJE_BORRADO);
		 else
			 	System.out.println(MENSAJE_BORRADO_ERROR);
	     
	     //LEER UN SOLO PACIENTE
	     System.out.println("LEO UN SOLO PACIENTE");
	     Paciente pacienteLeido = (Paciente) appContext.getBean("beanPaciente");
	     pacienteLeido = pacienteNegocio.ReadOne(1236);
	     
	     if (pacienteLeido != null) {
			    System.out.println("Paciente encontrado:");
			    System.out.println(pacienteLeido.toString());
			} else {
			    System.out.println("No se encontró el paciente");
			}
	     
	     //LEE TODOS:
	     List<Paciente> pacientes = pacienteNegocio.ReadAll();
	     for (Paciente paciente : pacientes) {
	    	 if(paciente.isActivo())
			System.out.println(MENSAJE_LISTADO_TODOS_LOS_USUARIOS+paciente.toString());
			
		}
	}
	
	public static void abmlTurnos() {
		boolean estado = false;
		
		/*Medico, Paciente, fecha, hora, observacion, estado (pendiente, presente, ausente).*/
		
		// Se inicializan las variables junto con los beans
	/*
		ApplicationContext appContext = new ClassPathXmlApplicationContext("frgp/utn/edu/ar/resources/Beans.xml");
		//MEDICOS
		MedicoNegocioImp medicoNegocio = (MedicoNegocioImp) appContext.getBean("beanMedicoNegocio");
		Medico med1 = (Medico) appContext.getBean("beanMedico");
		med1 = medicoNegocio.ReadOne(111);
		
		PacienteNegocioImp pacienteNegocio = (PacienteNegocioImp) appContext.getBean("beanPacienteNegocio");
		
		Paciente pac1 = (Paciente) appContext.getBean("beanPaciente");
		pac1 = pacienteNegocio.ReadOne(1236);
		Paciente pac2 = (Paciente) appContext.getBean("beanPaciente");
		pac2 = pacienteNegocio.ReadOne(3333);
		
		TurnoNegocio turnoNegocio = (TurnoNegocio) appContext.getBean("beanTurnoNegocio");
		Turno turno1 = (Turno) appContext.getBean("beanTurno");
		Turno turno2 = (Turno) appContext.getBean("beanTurno");
		Turno turno3 = (Turno) appContext.getBean("beanTurno");
		Turno turnoMod = (Turno) appContext.getBean("beanTurno");
		Turno turnoBorrar = (Turno) appContext.getBean("beanTurno");
		
		LocalDate fechaLocalDate = LocalDate.of(2025, 1, 1);
		Date fechaT1 = Date.valueOf(fechaLocalDate);
		
		LocalDate fechaLocalDate2 = LocalDate.now();
		Date fechaT2 = Date.valueOf(fechaLocalDate2);
		
		LocalDate fechaLocalDate3 = LocalDate.of(2024,7,3);
		Date fechaT3 = Date.valueOf(fechaLocalDate3);

		LocalTime horaLocalTime = LocalTime.now();
		Time horaT1 = Time.valueOf(horaLocalTime);
		
		LocalTime horaLocalTime2 = LocalTime.of(12, 00, 00);
		Time horaT2 = Time.valueOf(horaLocalTime2);
		
		LocalTime horaLocalTime3 = LocalTime.of(13, 30, 00);
		Time horaT3 = Time.valueOf(horaLocalTime3);
		
		turno1.setActivo(true);
		turno1.setEstado(Estado.presente);
		turno1.setFecha(fechaT1);
		turno1.setHora(horaT1);
		turno1.setMedico(med1);
		turno1.setPaciente(pac1);
		turno1.setObservacion("");
		
		 estado = turnoNegocio.Exist(1);
	     if(estado == false)
	     {
	    	  	turnoNegocio.Add(turno1);
	 	      	System.out.println(MENSAJE_AGREGADO);	 
	     }
	     else
	    		System.out.println(MENSAJE_YA_EXISTE);

	     
	     turno2.setActivo(true);
		 turno2.setEstado(Estado.ausente);
		 turno2.setFecha(fechaT2);
		 turno2.setHora(horaT2);
		 turno2.setMedico(med1);
		 turno2.setPaciente(pac1);
		 turno2.setObservacion("");
		 
	     estado = turnoNegocio.Exist(2);
	     if(estado == false)
	     {
	    	  	turnoNegocio.Add(turno2);
	 	      	System.out.println(MENSAJE_AGREGADO);	 
	     }
	     else
	    		System.out.println(MENSAJE_YA_EXISTE);
	     
	     turno3.setActivo(true);
		 turno3.setEstado(Estado.presente);
		 turno3.setFecha(fechaT3);
		 turno3.setHora(horaT3);
		 turno3.setMedico(med1);
		 turno3.setPaciente(pac2);
		 turno3.setObservacion("");
		 
	     estado = turnoNegocio.Exist(3);
	     if(estado == false)
	     {
	    	  	turnoNegocio.Add(turno3);
	 	      	System.out.println(MENSAJE_AGREGADO);	 
	     }
	     else
	    		System.out.println(MENSAJE_YA_EXISTE);
		
	     //MODIFICO TURNO 1 (TAMBIEN SIRVE PARA PROBAR EL METODO READONE
	     
	     turnoMod = turnoNegocio.ReadOne(1);
	     turnoMod.setObservacion("Este debería ser el turno modificado");
	     estado = turnoNegocio.Update(turnoMod);
		   
	     if(estado)
		    	System.out.println(MENSAJE_MODIFICADO);
		    else
		    	System.out.println(MENSAJE_MODIFICADO_ERROR);
	     
	     
	     
	     //BORRO TURNO 2
	     turnoBorrar = turnoNegocio.ReadOne(2);
	     estado = true;
	     estado = turnoNegocio.Delete(turnoBorrar);
	     if(estado)	 
		    	System.out.println(MENSAJE_BORRADO);
		 else
			 	System.out.println(MENSAJE_BORRADO_ERROR);
	     
	     //LEO TODOS
	     List<Turno> turnos = turnoNegocio.ReadAll();
	     for (Turno turno : turnos) {
	    	if(turno.isActivo())
			System.out.println(MENSAJE_LISTADO_TODOS_LOS_USUARIOS+ turno.toString());
	     }
	}
*/
}
