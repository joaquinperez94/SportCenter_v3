
package controllers.gestor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.GestorService;
import services.HorarioService;
import services.ServicioService;
import controllers.AbstractController;
import domain.Horario;
import domain.Servicio;

@Controller
@RequestMapping("/horario/gestor")
public class HorarioGestorController extends AbstractController {

	//	Services --------------------------------------------------------

	@Autowired
	private HorarioService	horarioService;

	@Autowired
	private GestorService	gestorService;

	@Autowired
	private ServicioService	servicioService;


	// List ---------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int servicioId) {
		ModelAndView result;
		final Collection<Horario> horarios;
		Collection<Horario> horariosLunes;
		Collection<Horario> horariosMartes;
		Collection<Horario> horariosMiercoles;
		Collection<Horario> horariosJueves;
		Collection<Horario> horariosViernes;
		Collection<Horario> horariosSabado;
		Collection<Horario> horariosDomingo;
		HashMap<String, Collection<Horario>> horarios_agrupados;
		horarios = this.horarioService.findHorariosByServicioId(servicioId);
		horarios_agrupados = new HashMap<>(this.horarioService.agruparHorarios(horarios));
		horariosLunes = new ArrayList<>(horarios_agrupados.get("Lunes"));
		horariosMartes = new ArrayList<>(horarios_agrupados.get("Martes"));
		horariosMiercoles = new ArrayList<>(horarios_agrupados.get("Miércoles"));
		horariosJueves = new ArrayList<>(horarios_agrupados.get("Jueves"));
		horariosViernes = new ArrayList<>(horarios_agrupados.get("Viernes"));
		horariosSabado = new ArrayList<>(horarios_agrupados.get("Sábado"));
		horariosDomingo = new ArrayList<>(horarios_agrupados.get("Domingo"));

		result = new ModelAndView("horario/list");
		result.addObject("horariosLunes", horariosLunes);
		result.addObject("horariosMartes", horariosMartes);
		result.addObject("horariosMiercoles", horariosMiercoles);
		result.addObject("horariosJueves", horariosJueves);
		result.addObject("horariosViernes", horariosViernes);
		result.addObject("horariosSabado", horariosSabado);
		result.addObject("horariosDomingo", horariosDomingo);
		result.addObject("servicioId", servicioId);
		result.addObject("requestURI", "/horario/gestor/list.do");

		return result;
	}
	//Crear	------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView crearHorario(@RequestParam final int servicioId) {
		ModelAndView result;
		Horario horario;
		final Servicio servicio;

		servicio = this.servicioService.findOne(servicioId);
		horario = this.horarioService.create(servicio);

		result = this.createEditModelAndView(horario);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int horarioId) {
		ModelAndView result;
		final Horario horario;
		Servicio servicio;
		Assert.notNull(horarioId);
		servicio = new Servicio();
		horario = this.horarioService.findOne(horarioId);
		servicio = this.servicioService.findServiceByHorarioId(horarioId);

		//TODO: RESTRICCIONES

		result = this.createEditModelAndView(horario);
		return result;
	}

	//Guardar	------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Horario horario, final BindingResult binding) {
		ModelAndView result;

		horario = this.horarioService.reconstruct(horario, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(horario);
		else
			try {
				this.horarioService.save(horario);
				result = new ModelAndView("redirect:/horario/gestor/list.do?servicioId=" + horario.getServicio().getId());
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("horario solapado"))
					result = this.createEditModelAndView(horario, "request.horario.solapado");
				else if (oops.getMessage().equals("horas inicio final error"))
					result = this.createEditModelAndView(horario, "request.horario.inicio.final.error");
				else if (oops.getMessage().equals("horario duración errónea"))
					result = this.createEditModelAndView(horario, "request.horario.duracion.novalida");
				else
					result = this.createEditModelAndView(horario, "horario.commit.error");
			}
		return result;
	}

	//Delete -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute final Horario horario, final BindingResult bindingResult) {
		ModelAndView result;
		try {
			//Solo un buyer podr eliminar un curso
			Assert.isTrue(this.gestorService.checkPrincipalBoolean());
			final int serviceId = horario.getServicio().getId();
			this.horarioService.delete(horario);
			result = new ModelAndView("redirect:/horario/gestor/list.do?servicioId=" + serviceId);
		} catch (final Throwable oops) {
			//TODO: Restrcciones
			result = this.createEditModelAndView(horario, "lesson.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Horario horario) {
		ModelAndView result;
		result = this.createEditModelAndView(horario, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Horario horario, final String message) {
		ModelAndView result;
		result = new ModelAndView("horario/edit");
		result.addObject("horario", horario);
		result.addObject("message", message);
		result.addObject("duracion", horario.getServicio().getDuración());
		//result.addObject("requestURI", "horario/edit.do");

		return result;
	}

}
