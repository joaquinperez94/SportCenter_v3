
package services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import repositories.CentroRepository;
import domain.Centro;
import domain.Comentario;
import domain.Gestor;
import domain.Servicio;
import domain.Usuario;

@Service
@Transactional
public class CentroService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CentroRepository	centroRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	GestorService				gestorService;
	@Autowired
	HorarioService				horarioService;
	@Autowired
	UsuarioService				usuarioService;

	@Autowired
	ServletContext				context;


	// Constructors -----------------------------------------------------------
	public CentroService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	//Crear -----------------------------------------------------------
	public Centro crearCentro() {
		Centro result;
		Gestor gestorPrincipal;
		Collection<Servicio> servicios;
		Collection<Comentario> comentarios;
		Collection<Usuario> usuarios;

		result = new Centro();
		servicios = new ArrayList<Servicio>();
		usuarios = new ArrayList<Usuario>();
		comentarios = new ArrayList<Comentario>();
		gestorPrincipal = this.gestorService.findByPrincipal();

		result.setGestor(gestorPrincipal);
		result.setServicios(servicios);
		result.setComentarios(comentarios);
		result.setUsuarios(usuarios);
		return result;
	}

	//Guardar -----------------------------------------------------------
	public Centro save(final Centro centro, final MultipartFile file) throws IOException {
		Centro result;

		if (!file.isEmpty()) {
			final byte[] img = file.getBytes();
			final byte[] encodeBase64 = Base64.encode(img);
			final String base64DataString = new String(encodeBase64, "UTF-8");
			centro.setImagen(base64DataString);
		} else if (centro.getId() == 0) {
			final URL url = new URL("https://storage.googleapis.com/imagenes_sport/default.png");
			final InputStream is = url.openStream();
			final byte[] fileContent = IOUtils.toByteArray(is);
			final byte[] encodeBase64 = Base64.encode(fileContent);
			final String base64DataString = new String(encodeBase64, "UTF-8");
			centro.setImagen(base64DataString);
		}

		Assert.notNull(centro);

		Assert.isTrue(centro.getGestor().equals(this.gestorService.findByPrincipal()));

		result = this.centroRepository.save(centro);
		return result;
	}
	//Eliminar -----------------------------------------------------------
	public void delete(final Centro centro) {

		Assert.notNull(centro);
		Assert.notNull(this.gestorService.findByPrincipal());

		//TODO: Para eliminar un centro si sus servicios tienen reservas hay que eliminarlas.
		//Obtener servicios del centro y luego sus reservas.

		this.centroRepository.delete(centro);
	}

	public Collection<Centro> findAll() {

		Collection<Centro> result;

		result = this.centroRepository.findAll();

		return result;

	}

	public Centro findOne(final int centroId) {

		Assert.isTrue(centroId != 0);
		Centro result;
		result = this.centroRepository.findOne(centroId);

		return result;

	}

	public Collection<Centro> findCentroByName(final String name) {
		Collection<Centro> result;

		result = this.centroRepository.findCentroByName(name);
		return result;
	}

	public Collection<Centro> findCentrosByGestorSinPage(final int gestorId) {
		Collection<Centro> result;

		result = this.centroRepository.findCentrosByGestorSinPage(gestorId);
		return result;
	}

	public Page<Centro> findCentrosByGestor(final int gestorId, final int numPagina, final int totalPorPagina) {

		Page<Centro> result;
		final Pageable pageable = new PageRequest(numPagina, totalPorPagina);
		result = this.centroRepository.findCentrosByGestor(gestorId, pageable);
		//result = resPage.getContent();
		return result;
	}

	public Centro findCentroByServiceId(final int servicioId) {
		Centro centro;
		Assert.notNull(servicioId);
		centro = this.centroRepository.findCentroByServiceId(servicioId);
		return centro;
	}

	public Collection<Centro> findCentrosByUsuario(final int usuarioId) {
		Collection<Centro> result;
		result = new ArrayList<>(this.centroRepository.findCentrosByUsuario(usuarioId));
		return result;
	}

	public Collection<Centro> findCentrosByUsuarioExclude(final int usuarioId) {
		Collection<Centro> centrosUsuario;
		Collection<Centro> todos;

		centrosUsuario = new ArrayList<>(this.centroRepository.findCentrosByUsuario(usuarioId));
		todos = new ArrayList<>(this.findAll());
		todos.removeAll(centrosUsuario);

		return todos;
	}

	public void anadirCentroFavorito(final Centro centro, final Usuario usuario) {
		Collection<Centro> centrosUsuarios;
		Collection<Usuario> usuariosCentro;
		usuariosCentro = new ArrayList<>(this.usuarioService.findUsuariosByCentroId(centro.getId()));
		centrosUsuarios = new ArrayList<>(this.centroRepository.findCentrosByUsuario(usuario.getId()));
		Assert.isTrue(!centrosUsuarios.contains(centro), "Ya lo contiene");
		usuariosCentro.add(usuario);
		centrosUsuarios.add(centro);
		//usuario.setCentros(centrosUsuarios);
		centro.setUsuarios(usuariosCentro);
		this.usuarioService.save(usuario);
		this.centroRepository.save(centro);
	}

	public String getSaltString() {
		final String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		final StringBuilder salt = new StringBuilder();
		final Random rnd = new Random();
		while (salt.length() < 9) { // length of the random string.
			final int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		final String saltStr = salt.toString();
		return saltStr;

	}

}
