
package controllers.centro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.HorarioService;
import controllers.AbstractController;

@Controller
@RequestMapping("/horario/gestor")
public class HorarioGestorController extends AbstractController {

	//	Services --------------------------------------------------------

	@Autowired
	private HorarioService	horarioService;

}
