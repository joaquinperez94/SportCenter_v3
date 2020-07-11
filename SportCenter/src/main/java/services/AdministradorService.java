
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministradorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrador;

@Service
@Transactional
public class AdministradorService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private AdministradorRepository	administradorRepository;


	// Constructors-------------------------------------------------------
	public AdministradorService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public Administrador create() {
		final Administrador result;
		final UserAccount userAccount;
		final Authority authority;

		result = new Administrador();
		userAccount = new UserAccount();
		authority = new Authority();

		authority.setAuthority(Authority.ADMIN);
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);

		return result;
	}

	public Collection<Administrador> findAll() {
		Collection<Administrador> result;
		result = this.administradorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Administrador findOne(final int administratorId) {
		Assert.isTrue(administratorId != 0);
		Administrador result;
		result = this.administradorRepository.findOne(administratorId);
		return result;
	}

	public Administrador save(final Administrador administrator) {
		Assert.notNull(administrator);
		final Administrador admin;
		final Md5PasswordEncoder encoder;
		final String hash;

		if (administrator.getId() == 0) {
			final String password = administrator.getUserAccount().getPassword();
			encoder = new Md5PasswordEncoder();
			hash = encoder.encodePassword(password, null);
			administrator.getUserAccount().setPassword(hash);
		}
		admin = this.administradorRepository.save(administrator);
		Assert.notNull(admin);

		return admin;
	}

	public void delete(final Administrador administrador) {
		Assert.notNull(administrador);
		Assert.isTrue(administrador.getId() != 0);
		this.administradorRepository.delete(administrador);
	}

	public Administrador findByPrincipal() {
		Administrador result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.administradorRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

	public void checkPrincipal() {

		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		final Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		final Authority auth = new Authority();
		auth.setAuthority(Authority.ADMIN);

		Assert.isTrue(authorities.contains(auth));
	}

}
