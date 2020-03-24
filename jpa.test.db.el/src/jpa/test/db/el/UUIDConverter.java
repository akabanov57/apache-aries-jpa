package jpa.test.db.el;

import java.sql.SQLException;
import java.util.UUID;

import javax.persistence.AttributeConverter;

import org.postgresql.util.PGobject;

@javax.persistence.Converter(autoApply=true)
public class UUIDConverter implements AttributeConverter<UUID, Object> {

	@Override
	public Object convertToDatabaseColumn(UUID arg0) {
		PGobject result = new PGobject();
		result.setType("uuid");
		try {
			if (arg0 != null) {
				result.setValue(arg0.toString());
			} else {
				result.setValue(null);
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException("Error converting uuid to database column.",e);
		}
		return result;
	}

	@Override
	public UUID convertToEntityAttribute(Object arg0) {
		return (UUID)arg0;
	}

}
