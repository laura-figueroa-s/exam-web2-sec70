package examsec70.examsec70.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examsec70.examsec70.models.UsuarioAlbum;

@Repository
public interface UsuarioAlbumRepository extends JpaRepository<UsuarioAlbum, Long> {
    List<UsuarioAlbum> findByUsuAlbUsuarioUsuId(long usuId);

}
