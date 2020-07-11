
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.CentroRepository;
import domain.Centro;

@Component
@Transactional
public class StringToCentroConverter implements Converter<String, Centro> {

	@Autowired
	private CentroRepository	centroRepository;


	@Override
	public Centro convert(final String text) {

		Centro result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.centroRepository.findOne(id);

			}

		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
