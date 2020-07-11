
package forms;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import domain.Usuario;

public class UsuarioForm {

	@Valid
	private Usuario	usuario;
	private String	passwordCheck;
	private Boolean	conditions;


	public UsuarioForm() {
		super();
	}

	public UsuarioForm(final Usuario usuario) {
		this.usuario = usuario;
		this.passwordCheck = "";
		this.conditions = false;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(final Usuario usuario) {
		this.usuario = usuario;
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
