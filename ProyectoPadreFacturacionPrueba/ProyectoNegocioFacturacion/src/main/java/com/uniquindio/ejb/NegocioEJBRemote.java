package com.uniquindio.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.uniquindio.exception.ObjetoDuplicadoException;
import com.uniquindio.exception.ObjetoNoExisteException;

import co.uniquindio.entidades.Cliente;
import co.uniquindio.entidades.Entrada_Madera;
import co.uniquindio.entidades.Factura;
import co.uniquindio.entidades.Producto_Factura;
import co.uniquindio.entidades.Producto_Madera;
import co.uniquindio.entidades.Proveedor;
import co.uniquindio.entidades.Tipo_Madera;
import co.uniquindio.entidades.Usuario;

@Remote
public interface NegocioEJBRemote {
	
	String JNDI="java:global/ProyectoEAR/ProyectoNegocioFacturacion/NegocioEJB!com.uniquindio.ejb.NegocioEJB";
	
	
	/**
	 * metedoto para agregar un cliente
	 * 
	 * @param cliente
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	Cliente agregarCliente(Cliente cliente) throws ObjetoDuplicadoException;
	
	/**
	 * Actualiza datos del cliente
	 * 
	 * @param cliente actualizar
	 * @return
	 * @throws ObjetoNoExisteException si la persona a actualizar no existe
	 */
	Cliente actualizarCliente(Cliente cliente) throws ObjetoNoExisteException;
	
	/**
	 * lista todos los Clientes
	 * 
	 * @return lista de clientes
	 */
	List<Cliente> listarCliente();
	
	/**
	 * metodo para agregar una entrada de madera
	 * 
	 * @param entrada entrada
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	Entrada_Madera agregarEntradaMadera(Entrada_Madera entrada) throws ObjetoDuplicadoException;
	
	/**
	 * lista todas las entradas de madera
	 * 
	 * @return
	 */
	List<Entrada_Madera> listarEntradas();
	
	/**
	 * lista entradas de madera por tipo
	 * 
	 * @param id_madera
	 * @return
	 */
	List<Entrada_Madera> listarEntradasTipo(int id_madera);
	
	
	/**
	 * metodo para modificar una entrada
	 * 
	 * @param entrada
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	Entrada_Madera actualizarEntradaMadera(Entrada_Madera entrada) throws ObjetoNoExisteException;
	
	/**
	 * elimina una entrada de madera
	 * 
	 * @param idEntrada
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	boolean eliminarEntradaMadera(int idEntrada) throws ObjetoNoExisteException;
	
	/**
	 * agrega facturas a la base de datos
	 * 
	 * @param factura
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Factura agregarAgregarFactura(Factura factura) throws ObjetoDuplicadoException;
	
	/**
	 * metodo para anular una facutura
	 * 
	 * @param entrada
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public Factura anularFactura(Factura factura) throws ObjetoNoExisteException;
	
	/**
	 * lista todas las Facturas de madera
	 * 
	 * @return
	 */
	public List<Factura> listarFacturas();
	
	/**
	 * lista todas las facturas por cliente
	 * @param cedula
	 * @return
	 */
	public List<Factura> listarFacturasCliente(int cedula);
	
	/**
	 * agrega un producto a una factura
	 * @param producto
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Producto_Factura agregarAgregarProductoFacura(Producto_Factura producto) throws ObjetoDuplicadoException;
	
	/**
	 * metodo para actualizar los productos de una factura
	 * @param producto
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public Producto_Factura actualizarProductoFactura(Producto_Factura producto) throws ObjetoNoExisteException;
	
	public List<Producto_Factura> listarProductosFactura(int idFactura);
	
	/**
	 * elimina un producto de una factura
	 * @param idproducto
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public boolean eliminarProductoFactura(int idproducto) throws ObjetoNoExisteException;
	
	/**
	 * lista todos los productos de un tipo de madera
	 * @param idFactura
	 * @return
	 */
	public List<Producto_Madera> listarProductosMadera(int idMadera);
	
