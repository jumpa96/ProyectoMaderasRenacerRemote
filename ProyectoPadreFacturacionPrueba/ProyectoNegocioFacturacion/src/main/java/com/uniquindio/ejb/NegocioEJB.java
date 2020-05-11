package com.uniquindio.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.uniquindio.exception.ObjetoDuplicadoException;
import com.uniquindio.exception.ObjetoNoExisteException;

import co.uniquindio.entidades.Cliente;
import co.uniquindio.entidades.Entrada_Madera;
import co.uniquindio.entidades.Factura;
import co.uniquindio.entidades.Persona;
import co.uniquindio.entidades.Producto_Factura;
import co.uniquindio.entidades.Producto_Madera;
import co.uniquindio.entidades.Proveedor;
import co.uniquindio.entidades.Tipo_Madera;
import co.uniquindio.entidades.Usuario;


/**
 * Session Bean implementation class NegocioEJB
 */
@Stateless
@LocalBean
public class NegocioEJB implements NegocioEJBRemote {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public NegocioEJB() {
		
	}

	/**
	 * Metodo para buscar persona por email
	 * 
	 * @param email email a buscar
	 * @return true si lo encuentra false en caso contrario
	 */
	private boolean buscarPersonaPorEmail(String email) {
		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.BUSCAR_POR_EMAIL, Persona.class);
		query.setParameter("email", email);

