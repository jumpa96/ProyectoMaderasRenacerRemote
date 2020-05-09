package co.uniquindio.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Producto_Factura
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Producto_Factura.LISTAR_PRODUCTOS_FACTURA, query = "select p from Producto_Factura p where p.factura.id_factura=:idFactura")
})
public class Producto_Factura implements Serializable {

	/**
	 * lista todos los productos de una factura
	 */
	public static final String LISTAR_PRODUCTOS_FACTURA="lista todos los productos de una factura";
	
	/**
	 * id de los productos de la factura   
	 */
	@Id
	@Column(name = "id_producto_factura", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_producto_factura;
	
	/**
	 * cantidad de producto
	 */
	@Column(name = "cantidad", nullable = false)
	private int cantidad;
	
	/**
	 * valor neto de la factura
	 */
	@Column(name = "valor_neto", nullable = false, precision=20, scale=10)
	private double valor_neto;
	
	/**
	 * factura a la que pertenecen los productos
	 */
	@ManyToOne
	@JoinColumn(name = "factura", nullable = true)
	private Factura factura;
	
	/**
	 * producto de la factura
	 */
	@ManyToOne
	@JoinColumn(name = "producto", nullable = true)
	private Producto_Madera producto;
	
	private static final long serialVersionUID = 1L;

	public Producto_Factura() {
		super();
	}   
	public int getId_producto_factura() {
		return this.id_producto_factura;
	}

	public void setId_producto_factura(int id_producto_factura) {
		this.id_producto_factura = id_producto_factura;
	}   
	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}   
	public double getValor_neto() {
		return this.valor_neto;
	}

	public void setValor_neto(double valor_neto) {
		this.valor_neto = valor_neto;
	}
	/**
	 * @return the factura
	 */
	public Factura getFactura() {
		return factura;
	}
	/**
	 * @param factura the factura to set
	 */
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	/**
	 * @return the producto
	 */
	public Producto_Madera getProducto() {
		return producto;
	}
	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto_Madera producto) {
		this.producto = producto;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_producto_factura;
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
		Producto_Factura other = (Producto_Factura) obj;
		if (id_producto_factura != other.id_producto_factura)
			return false;
		return true;
	}
	
	
   
}
