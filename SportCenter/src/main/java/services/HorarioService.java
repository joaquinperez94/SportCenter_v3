
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.HorarioRepository;
import domain.Horario;

@Service
@Transactional
public class HorarioService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private HorarioRepository	horarioRepository;

	@Autowired
	private GestorService		gestorService;


	// Simple crud methods
	// Crear ------------------------------------------------------------------------

	public Horario create() {
		Horario horario;

		horario = new Horario();

		return horario;
	}

	//Guardar -----------------------------------------------------------
	public Horario save(final Horario horario) {
		Horario result;

		Assert.notNull(horario);

		this.gestorService.checkPrincipal();

		result = this.horarioRepository.save(horario);
		return result;
	}

}
