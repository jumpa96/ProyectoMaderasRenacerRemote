package co.uniquindio.entidades;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * Entity implementation class for Entity: Persona
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name = Persona.LISTAR_TODOS, query = "select p from Persona p"),
	@NamedQuery(name = Persona.BUSCAR_POR_EMAIL, query = "select p from Persona p where p.correo=:email")
})
public class Persona implements Serializable {

	/**
	 * Lista todas las personas de la base de datos
	 */
	public static final String LISTAR_TODOS = "Lista todas las personas de la base de datos";

	/**
	 * Busca persona por email
	 */
	public static final String BUSCAR_POR_EMAIL = "Busca persona por email";
	
	/**
	 * cedula de la persona
	 */
	@Id
	@Column(name = "cedula", nullable = false)
	private String cedula;
	
	/**
	 * nombre de una persona
	 */
	@Column(name = "nombre", nullable = false, length=30)
	private String nombre;
	
	/**
	 * apellido de una persona
	 */
	@Column(name = "apellido", nullable = true, length=30)
	private String apellido;
	
	/**
	 * telefono de una persona
	 */
	@Column(name = "telefono", nullable = false)
	private String telefono;
	
	/**
	 * correo de una persona
	 */
	@Column(name = "correo", nullable = true, length=40)
	private String correo;
	
	/**
	 * direccion de una persona
	 */
	@Column(name = "direccion", nullable = true, length=40)
	private String direccion;
	
	private static final long serialVersionUID = 1L;

	/*
	 * constructor
	 */
	public Persona() {
		super();
	}   
	
	//setters and getters
	
	
	




	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}




	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}




	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}







	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}




	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}




	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}




	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		return true;
	}
	   
	
}
