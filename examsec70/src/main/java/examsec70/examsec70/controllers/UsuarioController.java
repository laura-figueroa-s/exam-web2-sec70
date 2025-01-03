package examsec70.examsec70.controllers;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import examsec70.examsec70.models.Album;
import examsec70.examsec70.models.Lamina;
import examsec70.examsec70.models.Usuario;
import examsec70.examsec70.models.UsuarioAlbum;
import examsec70.examsec70.models.UsuarioLamina;
import examsec70.examsec70.responses.UsuarioAlbumResponse;
import examsec70.examsec70.responses.UsuarioAlbumsResponse;
import examsec70.examsec70.responses.UsuarioLaminaResponse;
import examsec70.examsec70.responses.UsuarioLaminasResponse;
import examsec70.examsec70.services.AlbumService;
import examsec70.examsec70.services.LaminaService;
import examsec70.examsec70.services.UsuarioAlbumService;
import examsec70.examsec70.services.UsuarioLaminaService;
import examsec70.examsec70.services.UsuarioService;

@Controller
@RequestMapping("api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioAlbumService usuarioAlbumService;
    @Autowired
    private UsuarioLaminaService usuarioLaminaService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LaminaService laminaService;

    // Añadir Usuario-Album - Post
    @PostMapping(value = "album/add", produces = "application/json")
    public ResponseEntity<Object> createAlbum(@RequestBody UsuarioAlbum usuarioAlbum) {

        if (usuarioAlbum.getUsuAlbAlbum() == null) {
            throw new RuntimeException("Indicar el Id del Álbum es obligatorio");
        }

        Album album = albumService.buscar(usuarioAlbum.getUsuAlbAlbum().getAlbId());
        if (album == null) {
            throw new RuntimeException("El álbum con id " + usuarioAlbum.getUsuAlbAlbum().getAlbId() + " no existe");
        }

        usuarioAlbumService.crear(usuarioAlbum);

        // Estructura de Respuesta
        UsuarioAlbumResponse usuarioAlbumResponse = new UsuarioAlbumResponse();
        usuarioAlbumResponse.setStatus(200);
        usuarioAlbumResponse.setMessage("Álbum añadido correctamente");
        usuarioAlbumResponse.setUsuarioAlbum(usuarioAlbum);

        return ResponseEntity.ok()
                .body(usuarioAlbumResponse);
    }

    // Listar Usuario-Albumes Propios del Usuario - Get
    @GetMapping(value = "album/listar/{id}", produces = "application/json")
    public ResponseEntity<Object> getAlbum(@PathVariable long id) {
        UsuarioAlbumsResponse usuarioAlbumsResponse = new UsuarioAlbumsResponse();
        usuarioAlbumsResponse.setStatus(200);
        usuarioAlbumsResponse.setMessage("Álbumes listados correctamente");
        usuarioAlbumsResponse.setUsuarioAlbums(usuarioAlbumService.buscarPorUsuario(id));

        return ResponseEntity.ok()
                .body(usuarioAlbumsResponse);
    }

    // Actualizar Usuario-Álbum por Id - Put
    @PutMapping(value = "album/actualizar/{id}", produces = "application/json")
    public ResponseEntity<Object> setAlbum(@PathVariable long id, @RequestBody UsuarioAlbum usuarioAlbumRequest) {
        UsuarioAlbumResponse usuarioAlbumResponse = new UsuarioAlbumResponse();
        UsuarioAlbum usuarioAlbum = new UsuarioAlbum();
        usuarioAlbum = usuarioAlbumService.buscar(id);
        usuarioAlbum.setUsuAlbAlbum(usuarioAlbumRequest.getUsuAlbAlbum());
        usuarioAlbum.setUsuAlbUsuario(usuarioAlbumRequest.getUsuAlbUsuario());
        usuarioAlbumService.crear(usuarioAlbum);

        usuarioAlbumResponse.setStatus(200);
        usuarioAlbumResponse.setMessage("Álbum actualizado exitosamente");
        usuarioAlbumResponse.setUsuarioAlbum(usuarioAlbum);
        return ResponseEntity.ok()
                .body(usuarioAlbumResponse);
    }

    // Eliminar Usuario-Álbum por Id - Delete
    @DeleteMapping(value = "album/eliminar/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteAlbum(@PathVariable long id) {
        UsuarioAlbumResponse usuarioAlbumResponse = new UsuarioAlbumResponse();
        usuarioAlbumService.eliminar(id);
        usuarioAlbumResponse.setStatus(200);
        usuarioAlbumResponse.setMessage("Álbum eliminado exitosamente");
        return ResponseEntity.ok()
                .body(usuarioAlbumResponse);

    }

    // Añadir Usuario-Lámina - Post
    @PostMapping(value = "lamina/add", produces = "application/json")
    public ResponseEntity<Object> createLamina(@RequestBody UsuarioLamina usuarioLamina) {

    if (usuarioLamina.getUsuLamLamina() == null) {
        throw new RuntimeException("Indicar el Id de la Lámina es obligatorio");
    }
    if (usuarioLamina.getUsuLamUsuario() == null) {
        throw new RuntimeException("Indicar el Id del Usuario es obligatorio");
    }
    if (usuarioLamina.getCantidad() <= 0) {
        throw new RuntimeException("La cantidad debe ser un número positivo");
    }

    // Check if the user and lamina exist
    Usuario usuario = usuarioService.buscar(usuarioLamina.getUsuLamUsuario().getUsuId());
    if (usuario == null) {
        throw new RuntimeException("El usuario no existe");
    }
    Lamina lamina = laminaService.buscar(usuarioLamina.getUsuLamLamina().getLamId());
    if (lamina == null) {
        throw new RuntimeException("La lámina no existe");
    }

    usuarioLaminaService.crear(usuarioLamina);

    // Estructura de Respuesta
    UsuarioLaminaResponse usuarioLaminaResponse = new UsuarioLaminaResponse();
    usuarioLaminaResponse.setStatus(200);
    usuarioLaminaResponse.setMessage("Lámina añadida correctamente");
    usuarioLaminaResponse.setUsuarioLamina(usuarioLamina);

    return ResponseEntity.ok()
            .body(usuarioLaminaResponse);

}

    // Listar Usuario-Láminas Propias del Usuario - Get
    @GetMapping(value = "lamina/listar/{id}", produces = "application/json")
    public ResponseEntity<Object> getLamina(@PathVariable long id) {
    UsuarioLaminasResponse usuarioLaminasResponse = new UsuarioLaminasResponse();
    usuarioLaminasResponse.setStatus(200);
    usuarioLaminasResponse.setMessage("Láminas listadas correctamente");
    usuarioLaminasResponse.setUsuarioLaminas(usuarioLaminaService.buscarPorUsuarioId(id));

    return ResponseEntity.ok()
            .body(usuarioLaminasResponse);
}

    // Listar Usuario-Láminas Repetidas - Get

    @GetMapping("/lamina/listar/{id}/repetidas")
    public ResponseEntity<List<UsuarioLamina>> getLaminasRepetidas(@PathVariable Long id) {
        List<UsuarioLamina> laminasRepetidas = usuarioLaminaService.buscarLaminasRepetidas(id);
        return ResponseEntity.ok(laminasRepetidas);
    }

    // Listar Usuario-Láminas Faltantes - Get
    @GetMapping("/laminas/faltantes/{id}")
    public ResponseEntity<Object> getLaminasFaltantes(@PathVariable long id) {
        List<Lamina> faltantes = usuarioLaminaService.buscarLaminasFaltantes(id, 1);
        return ResponseEntity.ok(faltantes);
    }

    // Actualizar Usuario-Lámina por Id - Put
    @PutMapping(value = "lamina/actualizar/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> setLamina(@PathVariable long id, @RequestBody UsuarioLamina usuarioLaminaRequest) {
        UsuarioLaminaResponse usuarioLaminaResponse = new UsuarioLaminaResponse();
        UsuarioLamina usuarioLamina = new UsuarioLamina();
        usuarioLamina = usuarioLaminaService.buscar(id);
        if (usuarioLamina == null) {
            usuarioLaminaResponse.setStatus(404);
            usuarioLaminaResponse.setMessage("Lámina no encontrada");
            return ResponseEntity.status(404).body(usuarioLaminaResponse);
        }
        usuarioLamina.setUsuLamLamina(usuarioLaminaRequest.getUsuLamLamina());
        usuarioLamina.setUsuLamUsuario(usuarioLaminaRequest.getUsuLamUsuario());
        usuarioLamina.setCantidad(usuarioLaminaRequest.getCantidad());
        usuarioLaminaService.crear(usuarioLamina);

        usuarioLaminaResponse.setStatus(200);
        usuarioLaminaResponse.setMessage("Lámina actualizada exitosamente");
        usuarioLaminaResponse.setUsuarioLamina(usuarioLamina);
        return ResponseEntity.ok()
                .body(usuarioLaminaResponse);
    }

    // Eliminar Usuario-Lámina por Id - Delete
    @DeleteMapping(value = "lamina/eliminar/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteLamina(@PathVariable long id) {
        UsuarioLaminaResponse usuarioLaminaResponse = new UsuarioLaminaResponse();
        usuarioLaminaService.eliminar(id);
        usuarioLaminaService.eliminar(id);
        usuarioLaminaResponse.setStatus(200);
        usuarioLaminaResponse.setMessage("Lámina eliminada exitosamente");
        return ResponseEntity.ok()
                .body(usuarioLaminaResponse);

    }

}
