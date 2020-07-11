
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Servicio extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	nombre;
	private byte[]	imagen;
	private String	tipoServicio;
	private String	descripcion;
	private Double	precio;
	private Double	duración;


	@NotNull
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(final byte[] imagen) {
		this.imagen = imagen;
	}

	@NotNull
	public String getTipoServicio() {
		return this.tipoServicio;
	}

	public void setTipoServicio(final String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	@NotNull
	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(final double precio) {
		this.precio = precio;
	}

	@NotNull
	public double getDuración() {
		return this.duración;
	}

	public void setDuración(final double duración) {
		this.duración = duración;
	}


	// Relationships--------------------------------------------------------------
	private Centro				centro;
	private Collection<Reserva>	reservas;


	@Valid
	@ManyToOne(optional = false)
	public Centro getCentro() {
		return this.centro;
	}

	public void setCentro(final Centro centro) {
		this.centro = centro;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy = "servicio")
	public Collection<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(final Collection<Reserva> reservas) {
		this.reservas = reservas;
	}

}
