
package controllers.usuario;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import services.HorarioService;
import services.ReservaService;
import services.ServicioService;
import domain.Reserva;
import domain.Servicio;

@Controller
@RequestMapping("/reserva/usuario")
public class ReservaUsuarioController {

	//	Services --------------------------------------------------------

	@Autowired
	private ReservaService	reservaService;

	@Autowired
	private ServicioService	servicioService;

	@Autowired
	private HorarioService	horarioService;


	//Crear	------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView crearHorario(@RequestParam final int servicioId) {
		ModelAndView result;
		Reserva reserva;
		final Servicio servicio;

		servicio = this.servicioService.findOne(servicioId);
		reserva = this.reservaService.create(servicio);

		result = this.createEditModelAndView(reserva);
		result.addObject("servicioId", servicioId);
		return result;
	}

	//Guardar	------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Reserva reserva, final BindingResult binding) {
		ModelAndView result;

		//horario = this.horarioService.reconstruct(horario, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(reserva);
		else
			try {
				this.reservaService.save(reserva);
				result = new ModelAndView("redirect:/horario/gestor/list.do?servicioId=" + reserva.getServicio().getId());
			} catch (final Throwable oops) {
				//if (oops.getMessage().equals("horario solapado"))
				//result = this.createEditModelAndView(horario, "request.horario.solapado");
				//else if (oops.getMessage().equals("horas inicio final error"))
				//result = this.createEditModelAndView(horario, "request.horario.inicio.final.error");
				//else if (oops.getMessage().equals("horario duración errónea"))
				//result = this.createEditModelAndView(horario, "request.horario.duracion.novalida");
				//else
				result = this.createEditModelAndView(reserva, "horario.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/obtenerReservasDisponibles", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Collection<String> obtenerReservasDisponibles(@RequestBody final String date, @RequestBody final String serviceId) {
		//if (date != null) {
		Collection<String> reservasDisponibles;
		reservasDisponibles = new ArrayList<>(this.reservaService.obtenerReservas(date));

		return reservasDisponibles;

	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Reserva reserva) {
		ModelAndView result;
		result = this.createEditModelAndView(reserva, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Reserva reserva, final String message) {
		ModelAndView result;
		result = new ModelAndView("reserva/edit");
		result.addObject("reserva", reserva);
		result.addObject("message", message);
		//result.addObject("requestURI", "horario/edit.do");

		return result;
	}

}
