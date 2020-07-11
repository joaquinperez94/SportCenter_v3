
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Centro;

@Component
@Transactional
public class CentroToStringConverter implements Converter<Centro, String> {

	@Override
	public String convert(final Centro centro) {
		String result;

		if (centro == null)
			result = null;
		else
			result = String.valueOf(centro.getId());
		return result;
	}
}
