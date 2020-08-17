
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Servicio extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	nombre;
	private String	identificador;
	private String	imagen;
	private String	descripcion;
	private double	precio;
	private double	duración;


	@NotBlank
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	@NotBlank
	public String getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(final String identificador) {
		this.identificador = identificador;
	}

	@Lob
	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(final String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	@NotNull
	@Digits(fraction = 2, integer = 8)
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
	private Collection<Horario>	horarios;


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

	@NotNull
	@Valid
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "servicio")
	public Collection<Horario> getHorarios() {
		return this.horarios;
	}

	public void setHorarios(final Collection<Horario> horarios) {
		this.horarios = horarios;
	}

}
