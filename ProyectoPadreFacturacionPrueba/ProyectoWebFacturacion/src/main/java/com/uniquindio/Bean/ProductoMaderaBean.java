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

import com.uniquindio.ejb.NegocioEJB;
import com.uniquindio.exception.ObjetoDuplicadoException;
import com.uniquindio.exception.ObjetoNoExisteException;
import com.uniquindio.util.Util;

import co.uniquindio.entidades.Producto_Madera;
import co.uniquindio.entidades.Tipo_Madera;

/**
 * @author jpgb9
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value="productoMaderaBean")
@ViewScoped
public class ProductoMaderaBean implements Serializable {

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
	 * nombre del producto
	 */
	private String nombre;
	
	/**
	 * alto de la madera
	 */
	private Double alto;
	
	/**
	 * ancho de la madera
	 */
	private Double ancho;
	
	/**
	 * largo del producto
	 */
	private Double largo;
	
	/**
	 * precio del product
	 */
	private Double precio;
	
	private Double precioaux;
	/**
	 * pulgadas del producto
	 */
	private Double pulgadas;
	
	/**
	 * el tipo de madera al que pertenece el producto
	 */
	private Tipo_Madera tipo;
	
	private List<Tipo_Madera> tipos;
	
	private Producto_Madera producto;
	
	private List<Producto_Madera> productos;
	
	private List<Producto_Madera> productosFilter;
	
	@PostConstruct
	private void init() {
		productos=adminEJB.listarProducto();
		producto= new Producto_Madera();
		tipos=adminEJB.listarTiposMadera();
		precioaux=producto.getPrecio();
		
	}

	public void limpiarCampos() {
		alto =0.0;
		nombre="";
		ancho=0.0;
		largo=0.0;
		precio=0.0;
		pulgadas=0.0;
		precioaux=0.0;
		tipo=new Tipo_Madera();
		
	}
	
	/**
	 * metodo para agregar un producto
	 * @return
	 */
	public String agregarProducto() {
		
		Producto_Madera pro = new Producto_Madera();
		
		pro.setAlto(alto);
		pro.setAncho(ancho);
		pro.setLargo(largo);
		pro.setNombre(nombre);
		pro.setPrecio(precio);
		pro.setPulgadas(pulgadas);
		pro.setTipo_madera(tipo);
		
		try {
			adminEJB.agregarAgregarProductoMadera(pro);
			
			Util.mostrarMensaje("Registro Exitoso", "Registro Exitoso");

			limpiarCampos();
			
			productos=adminEJB.listarProducto();
			
			return "/index.xhtml";
			
		} catch (ObjetoDuplicadoException e) {
			Util.mostrarMensaje("Producto Repetido", "Producto Repetido");
			e.printStackTrace();
			return "/index.xhtml";		
			}
		
	}
	
	
	/**
	 * metodo para actualizar un producto
	 * @return
	 */
	public String actualizarProducto () {
		
		try {
			producto.setPrecio(precioaux);
			
			adminEJB.actualizarProductoMadera(producto);
			
			Util.mostrarMensaje("Cambio Exitoso", "Cambio Exitoso");
			
			limpiarCampos();
			productos=adminEJB.listarProducto();

			return "/seguro/gestionarProductos.xhtml";

			
		} catch (ObjetoNoExisteException e) {
			
			Util.mostrarMensaje("Algo fallo", "Algo fallo");
			e.printStackTrace();
			return "/index.xhtml";	
			
		}
		
	}
	
	/**
	 * metodo para eliminar un producto
	 * @return
	 */
	public String eliminarProducto( ) {
		
		try {
			
			adminEJB.eliminarProductoMadera(producto.getId_producto());
			
			Util.mostrarMensaje("El producto fue eliminado", "El producto fue eliminado");
			
			productos=adminEJB.listarProducto();

			return "/seguro/gestionarProductos.xhtml";
			
			
		} catch (ObjetoNoExisteException e) {
			
			Util.mostrarMensaje("Algo fallo", "Algo fallo");
			e.printStackTrace();
			return "/index.xhtml";
			
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
	 * @return the alto
	 */
	public Double getAlto() {
		return alto;
	}

	/**
	 * @param alto the alto to set
	 */
	public void setAlto(Double alto) {
		this.alto = alto;
	}

	/**
	 * @return the ancho
	 */
	public Double getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the largo
	 */
	public Double getLargo() {
		return largo;
	}

	/**
	 * @param largo the largo to set
	 */
	public void setLargo(Double largo) {
		this.largo = largo;
	}

	/**
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	/**
	 * @return the pulgadas
	 */
	public Double getPulgadas() {
		return pulgadas;
	}

	/**
	 * @param pulgadas the pulgadas to set
	 */
	public void setPulgadas(Double pulgadas) {
		this.pulgadas = pulgadas;
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
	 * @return the producto
	 */
	public Producto_Madera getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto_Madera producto) {
		this.producto = producto;
	}

	/**
	 * @return the productos
	 */
	public List<Producto_Madera> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<Producto_Madera> productos) {
		this.productos = productos;
	}

	/**
	 * @return the productosFilter
	 */
	public List<Producto_Madera> getProductosFilter() {
		return productosFilter;
	}

	/**
	 * @param productosFilter the productosFilter to set
	 */
	public void setProductosFilter(List<Producto_Madera> productosFilter) {
		this.productosFilter = productosFilter;
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
	 * @return the precioaux
	 */
	public Double getPrecioaux() {
		return precioaux;
	}

	/**
	 * @param precioaux the precioaux to set
	 */
	public void setPrecioaux(Double precioaux) {
		this.precioaux = precioaux;
	}
	
	
	

}
