package co.uniquindio.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Entrada_Madera
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Entrada_Madera.LISTAR_ENTRADAS, query = "select e from Entrada_Madera e"),
	@NamedQuery(name = Entrada_Madera.PULGADAS_TIPO, query = "select SUM(e.cantidad_pulgadas) from Entrada_Madera e where e.tipo_madera.id_madera=:id"),
	@NamedQuery(name = Entrada_Madera.LISTAR_ENTRADAS_TIPO, query = "select e from Entrada_Madera e where e.tipo_madera.id_madera=:id_madera")
})
public class Entrada_Madera implements Serializable {

	/**
	 * lista todas las entradas de madera por tipo
	 */
	public static final String LISTAR_ENTRADAS_TIPO="lista todas las entradas de madera por tipo";
	
	
	/**
	 * Suma todas las entradas de un tipo de madera
	 */
	public static final String PULGADAS_TIPO="Suma todas las entradas de un tipo de madera";
	
	/**
	 * lista todas las entradas de madera
	 */
	public static final String LISTAR_ENTRADAS="lista todas las entradas de madera";
	
	/**
	 * idetificacion del salvocionduco   
	 */
	@Id
	@Column(name = "salvoconducto", nullable = false)
	private int salvoconducto;
	
	/**
	 * cantidad de pulgadas de la madera
	 */
	@Column(name = "cantidad_pulgadas", nullable = false, precision=10, scale=10)
	private double cantidad_pulgadas;
	
	/**
	 * nombre cientifico de la madera
	 */
	@Column(name = "nombre_cientifico", nullable = false, length=40)
	private String nombre_cientifico;
	
	/**
	 * de donde viene la madera
	 */
	@Column(name = "procedencia", nullable = false, length=40)
	private String procedencia;
	
	/**
	 * corporacion que emite el salvo conducto
	 */
	@Column(name = "corporacion", nullable = false, length=15)
	private String corporacion;
	
	/**
	 * fecha de entrada
	 */
	@Column(name = "fecha", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	/**
	 * proveedor que realizo la entrega
	 */
	@ManyToOne
	@JoinColumn(name = "proveedor", nullable = true)
	private Proveedor proveedor; 
	
	/**
	 * tipo de madera a la que pertenece la entrada
	 */
	@ManyToOne
	@JoinColumn(name = "tipo_madera", nullable = true)
	private Tipo_Madera tipo_madera;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor
	 */
	public Entrada_Madera() {
		super();
	}   
	
	// setters and getters
	
	public int getSalvoconducto() {
		return this.salvoconducto;
	}

	public void setSalvoconducto(int salvoconducto) {
		this.salvoconducto = salvoconducto;
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

	public String getNombre_cientifico() {
		return this.nombre_cientifico;
	}

	public void setNombre_cientifico(String nombre_cientifico) {
		this.nombre_cientifico = nombre_cientifico;
	}   
	public String getProcedencia() {
		return this.procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}   
	public String getCorporacion() {
		return this.corporacion;
	}

	public void setCorporacion(String corporacion) {
		this.corporacion = corporacion;
	}   
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the proveedor
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}

	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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
		result = prime * result + salvoconducto;
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
		Entrada_Madera other = (Entrada_Madera) obj;
		if (salvoconducto != other.salvoconducto)
			return false;
		return true;
	}
	
   
}
