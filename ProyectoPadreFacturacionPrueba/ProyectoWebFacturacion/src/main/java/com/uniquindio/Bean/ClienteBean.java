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

import co.uniquindio.entidades.Cliente;

/**
 * @author jpgb9
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value="clienteBean")
@ApplicationScoped
public class ClienteBean implements Serializable {

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
	
	private Cliente cliente;
	
	private List<Cliente>clientes;
	
	private List<Cliente>clientesFilter;

	
	/**
	 * constructor del bean
	 */
	@PostConstruct
	private void init() {
		clientes= adminEJB.listarCliente();
		cliente = new Cliente();
		
	}
	
	/**
	 * medoto para limpiar campos
	 */
	public void limpiarCampos() {
		cedula ="";
		nombre="";
		apellido="";
		telefono="";
		correo="";
		direccion="";
		
	}
	
	/**
	 * metodo para agregar un cliente
	 * @return pagina de destino
	 */
	public String agregarCliente() {
		
				
				Cliente cliente =new Cliente();

				cliente.setApellido(apellido);
				cliente.setCedula(cedula);
				cliente.setCorreo(correo);
				cliente.setDireccion(direccion);
				cliente.setNombre(nombre);
				cliente.setTelefono(telefono);
		try {	
				adminEJB.agregarCliente(cliente);
				
				Util.mostrarMensaje("Registro Exitoso", "Registro Exitoso");
				
				limpiarCampos();
				
				clientes= adminEJB.listarCliente();
				
				return "/index.xhtml";

		} catch (ObjetoDuplicadoException e) {
			
			Util.mostrarMensaje("Cliente Repetido", "Cliente Repetido");
			e.printStackTrace();
			return "/index.xhtml";
		}	
		
	}
	
	/**
	 * metodo para actualizar un cliente 
	 * @return
	 */
	public String actualizarCliente() {
		
		try {
			adminEJB.actualizarCliente(cliente);
			
			Util.mostrarMensaje("Cambio Exitoso", "Cambio Exitoso");
			
			clientes= adminEJB.listarCliente();
			
			return "/seguro/gestionarCliente.xhtml";
			
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
	 * @return the clientes
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * @return the clientesFilter
	 */
	public List<Cliente> getClientesFilter() {
		return clientesFilter;
	}

	/**
	 * @param clientesFilter the clientesFilter to set
	 */
	public void setClientesFilter(List<Cliente> clientesFilter) {
		this.clientesFilter = clientesFilter;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
