package co.uniquindio.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Producto_Madera
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Producto_Madera.LISTAR_PRODUCTOS_MADERA, query = "select p from Producto_Madera p where p.tipo_madera.id_madera=:idMadera"),
	@NamedQuery(name = Producto_Madera.LISTAR_PRODUCTOS, query = "select p from Producto_Madera p ")
})
public class Producto_Madera implements Serializable {
	
	/**
	 * lista todos los productos de un tipo de madera
	 */
	public static final String LISTAR_PRODUCTOS_MADERA="lista todos los productos de un tipo de madera";
	
	/**
	 * lista todos los productos
	 */
	public static final String LISTAR_PRODUCTOS="lista todos los productos";

	/**
	 * id del producto  
	 */
	@Id
	@Column(name = "id_producto", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_producto;
	
	/**
	 * nombre del producto
	 */
	@Column(name = "nombre", nullable = false, length=20)
	private String nombre;
	
	/**
	 * alto de la madera
	 */
	@Column(name = "alto", nullable = false, precision=10,scale=10)
	private double alto;
	
	/**
	 * ancho de la madera
	 */
	@Column(name = "ancho", nullable = false, precision=10,scale=10)
	private double ancho;
	
	/**
	 * largo del producto
	 */
	@Column(name = "largo", nullable = false, precision=10,scale=10)
	private double largo;
	
	/**
	 * precio del product
	 */
	@Column(name = "precio", nullable = false, precision=20,scale=20)
	private double precio;
	
	/**
	 * pulgadas del producto
	 */
	@Column(name = "pulgadas", nullable = false, precision=10,scale=10)
	private double pulgadas;
	
	/**
	 * el tipo de madera al que pertenece el producto
	 */
	@ManyToOne
	@JoinColumn(name = "tipo_madera", nullable = true)
	private Tipo_Madera tipo_madera;
	
	/**
	 * productos de la factura
	 */
	@OneToMany(mappedBy="producto")
	@JoinColumn(name = "productos", nullable = true)
	private List<Producto_Factura> productos;
	
	private static final long serialVersionUID = 1L;

	public Producto_Madera() {
		super();
	}   
	public int getId_producto() {
		return this.id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
 
	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}   

	/**
	 * @return the alto
	 */
	public double getAlto() {
		return alto;
	}
	/**
	 * @param alto the alto to set
	 */
	public void setAlto(double alto) {
		this.alto = alto;
	}
	/**
	 * @return the ancho
	 */
	public double getAncho() {
		return ancho;
	}
	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(double ancho) {
		this.ancho = ancho;
	}
	/**
	 * @return the largo
	 */
	public double getLargo() {
		return largo;
	}
	/**
	 * @param largo the largo to set
	 */
	public void setLargo(double largo) {
		this.largo = largo;
	}
	/**
	 * @return the pulgadas
	 */
	public double getPulgadas() {
		return pulgadas;
	}
	/**
	 * @param pulgadas the pulgadas to set
	 */
	public void setPulgadas(double pulgadas) {
		this.pulgadas = pulgadas;
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
	/**
	 * @return the tipo_madera
	 */
	public Tipo_Madera getTipo_madera() {
		return tipo_madera;
	}
	/**
	 * @param tipo_madera the tipo_madera to set
	 */
	public void setTipo_madera(Tipo_Madera tipo_madera) {
		this.tipo_madera = tipo_madera;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_producto;
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
		Producto_Madera other = (Producto_Madera) obj;
		if (id_producto != other.id_producto)
			return false;
		return true;
	}
   
}
