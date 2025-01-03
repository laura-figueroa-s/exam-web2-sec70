package examsec70.examsec70.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examsec70.examsec70.models.UsuarioAlbum;
import examsec70.examsec70.repositories.UsuarioAlbumRepository;

@Service
public class UsuarioAlbumService {

    @Autowired
    public UsuarioAlbumRepository usuarioAlbumRepository;

    // crear
    public UsuarioAlbum crear(UsuarioAlbum usuAlb) {
        return usuarioAlbumRepository.save(usuAlb);
    }

    // listar
    public List<UsuarioAlbum> ListarTodos() {
        return usuarioAlbumRepository.findAll();
    }

    // buscar
    public UsuarioAlbum buscar(long usuAlb) {
        return usuarioAlbumRepository.findById(usuAlb).orElse(null);
    }

    // buscar por usuario
    public List<UsuarioAlbum> buscarPorUsuario(long usuId) {
        return usuarioAlbumRepository.findByUsuAlbUsuarioUsuId(usuId);
    }
    
    // eliminar
    public void eliminar(long usuAlb) {
        usuarioAlbumRepository.deleteById(usuAlb);
    }

}
