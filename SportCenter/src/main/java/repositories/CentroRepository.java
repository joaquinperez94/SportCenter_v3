
package repositories;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Centro;

@Repository
public interface CentroRepository extends JpaRepository<Centro, Integer> {

	@Query("select c from Centro c where c.nombre like %?1%")
	Collection<Centro> findCentroByName(String name);

	@Query("select c from Centro c where c.gestor.id = ?1 ")
	Page<Centro> findCentrosByGestor(int gestorId, Pageable pageable);

	@Query("select c from Centro c join c.servicios s where s.id=?1")
	Centro findCentroByServiceId(int servicioId);

	@Query("select c from Centro c join c.usuarios u where u.id = ?1")
	Collection<Centro> findCentrosByUsuario(int usuarioId);

	@Query("select c from Centro c where c.gestor.id = ?1")
	Collection<Centro> findCentrosByGestorSinPage(int gestorId);
}
