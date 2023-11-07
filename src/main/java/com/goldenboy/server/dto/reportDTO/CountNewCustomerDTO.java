package com.goldenboy.server.dto.reportDTO;

import java.time.LocalDate;

public interface CountNewCustomerDTO {
    LocalDate getDate();

    Integer getMonth();

    Integer getYear();

    Integer getNumberOfNewCustomers();
}
