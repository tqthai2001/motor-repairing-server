package com.goldenboy.server.dto.connectDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goldenboy.server.dto.ProductDTO;
import com.goldenboy.server.entity.compositekey.TicketProductId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class TicketProductDTO {
    private TicketProductId id;
    private ProductDTO product;
    private BigDecimal stockPrice;
    private Integer quantity;
}
