
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {

	@Query("select s.horarios from Servicio s where s.id=?1")
	Collection<Horario> findHorariosByServicioId(int servicioId);

	@Query("select h from Horario h where h.diaSemana like %?1% and h.servicio.id=?2")
	Collection<Horario> findHorariosByDiaSemanaYServicioId(String dia, int servicioId);
}
