package fp.grados.utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.AlumnoImpl2;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Beca;
import fp.grados.tipos.BecaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.CentroImpl2;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.DepartamentoImpl2;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.Grado;
import fp.grados.tipos.GradoImpl;
import fp.grados.tipos.GradoImpl2;
import fp.grados.tipos.Nota;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.ProfesorImpl2;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TipoBeca;
import fp.grados.tipos.TipoEspacio;
import fp.grados.tipos.Tutoria;

public class Grados {
		
		private static Map<String, Asignatura> asignaturaPorCodigo = new HashMap<String, Asignatura>();
		
		private static Set<Beca> becas = new HashSet<Beca>();
		private static Integer[] numBecasPorTipo = { 0, 0, 0 };

		private static Set<Profesor> profesores = new HashSet<Profesor>();
		private static Boolean setProfesor = true;
		private static Set<Alumno> alumnos = new HashSet<Alumno>();
		private static Boolean setJava8 = true;
		
		private static Set<Centro> centros = new HashSet<>();
		
		private static Set<Departamento> departamentos = new HashSet<Departamento>();
		
		private static SortedSet<Espacio> espacios = new TreeSet<>();
		
		private static Set<Grado> grados = new HashSet<>();
		
		
		public static void setUsarJava8(Boolean b) {
			setJava8 = b;
		}
		
		public static <T> List<T> leeFichero(String nombreFichero, Function<String,T> funcion_deString_aT){
			List<T> res = null;
			try {
				res = Files.lines(Paths.get(nombreFichero))
						 .map(funcion_deString_aT)
						 .collect(Collectors.toList());
			} catch (IOException e) {
				System.out.println("Error en lectura del fichero: "+nombreFichero);
			}
			
			return res;
		}
		//Alumno
		public static Alumno createAlumno(String dni, String nombre, String apellidos,LocalDate fecha, String email){
			Alumno res = null; 
			if(!setJava8){
				res = new AlumnoImpl(dni, nombre, apellidos, fecha, email);
			}else{
				res = new AlumnoImpl2(dni, nombre, apellidos, fecha, email);
			}
			alumnos.add(res);
			return res;
		}
		
		public static Alumno createAlumno(Alumno a){
			Alumno res = createAlumno(a.getDNI(), a.getNombre(), a.getApellidos(), a.getFechaNacimiento(), a.getEmail());
			copiaAsignaturas(a,res);
			alumnos.add(res);
			return res;
		}
		
		private static void copiaAsignaturas(Alumno a, Alumno res){
			for(Asignatura e: a.getAsignaturas()){
				res.matriculaAsignatura(e);
			}
			
			for(Nota n : a.getExpediente().getNotas()){
				res.evaluaAlumno(n.getAsignatura(), n.getCursoAcademico(), n.getConvocatoria(), n.getValor(), n.getMencionHonor());
			}
		}
		
		public static Alumno createAlumno(String s){
			Alumno res = null;
				if(!setJava8){
					res = new AlumnoImpl(s);
				}else{
					res = new AlumnoImpl2(s);
				}
			alumnos.add(res);
			return res;
		}
		
		public static List<Alumno> createAlumnos(String nombreFichero){
			List<Alumno> res = leeFichero(nombreFichero, s ->createAlumno(s));
			return res;
		}
		
		public static Integer getNumAlumnosCreados(){
			return alumnos.size();
		}
		
		public static Set<Alumno> getAlumnosCreados(){
			return new HashSet<>(alumnos);
		}
		
		//Asignatura
		public static Asignatura createAsignatura(String nombre, String codigo, Double creditos, TipoAsignatura tipo, Integer curso, Departamento d){
			Asignatura res = new AsignaturaImpl( nombre,  codigo,  creditos,  tipo,  curso,  d);
			asignaturaPorCodigo.put(res.getCodigo(), res);
			return res;
		}
		
		public static Asignatura createAsignatura(String s){
			Asignatura res = new AsignaturaImpl(s);
			asignaturaPorCodigo.put(res.getCodigo(), res);
			return res;
		}
		
		public static List<Asignatura> createAsignaturas(String nombreFichero){
			List<Asignatura> res = leeFichero(nombreFichero, s ->createAsignatura(s));
			return res;
		}
		
		public static Integer getNumAsignaturasCreadas(){
			return asignaturaPorCodigo.keySet().size();
		}
		
		public static Set<Asignatura> getAsignaturasCreadas(){
			return new HashSet<>(asignaturaPorCodigo.values());
		}
		
		public static Set<String> getCodigosAsignaturasCreadas(){
			return new HashSet<>(asignaturaPorCodigo.keySet());
		}
		
		public static Asignatura getAsignaturaCreada(String codigo){
			return asignaturaPorCodigo.get(codigo);
		}
		
