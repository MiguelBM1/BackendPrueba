package com.example.backend.mappers;

import com.example.backend.dto.response.ListarTareaResponse;
import com.example.backend.entities.Tareas;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ListarTareaMapper implements Function<Tareas, ListarTareaResponse> {

    @Override
    public ListarTareaResponse apply(Tareas tarea) {
        return ListarTareaResponse.builder()
                .id(tarea.getIdTarea().toString())
                .nombre(tarea.getNombre())
                .descripcion(tarea.getDescripcion())
                .fechaFinalizacion(tarea.getFechaFinalizacion().toString())
                .estado(tarea.getEstados().getNombre())
                .persona(tarea.getPersonas().getNombre())
                .build();
    }
}
