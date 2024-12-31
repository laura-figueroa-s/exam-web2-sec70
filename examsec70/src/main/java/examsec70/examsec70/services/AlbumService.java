package examsec70.examsec70.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examsec70.examsec70.models.Album;
import examsec70.examsec70.repositories.AlbumRepository;

@Service
public class AlbumService {

    @Autowired
    public AlbumRepository albumRepository;

    // crear
    public Album crear(Album album) {
        return albumRepository.save(album);
    }

    // listar
    public List<Album> ListarTodos() {
        return albumRepository.findAll();
    }

    // buscar
    public Album buscar(long albId) {
        return albumRepository.findById(albId).orElse(null);
    }

    // eliminar
    public void eliminar(long albId) {
        albumRepository.deleteById(albId);
    }
}
