
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReservaRepository;
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


	// Crear ------------------------------------------------------------------------
	public Reserva create(final Servicio servicio) {
		Reserva reserva;
		Usuario usuarioPrincipal;

		reserva = new Reserva();
		usuarioPrincipal = this.usuarioService.findByPrincipal();

		reserva.setServicio(servicio);
		reserva.setUsuario(usuarioPrincipal);

		return reserva;
	}

	//Guardar -----------------------------------------------------------
	public Reserva save(final Reserva reserva) {
		Reserva result;

		Assert.notNull(reserva);

		this.usuarioService.checkPrincipal();
		result = this.reservaRepository.save(reserva);
		return result;
	}

	//Eliminar -----------------------------------------------------------
	public void delete(final Reserva reserva) {
		Assert.notNull(reserva);
		Assert.isTrue(reserva.getId() != 0);
		final Collection<Servicio> serviciosGestor;
		final Servicio servicio;

		//serviciosGestor = new ArrayList<>(this.servicioService.findServiciosCreatedByGestor());

		//servicio = this.servicioService.findServiceByHorarioId(horario.getId());
		//TODO:
		//Comprobamos que el servicio del horario pertenezca a este gestor
		//Assert.isTrue(serviciosGestor.contains(servicio));

		reserva.setServicio(null);
		this.reservaRepository.delete(reserva);

	}

}
