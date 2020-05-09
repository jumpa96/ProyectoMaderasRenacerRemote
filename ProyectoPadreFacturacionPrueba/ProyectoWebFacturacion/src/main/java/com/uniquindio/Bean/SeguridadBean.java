/**
 * 
 */
package com.uniquindio.Bean;

import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;

import com.uniquindio.ejb.NegocioEJB;
import com.uniquindio.exception.ObjetoNoExisteException;
import com.uniquindio.util.Util;

import co.uniquindio.entidades.Usuario;

/**
 * @author juamp
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("seguridadBean")
@SessionScoped
public class SeguridadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * usuario a iniciar session
	 */
	@NotNull
	private Usuario usuario;

	/**
	 * clave con la que inicia sesion
	 */
	@NotNull
	private String clave;
	/**
	 * Avisa si es usuario esta en session
	 */
	private boolean autenticado;

	@EJB
	private NegocioEJB adminEJB;

	/**
	 * Usuario del correo electronico del cual se envian los corres
	 */
	private static String user = "analisis1212";

	/**
	 * Contrasenia del correo electronico del cual se envian los corres
	 */
	private static String password = "analisis1234@";

	/**
	 * inicia session
	 */
	@PostConstruct
	private void init() {
		usuario = new Usuario();
		autenticado = false;

	}

	public void iniciarSesion() throws ObjetoNoExisteException {

		if (Util.isNumeric(clave)) {
			int pas =Integer.parseInt(clave);
			Usuario u = adminEJB.iniciarSesion(usuario.getCorreo(),pas);

			if (u == null) {
				Util.mostrarMensaje("No se pudo iniciar sesion verifique sus credenciales",
						"No se pudo iniciar sesion verifique sus credenciales");
			} else {
				usuario = u;
				autenticado = true;
			}
		}else {
			Util.mostrarMensaje("la clave es numerica", "la clave es numerica");
		}
	}



	public void recuperar() {
		Usuario us = new Usuario();
		try {
			us = adminEJB.buscarUsuarioEmail(usuario.getCorreo());
		} catch (ObjetoNoExisteException e1) {
			
			Util.mostrarMensaje("El correo no exite", "El correo no exite");
			e1.printStackTrace();
		}

		String para = usuario.getCorreo();
		String asunto = "OLVIDE CONTRASEÑA DEL MINIMARKET";
		String mensaje = "Su contraseña de acceso al sistemas es: " + us.getClave();

		Properties props = new Properties();

		props = System.getProperties();
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			message.setSubject(asunto);
			message.setText(mensaje);

			Transport.send(message);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * permite cerrar sesion
	 */
	public void cerrarSesion() {
		if (usuario != null) {
			usuario = null;
			autenticado = false;
			init();
		}
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
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
	 * @return the user
	 */
	public static String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public static void setUser(String user) {
		SeguridadBean.user = user;
	}

	/**
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public static void setPassword(String password) {
		SeguridadBean.password = password;
	}

}
