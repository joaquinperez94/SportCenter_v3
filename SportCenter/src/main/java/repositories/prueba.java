
package repositories;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class prueba {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(final String[] args) throws ParseException {
		final String sdate = "06-07-2020";
		//sdate = sdate.replace("-", " ");
		final DateFormat df = new SimpleDateFormat("dd-MM-yy");
		final Date result = df.parse(sdate);

		System.out.println(result + "   " + result.getClass());

		final String sdate2 = "06/07/2020";
		final SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		final Date d = sdf2.parse(sdate2);

		System.out.println(d + "   " + d.getClass());

	}

}
