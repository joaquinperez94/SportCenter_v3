
package controllers.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UsuarioService;
import domain.Usuario;
import forms.UsuarioForm;

@Controller
@RequestMapping("/perfil/usuario")
public class PerfilUsuarioController {

	// Services---------------------------------------------------------
	@Autowired
	private UsuarioService	usuarioService;


	//Constructor--------------------------------------------------------
	public PerfilUsuarioController() {
		super();
	}

	//Visualizar ------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView displayUser() {
		ModelAndView result;
		Usuario usuario;

		usuario = this.usuarioService.findByPrincipal();

		result = new ModelAndView("usuario/display");
		result.addObject("usuario", usuario);
		//nombre de la vista
		result.addObject("requestURI", "usuario/display.do");

		return result;
	}

	//Editar ------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editarUsuario() {
		ModelAndView result;
		Usuario usuario;

		usuario = this.usuarioService.findByPrincipal();
		UsuarioForm usuarioForm;
		usuarioForm = new UsuarioForm(usuario);
		//nombre de la vista
		result = new ModelAndView("usuario/edit");
		result.addObject("usuarioForm", usuarioForm);
		result.addObject("requestURI", "perfil/usuario/edit.do");

		return result;

	}

	//Guardar	------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView guardarUsuario(@ModelAttribute("usuarioForm") UsuarioForm usuarioForm, final BindingResult binding) {
		ModelAndView result;

		usuarioForm = this.usuarioService.reconstruct(usuarioForm, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(usuarioForm);
		else
			try {
				if ((usuarioForm.getUsuario().getId() == 0)) {
					Assert.isTrue(usuarioForm.getUsuario().getUserAccount().getPassword().equals(usuarioForm.getPasswordCheck()), "password does not match");
					Assert.isTrue(usuarioForm.getConditions(), "the conditions must be accepted");
				}
				this.usuarioService.save(usuarioForm.getUsuario());
				result = new ModelAndView("redirect:/perfil/usuario/display.do");
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("password does not match"))
					result = this.createEditModelAndView(usuarioForm, "usuario.contrasena.coincide");
				else if (oops.getMessage().equals("the conditions must be accepted"))
					result = this.createEditModelAndView(usuarioForm, "usuario.condiciones");
				//else if (oops.getMessage().equals("could not execute statement; SQL [n/a]; constraint [null]" + "; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"))
				//result = this.createEditModelAndView(usuarioForm, "recycler.commit.error.duplicateProfile");
				else
					result = this.createEditModelAndView(usuarioForm, "usuario.commit.error");
			}
		return result;
	}

	// Métodos Auxiliares ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final UsuarioForm usuarioForm) {
		ModelAndView result;
		result = this.createEditModelAndView(usuarioForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final UsuarioForm usuarioForm, final String message) {
		ModelAndView result;
		result = new ModelAndView("usuario/edit");
		result.addObject("usuario", usuarioForm);
		result.addObject("message", message);
		result.addObject("requestURI", "usuario/edit.do");

		return result;
	}
}
