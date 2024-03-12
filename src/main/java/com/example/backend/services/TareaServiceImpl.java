package com.example.backend.services;

import com.example.backend.dto.requests.CrearTareaRequest;
import com.example.backend.dto.requests.EditarTareaRequest;
import com.example.backend.dto.response.ListarTareaResponse;
import com.example.backend.entities.Tareas;
import com.example.backend.exceptions.NotFoundException;
import com.example.backend.mappers.ListarTareaMapper;
import com.example.backend.repositories.EstadoRepository;
import com.example.backend.repositories.PersonaRepository;
import com.example.backend.repositories.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService{

    private final TareaRepository tareaRepository;
    private final ListarTareaMapper tareaMapper;

    private final EstadoRepository estadoRepository;

    private final PersonaRepository personaRepository;

    @Override
    public List<ListarTareaResponse> obtenerTareas() {
        List<Tareas> lista = this.tareaRepository.findAll();
        List<ListarTareaResponse> listaRespuestas = new ArrayList<>();
        for (Tareas tarea : lista) {
            ListarTareaResponse respuesta = new ListarTareaResponse();
            respuesta.setId(tarea.getIdTarea().toString());
            respuesta.setNombre(tarea.getNombre());
            respuesta.setDescripcion(tarea.getDescripcion());
            respuesta.setFechaFinalizacion(tarea.getFechaFinalizacion().toString());
            respuesta.setEstado(tarea.getEstados().getNombre());
            respuesta.setPersona(tarea.getPersonas().getNombre());
            listaRespuestas.add(respuesta);
        }
        return listaRespuestas;

    }

    @Override
    @Transactional
    public ListarTareaResponse crearTareas(CrearTareaRequest request) {
   /*  var tareaExiste = tareaRepository.findByNombre(request.getNombre());

    if (tareaExiste.isPresent()) {
      throw new ConflictException("Ya existe una tarea con el nombre: " + request.getNombre());
    }*/

        var tarea = tareaRepository.save(request.toEntity());

        return tareaMapper.apply(tarea);
    }

    @Override
    public ListarTareaResponse obtenerTarea(String id) {
        var tarea = tareaRepository.findById(Integer.valueOf(id));

        if (tarea.isEmpty()) {
            throw new NotFoundException("No existe una tarea con el id: " + id);
        }

        return tareaMapper.apply(tarea.get());
    }
    @Override
    public ListarTareaResponse actualizarTareas(String id, EditarTareaRequest request) {
        var tarea = tareaRepository.findById(Integer.valueOf(id));

        if (tarea.isEmpty()) {
            throw new NotFoundException("No existe una tarea con el id: " + id);
        }

        tarea.get().setNombre(request.getNombre());
        tarea.get().setDescripcion(request.getDescripcion());
        tarea.get().setFechaFinalizacion(LocalDate.parse(request.getFechaFinalizacion()));
        tarea.get().setEstados(estadoRepository.findById(Integer.valueOf(request.getEstado())).orElseThrow(() -> new NotFoundException("El estado no existe")));
        tarea.get().setPersonas(personaRepository.findById(Integer.valueOf(request.getPersona())).orElseThrow(() -> new NotFoundException("La persona no existe")));

        var tareaActualizada = tareaRepository.save(tarea.get());

        return tareaMapper.apply(tareaActualizada);
    }

    @Override
    public void eliminarTareas(String id) {
        var tarea = tareaRepository.findById(Integer.valueOf(id));

        if (tarea.isEmpty()) {
            throw new NotFoundException("No existe una tarea con el id: " + id);
        }
        tareaRepository.delete(tarea.get());
    }
}
