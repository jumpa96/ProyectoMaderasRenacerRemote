/**
 * 
 */
package co.uniquindio.test;


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
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;



import co.uniquindio.entidades.Cliente;
import co.uniquindio.entidades.Entrada_Madera;
import co.uniquindio.entidades.Persona;


/**
 * @author jpgb9
 *
 */
@RunWith(Arquillian.class)
public class ModeloTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "prueba.war").addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void generarTest() {
	}

	/**
	 * Agrega un usuario
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

		entityManager.persist(usuario);

		Cliente usuario2 = entityManager.find(Cliente.class, "1093123");
		Assert.assertNotNull(usuario2);
	}

	/**
	 * Metodo que busca un usuario por medio de la cedula
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json" })
	public void buscarCliente() {
		Cliente usuario = entityManager.find(Cliente.class, "admin2");
		Assert.assertEquals(usuario.getCedula(), "admin2");

	}

	/**
	 * Metodo de prueba para Agregar usuario
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "personas.json", "entrada_madera.json", "tipo_madera.json" })
	public void agregarEntrada() {

		try {

			double suma;
			TypedQuery<Double> query = entityManager.createNamedQuery(Entrada_Madera.PULGADAS_TIPO, Double.class);
			query.setParameter("id", 1);

			suma = query.getSingleResult();

			System.out.println(suma);

			

		} catch (NoResultException e) {
			e.printStackTrace();

		}

	}
}