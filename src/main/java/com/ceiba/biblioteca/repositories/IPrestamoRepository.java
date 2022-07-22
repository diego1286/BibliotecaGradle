package com.ceiba.biblioteca.repositories;

import com.ceiba.biblioteca.models.Prestamo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPrestamoRepository extends CrudRepository<Prestamo, Long> {

    List<Prestamo> findByIdentificacionUsuario(String identificacionUsuario);

    Optional<Prestamo> findById(Long id);
}
