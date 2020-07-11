
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Reserva;

@Component
@Transactional
public class ReservaToStringConverter implements Converter<Reserva, String> {

	@Override
	public String convert(final Reserva reserva) {
		String result;

		if (reserva == null)
			result = null;
		else
			result = String.valueOf(reserva.getId());
		return result;
	}
}
