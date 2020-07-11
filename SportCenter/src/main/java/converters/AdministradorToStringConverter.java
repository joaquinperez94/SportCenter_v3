
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrador;;

@Component
@Transactional
public class AdministradorToStringConverter implements Converter<Administrador, String> {

	@Override
	public String convert(Administrador administrator) {
		String result;

		if (administrator == null)
			result = null;
		else
			result = String.valueOf(administrator.getId());
		return result;
	}

}
