package com.goldenboy.server.mapper.dto;

import com.goldenboy.server.dto.ModelDTO;
import com.goldenboy.server.entity.Model;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ModelDTOMapper {
    @Autowired
    private ModelMapper mapper;

    public ModelDTO toModelDTO(Model model) {
        return mapper.map(model, ModelDTO.class);
    }

    public List<ModelDTO> toModelDTOs(List<Model> models) {
        return models.stream().map(this::toModelDTO).collect(Collectors.toList());
    }

    public Set<ModelDTO> toModelDTOs(Set<Model> models) {
        return models.stream().map(this::toModelDTO).collect(Collectors.toSet());
    }
}
