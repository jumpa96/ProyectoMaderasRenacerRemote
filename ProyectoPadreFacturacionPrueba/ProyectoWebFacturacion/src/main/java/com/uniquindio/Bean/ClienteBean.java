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
@Named(value = "clienteBean")
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

	private String nombreaux;

	/**
	 * apellido de una persona
	 */
	private String apellido;
	private String apellidoaux;

	/**
	 * telefono de una persona
	 */
	@NotNull
	@NotEmpty
	private String telefono;
	private String telefonoaux;

	/**
	 * correo de una persona
	 */
	private String correo;
	private String correoaux;

	/**
	 * direccion de una persona
	 */
	private String direccion;
	private String direccionaux;

	private Cliente cliente;

	private List<Cliente> clientes;

	private List<Cliente> clientesFilter;

	/**
	 * constructor del bean
	 */
	@PostConstruct
	private void init() {
		clientes = adminEJB.listarCliente();
		cliente = new Cliente();

	}

	/**
	 * medoto para limpiar campos
	 */
	public void limpiarCampos() {
		cedula = "";
		nombre = "";
		apellido = "";
		telefono = "";
		correo = "";
		direccion = "";

		nombreaux = "";
		apellidoaux = "";
		telefonoaux = "";
		correoaux = "";
		direccionaux = "";
	}

	/**
	 * metodo para agregar un cliente
	 * 
	 * @return pagina de destino
	 */
	public String agregarCliente() {

		Cliente cliente = new Cliente();

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

			clientes = adminEJB.listarCliente();

			return "/index.xhtml";

		} catch (ObjetoDuplicadoException e) {

			Util.mostrarMensaje("La cedula del cliente ya se encuentra registrada",
					"La cedula del cliente ya se encuentra registrada");
			limpiarCampos();
			e.printStackTrace();
			return "/index.xhtml";
		}

	}

	/**
	 * metodo para actualizar un cliente
	 * 
	 * @return
	 */
	public String actualizarCliente() {

		try {

			if (cliente.getApellido() != null && apellidoaux.equals("")) {
				apellidoaux = cliente.getApellido();
			}

			if (cliente.getCorreo() != null && correoaux.equals("")) {

				correoaux = cliente.getCorreo();
			}

			if (cliente.getDireccion() != null && direccionaux.equals("")) {
				direccionaux = cliente.getDireccion();
			}

			if (cliente.getNombre() != null && nombreaux.equals("")) {

				nombreaux = cliente.getNombre();
			}

			if (cliente.getTelefono() != null && telefonoaux.equals("")) {

				telefonoaux = cliente.getTelefono();
			}

			cliente.setApellido(apellidoaux);
			cliente.setCorreo(correoaux);
			cliente.setDireccion(direccionaux);
			cliente.setNombre(nombreaux);
			cliente.setTelefono(telefonoaux);

			adminEJB.actualizarCliente(cliente);

			Util.mostrarMensaje("Cambio Exitoso", "Cambio Exitoso");

			clientes = adminEJB.listarCliente();

			limpiarCampos();

			return "/seguro/gestionarCliente.xhtml";

		} catch (ObjetoNoExisteException e) {
			Util.mostrarMensaje("Algo fallo", "Algo fallo");
			limpiarCampos();
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

	/**
	 * @return the nombreaux
	 */
	public String getNombreaux() {
		return nombreaux;
	}

	/**
	 * @return the apellidoaux
	 */
	public String getApellidoaux() {
		return apellidoaux;
	}

	/**
	 * @return the telefonoaux
	 */
	public String getTelefonoaux() {
		return telefonoaux;
	}

	/**
	 * @return the correoaux
	 */
	public String getCorreoaux() {
		return correoaux;
	}

	/**
	 * @return the direccionaux
	 */
	public String getDireccionaux() {
		return direccionaux;
	}

	/**
	 * @param nombreaux the nombreaux to set
	 */
	public void setNombreaux(String nombreaux) {
		this.nombreaux = nombreaux;
	}

	/**
	 * @param apellidoaux the apellidoaux to set
	 */
	public void setApellidoaux(String apellidoaux) {
		this.apellidoaux = apellidoaux;
	}

	/**
	 * @param telefonoaux the telefonoaux to set
	 */
	public void setTelefonoaux(String telefonoaux) {
		this.telefonoaux = telefonoaux;
	}

	/**
	 * @param correoaux the correoaux to set
	 */
	public void setCorreoaux(String correoaux) {
		this.correoaux = correoaux;
	}

	/**
	 * @param direccionaux the direccionaux to set
	 */
	public void setDireccionaux(String direccionaux) {
		this.direccionaux = direccionaux;
	}

}
