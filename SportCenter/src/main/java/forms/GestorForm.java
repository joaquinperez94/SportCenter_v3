
package forms;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import domain.Gestor;

public class GestorForm {

	@Valid
	private Gestor	gestor;
	private String	passwordCheck;
	private Boolean	conditions;


	public GestorForm() {
		super();
	}

	public GestorForm(final Gestor gestor) {
		this.gestor = gestor;
		this.passwordCheck = "";
		this.conditions = false;
	}

	public Gestor getGestor() {
		return this.gestor;
	}

	public void setGestor(final Gestor gestor) {
		this.gestor = gestor;
	}

	@Size(min = 5, max = 32)
	public String getPasswordCheck() {
		return this.passwordCheck;
	}

	public void setPasswordCheck(final String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public Boolean getConditions() {
		return this.conditions;
	}

	public void setConditions(final Boolean conditions) {
		this.conditions = conditions;
	}

}
