
package forms;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ElementCollection;

import domain.Horario;

public class HorarioForm {

	private Collection<Horario>	horarios;


	public HorarioForm() {
		this.horarios = new ArrayList<Horario>();
	}

	@ElementCollection
	public Collection<Horario> getHorarios() {
		return this.horarios;
	}

	public void setHorarios(final Collection<Horario> horarios) {
		this.horarios = horarios;
	}

}
