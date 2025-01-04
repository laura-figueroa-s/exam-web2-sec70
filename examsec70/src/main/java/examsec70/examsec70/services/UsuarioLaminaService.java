package examsec70.examsec70.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examsec70.examsec70.models.Lamina;
import examsec70.examsec70.models.UsuarioLamina;
import examsec70.examsec70.repositories.AlbumRepository;
import examsec70.examsec70.repositories.LaminaRepository;
import examsec70.examsec70.repositories.UsuarioLaminaRepository;

@Service
public class UsuarioLaminaService {

    @Autowired
    public UsuarioLaminaRepository usuarioLaminaRepository;

    @Autowired
    public AlbumService albumService;

    @Autowired
    public AlbumRepository albumRepository;

    @Autowired
    public LaminaRepository laminaRepository;

    // crear
    public UsuarioLamina crear(UsuarioLamina usuLam) {
        return usuarioLaminaRepository.save(usuLam);
    }

    // listar
    public List<UsuarioLamina> ListarTodos() {
        return usuarioLaminaRepository.findAll();
    }

    // buscar
    public UsuarioLamina buscar(long id) {
        return usuarioLaminaRepository.findById(id).orElse(null);
    }

    public List<UsuarioLamina> buscarPorUsuarioId(Long id) {
        List<UsuarioLamina> usuarioLaminas = usuarioLaminaRepository.findByUsuLamUsuarioUsuId(id);

        for (UsuarioLamina usuarioLamina : usuarioLaminas) {
            Hibernate.initialize(usuarioLamina.getUsuLamLamina());
            Hibernate.initialize(usuarioLamina.getUsuLamUsuario());
        }

        return usuarioLaminas;
    }

    // buscar si es mayor que un numero
    public List<UsuarioLamina> buscarLaminasRepetidas(Long userId) {
        return usuarioLaminaRepository.findByUsuLamUsuarioUsuIdAndCantidadGreaterThan(userId, 1);
    }

    /* public List<UsuarioLamina> buscarMayorQue(long id, long cantidad) {
        return usuarioLaminaRepository.findByCantidadGreaterThanAndUsuLamUsuarioUsuId(id, cantidad);
    } */


    // buscar faltantes por usuario
    public List<Lamina> buscarLaminasFaltantes(long id, long cantidad) {
        return usuarioLaminaRepository.findMissingLaminasForUserAlbums(id, cantidad);
    }

    /* public List<UsuarioLamina> buscarMenorQue(long id, long cantidad) {
        return usuarioLaminaRepository.findByCantidadLessThanAndUsuLamUsuarioUsuId(cantidad, id);
    }  */

    // eliminar
    public void eliminar(long usuLam) {
        usuarioLaminaRepository.deleteById(usuLam);
    }
}
