
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("select u from Usuario u where u.userAccount.id = ?1")
	Usuario findByUserAccountId(int id);

	@Query("select c.usuarios from Centro c where c.id = ?1")
	Collection<Usuario> findUsuariosByCentroId(int centroId);
}
