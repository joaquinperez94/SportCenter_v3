
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Gestor extends Actor {

	// Relationships--------------------------------------------------------------

	private Collection<Centro>	centros;


	@NotNull
	@Valid
	@OneToMany(mappedBy = "gestor")
	public Collection<Centro> getCentros() {
		return this.centros;
	}

	public void setCentros(final Collection<Centro> centros) {
		this.centros = centros;
	}
}
