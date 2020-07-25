
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Horario extends DomainEntity {

	// Attributes ---------------------------------------------------------
	private String	diaSemana;
	private String	horaInicio;
	private String	minutosInicio;
	private String	horaFin;
	private String	minutosFin;


	@NotBlank
	public String getDiaSemana() {
		return this.diaSemana;
	}

	public void setDiaSemana(final String diaSemana) {
		this.diaSemana = diaSemana;
	}

	@NotBlank
	public String getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(final String horaInicio) {
		this.horaInicio = horaInicio;
	}

	@NotBlank
	public String getMinutosInicio() {
		return this.minutosInicio;
	}

	public void setMinutosInicio(final String minutosInicio) {
		this.minutosInicio = minutosInicio;
	}

	public String getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(final String horaFin) {
		this.horaFin = horaFin;
	}

	public String getMinutosFin() {
		return this.minutosFin;
	}

	public void setMinutosFin(final String minutosFin) {
		this.minutosFin = minutosFin;
	}


	// Relationships--------------------------------------------------------------
	private Servicio	servicio;


	@Valid
	@ManyToOne(optional = false)
	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(final Servicio servicio) {
		this.servicio = servicio;
	}

}
