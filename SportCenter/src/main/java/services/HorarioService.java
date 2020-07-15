
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.HorarioRepository;
import domain.Horario;
import forms.HorarioForm;

@Service
@Transactional
public class HorarioService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private HorarioRepository	horarioRepository;

	@Autowired
	private GestorService		gestorService;


	// Simple crud methods
	// Crear ------------------------------------------------------------------------

	public Horario create() {
		Horario horario;

		horario = new Horario();

		return horario;
	}

	//Guardar -----------------------------------------------------------
	public Horario save(final Horario horario) {
		Horario result;

		Assert.notNull(horario);

		this.gestorService.checkPrincipal();

		result = this.horarioRepository.save(horario);
		return result;
	}

	public HorarioForm reconstruct(final HorarioForm horarioForm, final BindingResult binding) {
		HorarioForm result;
		Collection<Horario> horariosForm;
		Collection<Horario> horarios;

		result = new HorarioForm();
		horariosForm = new ArrayList<>();
		horarios = new ArrayList<>();
		horariosForm = horarioForm.getHorarios();

		for (final Horario h : horariosForm)
			if (!(h.getDiaSemana().equals("Lúnes") && h.getHorarioInicioM().equals("00,00") && h.getHorarioFinM().equals("00,00"))) {
				final Horario nuevo = new Horario();
				nuevo.setDiaSemana(h.getDiaSemana());
				nuevo.setHorarioInicioM(h.getHorarioInicioM().replace(",", ":"));
				nuevo.setHorarioFinM(h.getHorarioFinM().replace(",", ":"));
				horarios.add(nuevo);
			}
		result.setHorarios(horarios);

		return result;
	}
	public boolean checkHorarios(final Collection<Horario> horarios) {
		Collection<String> diasSemana;
		Collection<Horario> filtrado;
		Collection<Horario> horarios2;
		boolean result;

		result = false;
		diasSemana = new ArrayList<>();
		horarios2 = new ArrayList<>();

		diasSemana.add("Lúnes");
		diasSemana.add("Martes");
		diasSemana.add("Miércoles");
		diasSemana.add("Jueves");
		diasSemana.add("Viernes");
		diasSemana.add("Sábado");
		diasSemana.add("Domingo");

		for (final String dia : diasSemana) {
			filtrado = new ArrayList<>();
			for (final Horario h : horarios)
				if (h != null)
					if (h.getDiaSemana().equals(dia))
						filtrado.add(h);
			if (filtrado.size() > 1)

				for (final Horario h2 : filtrado) {
					final int horaInicio1 = Integer.parseInt(h2.getHorarioInicioM().replace(":", ""));
					final int horaFin1 = Integer.parseInt(h2.getHorarioFinM().replace(":", ""));
					horarios2 = new ArrayList<>(filtrado);
					horarios2.remove(h2);
					for (final Horario h3 : horarios2) {
						final int horaInicio2 = Integer.parseInt(h3.getHorarioInicioM().replace(":", ""));
						final int horaFin2 = Integer.parseInt(h3.getHorarioFinM().replace(":", ""));
						if ((horaInicio1 == horaInicio2) || (horaInicio2 > horaInicio1 && horaInicio2 < horaFin1) || (horaInicio2 < horaInicio1 && horaFin2 < horaInicio1) || (horaInicio1 < horaInicio2 && horaFin2 < horaFin1)) {
							result = true;
							break;
						}
					}
					break;
				}
			break;
		}
		return result;
	}

}
