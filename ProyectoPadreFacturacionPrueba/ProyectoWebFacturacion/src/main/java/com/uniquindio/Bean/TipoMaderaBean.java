/**
 * 
 */
package com.uniquindio.Bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.uniquindio.ejb.NegocioEJB;
import com.uniquindio.exception.ObjetoDuplicadoException;
import com.uniquindio.exception.ObjetoNoExisteException;
import com.uniquindio.util.Util;

import co.uniquindio.entidades.Tipo_Madera;

/**
 * @author jpgb9
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "tipoMaderaBean")
@ViewScoped
public class TipoMaderaBean implements Serializable {

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
	 * nombre de una persona
	 */
	@NotNull
	@NotEmpty
	private String nombre;

	private String nombreaux;

	private Tipo_Madera tipo;

	private List<Tipo_Madera> tipos;

	private List<Tipo_Madera> tiposFilter;

	@PostConstruct
	private void init() {
		tipos = adminEJB.listarTiposMadera();

	}

	public void limpiarCampos() {

		nombre = "";

	}

	/**
	 * metodo para agregar un tipo de madera a la BD
	 * 
	 * @return
	 */
	public String agregarTipoMadera() {
		
		try {
			
			if (adminEJB.buscarTipoMaderaNombre(nombre).isEmpty()) {
				Tipo_Madera tipo2 = new Tipo_Madera();
				tipo2.setCantidad_pulgadas(0.0);
				tipo2.setNombre(nombre);

				
					adminEJB.agregarAgregarTipoMadera(tipo2);
					Util.mostrarMensaje("Registro Exitoso", "Registro Exitoso");

					limpiarCampos();
					tipos = adminEJB.listarTiposMadera();

					return "/index.xhtml";

				
			}else {
				Util.mostrarMensaje("El tipo de madera ya se encuentra registrado", "El tipo de madera ya se encuentra registrado");
				return "";
			}
		}catch (ObjetoDuplicadoException e) {

			Util.mostrarMensaje("Tipo Madera Repetido", "Tipo Madera Repetido");
			e.printStackTrace();
			return "/index.xhtml";
		}
		
		
	}

	public String actualizarTipoMadera() {

		if (nombreaux.equals("")) {
			Util.mostrarMensaje("El nombre no puede estar vacio", "El nombre no puede estar vacio");
			nombreaux="";
			tipos = adminEJB.listarTiposMadera();
			return "";
		
		}else {
			
			try {
				tipo.setNombre(nombreaux);
				adminEJB.actualizarTipoMadera(tipo);

				Util.mostrarMensaje("Cambio Exitoso", "Cambio Exitoso");

				tipos = adminEJB.listarTiposMadera();
				nombreaux="";

				return "/seguro/gestionarTipoMadera.xhtml";

			} catch (ObjetoNoExisteException e) {

				Util.mostrarMensaje("Algo fallo", "Algo fallo");
				nombreaux="";
				tipos = adminEJB.listarTiposMadera();
				e.printStackTrace();
				return "/index.xhtml";
			}

		}

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
	 * @return the tipo
	 */
	public Tipo_Madera getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Tipo_Madera tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the tipos
	 */
	public List<Tipo_Madera> getTipos() {
		return tipos;
	}

	/**
	 * @param tipos the tipos to set
	 */
	public void setTipos(List<Tipo_Madera> tipos) {
		this.tipos = tipos;
	}

	/**
	 * @return the tiposFilter
	 */
	public List<Tipo_Madera> getTiposFilter() {
		return tiposFilter;
	}

	/**
	 * @param tiposFilter the tiposFilter to set
	 */
	public void setTiposFilter(List<Tipo_Madera> tiposFilter) {
		this.tiposFilter = tiposFilter;
	}

	/**
	 * @return the nombreaux
	 */
	public String getNombreaux() {
		return nombreaux;
	}

	/**
	 * @param nombreaux the nombreaux to set
	 */
	public void setNombreaux(String nombreaux) {
		this.nombreaux = nombreaux;
	}

}
