
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Usuario extends Actor {

	// Relationships--------------------------------------------------------------

	//private Collection<Centro>	centros;
	private Collection<Reserva>	reservas;


	/*@NotNull
	@Valid
	@ManyToMany
	public Collection<Centro> getCentros() {
		return this.centros;
	}

	public void setCentros(final Collection<Centro> centros) {
		this.centros = centros;
	}*/

	@NotNull
	@Valid
	@OneToMany(mappedBy = "usuario")
	public Collection<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(final Collection<Reserva> reservas) {
		this.reservas = reservas;
	}
}
