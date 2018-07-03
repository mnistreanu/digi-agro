package com.arobs.repository.custom;

import java.math.BigDecimal;

public interface TelemetryCustomRepository {
    void updateCoordinate(Long id, String field, BigDecimal value);
}
