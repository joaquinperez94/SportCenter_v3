
package controllers.centro;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.HorarioService;
import controllers.AbstractController;
import domain.Horario;
import forms.HorarioForm;

@Controller
@RequestMapping("/horario/gestor")
public class HorarioGestorController extends AbstractController {

	//	Services --------------------------------------------------------

	@Autowired
	private HorarioService	horarioService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView crearHorario() {
		ModelAndView result;
		Collection<Horario> horarios;
		horarios = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			Horario horario;
			horario = this.horarioService.create();
			horarios.add(horario);
		}

		HorarioForm horarioForm;
		horarioForm = new HorarioForm();
		horarioForm.setHorarios(horarios);

		result = new ModelAndView("horario/edit");
		result.addObject("horarioForm", horarioForm);
		//result.addObject("horarios", horarios);
		//result.addObject("requestURI", "horario/gestor/edit.do");

		return result;
	}

	//Guardar	------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView guardarGestor(@ModelAttribute("horarioForm") final HorarioForm horarioForm, final BindingResult binding) {
		ModelAndView result;

		final HorarioForm horarioForm2 = this.horarioService.reconstruct(horarioForm, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(horarioForm2);
		else
			try {

				for (final Horario horario : horarioForm.getHorarios()) {

					Assert.isTrue(!this.horarioService.checkHorarios(horarioForm2.getHorarios()), "horario.inicio.error");
					this.horarioService.save(horario);
				}

				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {

				result = this.createEditModelAndView(horarioForm, "gestor.commit.error");
			}
		return result;
	}
	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final HorarioForm horarioForm) {
		ModelAndView result;
		result = this.createEditModelAndView(horarioForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final HorarioForm horarioForm, final String message) {
		ModelAndView result;
		result = new ModelAndView("horario/edit");
		result.addObject("horarioForm", horarioForm);
		result.addObject("message", message);
		result.addObject("requestURI", "horario/edit.do");

		return result;
	}

}
