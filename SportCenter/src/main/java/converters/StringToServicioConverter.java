
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ServicioRepository;
import domain.Servicio;

@Component
@Transactional
public class StringToServicioConverter implements Converter<String, Servicio> {

	@Autowired
	private ServicioRepository	servicioRepository;


	@Override
	public Servicio convert(final String text) {

		Servicio result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.servicioRepository.findOne(id);

			}

		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
