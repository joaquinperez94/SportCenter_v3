
package controllers.centro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CentroService;
import services.ServicioService;
import controllers.AbstractController;
import domain.Centro;
import domain.Servicio;

@Controller
@RequestMapping("/servicio/gestor")
public class ServcicioGestorController extends AbstractController {

	//	Services --------------------------------------------------------

	@Autowired
	private ServicioService	servicioService;

	@Autowired
	private CentroService	centroService;


	// Create -----------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int centroId) {
		ModelAndView result;
		Centro centro;
		Servicio servicio;

		centro = this.centroService.findOne(centroId);
		servicio = this.servicioService.create(centro);

		result = this.createEditModelAndView(servicio);
		return result;

	}

	// Save -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Servicio servicio, final BindingResult binding) {
		ModelAndView result;

		servicio = this.servicioService.reconstruct(servicio, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(servicio);
		else
			try {

				this.servicioService.save(servicio);
				result = new ModelAndView("redirect:display.do?centroId=" + servicio.getCentro().getId());
			} catch (final Throwable oops) {
				//if (oops.getMessage().equals("Summary demasiado pequeo"))
				//result = this.createEditModelAndView(servicio, "request.servicio.summary.min");
				//else if (oops.getMessage().equals("Summary demasiado grande"))
				//result = this.createEditModelAndView(servicio, "request.servicio.summary.max");
				//else
				result = this.createEditModelAndView(servicio, "servicio.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Servicio servicio) {
		Assert.notNull(servicio);
		ModelAndView result;
		result = this.createEditModelAndView(servicio, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Servicio servicio, final String message) {
		assert servicio != null;

		ModelAndView result;

		result = new ModelAndView("servicio/edit");
		result.addObject("servicio", servicio);
		result.addObject("message", message);
		return result;
	}
}
