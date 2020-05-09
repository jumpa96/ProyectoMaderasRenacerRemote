/**
 * 
 */
package co.uniquindio.ejb;



import java.util.Date;

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
import co.uniquindio.entidades.Persona;
import co.uniquindio.entidades.Proveedor;
import co.uniquindio.entidades.Tipo_Madera;




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
	 * Metodo de prueba para Agregar usuario
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
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void iniciarSesion() {

		String correo="nome@hotmail";
		int clave =1234;
		try {
		
			Assert.assertNotNull(negocioEJB.iniciarSesion(correo, clave));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}
	
	/**
	 * Metodo de prueba para Agregar usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json","entrada_madera.json","tipo_madera.json" })
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
			Entrada_Madera aux=negocioEJB.agregarEntradaMadera(entrada);
			
			if (aux.getCantidad_pulgadas()==200.0)System.out.println("weeeeee");
			Assert.assertNotNull(aux);
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(String.format("Error: %s", e.getMessage()));
		}
	}
	
	/**
	 * Metodo de prueba para las entradas 
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

			System.out.println(suma);
			
			Tipo_Madera tipo = entityManager.find(Tipo_Madera.class, 1);
			
			tipo.setCantidad_pulgadas(suma);
		
			Tipo_Madera aux=negocioEJB.actualizarTipoMadera(tipo);
			
			if(aux.getCantidad_pulgadas()==suma)System.out.println("Rewwwwweeeeeee");
			
			Assert.assertNotNull(aux);

		} catch (NoResultException e) {
			e.printStackTrace();

		}

	}


}
