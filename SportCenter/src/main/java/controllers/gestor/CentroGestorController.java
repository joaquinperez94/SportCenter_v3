
package controllers.gestor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CentroService;
import services.ComentarioService;
import services.GestorService;
import services.ServicioService;
import controllers.AbstractController;
import domain.Centro;
import domain.Comentario;
import domain.Gestor;
import domain.Servicio;

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

		centro = this.centroService.crearCentro();

		result = this.createEditModelAndView(centro);

		result.addObject("centro", centro);

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
	public ModelAndView save(final Centro centro, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(centro, "centro.mio");
		else
			try {
				this.centroService.save(centro);
				result = new ModelAndView("redirect:my-center.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(centro, "centro.commit.error");
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
	public ModelAndView listPorGestor(@RequestParam(required = false) Integer page) {
		Page<Centro> centrosPaginados;
		final ModelAndView result;
		final List<Centro> centros;
		Gestor gestorConectado;
		final int totalPorPagina = 2;

		gestorConectado = this.gestorService.findByPrincipal();
		centros = new ArrayList<>(this.centroService.findCentrosByGestorSinPage(gestorConectado.getId()));
		final PagedListHolder<Centro> pagedListHolder = new PagedListHolder<>(centros);
		pagedListHolder.setPageSize(5);
		result = new ModelAndView("centro/list");

		if (page == null || page < 1 || page > pagedListHolder.getPageCount())
			page = 1;
		result.addObject("page", page);

		if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(0);
			result.addObject("centros", pagedListHolder.getPageList());
		} else if (page <= pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(page - 1);
			result.addObject("centros", pagedListHolder.getPageList());
		}

		centrosPaginados = this.centroService.findCentrosByGestor(gestorConectado.getId(), page, totalPorPagina);
		//final PagedListHolder<Centro> pagedListHolder = new PagedListHolder<>(centrosPaginados.getContent());

		result.addObject("requestURI", "centro/gestor/my-center.do");
		//result.addObject("centros", centros);
		result.addObject("mostrarBotonGestor", true);
		result.addObject("maxPages", pagedListHolder.getPageCount());
		result.addObject("page", page);

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

		result = new ModelAndView("centro/edit");
		result.addObject("centro", centro);
		result.addObject("message", messageCode);

		return result;

	}

}
