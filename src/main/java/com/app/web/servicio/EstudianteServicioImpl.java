package com.app.web.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.app.web.dto.EstudianteDTO;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.entidad.Estudiante;
import com.app.web.repositorio.EstudianteRepositorio;

@Service
public class EstudianteServicioImpl implements EstudianteServicio {

    @Autowired
    private EstudianteRepositorio repository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<EstudianteDTO> listarTodosLosEstudiantes() {
        //return repository.findAll().stream().map(estudiantes -> modelMapper.map(estudiantes, EstudianteDTO.class)).collect(Collectors.toList());
        return repository.findAll().stream().map(estudiantes -> {
            EstudianteDTO estudianteDTO = new EstudianteDTO();
            estudianteDTO.setId(estudiantes.getId());
            estudianteDTO.setNombre(estudiantes.getNombre());
            estudianteDTO.setApellido(estudiantes.getApellido());
            estudianteDTO.setEmail(estudiantes.getEmail());
            return estudianteDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public Estudiante guardarEstudiante(EstudianteDTO estudianteDTO) {
        //Estudiante estudianteNuevo = modelMapper.map(estudianteDTO, Estudiante.class);
        Estudiante estudianteNuevo = new Estudiante();
        estudianteNuevo.setId(estudianteDTO.getId());
        estudianteNuevo.setNombre(estudianteDTO.getNombre());
        estudianteNuevo.setApellido(estudianteDTO.getApellido());
        estudianteNuevo.setEmail(estudianteDTO.getEmail());
        return repository.save(estudianteNuevo);
    }

    @Override
    public Estudiante obtenerEstudiantePorId(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public boolean validarEstudianteExiste(Long id) {
        return repository.findById(id).isPresent();
    }

    @Override
    public Estudiante actualizarEstudiante(EstudianteDTO estudianteDTO) {
        if (this.validarEstudianteExiste(estudianteDTO.getId())) {
            //Estudiante estudianteExistente = modelMapper.map(estudianteDTO, Estudiante.class);
            Estudiante estudianteExistente = new Estudiante();
            estudianteExistente.setId(estudianteDTO.getId());
            estudianteExistente.setNombre(estudianteDTO.getNombre());
            estudianteExistente.setApellido(estudianteDTO.getApellido());
            estudianteExistente.setEmail(estudianteDTO.getEmail());
            return repository.save(estudianteExistente);
        }
        return null;
    }

    @Override
    public void eliminarEstudiante(Long id) {
        repository.deleteById(id);

    }

}
