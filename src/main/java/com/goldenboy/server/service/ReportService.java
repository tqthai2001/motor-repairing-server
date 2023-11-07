package com.goldenboy.server.service;

import com.goldenboy.server.dto.reportDTO.TopCustomerDTO;
import com.goldenboy.server.dto.reportDTO.TopMechanicDTO;
import com.goldenboy.server.dto.reportDTO.TopUsedProductDTO;
import com.goldenboy.server.dto.reportDTO.TopUsedServiceDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ReportService {
    List<BigDecimal> selectRevenueByDate(String startDate, String endDate);

    List<BigDecimal> selectRevenueByMonth(String startDate, String endDate);

    List<TopMechanicDTO> selectTopMechanicByDate(String startDate, String endDate, Integer top);

    List<TopMechanicDTO> selectMechanicTicket(String startDate, String endDate);

    List<TopUsedProductDTO> selectTopProductByDate(String startDate, String endDate, Integer top);

    List<TopUsedServiceDTO> selectTopServiceByDate(String startDate, String endDate, Integer top);

    List<TopCustomerDTO> selectTopCustomer(Integer top);

    List<Integer> selectNewCustomerByDate(String startDate, String endDate);

    List<Integer> selectNewCustomerByMonth(String startDate, String endDate);
}
