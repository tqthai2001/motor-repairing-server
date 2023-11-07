package com.goldenboy.server.mapper.dto;

import com.goldenboy.server.dto.EmployeeDTO;
import com.goldenboy.server.entity.Employee;
import com.goldenboy.server.entity.Role;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeDTOMapper {
    @Autowired
    private ModelMapper mapper;

    public EmployeeDTO toEmployeeDTO(Employee employee) {
        TypeMap<Employee, EmployeeDTO> propertyMapper = this.mapper.getTypeMap(Employee.class, EmployeeDTO.class) ==
                                                        null ? this.mapper.createTypeMap(Employee.class,
                                                                                         EmployeeDTO.class) :
                this.mapper.getTypeMap(Employee.class, EmployeeDTO.class);
        Converter<Set<Role>, Set<String>> converter = context -> context.getSource()
                                                                        .stream()
                                                                        .map(item -> item.getName()
                                                                                         .name())
                                                                        .collect(Collectors.toSet());
        propertyMapper.addMappings(mapper -> mapper.using(converter).map(Employee::getRoles, EmployeeDTO::setRoles));
        EmployeeDTO tmp = this.mapper.map(employee, EmployeeDTO.class);
        return mapper.map(employee, EmployeeDTO.class);
    }

    public List<EmployeeDTO> toEmployeeDTOs(List<Employee> employees) {
        return employees.stream().map(employee -> this.toEmployeeDTO(employee)).collect(Collectors.toList());
    }
}
