
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
		final String horaI = horario.getHoraInicio().split(":")[0];
		final String minI = horario.getHoraInicio().split(":")[1];
		final String horaF = horario.getHoraFin().split(":")[0];
		final String minF = horario.getHoraFin().split(":")[1];
		horario.setHoraInicio(horaI);
		horario.setMinutosInicio(minI);
		horario.setHoraFin(horaF);
		horario.setMinutosFin(minF);

		Assert.notNull(horario);

		this.gestorService.checkPrincipal();
		Assert.isTrue(this.checkHoras(horario), "horas inicio final error");
		//if (horario.getId() == 0)
		Assert.isTrue(this.checkHorarioSolapado(horario), "horario solapado");
		Assert.isTrue(this.checkHorarioDuracion(horario), "horario duraci�n err�nea");

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
			case "L�nes":
				horariosLunes.add(h);
				break;
			case "Martes":
				horariosMartes.add(h);
				break;
			case "Mi�rcoles":
				horariosMiercoles.add(h);
				break;
			case "Jueves":
				horariosJueves.add(h);
				break;
			case "Viernes":
				horariosViernes.add(h);
				break;
			case "S�bado":
				horariosSabado.add(h);
				break;
			case "Domingo":
				horariosDomingo.add(h);
				break;

			}
		result.put("Lunes", horariosLunes);
		result.put("Martes", horariosMartes);
		result.put("Mi�rcoles", horariosMiercoles);
		result.put("Jueves", horariosJueves);
		result.put("Viernes", horariosViernes);
		result.put("S�bado", horariosSabado);
		result.put("Domingo", horariosDomingo);

		return result;
	}

	public Horario findOne(final int horarioId) {
		Assert.isTrue(horarioId != 0);

		Horario result;

		result = this.horarioRepository.findOne(horarioId);
		return result;
	}

	public Collection<Horario> findHorariosByDiaSemanaYServicioId(final String diaSemana, final int servicioId) {
		Collection<Horario> result;
		result = new ArrayList<>();

		result = this.horarioRepository.findHorariosByDiaSemanaYServicioId(diaSemana, servicioId);
		return result;
	}

	public boolean checkHorarioSolapado(final Horario horario) {
		Collection<Horario> horariosBD;
		boolean result;

		result = true;
		horariosBD = this.findHorariosByDiaSemanaYServicioId(horario.getDiaSemana(), horario.getServicio().getId());
		horariosBD.remove(horario);
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

	public boolean checkHorarioDuracion(final Horario horario) {
		boolean result;
		result = true;
		double duracionD;
		final double horaInicio = Double.parseDouble(horario.getHoraInicio()) * 60.;
		final double horaFin = Double.parseDouble(horario.getHoraFin()) * 60.;
		final double minutosInicio = Double.parseDouble(horario.getMinutosInicio());
		final double minutosFin = Double.parseDouble(horario.getMinutosFin());

		final double inicio = horaInicio + minutosInicio;
		final double fin = horaFin + minutosFin;

		final String duracionS = String.valueOf(horario.getServicio().getDuraci�n());
		final int indexOfDecimal = duracionS.indexOf(".");
		final String hora = duracionS.substring(0, indexOfDecimal);
		final String minutos = duracionS.substring(indexOfDecimal).replace(".", "");

		final double horaD = Double.parseDouble(hora) * 60.;
		final double minutosD = Double.parseDouble(minutos);

		if (horaD != 0)
			duracionD = horaD + minutosD;
		else
			duracionD = minutosD;

		final double modulo = (fin - inicio) % duracionD;
		//final double duracionModulo = Math.round(modulo * 100.0) / 100.0;

		if (modulo != 0)
			result = false;
		return result;
	}

}
