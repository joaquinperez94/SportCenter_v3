
package services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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
	public Centro save(final Centro centro, final MultipartFile file, final String path2) throws IOException {
		Centro result;

		final String aaa = new FileSystemResource("").getFile().getAbsolutePath();
		//final Path path = Paths.get(this.context.getRealPath("/"));
		//final String absolutePath = this.context.getRealPath("resources/uploads");

		final byte[] img = file.getBytes();
		centro.setImagen(img);
		//final byte[] img64 = Base64.encode(img);
		//final String photo64 = new String(img64);

		final byte[] encodeBase64 = Base64.encode(img);
		final String base64DataString = new String(encodeBase64, "UTF-8");
		centro.setImagen64(base64DataString);

		final String idImagen = this.getSaltString();
		final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		//final String relativeFolder = File.separator + "images" + File.separator + "centros" + File.separator;

		final String relativeFolder = path2 + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "images";
		final File folder = new File(relativeFolder);
		final String filename = relativeFolder + idImagen + "." + extension;
		final BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
		/*
		 * if (!file.isEmpty()) {
		 * final boolean creada = folder.mkdirs();
		 * //CREACION
		 * if (centro.getId() == 0 || centro.getImagen().equals("https://www.csqusa.com/wp-content/themes/dante/images/default-thumb.png")) {
		 * final File destination = new File(filename);
		 * ImageIO.write(src, extension, destination);
		 * centro.setImagen(filename);
		 * } else {
		 * //eliminamos fichero
		 * final File file2 = new File(centro.getImagen());
		 * file2.delete();
		 * final File destination = new File(filename);
		 * ImageIO.write(src, extension, destination);
		 * centro.setImagen(filename);
		 * }
		 * } else if (centro.getId() == 0)
		 * centro.setImagen("https://www.csqusa.com/wp-content/themes/dante/images/default-thumb.png");
		 */

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
