package com.goldenboy.server.mapper.dto;

import com.goldenboy.server.dto.MotorbikeDTO;
import com.goldenboy.server.entity.Motorbike;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MotorbikeDTOMapper {
    @Autowired
    private ModelMapper mapper;

    public MotorbikeDTO toMotorbikeDTO(Motorbike motorbike) {
        return this.mapper.map(motorbike, MotorbikeDTO.class);
    }

    public List<MotorbikeDTO> toMotorbikeDTOs(List<Motorbike> motorbikes) {
        return motorbikes.stream().map(this::toMotorbikeDTO).collect(Collectors.toList());
    }
}
