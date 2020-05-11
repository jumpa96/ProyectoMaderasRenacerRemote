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
import javax.faces.annotation.ManagedProperty;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.uniquindio.ejb.NegocioEJB;
import com.uniquindio.exception.ObjetoDuplicadoException;
import com.uniquindio.exception.ObjetoNoExisteException;
import com.uniquindio.util.Util;

import co.uniquindio.entidades.Cliente;
import co.uniquindio.entidades.Factura;
import co.uniquindio.entidades.Producto_Factura;
import co.uniquindio.entidades.Producto_Madera;
import co.uniquindio.entidades.Usuario;

/**
 * @author jpgb9
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "facturaBean")
@ViewScoped
public class FacturaBean implements Serializable {

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
	 * cedula del cliente
	 */
	private String cedula;

	private String cantidad;

	private String cantidadaux;
	
	private Double valor;

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

	private boolean autenticado = false;

	private boolean fac = false;

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

	/**
	 * fecha de la factura
	 */
	private Date fecha;

	/**
	 * Estado de la factura
	 */
	private boolean estado;

	/**
	 * id de la factura
	 */
	private int id_factura;

	/**
	 * valor total de la factura
	 */
	private double valor_total;

	/**
	 * cliente de la factura
	 */
	private Cliente cliente;

	/**
	 * vendedor que elaboro la factura
	 */
	@Inject
	@ManagedProperty(value = "#{seguridadBean.usuario}")
	private Usuario vendedor;

	private Producto_Factura productoF;

	private Producto_Madera producto;

	private List<Producto_Madera> productos;

	private List<Producto_Madera> productosFilter;

	private List<Producto_Factura> productosFacturados;
	
	private List<Producto_Factura> productosFactura;

	private List<Producto_Factura> productosFacturadosFilter;
	
	private List<Factura> facturas;
	
	private List<Factura>facturasFilter;
	
	private Factura factura;

	/**
	 * factura agregada
	 */
	private Factura facturaaux;
	
	
	@PostConstruct
	private void init() {
		productos = adminEJB.listarProducto();
		producto = new Producto_Madera();
		productoF = new Producto_Factura();
		facturas = adminEJB.listarFacturas();
		
	}

	/**
	 * Metodo para crear una factura
	 * 
	 * @return
	 */
	public void agregarFactura() {

		cliente = adminEJB.buscarCliente(cedula);

		if (cliente != null) {

			Factura fact = new Factura();
			fact.setCliente(cliente);
			fact.setEstado(true);
			fact.setFecha(new Date());
			fact.setValor_total(0);
			fact.setVendedor(vendedor);

			nombre = cliente.getNombre();
			apellido = cliente.getApellido();
			correo = cliente.getCorreo();
			direccion = cliente.getDireccion();
			telefono = cliente.getTelefono();

			try {

				facturaaux = adminEJB.agregarAgregarFactura(fact);
				productosFacturados = adminEJB.listarProductosFactura(facturaaux.getId_factura());
				fac = true;
				facturas = adminEJB.listarFacturas();
			} catch (ObjetoDuplicadoException e) {
				Util.mostrarMensaje("Algo fallo", "Algo fallo");
				e.printStackTrace();
			}
		} else {

			Util.mostrarMensaje("Debe agregar el cliente", "Debe agregar el cliente");
			autenticado = true;
			fac = false;

		}

	}

	/**
	 * metodo para agregar un cliente
	 * 
	 * @return pagina de destino
	 */
	public void agregarCliente() {

		Cliente cliente = new Cliente();

		cliente.setApellido(apellido);
		cliente.setCedula(cedula);
		cliente.setCorreo(correo);
		cliente.setDireccion(direccion);
		cliente.setNombre(nombre);
		cliente.setTelefono(telefono);
		try {
			adminEJB.agregarCliente(cliente);
			autenticado = false;
			Util.mostrarMensaje("Registro Exitoso", "Registro Exitoso");

			agregarFactura();
			fac = true;

		} catch (ObjetoDuplicadoException e) {

			Util.mostrarMensaje("Cliente Repetido", "Cliente Repetido");
			e.printStackTrace();
		}

	}

	/**
	 * metodo para actualizar un producto de un factura
	 */
	public void actualizarProductoFactura() {

		try {
			
			int can  = Integer.parseInt(cantidadaux);
			productoF.setCantidad(can);
			productoF.setValor_neto(can*productoF.getProducto().getPrecio());

			facturaaux=adminEJB.actualizarProductoFactura(productoF).getFactura();
			valor=adminEJB.calcularValorFactura(facturaaux.getId_factura());

			Util.mostrarMensaje("Cambio Exitoso", "Cambio Exitoso");

			cantidadaux = "";
			productosFacturados = adminEJB.listarProductosFactura(facturaaux.getId_factura());

		} catch (ObjetoNoExisteException e) {

			Util.mostrarMensaje("Algo fallo", "Algo fallo");
			e.printStackTrace();

		}

	}

	/**
	 * metodo para eliminar unproducto de una factura
	 */
	public void eliminarProductoFactura() {

		try {

			adminEJB.eliminarProductoFactura(productoF.getId_producto_factura());

			Util.mostrarMensaje("El producto fue eliminado", "El producto fue eliminado");

			productosFacturados = adminEJB.listarProductosFactura(facturaaux.getId_factura());
			
			if (productosFacturados.size()>0) {
				
				valor = adminEJB.calcularValorFactura(productoF.getFactura().getId_factura());
			}else {
				valor = 0.0;
			}


		} catch (ObjetoNoExisteException e) {

			Util.mostrarMensaje("Algo fallo", "Algo fallo");
			e.printStackTrace();

		}

	}

	public void agregarProductoFactura() {
		
		Producto_Factura pro = new Producto_Factura(); 
		int can = Integer.parseInt(cantidad);
		pro.setCantidad(can);
		pro.setFactura(facturaaux);
		pro.setProducto(producto);
		pro.setValor_neto(can*producto.getPrecio());
		
		
		try {
			facturaaux=adminEJB.agregarAgregarProductoFacura(pro).getFactura();
			cantidad="";
			valor=adminEJB.calcularValorFactura(pro.getFactura().getId_factura());
			productosFacturados=adminEJB.listarProductosFactura(facturaaux.getId_factura());
			
		} catch (ObjetoDuplicadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public List<Producto_Factura> listarProductosfactura(){
		productosFactura = adminEJB.listarProductosFactura(factura.getId_factura());
		return productosFactura;
		
	}
	
	public String anularFactura() {
		
		try {
			factura.setEstado(false);
			adminEJB.anularFactura(factura);
			facturas = adminEJB.listarFacturas();
			return "/seguro/gestionarFacturas";
			
			
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * @return the id_factura
	 */
	public int getId_factura() {
		return id_factura;
	}

	/**
	 * @param id_factura the id_factura to set
	 */
	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	/**
	 * @return the valor_total
	 */
	public double getValor_total() {
		return valor_total;
	}

	/**
	 * @param valor_total the valor_total to set
	 */
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
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
	 * @return the vendedor
	 */
	public Usuario getVendedor() {
		return vendedor;
	}

	/**
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
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
	 * @return the autenticado
	 */
	public boolean isAutenticado() {
		return autenticado;
	}

	/**
	 * @param autenticado the autenticado to set
	 */
	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	/**
	 * @return the facturaaux
	 */
	public Factura getFacturaaux() {
		return facturaaux;
	}

	/**
	 * @param facturaaux the facturaaux to set
	 */
	public void setFacturaaux(Factura facturaaux) {
		this.facturaaux = facturaaux;
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
	 * @return the productosFacturados
	 */
	public List<Producto_Factura> getProductosFacturados() {
		return productosFacturados;
	}

	/**
	 * @param productosFacturados the productosFacturados to set
	 */
	public void setProductosFacturados(List<Producto_Factura> productosFacturados) {
		this.productosFacturados = productosFacturados;
	}

	/**
	 * @return the productosFacturadosFilter
	 */
	public List<Producto_Factura> getProductosFacturadosFilter() {
		return productosFacturadosFilter;
	}

	/**
	 * @param productosFacturadosFilter the productosFacturadosFilter to set
	 */
	public void setProductosFacturadosFilter(List<Producto_Factura> productosFacturadosFilter) {
		this.productosFacturadosFilter = productosFacturadosFilter;
	}

	/**
	 * @return the productoF
	 */
	public Producto_Factura getProductoF() {
		return productoF;
	}

	/**
	 * @param productoF the productoF to set
	 */
	public void setProductoF(Producto_Factura productoF) {
		this.productoF = productoF;
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
	 * @return the fac
	 */
	public boolean isFac() {
		return fac;
	}

	/**
	 * @param fac the fac to set
	 */
	public void setFac(boolean fac) {
		this.fac = fac;
	}

	/**
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * @return the cantidadaux
	 */
	public String getCantidadaux() {
		return cantidadaux;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @param cantidadaux the cantidadaux to set
	 */
	public void setCantidadaux(String cantidadaux) {
		this.cantidadaux = cantidadaux;
	}

	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * @return the facturas
	 */
	public List<Factura> getFacturas() {
		return facturas;
	}

	/**
	 * @return the facturasFilter
	 */
	public List<Factura> getFacturasFilter() {
		return facturasFilter;
	}

	/**
	 * @return the factura
	 */
	public Factura getFactura() {
		return factura;
	}

	/**
	 * @param facturas the facturas to set
	 */
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	/**
	 * @param facturasFilter the facturasFilter to set
	 */
	public void setFacturasFilter(List<Factura> facturasFilter) {
		this.facturasFilter = facturasFilter;
	}

	/**
	 * @param factura the factura to set
	 */
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}

	/**
	 * @return the productosFactura
	 */
	public List<Producto_Factura> getProductosFactura() {
		return productosFactura;
	}

	/**
	 * @param productosFactura the productosFactura to set
	 */
	public void setProductosFactura(List<Producto_Factura> productosFactura) {
		this.productosFactura = productosFactura;
	}

	

}
