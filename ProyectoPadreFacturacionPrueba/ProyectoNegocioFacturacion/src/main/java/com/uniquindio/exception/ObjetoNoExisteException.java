/**
 * 
 */
package com.uniquindio.exception;

/**
 *  
 * @author juamp
 * Clase encargada de manejar la excepcion si el usuario solicitado no existe
 */
public class ObjetoNoExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public ObjetoNoExisteException(String mensaje) {
		super(mensaje);
		// TODO Auto-generated constructor stub
	}

}