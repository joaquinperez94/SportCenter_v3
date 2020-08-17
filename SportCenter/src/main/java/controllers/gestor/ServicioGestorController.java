
package controllers.gestor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import services.CentroService;
import services.GestorService;
import services.ServicioService;
import controllers.AbstractController;
import domain.Centro;
import domain.Gestor;
import domain.Servicio;

@Controller
@RequestMapping("/servicio/gestor")
public class ServicioGestorController extends AbstractController {

	//	Services --------------------------------------------------------

	@Autowired
	private ServicioService	servicioService;

	@Autowired
	private CentroService	centroService;

	@Autowired
	private GestorService	gestorService;


	//Display-----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView verServicio(@RequestParam final int servicioId) {
		final ModelAndView result;
		Servicio servicio;

		servicio = new Servicio();

		servicio = this.servicioService.findOne(servicioId);

		result = new ModelAndView("servicio/display");
		result.addObject("servicio", servicio);
		result.addObject("requestURI", "servicio/gestor/display.do");

		return result;
	}

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
	public ModelAndView save(@Valid final Servicio servicio, final BindingResult binding, @RequestParam(required = false, defaultValue = "None") final MultipartFile file) {
		ModelAndView result;

		//servicio = this.servicioService.reconstruct(servicio, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(servicio);
		else
			try {

				this.servicioService.save(servicio, file);
				result = new ModelAndView("redirect:/centro/gestor/display.do?centroId=" + servicio.getCentro().getId());
			} catch (final Throwable oops) {
				if (oops.getMessage().contains("Failed to convert property value of type java.lang.String to required type java.lang.Double for property precio; nested exception is java.lang.NumberFormatException:"))
					result = this.createEditModelAndView(servicio, "servicio.error.double");
				else if (oops.getMessage().equals("duracion no valida"))
					result = this.createEditModelAndView(servicio, "request.servicio.duracion.error");
				else if (oops.getMessage().equals("duracion no cumple patron"))
					result = this.createEditModelAndView(servicio, "request.servicio.duracion.patron");
				else if (oops.getMessage().equals("ya existe servicio"))
					result = this.createEditModelAndView(servicio, "request.servicio.existe");
				else
					result = this.createEditModelAndView(servicio, "servicio.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int servicioId) {
		ModelAndView result;
		final Servicio servicio;
		Centro centro;
		Assert.notNull(servicioId);
		final Collection<Centro> centrosOfGestor;
		Gestor gestor;

		gestor = this.gestorService.findByPrincipal();
		centro = new Centro();

		//centrosOfGestor = new ArrayList<>(this.centroService.findCentrosByGestor(gestor.getId()));
		servicio = this.servicioService.findOne(servicioId);
		centro = this.centroService.findCentroByServiceId(servicioId);

		//Assert.isTrue(centrosOfGestor.contains(centro));

		result = this.createEditModelAndView(servicio);
		return result;
	}

	//Delete -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute final Servicio servicio, final BindingResult bindingResult) {
		ModelAndView result;
		try {
			Assert.isTrue(this.gestorService.checkPrincipalBoolean());
			final Integer centroId = servicio.getCentro().getId();

			this.servicioService.delete(servicio);
			result = new ModelAndView("redirect:/servicio/gestor/display.do?centroId=" + centroId);
		} catch (final Throwable oops) {
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
