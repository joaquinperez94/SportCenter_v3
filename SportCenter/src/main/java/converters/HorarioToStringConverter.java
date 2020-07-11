
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Horario;

@Component
@Transactional
public class HorarioToStringConverter implements Converter<Horario, String> {

	@Override
	public String convert(final Horario horario) {
		String result;

		if (horario == null)
			result = null;
		else
			result = String.valueOf(horario.getId());
		return result;
	}
}
