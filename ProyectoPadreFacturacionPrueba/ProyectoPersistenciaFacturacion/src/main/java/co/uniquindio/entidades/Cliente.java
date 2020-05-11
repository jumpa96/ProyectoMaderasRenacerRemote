package co.uniquindio.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Cliente.LISTAR_CLIENTES, query = "select c from Cliente c"),
	@NamedQuery(name = Cliente.BUSCAR_CLIENTE, query = "select c from Cliente c where c.cedula=:cedula")
})
public class Cliente extends Persona implements Serializable {

	
	/**
	 * lista todos los clientes
	 */
	public static final String BUSCAR_CLIENTE="Busca un cliente";
	
	/**
	 * lista todos los clientes
	 */
	public static final String LISTAR_CLIENTES="lista todos los clientes";
	
	/**
	 * facturas del cliente
	 */
	@JoinColumn(name = "facturas", updatable = true, nullable = true)
	@OneToMany(mappedBy="cliente")
	private List<Factura> facturas;
	
	private static final long serialVersionUID = 1L;

	public Cliente() {
		super();
	}

	/**
	 * @return the facturas
	 */
	public List<Factura> getFacturas() {
		return facturas;
	}

	/**
	 * @param facturas the facturas to set
	 */
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	
	
   
}
