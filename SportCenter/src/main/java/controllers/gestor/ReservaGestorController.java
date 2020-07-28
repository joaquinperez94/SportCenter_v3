
package controllers.gestor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.GestorService;
import services.ReservaService;
import controllers.AbstractController;
import domain.Gestor;
import domain.Reserva;

@Controller
@RequestMapping("/reserva/gestor")
public class ReservaGestorController extends AbstractController {

	//	Services --------------------------------------------------------
	@Autowired
	private ReservaService	reservaService;

	@Autowired
	private GestorService	gestorService;


	// Display ----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int reservaId) {
		final ModelAndView result;
		Reserva reserva = new Reserva();

		reserva = this.reservaService.findOne(reservaId);

		//TODOS LOS ARTCULOS DE UN PERIDICO

		result = new ModelAndView("reserva/display");
		result.addObject("reserva", reserva);
		result.addObject("requestURI", "reserva/gestor/display.do");

		return result;
	}

	//Cancelación	------------------------------------------------------------
	@RequestMapping(value = "/cancelar", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int reservaId) {
		ModelAndView result;

		try {
			this.reservaService.cancelarReservaGestor(reservaId);
			result = new ModelAndView("redirect:list.do?d-16544-p=1");
		} catch (final Throwable oops) {
			if (oops.getMessage().equals("error fecha cercana"))
				result = this.lista("reserva.commit.error.cercana");
			else
				result = this.lista("reserva.commit.error");
		}
		return result;
	}

	// List ---------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Gestor gestorConectado;
		HashMap<String, Collection<Reserva>> reservas;
		Collection<Reserva> reservasHoy;
		Collection<Reserva> reservasOtros;

		gestorConectado = this.gestorService.findByPrincipal();
		reservas = new HashMap<>(this.reservaService.reservasDeServiciosGestor(gestorConectado.getId()));
		reservasHoy = new ArrayList<>(reservas.get("hoy"));
		reservasOtros = new ArrayList<>(reservas.get("otros"));

		result = new ModelAndView("reserva/list");
		result.addObject("requestURI", "reserva/gestor/list.do");
		result.addObject("reservasHoy", reservasHoy);
		result.addObject("reservasOtros", reservasOtros);
		//result.addObject("mostrarBotonGestor", true);

		return result;
	}

	protected ModelAndView lista(final String message) {
		final ModelAndView result;
		Gestor gestorConectado;
		HashMap<String, Collection<Reserva>> reservas;
		Collection<Reserva> reservasHoy;
		Collection<Reserva> reservasOtros;

		gestorConectado = this.gestorService.findByPrincipal();
		reservas = new HashMap<>(this.reservaService.reservasDeServiciosGestor(gestorConectado.getId()));
		reservasHoy = new ArrayList<>(reservas.get("hoy"));
		reservasOtros = new ArrayList<>(reservas.get("otros"));

		result = new ModelAndView("reserva/list");
		result.addObject("requestURI", "reserva/gestor/list.do");
		result.addObject("reservasHoy", reservasHoy);
		result.addObject("reservasOtros", reservasOtros);
		return result;

	}
}
