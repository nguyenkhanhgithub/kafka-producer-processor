package com.cmcglobal.kafkastreamprocessor.mappers;

import com.cmcglobal.dashboard.avro.DashboardEmailValue;
import com.cmcglobal.kafkastreamprocessor.entities.Example;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    DashboardEmailValue exampleToAvro(Example value);
}
