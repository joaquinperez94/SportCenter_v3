
package services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

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
	public Servicio save(final Servicio servicio, final MultipartFile file) throws IOException {
		Servicio result;
		Collection<Servicio> checkServicio;

		Assert.notNull(servicio);
		if (servicio.getNombre().contains("Otro"))
			servicio.setNombre(servicio.getNombre().split(",")[1]);
		else
			servicio.setNombre(servicio.getNombre().split(",")[0]);
		Assert.isTrue(servicio.getDuración() >= 0.15, "duracion no valida");

		checkServicio = new ArrayList<>();
		checkServicio = this.findServiceByCenterNameIdentifier(servicio.getCentro().getId(), servicio.getIdentificador(), servicio.getNombre());
		Assert.isTrue(checkServicio.size() == 0, "ya existe servicio");

		if (!file.isEmpty()) {
			final byte[] img = file.getBytes();
			final byte[] encodeBase64 = Base64.encode(img);
			final String base64DataString = new String(encodeBase64, "UTF-8");
			servicio.setImagen(base64DataString);
		} else if (servicio.getId() == 0) {
			final URL url = new URL("https://storage.googleapis.com/imagenes_sport/default.png");
			final InputStream is = url.openStream();
			final byte[] fileContent = IOUtils.toByteArray(is);
			final byte[] encodeBase64 = Base64.encode(fileContent);
			final String base64DataString = new String(encodeBase64, "UTF-8");
			servicio.setImagen(base64DataString);
		}

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

	public Collection<Servicio> findServiceByCenterNameIdentifier(final int centroId, final String identifier, final String nombre) {
		Collection<Servicio> result;
		Assert.notNull(centroId);
		result = new ArrayList<>(this.servicioRepository.findServiceByCenterNameIdentifier(centroId, identifier, nombre));

		return result;
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
