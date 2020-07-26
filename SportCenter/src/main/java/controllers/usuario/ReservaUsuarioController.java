
package controllers.usuario;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import services.ReservaService;
import services.ServicioService;
import services.UsuarioService;
import domain.Reserva;
import domain.Servicio;
import domain.Usuario;

@Controller
@RequestMapping("/reserva/usuario")
public class ReservaUsuarioController {

	//	Services --------------------------------------------------------

	@Autowired
	private ReservaService	reservaService;

	@Autowired
	private ServicioService	servicioService;

	@Autowired
	private UsuarioService	usuarioService;


	// Display ----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int reservaId) {
		final ModelAndView result;
		Reserva reserva = new Reserva();

		reserva = this.reservaService.findOne(reservaId);

		//TODOS LOS ARTCULOS DE UN PERIDICO

		result = new ModelAndView("reserva/display");
		result.addObject("reserva", reserva);
		result.addObject("requestURI", "reserva/usuario/display.do");

		return result;
	}
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

	//Cancelación	------------------------------------------------------------
	@RequestMapping(value = "/cancelar", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int reservaId) {
		ModelAndView result;
		final Reserva reserva;
		Usuario usuario;
		Collection<Reserva> reservasUsuario;
		reservasUsuario = new ArrayList<>();

		usuario = this.usuarioService.findByPrincipal();

		reservasUsuario = new ArrayList<>(this.reservaService.findReservasByUsuarioId(usuario.getId()));
		reserva = this.reservaService.findOne(reservaId);
		Assert.isTrue(reservasUsuario.contains(reserva));

		try {
			this.reservaService.delete(reserva);
			result = new ModelAndView("redirect:list.do?d-16544-p=1");
		} catch (final Throwable oops) {
			if (oops.getMessage().equals("error fecha cercana"))
				result = this.lista("reserva.commit.error.cercana");
			else
				result = this.lista("reserva.commit.error");
		}
		return result;
	}

	protected ModelAndView lista(final String message) {
		final ModelAndView result;
		Collection<Reserva> reservas;
		Usuario usuario;

		usuario = this.usuarioService.findByPrincipal();
		reservas = this.reservaService.findReservasByUsuarioId(usuario.getId());
		result = new ModelAndView("reserva/list");
		result.addObject("requestURI", "reserva/usuario/list.do");
		result.addObject("reservas", reservas);
		result.addObject("message", message);
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
				result = new ModelAndView("redirect:/reserva/usuario/list.do?");
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("fecha reserva pasado"))
					result = this.createEditModelAndView(reserva, "request.reserva.pasado");
				else
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

	// List ---------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Usuario usuarioConectado;
		Collection<Reserva> reservas;
		reservas = new ArrayList<>();

		usuarioConectado = this.usuarioService.findByPrincipal();
		reservas = this.reservaService.findReservasByUsuarioId(usuarioConectado.getId());

		result = new ModelAndView("reserva/list");
		result.addObject("requestURI", "reserva/usuario/list.do");
		result.addObject("reservas", reservas);
		//result.addObject("mostrarBotonGestor", true);

		return result;
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
		result.addObject("servicioId", reserva.getServicio().getId());
		//result.addObject("requestURI", "horario/edit.do");

		return result;
	}

}
