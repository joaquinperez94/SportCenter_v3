
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.GestorService;
import domain.Gestor;
import forms.GestorForm;

@Controller
@RequestMapping("/gestor")
public class GestorController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private GestorService	gestorService;


	//Constructor--------------------------------------------------------

	public GestorController() {
		super();
	}

	//Crear ------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView crearGestor() {
		ModelAndView result;
		Gestor gestor;

		gestor = this.gestorService.create();

		GestorForm u;
		u = new GestorForm(gestor);

		result = new ModelAndView("gestor/edit");
		result.addObject("gestorForm", u);
		result.addObject("requestURI", "gestor/edit.do");
		result.addObject("create", true);

		return result;
	}

	//Guardar	------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView guardarGestor(@ModelAttribute("gestorForm") GestorForm gestorForm, final BindingResult binding) {
		ModelAndView result;

		gestorForm = this.gestorService.reconstruct(gestorForm, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(gestorForm);
		else
			try {
				if ((gestorForm.getGestor().getId() == 0)) {
					Assert.isTrue(gestorForm.getGestor().getUserAccount().getPassword().equals(gestorForm.getPasswordCheck()), "password does not match");
					Assert.isTrue(gestorForm.getConditions(), "the conditions must be accepted");
				}
				this.gestorService.save(gestorForm.getGestor());
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("password does not match"))
					result = this.createEditModelAndView(gestorForm, "gestor.contrasena.coincide");
				else if (oops.getMessage().equals("the conditions must be accepted"))
					result = this.createEditModelAndView(gestorForm, "gestor.condiciones");
				//else if (oops.getMessage().equals("could not execute statement; SQL [n/a]; constraint [null]" + "; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"))
				//result = this.createEditModelAndView(gestorForm, "recycler.commit.error.duplicateProfile");
				else
					result = this.createEditModelAndView(gestorForm, "gestor.commit.error");
			}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final GestorForm gestorForm) {
		ModelAndView result;
		result = this.createEditModelAndView(gestorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final GestorForm gestorForm, final String message) {
		ModelAndView result;
		result = new ModelAndView("gestor/edit");
		result.addObject("gestor", gestorForm);
		result.addObject("message", message);
		result.addObject("requestURI", "gestor/edit.do");

		return result;
	}

}
