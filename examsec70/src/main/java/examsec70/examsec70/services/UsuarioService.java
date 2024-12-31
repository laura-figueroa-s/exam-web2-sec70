package examsec70.examsec70.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examsec70.examsec70.models.Usuario;
import examsec70.examsec70.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    // crear
    public Usuario crear(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // listar
    public List<Usuario> ListarTodos() {
        return usuarioRepository.findAll();
    }

    // buscar
    public Usuario buscar(long usuId) {
        return usuarioRepository.findById(usuId).orElse(null);
    }

    // eliminar
    public void eliminar(long usuId) {
        usuarioRepository.deleteById(usuId);
    }

}