		//Beca		
		public static Integer getNumBecasCreadas() {
			return becas.size();
			}
			public static Set<Beca> getBecasCreadas() {
			return new HashSet<Beca>(becas);
			}
			public static Integer getNumBecasTipo(TipoBeca tipo) {
			return numBecasPorTipo[tipo.ordinal()];
			}
			private static void actualizaPoblacionales(Beca b) {
			becas.add(b);
			numBecasPorTipo[b.getTipo().ordinal()]++;
			}
			public static Beca createBeca(String codigo, Double cuantiaTotal,
			Integer duracion, TipoBeca tipo) {
			Beca res = new BecaImpl(codigo, cuantiaTotal, duracion, tipo);
			actualizaPoblacionales(res);
			return res;
			}
			public static Beca createBeca(String codigo, TipoBeca tipo) {
			Beca res = new BecaImpl(codigo, tipo);
			actualizaPoblacionales(res);
			return res;
			}
			public static Beca createBeca(Beca beca) {
			Beca res = new BecaImpl(beca.getCodigo(), beca.getCuantiaTotal(),
			beca.getDuracion(), beca.getTipo());
			actualizaPoblacionales(res);
			return res;
			}
			public static Beca createBeca(String beca) {
			Beca res = new BecaImpl(beca);
			actualizaPoblacionales(res);
			return res;
			}
			public static List<Beca> createBecas(String nombreFichero) {
			List<Beca> res = leeFichero(nombreFichero, s -> createBeca(s));
			return res;
			}
		
		//Centro
		
			public static Integer getNumCentrosCreados(){
				return centros.size();
			}
			
			public static Set<Centro> getCentrosCreados(){
				return new HashSet<Centro>(centros);
			}
			

			public static Integer getMaxPlantas(){
				Integer max= null;
				for(Centro c: centros){
					if(max==null || c.getNumeroPlantas()>max){
						max= c.getNumeroPlantas();
					}
				}
				
				return max;
			}
			
			public static Integer getMaxSotanos(){
			Integer max= null;
			for(Centro c: centros){
				if(max==null || c.getNumeroSotanos()>max){
					max= c.getNumeroSotanos();
				}
			}
			return max;
			}
			
			
			public static Double getMediaPlantas(){
				Double res = 0.0;
				Integer cont=0;
				for(Centro c: centros){
						res= res+ c.getNumeroPlantas();
						cont++;
					}
				 if(cont.equals(0)){
					 return 0.0;
				 }
				 
				 else{
					return (res/cont);
				}
			}
			
			public static Double getMediaSotanos(){
				Double res = 0.0;
				Integer cont=0;
				for(Centro c: centros){
						res= res+ c.getNumeroSotanos();
						cont++;
					}
				 if(cont.equals(0)){
					 return 0.0;
				 }
				 
				 else{
					return (res/cont);
				}
			}
			
			public static Centro createCentro(String nombre,String direccion, Integer plantas,Integer sotanos){
				if(setJava8){
					Centro res= new CentroImpl2(nombre,direccion,plantas,sotanos);
					centros.add(res);
					return res;
				}
					
				else{
				Centro res= new CentroImpl(nombre,direccion,plantas,sotanos);
				centros.add(res);
				return res;
				}
			}
			
			public static Centro createCentro(Centro c){
				if(setJava8){
					Centro res= new CentroImpl2(c.getNombre(),c.getDireccion(),c.getNumeroPlantas(),c.getNumeroSotanos());
					for(Espacio e: c.getEspacios()){
						res.nuevoEspacio(e);
					}
					centros.add(res);
					return res;
				}
				
				else{
					Centro res= new CentroImpl(c.getNombre(),c.getDireccion(),c.getNumeroPlantas(),c.getNumeroSotanos());
					for(Espacio e: c.getEspacios()){
						res.nuevoEspacio(e);
					}
					centros.add(res);
					return res;
				}
			}
		
		//Departamento 
		public static Integer getNumDepartamentosCreados() {
			return departamentos.size();
		}
			
		public static Set<Departamento> getDepartamentosCreados() {
			return new HashSet<Departamento>(departamentos);
			
		}
		
		private static void actualizaPoblacionales(Departamento d){
			departamentos.add(d);
			
		}
			
		public static Departamento createDepartamento(String nombre) {
			Departamento res = null;
			if(!setJava8){
				res = new DepartamentoImpl(nombre);
			}else{
				res = new DepartamentoImpl2(nombre);
			}
			actualizaPoblacionales(res);
			return res;
		}

		
		//Despachos
		
		public static Despacho createDespacho(String nombre, Integer capacidad, 
				Integer planta){
			Despacho res = new DespachoImpl(nombre, capacidad, planta);
			espacios.add(res);
			return res;
		}
		
		public static Despacho createDespacho(Despacho d){
			Despacho res = new DespachoImpl(d.getNombre(), d.getCapacidad(), d.getPlanta());
			espacios.add(res);
			return res;
		}
		
		public static Despacho createDespacho(String cadena){
			Despacho res = new DespachoImpl(cadena);
			espacios.add(res);
			return res;
		}
		
		public static List<Despacho> createDespachos(String fileName){
			List<Despacho> res= Grados.leeFichero(fileName, s->createDespacho(s));
			return res;
		}
		
		
		//Espacio
		public static Integer getNumEspaciosCreados(){
			return espacios.size();
		}
		public static SortedSet<Espacio> getEspaciosCreados(){
			return new TreeSet<Espacio>(espacios);
		}
		

