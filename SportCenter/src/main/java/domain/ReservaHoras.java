
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Embeddable
@Access(AccessType.PROPERTY)
public class ReservaHoras {

	// Attributes -------------------------------------------------------------
	private String	horaInicio;
	private String	horaFin;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(final String horaInicio) {
		this.horaInicio = horaInicio;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getHoraFin() {
		return this.horaFin;
	}

	public void setgetHoraFin(final String horaFin) {
		this.horaFin = horaFin;
	}
}
