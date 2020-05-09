package co.uniquindio.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Proveedor
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Proveedor.LISTAR_PROVEEDORES, query = "select p from Proveedor p")
})
public class Proveedor extends Persona implements Serializable {

	/**
	 * lista todos los proveedores de la bd
	 */
	public static final String LISTAR_PROVEEDORES="lista todos los proveedores de la bd";
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Entregas de madera
	 */
	@OneToMany(mappedBy="proveedor")
	@JoinColumn(name = "entregas", nullable = true)
	private List <Entrada_Madera> entregas;
	
	public Proveedor() {
		super();
	}

	/**
	 * @return the entregas
	 */
	public List <Entrada_Madera> getEntregas() {
		return entregas;
	}

	/**
	 * @param entregas the entregas to set
	 */
	public void setEntregas(List <Entrada_Madera> entregas) {
		this.entregas = entregas;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  getNombre() + ",   Cedula:" + getCedula();
	}
	
	
   
}
