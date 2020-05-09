package com.uniquindio.ejb;

/**
 * @author juamp
 */
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.uniquindio.entidades.Usuario;

import javax.annotation.PostConstruct;



/**
 * Session Bean implementation class SetupEJB
 */
@Singleton
@LocalBean
@Startup
public class SetupEJB {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public SetupEJB() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void config() {

		TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.LISTAR_USUARIOS,
				Usuario.class);

		if (query.getResultList().isEmpty()) {
			Usuario administrador = new Usuario();
			administrador.setCedula("42064376");
			administrador.setClave(1234);
			administrador.setCorreo("luzelena.1275@hotmail.com");
			administrador.setDireccion("Calle 40 # 26-01");
			administrador.setApellido(" Botero Sanchez");
			administrador.setNombre("luz Elena");
			administrador.setTelefono("31949845");
			
			entityManager.persist(administrador);
			
			
		}
	}

}
