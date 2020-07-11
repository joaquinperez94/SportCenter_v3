
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {

}
