
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.HorarioRepository;
import domain.Horario;

@Component
@Transactional
public class StringToHorarioConverter implements Converter<String, Horario> {

	@Autowired
	private HorarioRepository	horarioRepository;


	@Override
	public Horario convert(final String text) {

		Horario result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.horarioRepository.findOne(id);

			}

		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
