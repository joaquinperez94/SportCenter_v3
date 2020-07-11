
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Usuario;

@Component
@Transactional
public class UsuarioToStringConverter implements Converter<Usuario, String> {

	@Override
	public String convert(final Usuario usuario) {
		String result;

		if (usuario == null)
			result = null;
		else
			result = String.valueOf(usuario.getId());
		return result;
	}
}
