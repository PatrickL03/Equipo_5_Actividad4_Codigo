package com.lasalle.contacto.repository;

import com.lasalle.contacto.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de acceso a datos para Contacto.
 * Spring Data JPA genera automaticamente la implementacion,
 * incluyendo metodos como count(), save(), findAll(), etc.
 */
@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
}