	/**
	 * elimina un producto de una factura
	 * @param idproducto
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public boolean eliminarProductoMadera(int idproducto) throws ObjetoNoExisteException;
	
	/**
	 * agrega un producto a un tipo de madera
	 * @param producto
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Producto_Madera agregarAgregarProductoMadera(Producto_Madera producto) throws ObjetoDuplicadoException;
	
	/**
	 * actualiza un producto de un tipo de madera
	 * @param producto
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public Producto_Madera actualizarProductoMadera(Producto_Madera producto) throws ObjetoNoExisteException;
	
	/**
	 * agrega un proveedor a la base de datos
	 * @param proveedor
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Proveedor agregarProveedor(Proveedor proveedor) throws ObjetoDuplicadoException;
	
	/**
	 * Actualiza datos del proveedor
	 * 
	 * @param proveedor actualizar
	 * @return
	 * @throws ObjetoNoExisteException si la persona a actualizar no existe
	 */
	public Proveedor actualizarproveedor(Proveedor proveedor) throws ObjetoNoExisteException;
	
	/**
	 * lista todos los proveedor
	 * 
	 * @return lista de proveedor
	 */
	public List<Proveedor> listarProveedores();
	
	/**
	 * agrega un tipo de madera
	 * @param madera
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Tipo_Madera agregarAgregarTipoMadera(Tipo_Madera madera) throws ObjetoDuplicadoException;
	
	/**
	 * metodo para actualizar los tipos de madera
	 * @param madera
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public Tipo_Madera actualizarTipoMadera(Tipo_Madera madera) throws ObjetoNoExisteException;
	
	/**
	 * lista todos los tipos de madera
	 * @return
	 */
	public List<Tipo_Madera> listarTiposMadera();
	
	/**
	 * elimina un tipo de madera
	 * @param idproducto
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public boolean eliminarTipoMadera(int idmadera) throws ObjetoNoExisteException;
	
	/**
	 * metedoto para agregar un vendedor
	 * 
	 * @param vendedor
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Usuario agregarUsuario(Usuario vendedor) throws ObjetoDuplicadoException;
	
	/**
	 * Actualiza datos del vendedor
	 * 
	 * @param vendedor actualizar
	 * @return
	 * @throws ObjetoNoExisteException si la persona a actualizar no existe
	 */
	public Usuario actualizarUsuario(Usuario vendedor) throws ObjetoNoExisteException;
	
	/**
	 * lista todos los vendedores
	 * 
	 * @return lista de vendedores
	 */
	public List<Usuario> listarUsuario();
	
	/**
	 * inicia session en la aplicacion
	 * @param correo
	 * @param c
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public Usuario iniciarSesion(String correo, int c) throws ObjetoNoExisteException;
	
	/**
	 * Busca Un usuario Por email
	 * 
	 * @return Usuario
	 */
	public Usuario buscarUsuarioEmail(String email) throws	ObjetoNoExisteException;
	
	/**
	 * calcula el valor total de la factura
	 * @param cedula
	 * @return
	 */
	public Double calcularValorFactura(int idFactura);
	
	/**
	 * lista todos los productos de un tipo de madera
	 * @param idFactura
	 * @return
	 */
	public List<Producto_Madera> listarProducto();
	
	/**
	 * metodo para actualizar pulgadas de los tipos de madera
	 * @param tipo_madera
	 */
	public void actualizarPulgadas(Tipo_Madera tipo_madera);
	
	/**
	 * busca tipo de madera por id
	 * @param id
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public Tipo_Madera buscarTipoMadera(int id) throws	ObjetoNoExisteException;
	
	/**
	 * calcula las ventas por usuario
	 * @param cedula
	 * @return
	 */
	public Double calcularVentasUsuario(String cedula);
	
	/**
	 * calcula el valor total ventas
	 * @param cedula
	 * @return
	 */
	public Double calcularTotalVentas();
	
	/**
	 * Busca un cliente por la cedula
	 * @param cedula
	 * @return
	 */
	public Cliente buscarCliente(String cedula);
	
}
