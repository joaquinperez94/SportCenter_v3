
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ServicioRepository;
import domain.Centro;
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


	// Simple crud methods
	// Crear ------------------------------------------------------------------------

	public Servicio create(final Centro centro) {
		Servicio servicio;

		servicio = new Servicio();

		final List<Reserva> reservas = new ArrayList<>();

		servicio.setReservas(reservas);
		servicio.setCentro(centro);

		return servicio;
	}

	//Guardar -----------------------------------------------------------
	public Servicio save(final Servicio servicio) {
		Servicio result;

		Assert.notNull(servicio);

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

}
