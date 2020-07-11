
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

import repositories.UsuarioRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Centro;
import domain.Reserva;
import domain.Usuario;
import forms.UsuarioForm;

@Service
@Transactional
public class UsuarioService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private UsuarioRepository	usuarioRepository;
	@Autowired
	private Validator			validator;


	// Supporting services ----------------------------------------------------

	// Constructors-------------------------------------------------------

	public UsuarioService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public Usuario create() {
		Usuario result;
		UserAccount userAccount;
		Authority authority;
		Collection<Centro> centros;
		Collection<Reserva> reservas;

		result = new Usuario();
		userAccount = new UserAccount();
		authority = new Authority();
		centros = new ArrayList<>();
		reservas = new ArrayList<>();

		authority.setAuthority("USUARIO");
		userAccount.addAuthority(authority);
		result.setUserAccount(userAccount);
		result.setCentros(centros);
		result.setReservas(reservas);

		return result;
	}

	public Collection<Usuario> findAll() {
		Collection<Usuario> result;
		result = this.usuarioRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Usuario findOne(final int rangerId) {
		Assert.isTrue(rangerId != 0);
		Usuario result;
		result = this.usuarioRepository.findOne(rangerId);
		return result;
	}

	public Usuario save(final Usuario usuario) {

		Assert.notNull(usuario);
		final Usuario result;
		final Md5PasswordEncoder encoder;
		final String passwordHash;

		if (usuario.getId() == 0) {
			final String password = usuario.getUserAccount().getPassword();
			encoder = new Md5PasswordEncoder();
			passwordHash = encoder.encodePassword(password, null);
			usuario.getUserAccount().setPassword(passwordHash);
		}
		result = this.usuarioRepository.save(usuario);
		Assert.notNull(result);

		return result;
	}

	public void delete(final Usuario usuario) {

		Assert.notNull(usuario);
		Assert.isTrue(usuario.getId() != 0);
		this.usuarioRepository.delete(usuario);

	}

	// Other business methods----------------------------------

	public Usuario findByPrincipal() {

		Usuario result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.usuarioRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}
	public void checkPrincipal() {

		final UserAccount userAccount = LoginService.getPrincipal();

		Assert.notNull(userAccount);

		final Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		final Authority auth = new Authority();
		auth.setAuthority("USUARIO");

		Assert.isTrue(authorities.contains(auth));
	}

	public UsuarioForm reconstruct(final UsuarioForm usuarioForm, final BindingResult binding) {

		UsuarioForm result = null;
		Usuario usuarioBD;
		usuarioBD = usuarioForm.getUsuario();

		if (usuarioBD.getId() == 0) {
			UserAccount userAccount;
			Authority authority;
			final Collection<Centro> centros;
			final Collection<Reserva> reservas;

			userAccount = usuarioForm.getUsuario().getUserAccount();
			authority = new Authority();
			authority.setAuthority(Authority.USUARIO);
			userAccount.addAuthority(authority);
			usuarioForm.getUsuario().setUserAccount(userAccount);
			centros = new ArrayList<>();
			reservas = new ArrayList<>();

			usuarioForm.getUsuario().setCentros(centros);
			usuarioForm.getUsuario().setReservas(reservas);

			result = usuarioForm;

		} else {

			usuarioBD = this.usuarioRepository.findOne(usuarioForm.getUsuario().getId());
			usuarioForm.getUsuario().setId(usuarioBD.getId());
			usuarioForm.getUsuario().setVersion(usuarioBD.getVersion());
			usuarioForm.getUsuario().setUserAccount(usuarioBD.getUserAccount());
			usuarioForm.getUsuario().setCentros(usuarioBD.getCentros());
			usuarioForm.getUsuario().setReservas(usuarioBD.getReservas());

			result = usuarioForm;

		}

		this.validator.validate(result, binding);

		return result;

	}

}
