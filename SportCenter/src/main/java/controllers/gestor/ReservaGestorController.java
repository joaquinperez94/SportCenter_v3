
package controllers.gestor;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		final Reserva reserva;
		Gestor gestor;
		Collection<Reserva> reservasUsuario;
		reservasUsuario = new ArrayList<>();

		gestor = this.gestorService.findByPrincipal();

		reservasUsuario = new ArrayList<>(this.reservaService.findReservasByUsuarioId(usuario.getId()));
		reserva = this.reservaService.findOne(reservaId);
		Assert.isTrue(reservasUsuario.contains(reserva));

		try {
			//this.reservaService.deleteGestor(reserva);
			result = new ModelAndView("redirect:list.do?d-16544-p=1");
		} catch (final Throwable oops) {
			if (oops.getMessage().equals("error fecha cercana"))
				result = this.lista("reserva.commit.error.cercana");
			else
				result = this.lista("reserva.commit.error");
		}
		return result;
	}
}
