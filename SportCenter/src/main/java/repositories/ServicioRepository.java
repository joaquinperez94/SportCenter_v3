
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

	@Query("select s from Servicio s join s.horarios h where h.id=?1")
	Servicio findServiceByHorarioId(int horarioId);

	@Query("select s from Servicio s where s.centro.id = ?1 and s.identificador like %?2% and s.nombre like %?3%")
	Collection<Servicio> findServiceByCenterNameIdentifier(int centroId, String identifier, String nombre);

	@Query("select c.servicios from Centro c where c.gestor.id=?1")
	Collection<Servicio> findServiciosByGestorId(int gestorId);
}
