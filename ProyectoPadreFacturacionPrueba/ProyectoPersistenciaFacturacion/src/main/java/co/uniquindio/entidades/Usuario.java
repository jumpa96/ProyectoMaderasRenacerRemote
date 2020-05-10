package co.uniquindio.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Usuario.LISTAR_USUARIOS, query = "select u from Usuario u"),
	@NamedQuery(name = Usuario.INICIAR_SESION, query = "select u from Usuario u where u.clave=:clave and u.correo=:email"),
	@NamedQuery(name = Usuario.BUSCAR_USUARIO_POR_EMAIL, query = "select v from Usuario v where v.correo=:email"),
	@NamedQuery(name = Usuario.VENTAS_USUARIO, query = "select sum(f.valor_total) from Factura f where f.vendedor.cedula=:cedula"),
	@NamedQuery(name = Usuario.TOTAL_VENTAS, query = "select sum(f.valor_total) from Factura f")
})
public class Usuario extends Persona implements Serializable {
	
	/**
	 * Calcula el total de ventas
	 */
	public static final String TOTAL_VENTAS="Calcula el total de ventas";
	
	/**
	 * Calcula el total de ventas por vendedor
	 */
	public static final String VENTAS_USUARIO="Calcula el total de ventas por vendedor";
	
	/**
	 * iniciar session
	 */
	public static final String BUSCAR_USUARIO_POR_EMAIL="Busca Usuario por email";
	
	/**
	 * iniciar session
	 */
	public static final String INICIAR_SESION="inicia session";
	
	/**
	 * lista todos los Usuarioes
	 */
	public static final String LISTAR_USUARIOS="lista todos los Usuarioes";
	
	/**
	 * salario base del Usuario
	 */
	@Column(name = "salario_base", nullable = false, precision=20,scale=20)
	private double salario_base;
	
	/**
	 * clave de entrada al sistema
	 */
	@Column(name = "clave", nullable = false)
	private int clave;
	
	/**
	 * Facturas echas por el Usuario
	 */
	@JoinColumn(name = "facturas", nullable = true)
	@OneToMany(mappedBy="vendedor")
	private List<Factura> facturas;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor
	 */
	public Usuario() {
		super();
	}

	// setters and getters
	
	public int getClave() {
		return this.clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public double getSalario_base() {
		return salario_base;
	}

	public void setSalario_base(double salario_base) {
		this.salario_base = salario_base;
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
