
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.GestorRepository;
import domain.Gestor;

@Component
@Transactional
public class StringToGestorConverter implements Converter<String, Gestor> {

	@Autowired
	private GestorRepository	gestorRepository;


	@Override
	public Gestor convert(final String text) {

		Gestor result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.gestorRepository.findOne(id);

			}

		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
