
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

	@Query("select s from Servicio s where s.centro.id = ?1")
	Collection<Servicio> findServiciosByCentroId(final int centroId);
}
