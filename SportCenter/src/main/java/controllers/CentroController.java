
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CentroService;
import services.ComentarioService;
import services.ServicioService;
import domain.Centro;
import domain.Comentario;
import domain.Gestor;
import domain.Servicio;

@Controller
@RequestMapping("/centro")
public class CentroController extends AbstractController {

	//	Services --------------------------------------------------------

	@Autowired
	private CentroService		centroService;

	@Autowired
	private ServicioService		servicioService;

	@Autowired
	private ComentarioService	comentarioService;


	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int centroId) {
		final ModelAndView result;
		Centro centro = new Centro();
		final Collection<Servicio> servicios;
		final Collection<Comentario> comentarios;

		centro = this.centroService.findOne(centroId);
		servicios = this.servicioService.findServiciosByCentroId(centroId);
		comentarios = this.comentarioService.findComentariosByCentroId(centroId);

		result = new ModelAndView("centro/display");
		result.addObject("centro", centro);
		result.addObject("servicios", servicios);
		result.addObject("comentarios", comentarios);
		result.addObject("serviciosEmpty", servicios.size() == 0);
		result.addObject("requestURI", "centro/display.do");

		return result;
	}

	//Listar
	@RequestMapping(value = "/my-center", method = RequestMethod.GET)
	public ModelAndView listPorGestor(@RequestParam(required = false) Integer page) {
		final Page<Centro> centrosPaginados;
		final ModelAndView result;
		final List<Centro> centros;
		final Gestor gestorConectado;
		final int totalPorPagina = 2;

		centros = new ArrayList<>(this.centroService.findAll());
		final PagedListHolder<Centro> pagedListHolder = new PagedListHolder<>(centros);
		pagedListHolder.setPageSize(6);
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

		//centrosPaginados = this.centroService.findCentrosByGestor(gestorConectado.getId(), page, totalPorPagina);
		//final PagedListHolder<Centro> pagedListHolder = new PagedListHolder<>(centrosPaginados.getContent());

		result.addObject("requestURI", "centro/gestor/my-center.do");
		//result.addObject("centros", centros);
		result.addObject("mostrarBotonGestor", true);
		result.addObject("maxPages", pagedListHolder.getPageCount());
		result.addObject("page", page);

		return result;

	}

}
