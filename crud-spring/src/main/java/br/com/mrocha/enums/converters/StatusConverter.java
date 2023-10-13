package br.com.mrocha.enums.converters;

import br.com.mrocha.enums.Category;
import br.com.mrocha.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status category) {
        if(category == null) {
            return null;
        }
        return category.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String value) {
        if(value == null) {
            return null;
        }
        return Stream.of(Status.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
