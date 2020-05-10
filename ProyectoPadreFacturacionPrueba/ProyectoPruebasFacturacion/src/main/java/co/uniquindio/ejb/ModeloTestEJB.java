/**
 * 
 */
package co.uniquindio.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.uniquindio.ejb.NegocioEJB;
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
 * @author jpgb9
 *
 */
@RunWith(Arquillian.class)
public class ModeloTestEJB {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private NegocioEJB negocioEJB;

	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(NegocioEJB.class).addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void generarTest() {
	}

	/**
	 * Metodo de prueba para Agregar Cliente
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void agregarCliente() {

		Cliente usuario = new Cliente();

		usuario.setApellido("apellidos");
		usuario.setCedula("1093123");
		usuario.setDireccion("calle 22");
		usuario.setCorreo("jpgb96");
		usuario.setTelefono("9123912");
		usuario.setNombre("luna");

		try {

			Assert.assertNotNull(negocioEJB.agregarCliente(usuario));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para buscar Cliente
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void buscarCliente() {

		String cedulaPersona = "admin2";

		try {
			Assert.assertNotNull(entityManager.find(Cliente.class, cedulaPersona));
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para actualizar Cliente
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void actualizarCliente() {

		Cliente cliente = entityManager.find(Cliente.class, "admin2");

		cliente.setApellido("grisales");
		try {
			Assert.assertEquals(negocioEJB.actualizarCliente(cliente).getApellido(), "grisales");
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para listar Cliente
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void listarCliente() {

		List<Cliente> clientes = negocioEJB.listarCliente();

		try {
			Assert.assertEquals(clientes.size(), 1);
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para Agregar Proveedor
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void agregarProveedor() {

		Proveedor usuario = new Proveedor();

		usuario.setApellido("apellidos");
		usuario.setCedula("1093123");
		usuario.setDireccion("calle 22");
		usuario.setCorreo("jpgb96");
		usuario.setTelefono("9123912");
		usuario.setNombre("luna");

		try {

			Assert.assertNotNull(negocioEJB.agregarProveedor(usuario));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para buscar Proveedor
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void buscarProveedor() {

		String cedulaPersona = "vendedor2";

		try {
			Assert.assertNotNull(entityManager.find(Proveedor.class, cedulaPersona));
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para actualizar Proveedor
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void actualizarProveedor() {

		Proveedor pro = entityManager.find(Proveedor.class, "vendedor2");

		pro.setApellido("grisales");
		try {
			Assert.assertEquals(negocioEJB.actualizarproveedor(pro).getApellido(), "grisales");
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para listar Proveedor
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void listarProveedores() {

		List<Proveedor> pros = negocioEJB.listarProveedores();

		try {
			Assert.assertEquals(pros.size(), 1);
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para Agregar Usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void agregarUsuario() {

		Usuario usuario = new Usuario();

		usuario.setApellido("apellidos");
		usuario.setCedula("1093123");
		usuario.setDireccion("calle 22");
		usuario.setCorreo("jpgb96");
		usuario.setTelefono("9123912");
		usuario.setNombre("luna");
		usuario.setClave(1234);
		usuario.setSalario_base(1200.000);

		try {

			Assert.assertNotNull(negocioEJB.agregarUsuario(usuario));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para buscar Usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void buscarUsuario() {

		String cedulaPersona = "admin1";

		try {
			Assert.assertNotNull(entityManager.find(Usuario.class, cedulaPersona));
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para actualizar Usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void actualizarUsuario() {

		Usuario usuario = entityManager.find(Usuario.class, "admin1");

		usuario.setApellido("grisales");
		try {
			Assert.assertEquals(negocioEJB.actualizarUsuario(usuario).getApellido(), "grisales");
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para listar Usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void listarUsuarios() {

		List<Usuario> usuarios = negocioEJB.listarUsuario();

		try {
			Assert.assertEquals(usuarios.size(), 2);
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo para iniciar Sesion
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void iniciarSesion() {

		String correo = "nome@hotmail";
		int clave = 1234;
		try {

			Assert.assertNotNull(negocioEJB.iniciarSesion(correo, clave));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para Agregar Entrada de madera
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json" })
	public void agregarEntrada() {

		Entrada_Madera entrada = new Entrada_Madera();
		entrada.setCantidad_pulgadas(200.0);
		entrada.setCorporacion("CRQ");
		entrada.setFecha(new Date());
		entrada.setNombre_cientifico("hola");
		entrada.setProcedencia("Armenia");
		entrada.setProveedor(new Proveedor());
		entrada.setSalvoconducto(123);
		entrada.setTipo_madera(entityManager.find(Tipo_Madera.class, 1));

		try {
			Entrada_Madera aux = negocioEJB.agregarEntradaMadera(entrada);

			Assert.assertNotNull(aux);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para las entradas
	 * 
	 * @throws ObjetoNoExisteException
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json" })
	public void probarEntrada() throws ObjetoNoExisteException {

		try {

			double suma;
			TypedQuery<Double> query = entityManager.createNamedQuery(Entrada_Madera.PULGADAS_TIPO, Double.class);
			query.setParameter("id", 1);

			suma = query.getSingleResult();

			Tipo_Madera tipo = entityManager.find(Tipo_Madera.class, 1);

			tipo.setCantidad_pulgadas(suma);

			Tipo_Madera aux = negocioEJB.actualizarTipoMadera(tipo);

			if (aux.getCantidad_pulgadas() == suma)

				Assert.assertNotNull(aux);

		} catch (NoResultException e) {
			e.printStackTrace();

		}

	}

	/**
	 * Metodo de prueba para actualizar Entrada de madera
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json" })
	public void actualizarEntrada() {

		Entrada_Madera entrada = entityManager.find(Entrada_Madera.class, 1);

		entrada.setProcedencia("Buenaventura");

		try {
			Assert.assertEquals(negocioEJB.actualizarEntradaMadera(entrada).getProcedencia(), "Buenaventura");
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para eliminar Entrada de madera
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json" })
	public void eliminarEntrada() {

		Entrada_Madera entrada = entityManager.find(Entrada_Madera.class, 1);

		try {
			Assert.assertEquals(negocioEJB.eliminarEntradaMadera(entrada.getSalvoconducto()), true);
			;
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para listar Entrada de maderas
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json" })
	public void listarEntradas() {

		List<Entrada_Madera> entradas = negocioEJB.listarEntradas();

		try {
			Assert.assertEquals(entradas.size(), 1);
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para Agregar Producto de madera
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json" })
	public void agregarProducto() {

		Producto_Madera producto = new Producto_Madera();
		producto.setTipo_madera(entityManager.find(Tipo_Madera.class, 1));
		producto.setAlto(8.0);
		producto.setAncho(4);
		producto.setLargo(3);
		producto.setNombre("cuarton");
		producto.setPrecio(6000);
		producto.setPulgadas(8);

		try {

			Assert.assertNotNull(negocioEJB.agregarAgregarProductoMadera(producto));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para actualizar Producto
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json" })
	public void actualizarProducto() {

		Producto_Madera producto = entityManager.find(Producto_Madera.class, 1);

		producto.setNombre("liston");
		producto.setAncho(4);

		try {
			Assert.assertEquals(negocioEJB.actualizarProductoMadera(producto).getNombre(), "liston");
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para eliminar Producto
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json" })
	public void eliminarProducto() {

		Producto_Madera producto = entityManager.find(Producto_Madera.class, 1);

		try {
			Assert.assertEquals(negocioEJB.eliminarProductoMadera(producto.getId_producto()), true);
			;
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para listar Productos
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json" })
	public void listarProductos() {

		List<Producto_Madera> productos = negocioEJB.listarProducto();

		try {
			Assert.assertEquals(productos.size(), 1);
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para Agregar Factura
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json",
			"facturas.json" })
	public void agregarFactura() {

		Factura factura = new Factura();

		factura.setCliente(entityManager.find(Cliente.class, "admin2"));
		factura.setEstado(true);
		factura.setFecha(new Date());
		factura.setValor_total(0);
		factura.setVendedor(entityManager.find(Usuario.class, "admin1"));

		try {

			Assert.assertNotNull(negocioEJB.agregarAgregarFactura(factura));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para anular Factura
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json",
			"facturas.json" })
	public void anularFactura() {

		Factura factura = entityManager.find(Factura.class, 1);
		factura.setEstado(false);

		try {
			Assert.assertNotNull(negocioEJB.anularFactura(factura));
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para listar Facturas
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json",
			"facturas.json" })
	public void listarFacturas() {

		List<Factura> facturas = negocioEJB.listarFacturas();

		try {
			Assert.assertEquals(facturas.size(), 1);
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para reportar ventas totales
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json",
			"facturas.json" })
	public void reportarVentas() {

		Double total = negocioEJB.calcularTotalVentas();

		try {

			if (total == 1200000) {

				Assert.assertTrue(true);
				
			} else {
				Assert.fail();

			}

		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para calcular ventas por usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json",
			"facturas.json" })
	public void reportarVentasUsuario() {

		Usuario usuario = entityManager.find(Usuario.class, "admin1");

		Double total = negocioEJB.calcularVentasUsuario(usuario.getCedula());

		try {

			if (total == 1200000) {
				Assert.assertTrue(true);
				;
			} else {
				Assert.fail();

			}

		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para agregar un producto a una factura
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json", "facturas.json",
			"productos_factura.json" })
	public void agregarProductoFactura() {

		Producto_Factura producto = new Producto_Factura();

		producto.setCantidad(3);
		producto.setFactura(entityManager.find(Factura.class, 1));
		producto.setProducto(entityManager.find(Producto_Madera.class, 1));
		producto.setValor_neto(producto.getCantidad() * producto.getProducto().getPrecio());

		try {
			if (producto.getValor_neto() == (3 * 8500)) {
				System.out.println("weeeee");
			}
			Assert.assertNotNull(negocioEJB.agregarAgregarProductoFacura(producto));

		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para actualizar un producto de una factura
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json", "facturas.json",
			"productos_factura.json" })
	public void actualizarProductoFactura() {

		Producto_Factura producto = entityManager.find(Producto_Factura.class, 1);

		producto.setCantidad(4);

		try {

			Assert.assertEquals(negocioEJB.actualizarProductoFactura(producto).getCantidad(), 4);

		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para eliminar un producto de una factura
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json", "facturas.json",
			"productos_factura.json" })
	public void eliminarProductoFactura() {

		Producto_Factura producto = entityManager.find(Producto_Factura.class, 1);

		try {
			Assert.assertEquals(negocioEJB.eliminarProductoFactura(producto.getId_producto_factura()), true);
			;
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para listar los productos de una factura
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json", "facturas.json",
			"productos_factura.json" })
	public void listarProductoFactura() {

		Factura factura = entityManager.find(Factura.class, 1);
		List<Producto_Factura> facturas = negocioEJB.listarProductosFactura(factura.getId_factura());

		try {
			Assert.assertEquals(facturas.size(), 2);
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

	/**
	 * Metodo de prueba para calcular el valor de una factura
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json", "producto_madera.json", "facturas.json",
			"productos_factura.json" })
	public void valorFactura() {

		Factura factura = entityManager.find(Factura.class, 1);

		Double total = negocioEJB.calcularValorFactura(factura.getId_factura());

		try {
			if (total == 34000) {

				Assert.assertTrue(true);
				
			} else {
				Assert.fail();

			}
		} catch (Exception e) {
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}

}
