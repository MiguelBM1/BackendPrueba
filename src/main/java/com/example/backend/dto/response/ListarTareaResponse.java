package com.example.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListarTareaResponse {

    private String id;
    private String nombre;
    private String descripcion;
    private String fechaFinalizacion;
    private String estado;
    private String persona;

    public ListarTareaResponse(String id, String nombre, String descripcion, String fechaFinalizacion, String estado, String persona) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.estado = estado;
        this.persona = persona;
    }

    public ListarTareaResponse() {

    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public String getEstado() {
        return estado;
    }

    public String getPersona() {
        return persona;
    }
}
