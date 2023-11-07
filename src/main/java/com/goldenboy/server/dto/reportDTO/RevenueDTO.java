package com.goldenboy.server.dto.reportDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface RevenueDTO {
    LocalDate getDate();

    BigDecimal getRevenue();

    Integer getMonth();

    Integer getYear();
}