		return query.getResultList().size() > 0;
	}

	
	/**
	 * metedoto para agregar un cliente
	 * 
	 * @param cliente
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Cliente agregarCliente(Cliente cliente) throws ObjetoDuplicadoException {
		
		if (entityManager.find(Persona.class, cliente.getCedula()) != null) {

			throw new ObjetoDuplicadoException("Esta cedula ya se encuentra registrada");

		} else if (buscarPersonaPorEmail(cliente.getCorreo())) {

			throw new ObjetoDuplicadoException("Este email ya se encientra registrado");

		}
		try {
			
			entityManager.persist(cliente);
			return cliente;
			
		} catch (Exception e) {
			return null;
		}
			

	}

	/**
	 * Actualiza datos del cliente
	 * 
	 * @param cliente actualizar
	 * @return
	 * @throws ObjetoNoExisteException si la persona a actualizar no existe
	 */
	public Cliente actualizarCliente(Cliente cliente) throws ObjetoNoExisteException {

		if (entityManager.find(Cliente.class, cliente.getCedula()) == null) {
			throw new ObjetoNoExisteException(" El cliente buscado no Existe !! ");
		}

		try {

			entityManager.merge(cliente);
			return cliente;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * lista todos los Clientes
	 * 
	 * @return lista de clientes
	 */
	public List<Cliente> listarCliente() {

		TypedQuery<Cliente> query = entityManager.createNamedQuery(Cliente.LISTAR_CLIENTES, Cliente.class);
		return query.getResultList();
	}

	/**
	 * metodo para agregar una entrada de madera
	 * 
	 * @param entrada entrada
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Entrada_Madera agregarEntradaMadera(Entrada_Madera entrada) throws ObjetoDuplicadoException {

		if (entityManager.find(Entrada_Madera.class, entrada.getSalvoconducto()) != null) {

			throw new ObjetoDuplicadoException("Este salvoconducto ya se encuentra registrado");
		}
		try {
			entityManager.persist(entrada);
			return entrada;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	/**
	 * lista todas las entradas de madera
	 * 
	 * @return
	 */
	public List<Entrada_Madera> listarEntradas() {

		TypedQuery<Entrada_Madera> query = entityManager.createNamedQuery(Entrada_Madera.LISTAR_ENTRADAS,
				Entrada_Madera.class);
		return query.getResultList();
	}

	/**
	 * lista entradas de madera por tipo
	 * 
	 * @param id_madera
	 * @return
	 */
	public List<Entrada_Madera> listarEntradasTipo(int id_madera) {

		TypedQuery<Entrada_Madera> query = entityManager.createNamedQuery(Entrada_Madera.LISTAR_ENTRADAS_TIPO,
				Entrada_Madera.class);
		query.setParameter("id_madera", id_madera);

		return query.getResultList();
	}

	/**
	 * metodo para modificar una entrada
	 * 
	 * @param entrada
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public Entrada_Madera actualizarEntradaMadera(Entrada_Madera entrada) throws ObjetoNoExisteException {

		if (entityManager.find(Entrada_Madera.class, entrada.getSalvoconducto()) == null) {
			throw new ObjetoNoExisteException(" El cliente buscado no Existe !! ");
		}

		try {

			entityManager.merge(entrada);
			return entrada;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * elimina una entrada de madera
	 * 
	 * @param idEntrada
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public boolean eliminarEntradaMadera(int idEntrada) throws ObjetoNoExisteException {
		Entrada_Madera entrada = entityManager.find(Entrada_Madera.class, idEntrada);

		if (entrada == null) {
			throw new ObjetoNoExisteException("La entrada a remover no existe en el sistema");
		}

		try {

			entityManager.remove(entrada);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * agrega facturas a la base de datos
	 * 
	 * @param factura
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Factura agregarAgregarFactura(Factura factura) throws ObjetoDuplicadoException {

		if (entityManager.find(Factura.class, factura.getId_factura()) != null) {

			throw new ObjetoDuplicadoException("Esta factura ya se encuentra registrada");
		}
		try {

			entityManager.persist(factura);

			return factura;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	/**
	 * metodo para anular una facutura
	 * 
	 * @param entrada
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public Factura anularFactura(Factura factura) throws ObjetoNoExisteException {

		if (entityManager.find(Factura.class, factura.getId_factura()) == null) {
			throw new ObjetoNoExisteException(" la factura buscada no existe buscado no Existe !! ");
		}

		try {

			entityManager.merge(factura);
			return factura;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * lista todas las Facturas de madera
	 * 
	 * @return
	 */
	public List<Factura> listarFacturas() {

		TypedQuery<Factura> query = entityManager.createNamedQuery(Factura.LISTAR_FACTURAS, Factura.class);
		return query.getResultList();
	}

	/**
	 * lista todas las facturas por cliente
	 * @param cedula
	 * @return
	 */
	public List<Factura> listarFacturasCliente(int cedula) {

		TypedQuery<Factura> query = entityManager.createNamedQuery(Factura.LISTAR_FACTURAS_CLIENTE,Factura.class);
		query.setParameter("cedula", cedula);

		return query.getResultList();
	}
	
	
	/**
	 * agrega un producto a una factura
	 * @param producto
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Producto_Factura agregarAgregarProductoFacura(Producto_Factura producto) throws ObjetoDuplicadoException {

		if (entityManager.find(Producto_Factura.class, producto.getId_producto_factura()) != null) {

			throw new ObjetoDuplicadoException("Este producto ya se encuentra registrado");
		}
		try {

			entityManager.persist(producto);
			calcularValorFactura(producto.getFactura().getId_factura());

			return producto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	/**
	 * metodo para actualizar los productos de una factura
	 * @param producto
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public Producto_Factura actualizarProductoFactura(Producto_Factura producto) throws ObjetoNoExisteException {

		if (entityManager.find(Producto_Factura.class, producto.getId_producto_factura()) == null) {
			throw new ObjetoNoExisteException(" El producto buscado no Existe !! ");
		}

		try {

			entityManager.merge(producto);
			
			calcularValorFactura(producto.getFactura().getId_factura());
			return producto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Producto_Factura> listarProductosFactura(int idFactura) {

		TypedQuery<Producto_Factura> query = entityManager.createNamedQuery(Producto_Factura.LISTAR_PRODUCTOS_FACTURA,Producto_Factura.class);
		query.setParameter("idFactura", idFactura);

		return query.getResultList();
	}
	
	/**
	 * elimina un producto de una factura
	 * @param idproducto
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public boolean eliminarProductoFactura(int idproducto) throws ObjetoNoExisteException {
		Producto_Factura producto = entityManager.find(Producto_Factura.class, idproducto);

		if (producto == null) {
			throw new ObjetoNoExisteException("El producto a remover no existe en el sistema");
		}

		try {

			entityManager.remove(producto);
			calcularValorFactura(producto.getFactura().getId_factura());
			
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	/**
	 * lista todos los productos de un tipo de madera
	 * @param idFactura
	 * @return
	 */
	public List<Producto_Madera> listarProductosMadera(int idMadera) {

		TypedQuery<Producto_Madera> query = entityManager.createNamedQuery(Producto_Madera.LISTAR_PRODUCTOS_MADERA,Producto_Madera.class);
		query.setParameter("idMadera", idMadera);

		return query.getResultList();
	}
	
	/**
	 * lista todos los productos de un tipo de madera
	 * @param idFactura
	 * @return
	 */
	public List<Producto_Madera> listarProducto() {

		TypedQuery<Producto_Madera> query = entityManager.createNamedQuery(Producto_Madera.LISTAR_PRODUCTOS,Producto_Madera.class);
		

		return query.getResultList();
	}
	
	
	/**
	 * elimina un producto de una factura
	 * @param idproducto
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public boolean eliminarProductoMadera(int idproducto) throws ObjetoNoExisteException {
		Producto_Madera producto = entityManager.find(Producto_Madera.class, idproducto);

		if (producto == null) {
			throw new ObjetoNoExisteException("El producto a remover no existe en el sistema");
		}

		try {

			entityManager.remove(producto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * agrega un producto a un tipo de madera
	 * @param producto
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Producto_Madera agregarAgregarProductoMadera(Producto_Madera producto) throws ObjetoDuplicadoException {

		if (entityManager.find(Producto_Madera.class, producto.getId_producto()) != null) {

			throw new ObjetoDuplicadoException("Este producto ya se encuentra registrado");
		}
		try {

			entityManager.persist(producto);

			return producto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	
	/**
	 * actualiza un producto de un tipo de madera
	 * @param producto
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public Producto_Madera actualizarProductoMadera(Producto_Madera producto) throws ObjetoNoExisteException {

		if (entityManager.find(Producto_Madera.class, producto.getId_producto()) == null) {
			throw new ObjetoNoExisteException(" El producto buscado no Existe !! ");
		}

		try {

			entityManager.merge(producto);
			return producto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * agrega un proveedor a la base de datos
	 * @param proveedor
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Proveedor agregarProveedor(Proveedor proveedor) throws ObjetoDuplicadoException {

		if (entityManager.find(Persona.class, proveedor.getCedula()) != null) {

			throw new ObjetoDuplicadoException("Esta cedula ya se encuentra registrada");

		} else if (buscarPersonaPorEmail(proveedor.getCorreo())) {

			throw new ObjetoDuplicadoException("Este email ya se encientra registrado");

		}

		try {
			entityManager.persist(proveedor);
			return proveedor;
		} catch (Exception e) {
			
			return null;
		}

	}

	/**
	 * Actualiza datos del proveedor
	 * 
	 * @param proveedor actualizar
	 * @return
	 * @throws ObjetoNoExisteException si la persona a actualizar no existe
	 */
	public Proveedor actualizarproveedor(Proveedor proveedor) throws ObjetoNoExisteException {

		if (entityManager.find(Proveedor.class, proveedor.getCedula()) == null) {
			throw new ObjetoNoExisteException(" El proveedor buscado no Existe !! ");
		}

		try {

			entityManager.merge(proveedor);
			return proveedor;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * lista todos los proveedor
	 * 
	 * @return lista de proveedor
	 */
	public List<Proveedor> listarProveedores() {

		TypedQuery<Proveedor> query = entityManager.createNamedQuery(Proveedor.LISTAR_PROVEEDORES, Proveedor.class);
		return query.getResultList();
	}
	
	/**
	 * agrega un tipo de madera
	 * @param madera
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Tipo_Madera agregarAgregarTipoMadera(Tipo_Madera madera) throws ObjetoDuplicadoException {

		if (entityManager.find(Tipo_Madera.class, madera.getId_madera()) != null) {

			throw new ObjetoDuplicadoException("Este madera ya se encuentra registrado");
		}
		try {

			entityManager.persist(madera);

			return madera;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
	/**
	 * metodo para actualizar los tipos de madera
	 * @param madera
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public Tipo_Madera actualizarTipoMadera(Tipo_Madera madera) throws ObjetoNoExisteException {

		if (entityManager.find(Tipo_Madera.class, madera.getId_madera()) == null) {
			throw new ObjetoNoExisteException(" El madera buscado no Existe !! ");
		}

		try {

			entityManager.merge(madera);
			return madera;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * lista todos los tipos de madera
	 * @return
	 */
	public List<Tipo_Madera> listarTiposMadera() {

		TypedQuery<Tipo_Madera> query = entityManager.createNamedQuery(Tipo_Madera.LISTAR_TIPOS_MADERA,Tipo_Madera.class);

		return query.getResultList();
	}
	
	/**
	 * elimina un tipo de madera
	 * @param idproducto
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public boolean eliminarTipoMadera(int idmadera) throws ObjetoNoExisteException {
		Tipo_Madera madera = entityManager.find(Tipo_Madera.class, idmadera);

		if (madera == null) {
			throw new ObjetoNoExisteException("El producto a remover no existe en el sistema");
		}

		try {

			entityManager.remove(madera);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * metedoto para agregar un vendedor
	 * 
	 * @param vendedor
	 * @return
	 * @throws ObjetoDuplicadoException
	 */
	public Usuario agregarUsuario(Usuario vendedor) throws ObjetoDuplicadoException {

		if (entityManager.find(Usuario.class, vendedor.getCedula()) != null) {

			throw new ObjetoDuplicadoException("Esta cedula ya se encuentra registrada");

		} else if (buscarPersonaPorEmail(vendedor.getCorreo())) {

			throw new ObjetoDuplicadoException("Este email ya se encientra registrado");

		}

		try {
			entityManager.persist(vendedor);
			return vendedor;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Actualiza datos del Usuario
	 * 
	 * @param vendedor actualizar
	 * @return
	 * @throws ObjetoNoExisteException si la persona a actualizar no existe
	 */
	public Usuario actualizarUsuario(Usuario vendedor) throws ObjetoNoExisteException {

		if (entityManager.find(Usuario.class, vendedor.getCedula()) == null) {
			throw new ObjetoNoExisteException(" El vendedor buscado no Existe !! ");
		}

		try {

			entityManager.merge(vendedor);
			return vendedor;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * lista todos los vendedores
	 * 
	 * @return lista de vendedores
	 */
	public List<Usuario> listarUsuario() {

		TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.LISTAR_USUARIOS, Usuario.class);
		return query.getResultList();
	}
	
	/**
	 * inicia session en la aplicacion
	 * @param correo
	 * @param c
	 * @return
	 * @throws ObjetoNoExisteException
	 */
	public Usuario iniciarSesion(String correo, int c) throws ObjetoNoExisteException {
		TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.INICIAR_SESION, Usuario.class);
		query.setParameter("email", correo);
		query.setParameter("clave", c);

		try {

			Usuario persona = query.getSingleResult();

			if (persona != null) {

				return persona;

			}
		} catch (NoResultException e) {
			
			return null;
			
		}

		return null;
	}
	
	/**
	 * Busca Un usuario Por email
	 * 
	 * @return Usuario
	 */
	public Usuario buscarUsuarioEmail(String email) throws	ObjetoNoExisteException {
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.BUSCAR_USUARIO_POR_EMAIL,
					Usuario.class);
			query.setParameter("email", email);

			return query.getSingleResult();
		} catch (NoResultException e) {
			throw new ObjetoNoExisteException("EL USUARIO NO FUE ENCONTRADO");
		
		}
	}

	public Tipo_Madera buscarTipoMadera(int id) throws	ObjetoNoExisteException {
		
		try {
			TypedQuery<Tipo_Madera> query = entityManager.createNamedQuery(Tipo_Madera.BUSCAR_TIPO_ID,
					Tipo_Madera.class);
			query.setParameter("id", id);

			return query.getSingleResult();
		} catch (NoResultException e) {
			
			throw new ObjetoNoExisteException("EL TIPO DE MADERA NO FUE ENCONTRADO");
		}
		
	}

	/**
	 * metodo para actualizar pulgadas de los tipos de madera
	 * @param tipo_madera
	 */
	public void actualizarPulgadas(Tipo_Madera tipo_madera) {
			double suma = 0.0;
		try {
			TypedQuery<Double> query = entityManager.createNamedQuery(Entrada_Madera.PULGADAS_TIPO,
					Double.class);
			query.setParameter("id",tipo_madera.getId_madera());
			
			suma= query.getSingleResult();
			
			tipo_madera.setCantidad_pulgadas(suma);
			
			actualizarTipoMadera(tipo_madera);
			
			
		} catch (NoResultException e) {
			e.printStackTrace();
			
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	/**
	 * metodo para buscar proovedor
	 * @param value
	 * @return
	 */
	public Proveedor buscarProveedor(String value) {
		
		Proveedor proveedor = entityManager.find(Proveedor.class, value);
		
		return proveedor;
		
	}
	
	/**
	 * calcula las ventas por usuario
	 * @param cedula
	 * @return
	 */
	public Double calcularVentasUsuario(String cedula) {
		
		TypedQuery<Double> query = entityManager.createNamedQuery(Usuario.VENTAS_USUARIO,Double.class);
		query.setParameter("cedula", cedula);

		return query.getSingleResult();
		
	}

	/**
	 * calcula el valor total de la factura
	 * @param cedula
	 * @return
	 */
	public Double calcularValorFactura(int idFactura) {
		
		TypedQuery<Double> query = entityManager.createNamedQuery(Factura.VALOR_FACTURA,Double.class);
		query.setParameter("idFactura", idFactura);
		
		Factura factura = entityManager.find(Factura.class, idFactura);
		
		factura.setValor_total(query.getSingleResult());
		
		entityManager.merge(factura);

		return query.getSingleResult();
		
	}
	
	/**
	 * calcula el valor total ventas
	 * @param cedula
	 * @return
	 */
	public Double calcularTotalVentas() {
		
		TypedQuery<Double> query = entityManager.createNamedQuery(Usuario.TOTAL_VENTAS,Double.class);

		return query.getSingleResult();
		
	}
	
	/**
	 * Busca un cliente por la cedula
	 * @param cedula
	 * @return
	 */
	public Cliente buscarCliente(String cedula) {
		
		Cliente cliente = entityManager.find(Cliente.class, cedula);
		
		return cliente;
	}
	
	

}
