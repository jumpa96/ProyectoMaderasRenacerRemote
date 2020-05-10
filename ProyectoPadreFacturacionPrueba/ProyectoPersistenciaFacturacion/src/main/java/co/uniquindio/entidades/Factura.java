package co.uniquindio.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Factura
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Factura.LISTAR_FACTURAS_CLIENTE, query = "select f from Factura f where f.cliente.cedula=:cedula"),
	@NamedQuery(name= Factura.LISTAR_FACTURAS, query="select f from Factura f"),
	@NamedQuery(name= Factura.VALOR_FACTURA, query="select sum(pf.valor_neto) from Producto_Factura pf where pf.factura.id_factura=:idFactura")
})
public class Factura implements Serializable {

	
	/**
	 * lista todas las facturas
	 */
	public static final String LISTAR_FACTURAS="lista todas las facturas";
	
	/**
	 * Calcula el valor total de la factura
	 */
	public static final String VALOR_FACTURA="Calcula el valor total de la factura";
	
	/**
	 * lista todas las facturas por cliente
	 */
	public static final String LISTAR_FACTURAS_CLIENTE="lista todas las facturas por cliente";
	
	/**
	 * fecha de la factura
	 */
	@Column(name = "fecha", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name="estado", nullable=false)
	private boolean estado;
	
	/**
	 * id de la factura
	 */
	@Id
	@Column(name = "id_factura", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_factura;
	
	/**
	 * valor total de la factura
	 */
	@Column(name = "valor_total", nullable = false, precision=10, scale=10)
	private double valor_total;
	
	/**
	 * cliente de la factura
	 */
	@ManyToOne
	@JoinColumn(name = "cliente", nullable = true)
	private Cliente cliente;
	
	/**
	 * vendedor que elaboro la factura
	 */
	@ManyToOne
	@JoinColumn(name = "vendedor", nullable = true)
	private Usuario vendedor;
	
	/**
	 * lista de productos
	 */
	@OneToMany(mappedBy="factura")
	@JoinColumn(name = "productos", nullable = true)
	private List<Producto_Factura> productos;
	
	private static final long serialVersionUID = 1L;

	public Factura() {
		super();
	}   
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}   
	public int getId_factura() {
		return this.id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}   
	public double getValor_total() {
		return this.valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	/**
	 * @return the vendedor
	 */
	public Usuario getVendedor() {
		return vendedor;
	}
	/**
	 * @param vendedor the vendedor to set
	 */
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}
	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return the productos
	 */
	public List<Producto_Factura> getProductos() {
		return productos;
	}
	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<Producto_Factura> productos) {
		this.productos = productos;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_factura;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		if (id_factura != other.id_factura)
			return false;
		return true;
	}
	/**
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
   
}
