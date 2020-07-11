
package controllers;

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
@RequestMapping("/usuario")
public class UsuarioController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private UsuarioService	usuarioService;


	//Constructor--------------------------------------------------------

	public UsuarioController() {
		super();
	}

	//Crear ------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView crearUsuario() {
		ModelAndView result;
		Usuario usuario;

		usuario = this.usuarioService.create();

		UsuarioForm u;
		u = new UsuarioForm(usuario);

		result = new ModelAndView("usuario/edit");
		result.addObject("usuarioForm", u);
		result.addObject("requestURI", "usuario/edit.do");

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
				result = new ModelAndView("redirect:/welcome/index.do");
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

	// Ancillary methods ------------------------------------------------------

	// Ancillary methods ------------------------------------------------------
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
