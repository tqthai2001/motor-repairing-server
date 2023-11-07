package com.goldenboy.server.dto.reportDTO;

import java.math.BigDecimal;

public interface TopCustomerDTO {
    Long getCustomerId();

    String getCustomerName();

    Integer getFrequency();

    BigDecimal getMoneyPaid();
}
