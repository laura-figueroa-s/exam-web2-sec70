package examsec70.examsec70.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examsec70.examsec70.models.UsuarioLamina;

@Repository
public interface UsuarioLaminaRepository extends JpaRepository<UsuarioLamina, Long> {
List<UsuarioLamina> findByCantidadGreaterThanAndUsuLamUsuarioUsuId(long cantidad, long usuId);
  List<UsuarioLamina> findByCantidadLessThanAndUsuLamUsuarioUsuId(long cantidad, long usuId);
}

