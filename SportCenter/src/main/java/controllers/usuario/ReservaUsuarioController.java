
package controllers.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ReservaService;

@Controller
@RequestMapping("/reserva/usuario")
public class ReservaUsuarioController {

	//	Services --------------------------------------------------------

	@Autowired
	private ReservaService	reservaService;

}
