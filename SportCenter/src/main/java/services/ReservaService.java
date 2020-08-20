
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReservaRepository;
import domain.Gestor;
import domain.Horario;
import domain.Reserva;
import domain.Servicio;
import domain.Usuario;

@Service
@Transactional
public class ReservaService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ReservaRepository	reservaRepository;

	@Autowired
	private GestorService		gestorService;

	@Autowired
	private UsuarioService		usuarioService;
	@Autowired
	private HorarioService		horarioService;
	@Autowired
	private ServicioService		servicioService;


	// Crear ------------------------------------------------------------------------
	public Reserva create(final Servicio servicio) {
		Reserva reserva;
		Usuario usuarioPrincipal;

		reserva = new Reserva();
		usuarioPrincipal = this.usuarioService.findByPrincipal();

		reserva.setServicio(servicio);
		reserva.setUsuario(usuarioPrincipal);
		reserva.setActiva(true);

		return reserva;
	}

	//Guardar -----------------------------------------------------------
	public Reserva save(final Reserva reserva) {
		Reserva result;
		Date fechaReserva;

		fechaReserva = new Date(System.currentTimeMillis() - 1000);
		reserva.setFechaRealizacion(fechaReserva);

		final String duracionS = String.valueOf(reserva.getServicio().getDuración());
		final int indexOfDecimal = duracionS.indexOf(".");
		final String hora = duracionS.substring(0, indexOfDecimal);
		final String minutos = duracionS.substring(indexOfDecimal).replace(".", "");
		double duracionD;
		final double horaD = Double.parseDouble(hora) * 60.;
		final double minutosD = Double.parseDouble(minutos);

		if (horaD != 0)
			duracionD = horaD + minutosD;
		else
			duracionD = minutosD;

		final double horaReserva = Double.parseDouble(reserva.getHoraInicio().split(":")[0]) * 60;
		final double minutosReserva = Double.parseDouble(reserva.getHoraInicio().split(":")[1]);
		final double reservaInicial = horaReserva + minutosReserva;
		final long totalReserva = (long) (reservaInicial + duracionD);

		final long horasReales = TimeUnit.MINUTES.toHours(totalReserva);
		final long minutosReales = TimeUnit.MINUTES.toMinutes(totalReserva) - TimeUnit.HOURS.toMinutes(TimeUnit.MINUTES.toHours(totalReserva));

		String horaReservaFinal = "";
		if (String.valueOf(horasReales).length() == 1 && String.valueOf(minutosReales).length() == 1)
			horaReservaFinal = "0" + horasReales + ":" + "0" + minutosReales;
		else if (String.valueOf(horasReales).length() == 1)
			horaReservaFinal = "0" + horasReales + ":" + minutosReales;
		else if (String.valueOf(minutosReales).length() == 1)
			horaReservaFinal = horasReales + ":" + "0" + minutosReales;
		else
			horaReservaFinal = horasReales + ":" + minutosReales;

		reserva.setHoraFin(horaReservaFinal);
		Assert.notNull(reserva);

		this.usuarioService.checkPrincipal();
		Assert.isTrue(this.checkFechaReserva(reserva), "fecha reserva pasado");
		result = this.reservaRepository.save(reserva);
		return result;
	}

	//Eliminar -----------------------------------------------------------
	public void delete(final Reserva reserva) {
		Assert.notNull(reserva);
		Assert.isTrue(reserva.getId() != 0);
		final Collection<Servicio> serviciosGestor;
		final Servicio servicio;
		Date date;
		date = new Date(System.currentTimeMillis() - 1000);
		final long diff = reserva.getFechaReserva().getTime() - date.getTime();
		final long dias = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		Assert.isTrue(dias >= 2, "error fecha cercana");
		reserva.setServicio(null);
		this.reservaRepository.delete(reserva);

	}

	public Collection<Reserva> findReservasByFechaReservaAndServiceId(final Date fecha, final int serviceId) {
		Collection<Reserva> result;
		result = new ArrayList<>();
		Assert.notNull(fecha);
		Assert.notNull(serviceId);
		//final Calendar cal = Calendar.getInstance();
		//cal.setTime(fecha);
		try {
			result = this.reservaRepository.findReservasByFechaReservaAndServiceId(fecha, serviceId);
			//result = this.reservaRepository.findReservasByFechaReservaAndServiceId(cal.getTime(), serviceId);
		} catch (final Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public Collection<Reserva> findReservasByUsuarioId(final int usuarioId) {
		Collection<Reserva> result;
		result = new ArrayList<>();
		Assert.notNull(usuarioId);
		result = this.reservaRepository.findReservasByUsuarioId(usuarioId);

		return result;
	}

	public Reserva findOne(final int reservaId) {
		Assert.isTrue(reservaId != 0);
		Reserva result;
		result = this.reservaRepository.findOne(reservaId);
		return result;
	}

	public Collection<String> obtenerReservas(final String body) {
		Collection<String> result;
		result = new ArrayList<>();
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Collection<Horario> horarios;
			Collection<Reserva> reservas;
			Collection<String> reservasOcupadas;
			reservasOcupadas = new ArrayList<>();
			final String[] parts = body.split("&");
			final String date = parts[0].replace("date=", "");
			final int servicioId = Integer.parseInt(parts[1].replace("serviceId=", ""));
			final Date date1 = formatter.parse(date);
			final Calendar c = Calendar.getInstance();
			c.setTime(date1);
			final int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			final String diaSemana = this.getDayOfWeek(dayOfWeek);
			double duracionD;

			reservas = new ArrayList<>(this.findReservasByFechaReservaAndServiceId(date1, servicioId));
			if (reservas != null || reservas.size() > 0)
				for (final Reserva r : reservas)
					reservasOcupadas.add(r.getHoraInicio());

			horarios = new ArrayList<>(this.horarioService.findHorariosByDiaSemanaYServicioId(diaSemana, servicioId));
			final double duracion = this.servicioService.findOne(servicioId).getDuración();
			//Obtengo una lista de horarios.

			for (final Horario h : horarios) {
				final int horaInicio = Integer.parseInt(h.getHoraInicio()) * 60;
				final int horaFin = Integer.parseInt(h.getHoraFin()) * 60;
				final int minutosInicio = Integer.parseInt(h.getMinutosInicio());
				final int minutosFin = Integer.parseInt(h.getMinutosFin());

				final int inicio = horaInicio + minutosInicio;
				final int fin = horaFin + minutosFin;
				final int actual = inicio;

				final String duracionS = String.valueOf(duracion);
				final int indexOfDecimal = duracionS.indexOf(".");
				final String hora = duracionS.substring(0, indexOfDecimal);
				final String minutos = duracionS.substring(indexOfDecimal).replace(".", "");

				final double horaD = Double.parseDouble(hora) * 60.;
				final double minutosD = Double.parseDouble(minutos);

				if (horaD != 0)
					duracionD = horaD + minutosD;
				else
					duracionD = minutosD;

				if (fin - actual <= duracionD) {
					//cuando no hay iteraciones solo una
				} else {
					final int iteraciones = (int) ((fin - inicio) / duracionD);
					for (int i = 0; i < iteraciones; i++) {
						final int suma = (int) (i * duracionD);
						final int reservaInicio = inicio + suma;
						final long horasReales = TimeUnit.MINUTES.toHours(reservaInicio);
						final long minutosReales = TimeUnit.MINUTES.toMinutes(reservaInicio) - TimeUnit.HOURS.toMinutes(TimeUnit.MINUTES.toHours(reservaInicio));

						String horaReserva = "";
						if (String.valueOf(horasReales).length() == 1 && String.valueOf(minutosReales).length() == 1)
							horaReserva = "0" + horasReales + ":" + "0" + minutosReales;
						else if (String.valueOf(horasReales).length() == 1)
							horaReserva = "0" + horasReales + ":" + minutosReales;
						else if (String.valueOf(minutosReales).length() == 1)
							horaReserva = horasReales + ":" + "0" + minutosReales;
						else
							horaReserva = horasReales + ":" + minutosReales;

						if (!reservasOcupadas.contains(horaReserva))
							result.add(horaReserva);
					}
				}
			}

		} catch (final ParseException e) {
			return result;
		}
		return result;
	}

	public String getDayOfWeek(final int numberDay) {
		String dia = "";
		switch (numberDay) {
		case 1:
			// domingo
			dia = "Domingo";
			break;
		case 2:
			// lunes
			dia = "Lúnes";
			break;
		case 3:
			dia = "Martes";
			// martes
			break;
		case 4:
			// miercoles
			dia = "Miércoles";
			break;
		case 5:
			// jueves
			dia = "Jueves";
			break;
		case 6:
			// viernes
			dia = "Viernes";
			break;
		case 7:
			// domingo
			dia = "Sábado";
			break;
		default:
			// code block
		}
		return dia;
	}

	public boolean checkFechaReserva(final Reserva reserva) {
		boolean result;
		result = true;
		Date dateReserva;
		Date dateNow;
		dateNow = new Date();
		dateReserva = reserva.getFechaReserva();

		final Calendar calNow = Calendar.getInstance();
		final Calendar calReserva = Calendar.getInstance();
		calNow.setTime(dateNow);
		calReserva.setTime(dateReserva);

		calNow.set(Calendar.HOUR_OF_DAY, 0);
		calNow.set(Calendar.MINUTE, 0);
		calNow.set(Calendar.SECOND, 0);
		calNow.set(Calendar.MILLISECOND, 0);
		dateNow = calNow.getTime();

		if (dateReserva.before(dateNow))
			result = false;
		else if ((calNow.get(Calendar.DAY_OF_WEEK_IN_MONTH) == calReserva.get(Calendar.DAY_OF_WEEK_IN_MONTH)) && (calNow.get(Calendar.MONTH) == calReserva.get(Calendar.MONTH)) && (calNow.get(Calendar.YEAR) == calReserva.get(Calendar.YEAR))) {

			final String horarioInicio = reserva.getHoraInicio();

			final int horaInicio = Integer.parseInt(horarioInicio.split(":")[0]);
			final int minutosInicio = Integer.parseInt(horarioInicio.split(":")[1]);
			final int horaActual = calNow.get(Calendar.HOUR_OF_DAY);
			final int minutosActual = calNow.get(Calendar.MINUTE);

			if (horaInicio < horaActual)
				result = false;
			else if (horaInicio == horaActual)
				if (minutosInicio < minutosActual)
					result = false;

		}

		return result;
	}

	public void cancelarReservaGestor(final int reservaId) {
		final Reserva reserva;
		Gestor gestor;
		Collection<Servicio> serviciosGestor;
		serviciosGestor = new ArrayList<>();

		reserva = this.reservaRepository.findOne(reservaId);
		gestor = this.gestorService.findByPrincipal();
		serviciosGestor = new ArrayList<>(this.servicioService.findServiciosByGestorId(gestor.getId()));
		Assert.isTrue(serviciosGestor.contains(reserva.getServicio()));
		//reserva.setActiva(false);
		this.reservaRepository.delete(reserva);
		//this.reservaRepository.save(reserva);
	}

	public HashMap<String, Collection<Reserva>> reservasDeServiciosGestor(final int gestorId) {
		HashMap<String, Collection<Reserva>> result;
		Collection<Reserva> reservasHoy;
		Collection<Reserva> reservasOtrosDias;
		Collection<Servicio> serviciosGestor;
		final Calendar calNow = Calendar.getInstance();
		calNow.set(Calendar.HOUR_OF_DAY, 0);
		calNow.set(Calendar.MINUTE, 0);
		calNow.set(Calendar.SECOND, 0);
		calNow.set(Calendar.MILLISECOND, 0);

		result = new HashMap<>();
		reservasHoy = new ArrayList<>();
		reservasOtrosDias = new ArrayList<>();
		serviciosGestor = new ArrayList<>(this.servicioService.findServiciosByGestorId(gestorId));
		for (final Servicio s : serviciosGestor)
			for (final Reserva r : s.getReservas()) {
				final Calendar calReserva = Calendar.getInstance();
				calReserva.setTime(r.getFechaReserva());
				if (calNow == calReserva)
					reservasHoy.add(r);
				else
					reservasOtrosDias.add(r);
			}
		result.put("hoy", reservasHoy);
		result.put("otros", reservasOtrosDias);

		return result;
	}

}