		public static Integer getPlantaMayorEspacio(){
			Integer max= null;
			for(Espacio e: espacios){
				if(max==null || e.getPlanta()>max){
					max= e.getPlanta();
				}
			}
			return max;
			
		}

		public static Integer getPlantaMenorEspacio(){
		Integer min= null;
		for(Espacio e: espacios){
			if(min==null || e.getPlanta()<min){
				min= e.getPlanta();
			}
		}
		return min;
		}
		
		public static Espacio createEspacio(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta){
			Espacio res= new EspacioImpl(tipo,nombre,capacidad,planta);
			espacios.add(res);
			return res;
		}
		
		public static Espacio createEspacio(Espacio e){
			Espacio res= new EspacioImpl(e.getTipo(),e.getNombre(),e.getCapacidad(),e.getPlanta());
			espacios.add(res);
			return res;
		}
		
		public static Espacio createEspacio(String s){
			Espacio res= new EspacioImpl(s);
			espacios.add(res);
			return res;
		}
		
		public static List<Espacio> createEspacios(String fileName){
			List<Espacio> res= Grados.leeFichero(fileName, s-> createEspacio(s));
			return res;
		}
		
		//Grado
		public static Integer getNumGradosCreados(){
			return grados.size();
		}
		public static Set<Grado> getGradosCreados(){
			return new HashSet<Grado>(grados);
		}
		
		public static Double getMediaAsignaturasGrados(){
			Double res = 0.0;
			Integer cont=0;
			for(Grado g: grados){
				res= res+ g.getAsignaturasObligatorias().size() + g.getAsignaturasOptativas().size();
				cont++;
			}
			 if(cont.equals(0)){
				 return 0.0;
			 }
			 
			 else{
				return (res/cont);
			}
		}
		
		public static Double getMediaAsignaturasObligatoriasGrados(){
			Double res = 0.0;
			Integer cont=0;
			for(Grado g: grados){
				res= res+ g.getAsignaturasObligatorias().size();
				cont++;
			}
			 if(cont.equals(0)){
				 return 0.0;
			 }
			 
			 else{
				return (res/cont);
			}
		}
		
		public static Double getMediaAsignaturasOptativasGrados(){
			Double res = 0.0;
			Integer cont=0;
			for(Grado g: grados){
				res= res+ g.getAsignaturasOptativas().size();
				cont++;
			}
			 if(cont.equals(0)){
				 return 0.0;
			 }
			 
			 else{
				return (res/cont);
			}
		}
		
		public static Grado createGrado(String nombre,Set<Asignatura> asignaturasObligatorias,
				Set<Asignatura> asignaturasOptativas,Double numeroMinimoCreditosOptativas){
			if(setJava8){
				Grado res= new GradoImpl2(nombre,asignaturasObligatorias,asignaturasOptativas,numeroMinimoCreditosOptativas);
				grados.add(res);
				return res;
			}
			else{
				Grado res= new GradoImpl(nombre,asignaturasObligatorias,asignaturasOptativas,numeroMinimoCreditosOptativas);
				grados.add(res);
				return res;
			}
		}
		
		//Profesor
		public static Integer getNumProfesoresCreados() {
			return profesores.size();
			}
			
		public static Set<Profesor> getProfesoresCreados() {
			return new HashSet<Profesor>(profesores);
			}
			
		public static void setUsarImplementacionMapProfesor(Boolean b) {
				setProfesor = b;
			}
			
		private static void actualizaPoblacionales(Profesor p){
			profesores.add(p);
			}
			
		public static Profesor createProfesor(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email,
			Categoria categoria, Departamento departamento) {
				Profesor res = null;
				if (setProfesor) {
					res = new ProfesorImpl2(dni, nombre, apellidos,
							fechaNacimiento, email, categoria, departamento);
				} else {
					res = new ProfesorImpl(dni, nombre, apellidos,
							fechaNacimiento, email, categoria, departamento);
				}
			actualizaPoblacionales(res);
			return res;
			}
			
		public static Profesor createProfesor(Profesor profesor) {
			Profesor res = createProfesor(profesor.getDNI(),
			profesor.getNombre(), profesor.getApellidos(),
			profesor.getFechaNacimiento(), profesor.getEmail(),
			profesor.getCategoria(), profesor.getDepartamento());
			
			copiaAsignaturasProfesor(res, profesor);
			copiaTutoriasProfesor(res, profesor);
			
			return res;
			}
			
		private static void copiaAsignaturasProfesor(Profesor res,
			Profesor profesor) {
				for (Asignatura a : profesor.getAsignaturas()) {
					res.imparteAsignatura(a, profesor.dedicacionAsignatura(a));
				}
			}
			
			
		private static void copiaTutoriasProfesor(Profesor res, Profesor profesor) {
					for (Tutoria t : profesor.getTutorias()) {
						res.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(), t.getDiaSemana());
					}
			}
		
}