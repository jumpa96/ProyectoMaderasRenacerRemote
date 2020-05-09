package co.uniquindio.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Tipo_Madera
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Tipo_Madera.LISTAR_TIPOS_MADERA, query = "select t from Tipo_Madera t"),
	@NamedQuery(name = Tipo_Madera.BUSCAR_TIPO_ID, query = "select t from Tipo_Madera t where t.id_madera=:id")
})
public class Tipo_Madera implements Serializable {

	/**
	 * lista todos los tipos de madera
	 */
	public static final String LISTAR_TIPOS_MADERA="lista todos los tipos de madera";
	
	/**
	 * Busca un tipo de madera
	 */
	public static final String BUSCAR_TIPO_ID="Busca un tipo de madera";
	
	/**
	 * nombre comun de la madera
	 */
	@Column(name = "nombre", nullable = false, length=20)
	private String nombre;
	
	/**
	 * cantidad de pulgadas disponibles de la madera
	 */
	@Column(name = "cantidad_pulgadas", nullable = false, precision=20,scale=20)
	private double cantidad_pulgadas;   
	
	/**
	 * id del tipo de madera
	 */
	@Id
	@Column(name = "id_madera", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_madera;
	
	/**
	 * entradas de madera
	 */
	@OneToMany(mappedBy="tipo_madera")
	@JoinColumn(name = "entradas_madera", nullable = true)
	private List <Entrada_Madera> entradas_madera;
	
	/**
	 * productos que tiene el tipo de madera
	 */
	@OneToMany(mappedBy="tipo_madera")
	@JoinColumn(name = "productos_madera", nullable = true)
	private List<Producto_Madera>productos_madera;
	
	private static final long serialVersionUID = 1L;

	public Tipo_Madera() {
		super();
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   

	/**
	 * @return the cantidad_pulgadas
	 */
	public double getCantidad_pulgadas() {
		return cantidad_pulgadas;
	}
	/**
	 * @param cantidad_pulgadas the cantidad_pulgadas to set
	 */
	public void setCantidad_pulgadas(double cantidad_pulgadas) {
		this.cantidad_pulgadas = cantidad_pulgadas;
	}
	public int getId_madera() {
		return this.id_madera;
	}

	public void setId_madera(int id_madera) {
		this.id_madera = id_madera;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_madera;
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
		Tipo_Madera other = (Tipo_Madera) obj;
		if (id_madera != other.id_madera)
			return false;
		return true;
	}
	/**
	 * @return the entradas_madera
	 */
	public List <Entrada_Madera> getEntradas_madera() {
		return entradas_madera;
	}
	/**
	 * @param entradas_madera the entradas_madera to set
	 */
	public void setEntradas_madera(List <Entrada_Madera> entradas_madera) {
		this.entradas_madera = entradas_madera;
	}
	/**
	 * @return the productos_madera
	 */
	public List<Producto_Madera> getProductos_madera() {
		return productos_madera;
	}
	/**
	 * @param productos_madera the productos_madera to set
	 */
	public void setProductos_madera(List<Producto_Madera> productos_madera) {
		this.productos_madera = productos_madera;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  nombre;
	}
   
	
	
	
}
