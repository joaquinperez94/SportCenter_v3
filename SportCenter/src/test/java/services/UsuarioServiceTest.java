
package services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class UsuarioServiceTest extends AbstractTest {

	// Service under test ---------------------------------
	@PersistenceContext
	EntityManager	entityManager;


	// Tests ----------------------------------------------
	//Login 
	@Test
	public void driverLoginUsuario() {

		final Object testingData[][] = {
			//Admin is registered
			{
				"paula", null
			},
			//Admin isn't registered
			{
				"adminNo", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateLoginUsuario((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}

	public void templateLoginUsuario(final String username, final Class<?> expected) {

		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
			this.entityManager.clear();
		}

		this.checkExceptions(expected, caught);
		super.unauthenticate();
	}

}
