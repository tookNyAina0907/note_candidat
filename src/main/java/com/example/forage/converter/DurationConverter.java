package com.example.forage.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Duration;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        // On convertit la Duration en nombre d'heures pour la base de données
        return duration == null ? null : duration.toHours();
    }

    @Override
    public Duration convertToEntityAttribute(Long dbData) {
        // On recrée la Duration à partir du nombre d'heures lu en base
        return dbData == null ? null : Duration.ofHours(dbData);
    }
}
