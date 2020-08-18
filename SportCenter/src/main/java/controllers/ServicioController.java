
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ServicioService;
import domain.Servicio;

@Controller
@RequestMapping("/servicio")
public class ServicioController extends AbstractController {

	//	Services --------------------------------------------------------

	@Autowired
	private ServicioService	servicioService;


	//Display-----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView verServicio(@RequestParam final int servicioId) {
		final ModelAndView result;
		Servicio servicio;

		servicio = new Servicio();

		servicio = this.servicioService.findOne(servicioId);

		result = new ModelAndView("servicio/display");
		result.addObject("servicio", servicio);
		result.addObject("requestURI", "servicio/display.do");

		return result;
	}
}
