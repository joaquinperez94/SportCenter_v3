
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

	@Query("select r from Reserva r where r.fechaReserva=?1 and r.servicio.id=?2 ")
	Collection<Reserva> findReservasByFechaReservaAndServiceId(Date fecha, int serviceId);

	@Query("select r from Reserva r where r.usuario.id=?1")
	Collection<Reserva> findReservasByUsuarioId(int usuarioId);
}
