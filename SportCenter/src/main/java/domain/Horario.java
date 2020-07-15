
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Horario extends DomainEntity {

	// Attributes ---------------------------------------------------------
	private String	diaSemana;
	private String	horarioInicioM;
	private String	horarioFinM;


	//private String	horarioInicioT;
	//private String	horarioFinT;

	public String getDiaSemana() {
		return this.diaSemana;
	}

	public void setDiaSemana(final String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getHorarioInicioM() {
		return this.horarioInicioM;
	}

	public void setHorarioInicioM(final String horarioInicioM) {
		this.horarioInicioM = horarioInicioM;
	}

	public String getHorarioFinM() {
		return this.horarioFinM;
	}

	public void setHorarioFinM(final String horarioFinM) {
		this.horarioFinM = horarioFinM;
	}

	/*
	 * public String getHorarioInicioT() {
	 * return this.horarioInicioT;
	 * }
	 * 
	 * public void setHorarioInicioT(final String horarioInicioT) {
	 * this.horarioInicioT = horarioInicioT;
	 * }
	 * 
	 * public String getHorarioFinT() {
	 * return this.horarioFinT;
	 * }
	 * 
	 * public void setHorarioFinT(final String horarioFinT) {
	 * this.horarioFinT = horarioFinT;
	 * }
	 */

}
