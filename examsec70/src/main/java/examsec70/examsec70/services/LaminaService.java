package examsec70.examsec70.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import examsec70.examsec70.models.Lamina;
import examsec70.examsec70.repositories.LaminaRepository;

@Service
public class LaminaService {

    @Autowired
    public LaminaRepository laminaRepository;

    // crear
    public Lamina crear(Lamina album) {
        return laminaRepository.save(album);
    }

    // listar
    public List<Lamina> ListarTodos() {
        return laminaRepository.findAll();
    }

    // buscar
    public Lamina buscar(long lamId) {
        return laminaRepository.findById(lamId).orElse(null);
    }

    // eliminar
    public void eliminar(long lamId) {
        laminaRepository.deleteById(lamId);
    }

}
