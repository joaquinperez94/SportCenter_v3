
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ReservaRepository;
import domain.Reserva;

@Component
@Transactional
public class StringToReservaConverter implements Converter<String, Reserva> {

	@Autowired
	private ReservaRepository	reservaRepository;


	@Override
	public Reserva convert(final String text) {

		Reserva result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.reservaRepository.findOne(id);

			}

		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
