
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.HorarioRepository;
import domain.Horario;
import domain.Servicio;

@Service
@Transactional
public class HorarioService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private HorarioRepository	horarioRepository;

	@Autowired
	private GestorService		gestorService;

	@Autowired
	private ServicioService		servicioService;

	@Autowired
	private Validator			validator;


	// Simple crud methods
	// Crear ------------------------------------------------------------------------

	public Horario create(final Servicio servicio) {
		Horario horario;

		horario = new Horario();
		horario.setServicio(servicio);

		return horario;
	}

	//Guardar -----------------------------------------------------------
	public Horario save(final Horario horario) {
		Horario result;

		Assert.notNull(horario);

		this.gestorService.checkPrincipal();
		Assert.isTrue(this.checkHoras(horario), "horas inicio final error");
		Assert.isTrue(this.checkHorarioSolapado(horario), "horario solapado");
		result = this.horarioRepository.save(horario);
		return result;
	}

	public void delete(final Horario horario) {
		Assert.notNull(horario);
		Assert.isTrue(horario.getId() != 0);
		final Collection<Servicio> serviciosGestor;
		Servicio servicio;

		//serviciosGestor = new ArrayList<>(this.servicioService.findServiciosCreatedByGestor());

		servicio = this.servicioService.findServiceByHorarioId(horario.getId());
		//TODO:
		//Comprobamos que el servicio del horario pertenezca a este gestor
		//Assert.isTrue(serviciosGestor.contains(servicio));

		horario.setServicio(null);
		this.horarioRepository.delete(horario);

	}

	public Collection<Horario> findHorariosByServicioId(final int servicioId) {
		Collection<Horario> result;

		result = this.horarioRepository.findHorariosByServicioId(servicioId);

		return result;

	}

	public Horario reconstruct(final Horario horario, final BindingResult bindingResult) {
		Horario result;
		final Horario horarioBd;

		if (horario.getId() == 0)
			result = horario;
		else {
			horarioBd = this.horarioRepository.findOne(horario.getId());
			horario.setId(horarioBd.getId());
			horario.setVersion(horarioBd.getVersion());
			result = horario;
		}
		this.validator.validate(result, bindingResult);
		return result;
	}

	public HashMap<String, Collection<Horario>> agruparHorarios(final Collection<Horario> horarios) {
		HashMap<String, Collection<Horario>> result;
		Collection<Horario> horariosLunes;
		Collection<Horario> horariosMartes;
		Collection<Horario> horariosMiercoles;
		Collection<Horario> horariosJueves;
		Collection<Horario> horariosViernes;
		Collection<Horario> horariosSabado;
		Collection<Horario> horariosDomingo;

		horariosLunes = new ArrayList<>();
		horariosMartes = new ArrayList<>();
		horariosMiercoles = new ArrayList<>();
		horariosJueves = new ArrayList<>();
		horariosViernes = new ArrayList<>();
		horariosSabado = new ArrayList<>();
		horariosDomingo = new ArrayList<>();
		result = new HashMap<>();

		for (final Horario h : horarios)
			switch (h.getDiaSemana()) {
			case "Lúnes":
				horariosLunes.add(h);
				break;
			case "Martes":
				horariosMartes.add(h);
				break;
			case "Miércoles":
				horariosMiercoles.add(h);
				break;
			case "Jueves":
				horariosJueves.add(h);
				break;
			case "Viernes":
				horariosViernes.add(h);
				break;
			case "Sábado":
				horariosSabado.add(h);
				break;
			case "Domingo":
				horariosDomingo.add(h);
				break;

			}
		result.put("Lunes", horariosLunes);
		result.put("Martes", horariosMartes);
		result.put("Miércoles", horariosMiercoles);
		result.put("Jueves", horariosJueves);
		result.put("Viernes", horariosViernes);
		result.put("Sábado", horariosSabado);
		result.put("Domingo", horariosDomingo);

		return result;
	}

	public Horario findOne(final int horarioId) {
		Assert.isTrue(horarioId != 0);

		Horario result;

		result = this.horarioRepository.findOne(horarioId);
		return result;
	}

	public Collection<Horario> findHorariosByDiaSemanaYServicioId(final Horario horario) {
		Collection<Horario> result;
		result = new ArrayList<>();

		result = this.horarioRepository.findHorariosByDiaSemanaYServicioId(horario.getDiaSemana(), horario.getServicio().getId());
		return result;
	}

	public boolean checkHorarioSolapado(final Horario horario) {
		Collection<Horario> horariosBD;
		boolean result;

		result = true;
		horariosBD = this.findHorariosByDiaSemanaYServicioId(horario);
		final String horaInicio_s = horario.getHoraInicio() + "." + horario.getMinutosInicio();
		final String horaFin_s = horario.getHoraFin() + "." + horario.getMinutosFin();

		final double horaInicio = Double.parseDouble(horaInicio_s);
		final double horaFin = Double.parseDouble(horaFin_s);

		for (final Horario hBD : horariosBD) {
			final String horaInicioBD_s = hBD.getHoraInicio() + "." + hBD.getMinutosInicio();
			final String horaFinBD_s = hBD.getHoraFin() + "." + hBD.getMinutosFin();

			final double horaInicioBD = Double.parseDouble(horaInicioBD_s);
			final double horaFinBD = Double.parseDouble(horaFinBD_s);

			if ((horaInicio == horaInicioBD) || (horaInicio < horaInicioBD && horaFin > horaInicioBD) || (horaInicio > horaInicioBD && horaFin <= horaFinBD) || (horaInicio > horaInicioBD && horaInicio < horaFinBD)) {
				result = false;
				break;
			}
		}

		return result;
	}

	public boolean checkHoras(final Horario horario) {
		boolean result;
		final String horaInicio_s = horario.getHoraInicio() + "." + horario.getMinutosInicio();
		final String horaFin_s = horario.getHoraFin() + "." + horario.getMinutosFin();

		final double horaInicio = Double.parseDouble(horaInicio_s);
		final double horaFin = Double.parseDouble(horaFin_s);
		result = true;

		if (horaInicio == horaFin || horaInicio > horaFin)
			result = false;
		return result;
	}

}
