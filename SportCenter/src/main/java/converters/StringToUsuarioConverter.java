
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.UsuarioRepository;
import domain.Usuario;

@Component
@Transactional
public class StringToUsuarioConverter implements Converter<String, Usuario> {

	@Autowired
	private UsuarioRepository	usuarioRepository;


	@Override
	public Usuario convert(final String text) {

		Usuario result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.usuarioRepository.findOne(id);

			}

		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
