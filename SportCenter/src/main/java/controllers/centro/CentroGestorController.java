
package controllers.centro;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CentroService;
import services.ComentarioService;
import services.GestorService;
import services.HorarioService;
import services.ServicioService;
import controllers.AbstractController;
import domain.Centro;
import domain.Comentario;
import domain.Gestor;
import domain.Horario;
import domain.Servicio;
import forms.CentroForm;

@Controller
@RequestMapping("/centro/gestor")
public class CentroGestorController extends AbstractController {

	//	Services --------------------------------------------------------

	@Autowired
	private CentroService		centroService;

	@Autowired
	private ServicioService		servicioService;

	@Autowired
	private ComentarioService	comentarioService;

	@Autowired
	private HorarioService		horarioService;

	@Autowired
	private GestorService		gestorService;


	//Buscar -----------------------------------------------------------
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView listarCentrosPorNombre(@RequestParam final String name) {
		final ModelAndView result;
		Collection<Centro> centros;

		centros = this.centroService.findCentroByName(name);

		result = new ModelAndView("centro/list");
		result.addObject("centros", centros);
		//result.addObject("showSearch", true);
		result.addObject("requestURI", "centro/gestor/buscar.do");
		//result.addObject("requestURISearchCentro", "centro/usuario/search.do");
		return result;
	}

	//Crear -----------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Centro centro;
		Horario horario;

		centro = this.centroService.crearCentro();
		horario = this.horarioService.create();
		CentroForm centroForm;

		centroForm = new CentroForm(centro, horario);

		final Collection<Horario> horarios = new ArrayList<>(centro.getHorarios());
		result = new ModelAndView("centro/edit");
		result.addObject("centroForm", centroForm);
		result.addObject("horarios", horarios);
		result.addObject("requestURI", "centro/gestor/edit.do");
		return result;

	}
	//Editar -----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int centroId) {
		ModelAndView result;
		Centro centro;
		Gestor gestor;

		gestor = this.gestorService.findByPrincipal();
		centro = this.centroService.findOne(centroId);
		Assert.isTrue(gestor.getCentros().contains(centro), "Cannot commit this operation, because its illegal");
		Assert.notNull(centro);
		result = this.createEditModelAndView(centro);
		return result;
	}

	// Save -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("centroForm") CentroForm centroForm, final BindingResult binding) {
		ModelAndView result;
		Collection<Horario> horariosCentro = new ArrayList<>();

		horariosCentro = this.centroService.manipularHorarios(centroForm);
		centroForm = this.centroService.reconstruct(centroForm, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView2(centroForm, "centro.mio");
		else
			try {
				result = this.createEditModelAndView2(centroForm, "centro.mio");
				//this.centroService.save(centroForm.getCentro());
				//result = new ModelAndView("redirect:my-center.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView2(centroForm, "centro.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Centro centro, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(centro);
		else {
			Assert.notNull(centro);
			try {
				this.centroService.delete(centro);
				result = new ModelAndView("redirect:my-center.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(centro, "centro.commit.error");
				result = this.listPorGestor();
			}
		}

		return result;
	}

	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int centroId) {
		final ModelAndView result;
		Centro centro = new Centro();
		final Collection<Servicio> servicios;
		final Collection<Comentario> comentarios;

		centro = this.centroService.findOne(centroId);

		//TODOS LOS ARTCULOS DE UN PERIDICO
		servicios = this.servicioService.findServiciosByCentroId(centroId);
		comentarios = this.comentarioService.findComentariosByCentroId(centroId);

		result = new ModelAndView("centro/display");
		result.addObject("centro", centro);
		result.addObject("servicios", servicios);
		result.addObject("comentarios", comentarios);
		result.addObject("serviciosEmpty", servicios.size() == 0);
		result.addObject("requestURI", "centro/gestor/display.do");

		return result;
	}

	//Listar --------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Centro> centros;

		centros = this.centroService.findAll();

		result = new ModelAndView("centro/list");
		result.addObject("requestURI", "centro/gestor/list.do");
		result.addObject("centros", centros);

		return result;

	}

	//Listar --------------------------

	@RequestMapping(value = "/my-center", method = RequestMethod.GET)
	public ModelAndView listPorGestor() {

		ModelAndView result;
		Collection<Centro> centros;
		Gestor gestorConectado;

		gestorConectado = this.gestorService.findByPrincipal();
		centros = this.centroService.findCentrosByGestor(gestorConectado.getId());

		result = new ModelAndView("centro/list");
		result.addObject("requestURI", "centro/gestor/list.do");
		result.addObject("centros", centros);
		result.addObject("mostrarBotonGestor", true);

		return result;

	}

	//Auxiliares ---------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Centro centro) {

		Assert.notNull(centro);
		ModelAndView result;
		result = this.createEditModelAndView(centro, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Centro centro, final String messageCode) {
		assert centro != null;

		ModelAndView result;

		//TODO: A�ADIR CREACION DE HORARIO AQUI
		result = new ModelAndView("centro/edit");
		result.addObject("centro", centro);
		result.addObject("message", messageCode);
		result.addObject("horarios", centro.getHorarios());
		return result;

	}

	protected ModelAndView createEditModelAndView2(final CentroForm centro) {

		Assert.notNull(centro);
		ModelAndView result;
		result = this.createEditModelAndView2(centro, null);
		return result;
	}

	protected ModelAndView createEditModelAndView2(final CentroForm centroForm, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("centro/edit");
		result.addObject("centroForm", centroForm);
		result.addObject("horarios", centroForm.getHorario());
		result.addObject("requestURI", "centro/gestor/edit.do");

		return result;

	}
}
