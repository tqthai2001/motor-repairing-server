package com.goldenboy.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.goldenboy.server.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class MotorbikeDTO extends BaseDTO {
    private String licensePlates;
    private ModelDTO model;
}
