
package forms;

import javax.validation.Valid;

import domain.Centro;
import domain.Horario;

public class CentroForm {

	@Valid
	private Centro	centro;
	private Horario	horario;


	public CentroForm() {
		super();
	}

	public CentroForm(final Centro centro, final Horario horario) {
		this.centro = centro;
		this.horario = horario;
	}

	public Centro getCentro() {
		return this.centro;
	}

	public void setCentro(final Centro centro) {
		this.centro = centro;
	}

	public Horario getHorario() {
		return this.horario;
	}

	public void setHorario(final Horario horario) {
		this.horario = horario;
	}
}
