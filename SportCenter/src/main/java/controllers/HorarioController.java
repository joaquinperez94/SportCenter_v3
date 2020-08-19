
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.HorarioService;
import domain.Horario;

@Controller
@RequestMapping("/horario")
public class HorarioController extends AbstractController {

	//	Services --------------------------------------------------------
	@Autowired
	private HorarioService	horarioService;


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
		result.addObject("requestURI", "/horario/list.do");

		return result;
	}

}
