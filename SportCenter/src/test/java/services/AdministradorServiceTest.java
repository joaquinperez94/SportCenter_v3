
package services;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Administrador;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AdministradorServiceTest extends AbstractTest {

	// Service under test ---------------------------------
	@Autowired
	private AdministradorService	administradorService;
	
	@PersistenceContext
	EntityManager				entityManager;


	// Tests ----------------------------------------------
	//Login 
	@Test
	public void driveLoginAdmin() {

		final Object testingData[][] = {
			//Admin is registered
			{
				"admin", null
			},
			//Admin isn't registered
			{
				"adminNoRegistrado", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateLoginAdmin((String) testingData[i][0], (Class<?>) testingData[i][1]);

	}
	
	public void templateLoginAdmin(final String username, final Class<?> expected) {

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

	/*	
	@Test
	public void testCreate() {
		Administrador administrador;
		administrador = this.administradorService.create();
		Assert.notNull(administrador);
	}

	@Test
	public void testSave() {
		Administrador administrador;
		administrador = this.administradorService.create();

		administrador.setNombre("admin nombre");
		administrador.setApellidos("admin apellidos");
		administrador.setEmail("admin@gmail.com");
		administrador.setProvincia("SEVILLA");
		administrador.setDireccion("Calle  Huertas 2");
		administrador.setTelefono("+34663456122");

		administrador = this.administradorService.save(administrador);
		Assert.notNull(administrador.getId());

	}

	@Test
	public void testDelete() {
		Administrador administrador;
		administrador = this.administradorService.findOne(super.getEntityId("administrador1"));
		this.administradorService.delete(administrador);

	}

	@Test
	public void testFindAll() {
		Collection<Administrador> administradors;
		administradors = this.administradorService.findAll();
		Assert.notEmpty(administradors);
		Assert.notNull(administradors);
	}

	@Test
	public void testFindOne() {
		Administrador administrador;
		administrador = this.administradorService.findOne(super.getEntityId("administrador2"));
		Assert.notNull(administrador);
	}*/

}
