package com.appoie.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appoie.ids.EstadoId;
import com.appoie.models.Estado;

public interface EstadoRepository extends JpaRepository<Estado, EstadoId>{

}
