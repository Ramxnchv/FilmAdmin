package es.ucm.fdi.iw.model.converters;

// https://github.com/perceptron8/datetime-jpa/blob/master/src/main/java/com/github/perceptron8/datetime/jpa/MonthDayToDateConverter.java

import java.sql.Date;
import java.time.MonthDay;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converts {@link MonthDay} to {@link Date} and back again.
 * <p>
 * Note: Year 2000 is used as default year.
 * </p>
 */
@Converter(autoApply = true)
public class MonthDayToDateConverter implements AttributeConverter<MonthDay, Date> {
	public static final int Y2K = 2000;

	@Override
	public Date convertToDatabaseColumn(MonthDay attribute) {
		return attribute == null ? null : Date.valueOf(attribute.atYear(Y2K));
	}

	@Override
	public MonthDay convertToEntityAttribute(Date dbData) {
		return dbData == null ? null : MonthDay.from(dbData.toLocalDate());
	}
}