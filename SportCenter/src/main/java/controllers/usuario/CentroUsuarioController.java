
package controllers.usuario;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CentroService;
import services.ComentarioService;
import services.ServicioService;
import services.UsuarioService;
import controllers.AbstractController;
import domain.Centro;
import domain.Comentario;
import domain.Servicio;
import domain.Usuario;

@Controller
@RequestMapping("/centro/usuario")
public class CentroUsuarioController extends AbstractController {

	//	Services --------------------------------------------------------

	@Autowired
	private CentroService		centroService;
	@Autowired
	private ServicioService		servicioService;
	@Autowired
	private ComentarioService	comentarioService;

	@Autowired
	private UsuarioService		usuarioService;


	//	Display --------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int centroId) {
		final ModelAndView result;
		Centro centro = new Centro();
		final Collection<Servicio> servicios;
		final Collection<Comentario> comentarios;
		Usuario usuario;

		usuario = this.usuarioService.findByPrincipal();
		centro = this.centroService.findOne(centroId);

		//TODOS LOS ARTCULOS DE UN PERIDICO
		servicios = this.servicioService.findServiciosByCentroId(centroId);
		comentarios = this.comentarioService.findComentariosByCentroId(centroId);

		result = new ModelAndView("centro/display");
		result.addObject("centro", centro);
		result.addObject("servicios", servicios);
		result.addObject("comentarios", comentarios);
		result.addObject("serviciosEmpty", servicios.size() == 0);
		result.addObject("requestURI", "centro/usuario/display.do");
		result.addObject("YaAnadido", centro.getUsuarios().contains(usuario));

		return result;
	}

	//	Lista todos --------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Centro> centros;

		centros = this.centroService.findAll();

		result = new ModelAndView("centro/list");
		result.addObject("requestURI", "centro/usuario/list.do");
		result.addObject("centros", centros);

		return result;

	}
	//	Lista sus favoritos --------------------------------------------------------
	@RequestMapping(value = "/my-center", method = RequestMethod.GET)
	public ModelAndView listPorGestor() {

		ModelAndView result;
		Collection<Centro> centros;
		Usuario usuarioConectado;

		usuarioConectado = this.usuarioService.findByPrincipal();
		centros = this.centroService.findCentrosByUsuario(usuarioConectado.getId());

		result = new ModelAndView("centro/list");
		result.addObject("requestURI", "centro/usuario/list.do");
		result.addObject("centros", centros);
		result.addObject("mostrarBotonGestor", true);

		return result;

	}

	//Añadir favoritos	------------------------------------------------------------
	@RequestMapping(value = "/anadir", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int centroId) {
		ModelAndView result;
		final Centro centro;
		Usuario usuario;

		centro = this.centroService.findOne(centroId);
		usuario = this.usuarioService.findByPrincipal();

		this.centroService.anadirCentroFavorito(centro, usuario);

		try {
			//this.reservaService.delete(reserva);
			result = new ModelAndView("redirect:my-center.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("centro/display");
			//if (oops.getMessage().equals("error fecha cercana"))
			//result = this.lista("reserva.commit.error.cercana");
			//else
			//result = this.lista("reserva.commit.error");
		}
		return result;
	}
}
