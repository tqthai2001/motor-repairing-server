package com.goldenboy.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.goldenboy.server.dto.base.BaseDTO;
import com.goldenboy.server.dto.connectDTO.TicketProductDTO;
import com.goldenboy.server.dto.connectDTO.TicketServiceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class TicketDTO extends BaseDTO {
    private String code;
    private String description;
    private String note;
    private String status;
    private BigDecimal discount;
    private BigDecimal totalPrice;
    private MotorbikeDTO motorbike;
    private CustomerDTO customer;
    private EmployeeDTO repairingEmployee;
    private String cashierName;
    private String paymentMethod;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime appointmentDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate;
    private Set<TicketProductDTO> products;
    private Set<TicketServiceDTO> services;
}
