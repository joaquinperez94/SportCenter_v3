
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Gestor;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Integer> {

	@Query("select g from Gestor g where g.userAccount.id = ?1")
	Gestor findByUserAccountId(int id);
}
