/**
 * 
 */
package com.uniquindio.Bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.uniquindio.ejb.NegocioEJB;
import com.uniquindio.exception.ObjetoDuplicadoException;
import com.uniquindio.util.Util;

import co.uniquindio.entidades.Entrada_Madera;
import co.uniquindio.entidades.Proveedor;
import co.uniquindio.entidades.Tipo_Madera;

/**
 * @author jpgb9
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "entradaBean")
@ViewScoped
public class EntradaBean implements Serializable {

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
	 * idetificacion del salvocionduco
	 */
	private String salvoconducto;

	/**
	 * cantidad de pulgadas de la madera
	 */
	private Double cantidad_pulgadas;

	/**
	 * nombre cientifico de la madera
	 */
	private String nombre_cientifico;

	/**
	 * de donde viene la madera
	 */
	private String procedencia;

	/**
	 * corporacion que emite el salvo conducto
	 */
	private String corporacion;

	/**
	 * fecha de entrada
	 */
	private Date fecha;

	/**
	 * proveedor que realizo la entrega
	 */
	private Proveedor proveedor;

	/**
	 * tipo de madera a la que pertenece la entrada
	 */
	private Tipo_Madera tipo_madera;

	private List<Tipo_Madera> tipos;
	
	private Entrada_Madera entrada;

	private List<Entrada_Madera> entradas;

	private List<Entrada_Madera> entradasFilter;
	
	private List<Proveedor>proveedores;

	@PostConstruct
	private void init() {
		entradas = adminEJB.listarEntradas();
		tipos = adminEJB.listarTiposMadera();
		proveedores=adminEJB.listarProveedores();
	}

	public void limpiarCampos() {
		cantidad_pulgadas = 0.0;
		nombre_cientifico = "";
		salvoconducto = "";
		procedencia = "";
		corporacion = "";
		tipo_madera=new Tipo_Madera();
		proveedor = new Proveedor();
		fecha=null;

	}

	/**
	 * metodo para agregar entradas de madera
	 * 
	 * @return
	 */
	public String agregarEntrada() {
		
		int id = Integer.parseInt(salvoconducto);
		Entrada_Madera ent = new Entrada_Madera();
		
		ent.setCantidad_pulgadas(cantidad_pulgadas);
		ent.setCorporacion(corporacion);
		ent.setFecha(fecha);
		ent.setNombre_cientifico(nombre_cientifico);
		ent.setProcedencia(procedencia);
		ent.setProveedor(proveedor);
		ent.setSalvoconducto(id);
		ent.setTipo_madera(tipo_madera);
		
		try {

			adminEJB.agregarEntradaMadera(ent);
			
			adminEJB.actualizarPulgadas(tipo_madera);
			
			Util.mostrarMensaje("Registro Exitoso", "Registro Exitoso");

			limpiarCampos();

			entradas = adminEJB.listarEntradas();

			return "/index.xhtml";

		} catch (ObjetoDuplicadoException e) {
			Util.mostrarMensaje("Entrada Repetido", "Entrada Repetido");
			e.printStackTrace();
			return "/index.xhtml";
		}

	}

	public String eliminarEntrada (){
		
		try {
			Tipo_Madera tipo = entrada.getTipo_madera();
			
			adminEJB.eliminarEntradaMadera(entrada.getSalvoconducto());
			
			Util.mostrarMensaje("La entrada fue eliminada", "La entrada fue eliminada");
			
			adminEJB.actualizarPulgadas(tipo);
			
			entradas = adminEJB.listarEntradas();
			
			return "/seguro/gestionarEntrada.xhtml";
			
			
		} catch (Exception e) {
			Util.mostrarMensaje("Algo fallo", "Algo fallo");
			e.printStackTrace();
			return "/index.xhtml";		
		}
		
	}

	
	/**
	 * @return the salvoconducto
	 */
	public String getSalvoconducto() {
		return salvoconducto;
	}

	/**
	 * @param salvoconducto the salvoconducto to set
	 */
	public void setSalvoconducto(String salvoconducto) {
		this.salvoconducto = salvoconducto;
	}

	/**
	 * @return the cantidad_pulgadas
	 */
	public Double getCantidad_pulgadas() {
		return cantidad_pulgadas;
	}

	/**
	 * @param cantidad_pulgadas the cantidad_pulgadas to set
	 */
	public void setCantidad_pulgadas(Double cantidad_pulgadas) {
		this.cantidad_pulgadas = cantidad_pulgadas;
	}

	/**
	 * @return the nombre_cientifico
	 */
	public String getNombre_cientifico() {
		return nombre_cientifico;
	}

	/**
	 * @param nombre_cientifico the nombre_cientifico to set
	 */
	public void setNombre_cientifico(String nombre_cientifico) {
		this.nombre_cientifico = nombre_cientifico;
	}

	/**
	 * @return the procedencia
	 */
	public String getProcedencia() {
		return procedencia;
	}

	/**
	 * @param procedencia the procedencia to set
	 */
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	/**
	 * @return the corporacion
	 */
	public String getCorporacion() {
		return corporacion;
	}

	/**
	 * @param corporacion the corporacion to set
	 */
	public void setCorporacion(String corporacion) {
		this.corporacion = corporacion;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * @return the tipo_madera
	 */
	public Tipo_Madera getTipo_madera() {
		return tipo_madera;
	}

	/**
	 * @param tipo_madera the tipo_madera to set
	 */
	public void setTipo_madera(Tipo_Madera tipo_madera) {
		this.tipo_madera = tipo_madera;
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
	 * @return the entrada
	 */
	public Entrada_Madera getEntrada() {
		return entrada;
	}

	/**
	 * @param entrada the entrada to set
	 */
	public void setEntrada(Entrada_Madera entrada) {
		this.entrada = entrada;
	}

	/**
	 * @return the entradas
	 */
	public List<Entrada_Madera> getEntradas() {
		return entradas;
	}

	/**
	 * @param entradas the entradas to set
	 */
	public void setEntradas(List<Entrada_Madera> entradas) {
		this.entradas = entradas;
	}

	/**
	 * @return the entradasFilter
	 */
	public List<Entrada_Madera> getEntradasFilter() {
		return entradasFilter;
	}

	/**
	 * @param entradasFilter the entradasFilter to set
	 */
	public void setEntradasFilter(List<Entrada_Madera> entradasFilter) {
		this.entradasFilter = entradasFilter;
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

}
