package examsec70.examsec70.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import examsec70.examsec70.models.UsuarioLamina;
import examsec70.examsec70.repositories.UsuarioLaminaRepository;

@Service
public class UsuarioLaminaService {

    @Autowired
    public UsuarioLaminaRepository usuarioLaminaRepository;

    // crear
    public UsuarioLamina crear(UsuarioLamina usuLam) {
        return usuarioLaminaRepository.save(usuLam);
    }

    // listar
    public List<UsuarioLamina> ListarTodos() {
        return usuarioLaminaRepository.findAll();
    }

    // buscar
    public UsuarioLamina buscar(long usuLam) {
        return usuarioLaminaRepository.findById(usuLam).orElse(null);
    }

    // buscar repetidos por usuario
    public List<UsuarioLamina> buscarRepetidos(long id) {
        return usuarioLaminaRepository.findByCantidadGreaterThanAndUsuLamId(1, id);
    }

    // buscar faltantes por usuario
    public List<UsuarioLamina> buscarFaltantes(long id) {
        return usuarioLaminaRepository.findByCantidadLessThanAndUsuLamId(1, id);
    }

    // eliminar
    public void eliminar(long usuLam) {
        usuarioLaminaRepository.deleteById(usuLam);
    }

}
