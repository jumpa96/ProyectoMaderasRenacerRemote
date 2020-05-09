/**
 * 
 */
package com.uniquindio.util;


import java.util.Properties;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author juamp
 *
 */
public class Util {

	/**
	 * Usuario del correo electronico del cual se envian los corres
	 */
	private static String usuario = "analisis1212";

	/**
	 * Contrasenia del correo electronico del cual se envian los corres
	 */
	private static String password = "analisis1234@";
	
	/**
	 * permite mostrar un mensaje en la pagina web
	 * 
	 * @param titulo  titulo del menasje
	 * @param mensaje texto a destacar
	 */
	public static void mostrarMensaje(String titulo, String mensaje) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	/**
	 * permite obtener la infomacion que se encuentra en el archivo de propiedades
	 * 
	 * @return resurce bunble con info de application.properties
	 */
	public static ResourceBundle getResourceBundle() {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		return bundle;
	}
	
	public static boolean isNumeric(String cadena) {

		boolean resultado;

		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}
	
	/**
	 * metodo para recuperar contraseña
	 * @param to
	 * @param mensaje
	 * @param subject
	 */
	public static void enviarCorreo(String to, String mensaje, String subject) {

		Properties props = new Properties();

		props = System.getProperties();
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuario, password);
			}
		});

		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(usuario));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(mensaje);

			Transport.send(message);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
