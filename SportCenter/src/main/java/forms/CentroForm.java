
package forms;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import domain.Centro;
import domain.Horario;

public class CentroForm {

	@Valid
	private Centro				centro;
	private Collection<Horario>	horarios;


	public CentroForm() {
		super();
	}

	public CentroForm(final Centro centro) {
		this.centro = centro;
		this.horarios = new ArrayList<>();
	}

	public Centro getCentro() {
		return this.centro;
	}

	public void setCentro(final Centro centro) {
		this.centro = centro;
	}

	public Collection<Horario> getHorarios() {
		return this.horarios;
	}

	public void setHorarios(final Collection<Horario> horarios) {
		this.horarios = horarios;
	}
}
