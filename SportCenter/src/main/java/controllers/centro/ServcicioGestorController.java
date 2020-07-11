
package controllers.centro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ServicioService;
import controllers.AbstractController;

@Controller
@RequestMapping("/servicio/gestor")
public class ServcicioGestorController extends AbstractController {

	//	Services --------------------------------------------------------

	@Autowired
	private ServicioService	servicioService;

}
