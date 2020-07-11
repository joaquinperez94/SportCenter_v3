
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.GestorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Centro;
import domain.Gestor;
import forms.GestorForm;

@Service
@Transactional
public class GestorService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private GestorRepository	gestorRepository;
	@Autowired
	private Validator			validator;


	// Supporting services ----------------------------------------------------

	// Constructors-------------------------------------------------------

	public GestorService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public Gestor create() {
		Gestor result;
		UserAccount userAccount;
		Authority authority;
		Collection<Centro> centros;

		result = new Gestor();
		userAccount = new UserAccount();
		authority = new Authority();
		centros = new ArrayList<>();

		authority.setAuthority("GESTOR");
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);
		result.setCentros(centros);

		return result;
	}

	public Collection<Gestor> findAll() {
		Collection<Gestor> result;
		result = this.gestorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Gestor findOne(final int rangerId) {
		Assert.isTrue(rangerId != 0);
		Gestor result;
		result = this.gestorRepository.findOne(rangerId);
		return result;
	}

	public Gestor save(final Gestor gestor) {

		Assert.notNull(gestor);
		final Gestor result;
		final Md5PasswordEncoder encoder;
		final String passwordHash;

		if (gestor.getId() == 0) {
			final String password = gestor.getUserAccount().getPassword();
			encoder = new Md5PasswordEncoder();
			passwordHash = encoder.encodePassword(password, null);
			gestor.getUserAccount().setPassword(passwordHash);
		}
		result = this.gestorRepository.save(gestor);
		Assert.notNull(result);

		return result;
	}

	public void delete(final Gestor gestor) {

		Assert.notNull(gestor);
		Assert.isTrue(gestor.getId() != 0);
		this.gestorRepository.delete(gestor);

	}

	// Other business methods----------------------------------

	public Gestor findByPrincipal() {

		Gestor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.gestorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}
	public void checkPrincipal() {

		final UserAccount userAccount = LoginService.getPrincipal();

		Assert.notNull(userAccount);

		final Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		final Authority auth = new Authority();
		auth.setAuthority("GESTOR");

		Assert.isTrue(authorities.contains(auth));
	}

	public GestorForm reconstruct(final GestorForm gestorForm, final BindingResult binding) {

		GestorForm result = null;
		Gestor gestorBD;
		gestorBD = gestorForm.getGestor();

		if (gestorBD.getId() == 0) {
			UserAccount userAccount;
			Authority authority;
			final Collection<Centro> centros;

			userAccount = gestorForm.getGestor().getUserAccount();
			authority = new Authority();
			authority.setAuthority(Authority.USUARIO);
			userAccount.addAuthority(authority);
			gestorForm.getGestor().setUserAccount(userAccount);
			centros = new ArrayList<>();
			gestorForm.getGestor().setCentros(centros);

			result = gestorForm;

		} else {

			gestorBD = this.gestorRepository.findOne(gestorForm.getGestor().getId());
			gestorForm.getGestor().setId(gestorBD.getId());
			gestorForm.getGestor().setVersion(gestorBD.getVersion());
			gestorForm.getGestor().setUserAccount(gestorBD.getUserAccount());
			gestorForm.getGestor().setCentros(gestorBD.getCentros());

			result = gestorForm;

		}

		this.validator.validate(result, binding);

		return result;

	}

}
