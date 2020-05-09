/**
 * 
 */
package com.uniquindio.Bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.uniquindio.ejb.NegocioEJB;
import com.uniquindio.exception.ObjetoDuplicadoException;
import com.uniquindio.exception.ObjetoNoExisteException;
import com.uniquindio.util.Util;

import co.uniquindio.entidades.Proveedor;

/**
 * @author jpgb9
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value="proveedorBean")
@ApplicationScoped
public class ProveedorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * realiza la conexion con la capa de negocio
	 */
	@EJB
	private NegocioEJB adminEJB;

	/**
	 * cedula de la persona
	 */
	@NotNull
	@NotEmpty
	private String cedula;

	/**
	 * nombre de una persona
	 */
	@NotNull
	@NotEmpty
	private String nombre;

	/**
	 * apellido de una persona
	 */
	private String apellido;

	/**
	 * telefono de una persona
	 */
	@NotNull
	@NotEmpty
	private String telefono;

	/**
	 * correo de una persona
	 */
	private String correo;

	/**
	 * direccion de una persona
	 */
	private String direccion;
	
	private Proveedor proveedor;
	
	private List<Proveedor>proveedores;
	
	private List<Proveedor>proveedorFilter;
	
	@PostConstruct
	private void init() {
		proveedores= adminEJB.listarProveedores();
		proveedor=new Proveedor();
		
	}
	
	public void limpiarCampos() {
		cedula ="";
		nombre="";
		apellido="";
		telefono="";
		correo="";
		direccion="";
		
	}
	
	/**
	 * metodo para agregar un proveedor
	 * @return pagina de destino
	 */
	public String agregarProveedor() {
		
				
				Proveedor prov =new Proveedor();

				prov.setApellido(apellido);
				prov.setCedula(cedula);
				prov.setCorreo(correo);
				prov.setDireccion(direccion);
				prov.setNombre(nombre);
				prov.setTelefono(telefono);
		try {	
				adminEJB.agregarProveedor(prov);
				
				Util.mostrarMensaje("Registro Exitoso", "Registro Exitoso");
				
				limpiarCampos();
				
				proveedores= adminEJB.listarProveedores();
				
				return "/index.xhtml";

		} catch (ObjetoDuplicadoException e) {
			
			Util.mostrarMensaje("Proveedor Repetido", "Proveedor Repetido");
			e.printStackTrace();
			return "/index.xhtml";
		}	
		
	}
	
	/**
	 * metodo para actualizar un cliente 
	 * @return
	 */
	public String actualizarProveedor() {
		
		try {
			adminEJB.actualizarproveedor(proveedor);
			
			Util.mostrarMensaje("Cambio Exitoso", "Cambio Exitoso");
			
			proveedores= adminEJB.listarProveedores();
			
			return "/seguro/gestionarProveedores.xhtml";
			
		} catch (ObjetoNoExisteException e) {
			Util.mostrarMensaje("Algo fallo", "Algo fallo");
			e.printStackTrace();
			return "/index.xhtml";
		}
		
		
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
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

	/**
	 * @return the proveedor
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}

	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * @return the proveedores
	 */
	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	/**
	 * @param proveedores the proveedores to set
	 */
	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	/**
	 * @return the proveedorFilter
	 */
	public List<Proveedor> getProveedorFilter() {
		return proveedorFilter;
	}

	/**
	 * @param proveedorFilter the proveedorFilter to set
	 */
	public void setProveedorFilter(List<Proveedor> proveedorFilter) {
		this.proveedorFilter = proveedorFilter;
	}
	
	
	

}
