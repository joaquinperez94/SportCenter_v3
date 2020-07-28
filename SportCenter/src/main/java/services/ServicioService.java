
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ServicioRepository;
import domain.Centro;
import domain.Horario;
import domain.Reserva;
import domain.Servicio;

@Service
@Transactional
public class ServicioService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ServicioRepository	servicioRepository;

	@Autowired
	private GestorService		gestorService;

	@Autowired
	private Validator			validator;


	// Simple crud methods
	// Crear ------------------------------------------------------------------------

	public Servicio create(final Centro centro) {
		Servicio servicio;
		Collection<Reserva> reservas;
		Collection<Horario> horarios;

		servicio = new Servicio();
		reservas = new ArrayList<>();
		horarios = new ArrayList<>();

		servicio.setReservas(reservas);
		servicio.setCentro(centro);
		servicio.setHorarios(horarios);

		return servicio;
	}

	//Guardar -----------------------------------------------------------
	public Servicio save(final Servicio servicio) {
		Servicio result;

		Assert.notNull(servicio);
		Assert.isTrue(servicio.getDuraci�n() > 0.15, "duracion no valida");
		Assert.isTrue(this.checkDuracion(servicio.getDuraci�n()), "duracion no cumple patron");
		this.gestorService.checkPrincipal();

		result = this.servicioRepository.save(servicio);
		return result;
	}

	//Eliminar -----------------------------------------------------------
	public void delete(final Servicio servicio) {

		Assert.notNull(servicio);
		Assert.notNull(this.gestorService.findByPrincipal());

		//TODO: Para eliminar un centro si sus servicios tienen reservas hay que eliminarlas.
		//Obtener servicios del centro y luego sus reservas.

		this.servicioRepository.delete(servicio);
	}

	public Collection<Servicio> findServiciosByCentroId(final int centroId) {
		Assert.isTrue(centroId != 0);
		Collection<Servicio> result;
		result = new ArrayList<Servicio>();
		result = this.servicioRepository.findServiciosByCentroId(centroId);
		return result;
	}

	public Servicio findServiceByHorarioId(final int horarioId) {
		Servicio servicio;
		Assert.notNull(horarioId);
		servicio = this.servicioRepository.findServiceByHorarioId(horarioId);
		return servicio;
	}

	public Collection<Servicio> findServiciosByGestorId(final int gestorId) {
		Assert.isTrue(gestorId != 0);
		Collection<Servicio> result;
		result = new ArrayList<Servicio>();
		result = this.servicioRepository.findServiciosByGestorId(gestorId);
		return result;
	}

	public Servicio reconstruct(final Servicio servicio, final BindingResult bindingResult) {
		Servicio result;
		final Servicio servicioBd;

		if (servicio.getId() == 0)
			result = servicio;
		else {
			servicioBd = this.servicioRepository.findOne(servicio.getId());
			servicio.setId(servicioBd.getId());
			servicio.setVersion(servicioBd.getVersion());
			servicio.setCentro(servicioBd.getCentro());
			servicio.setReservas(servicioBd.getReservas());
			servicio.setHorarios(servicioBd.getHorarios());

			result = servicio;
		}
		this.validator.validate(result, bindingResult);
		return result;
	}

	public Servicio findOne(final int servicioId) {
		Assert.isTrue(servicioId != 0);
		Servicio result;
		result = this.servicioRepository.findOne(servicioId);
		return result;
	}

	public boolean checkDuracion(final double duracion) {
		boolean result;
		result = false;

		final String duracionS = String.valueOf(duracion);
		final int indexOfDecimal = duracionS.indexOf(".");
		//String hora = duracionS.substring(0, indexOfDecimal);
		final String minutos = duracionS.substring(indexOfDecimal).replace(".", "");
		final double minutosD = Double.parseDouble(minutos);

		if (minutosD == 15. || minutosD == 30. || minutosD == 45. || minutosD == 0.)
			result = true;
		return result;
	}

}
