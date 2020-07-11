
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Gestor;

@Component
@Transactional
public class GestorToStringConverter implements Converter<Gestor, String> {

	@Override
	public String convert(final Gestor gestor) {
		String result;

		if (gestor == null)
			result = null;
		else
			result = String.valueOf(gestor.getId());
		return result;
	}
}
