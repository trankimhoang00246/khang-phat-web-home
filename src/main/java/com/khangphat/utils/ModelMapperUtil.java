package com.khangphat.utils;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelMapperUtil<D, E> {

    @Autowired
    private ModelMapper modelMapper;


    private Class<E> entityClass;

    private Class<D> dtoClass;

    public void updateClasses(Class<D> dtoClass, Class<E> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public E toEntity(D dto) {
        return modelMapper.map(dto,entityClass);
    }

    public D toDto(E entity) {
        return modelMapper.map(entity, dtoClass);
    }
}