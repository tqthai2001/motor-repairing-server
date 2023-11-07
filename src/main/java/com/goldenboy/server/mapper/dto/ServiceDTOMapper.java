package com.goldenboy.server.mapper.dto;

import com.goldenboy.server.dto.ServiceDTO;
import com.goldenboy.server.entity.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceDTOMapper {
    @Autowired
    private ModelMapper mapper;

    public ServiceDTO toServiceDTO(Service service) {
        TypeMap<Service, ServiceDTO> propertyMapper = mapper.getTypeMap(Service.class, ServiceDTO.class) ==
                                                      null ? mapper.createTypeMap(Service.class, ServiceDTO.class) :
                mapper.getTypeMap(Service.class, ServiceDTO.class);
        propertyMapper.addMappings(mapper -> {
        });
        return mapper.map(service, ServiceDTO.class);
    }

    public List<ServiceDTO> toServiceDTOs(List<Service> services) {
        return services.stream().map(this::toServiceDTO).collect(Collectors.toList());
    }
}
