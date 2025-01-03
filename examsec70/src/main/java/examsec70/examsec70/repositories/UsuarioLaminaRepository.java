package examsec70.examsec70.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import examsec70.examsec70.models.Lamina;
import examsec70.examsec70.models.UsuarioLamina;

@Repository
public interface UsuarioLaminaRepository extends JpaRepository<UsuarioLamina, Long> {
  List<UsuarioLamina> findByCantidadGreaterThanAndUsuLamUsuarioUsuId(long cantidad, long usuId);
  List<UsuarioLamina> findByCantidadLessThanAndUsuLamUsuarioUsuId(long cantidad, long usuId);

  @Query("SELECT ul FROM UsuarioLamina ul JOIN FETCH ul.usuLamLamina JOIN FETCH ul.usuLamUsuario WHERE ul.usuLamUsuario.usuId = :id")
  List<UsuarioLamina> findByUsuLamUsuarioUsuId(@Param("id") Long id);

  List<UsuarioLamina> findByUsuLamUsuarioUsuIdAndCantidadGreaterThan(Long userId, int cantidad);

  @Query("SELECT lam FROM Lamina lam " +
       "LEFT JOIN UsuarioLamina ul ON lam.lamId = ul.usuLamLamina.lamId AND ul.usuLamUsuario.usuId = :userId " +
       "JOIN UsuarioAlbum ua ON lam.lamAlbum.albId = ua.usuAlbAlbum.albId " +
       "WHERE ua.usuAlbUsuario.usuId = :userId " +
       "AND (ul.cantidad < :cantidad OR ul IS NULL)")
  List<Lamina> findMissingLaminasForUserAlbums(@Param("userId") long userId, @Param("cantidad") long cantidad);

}

