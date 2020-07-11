
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ComentarioRepository;
import domain.Comentario;

@Service
@Transactional
public class ComentarioService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ComentarioRepository	comentarioRepository;


	public Collection<Comentario> findComentariosByCentroId(final int centroId) {
		Assert.isTrue(centroId != 0);
		Collection<Comentario> result;
		result = new ArrayList<Comentario>();
		result = this.comentarioRepository.findComentariosByCentroId(centroId);
		return result;
	}
}
