package examsec70.examsec70.controllers;

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

import examsec70.examsec70.models.UsuarioAlbum;
import examsec70.examsec70.models.UsuarioLamina;
import examsec70.examsec70.responses.UsuarioAlbumResponse;
import examsec70.examsec70.responses.UsuarioLaminaResponse;
import examsec70.examsec70.responses.UsuarioLaminasResponse;

import examsec70.examsec70.services.UsuarioAlbumService;
import examsec70.examsec70.services.UsuarioLaminaService;

@Controller
@RequestMapping("api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioAlbumService usuarioAlbumService;
    @Autowired
    private UsuarioLaminaService usuarioLaminaService;

    // Añadir Usuario-Album - Post
    @PostMapping(value = "album/add", produces = "application/json")
    public ResponseEntity<Object> createAlbum(@RequestBody UsuarioAlbum usuarioAlbum) {

        if (usuarioAlbum.getUsuAlbAlbum() == null) {
            throw new RuntimeException("Indicar el Id del Álbum es obligatorio");
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
        UsuarioAlbumResponse usuarioAlbumResponse = new UsuarioAlbumResponse();
        usuarioAlbumResponse.setStatus(200);
        usuarioAlbumResponse.setMessage("Álbumes listados correctamente");
        usuarioAlbumResponse.setUsuarioAlbum(usuarioAlbumService.buscar(id));

        return ResponseEntity.ok()
                .body(usuarioAlbumResponse);
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
        usuarioLaminaService.crear(usuarioLamina);

        // Estructura de Respuesta
        UsuarioLaminaResponse usuarioLaminaResponse = new UsuarioLaminaResponse();
        usuarioLaminaResponse.setStatus(200);
        usuarioLaminaResponse.setMessage("Álbum añadido correctamente");
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
        usuarioLaminasResponse.setUsuarioLaminas(usuarioLaminaService.buscarMayordeCero(id));

        return ResponseEntity.ok()
                .body(usuarioLaminasResponse);
    }

    // Listar Usuario-Láminas Repetidas - Get
    @GetMapping(value = "lamina/listar/{id}/repetidas", produces = "application/json")
    public ResponseEntity<Object> getLaminaRepetida(@PathVariable long id) {
        UsuarioLaminasResponse usuarioLaminasResponse = new UsuarioLaminasResponse();
        usuarioLaminasResponse.setStatus(200);
        usuarioLaminasResponse.setMessage("Láminas repetidas listadas correctamente");
        usuarioLaminasResponse.setUsuarioLaminas(usuarioLaminaService.buscarRepetidos(id));

        return ResponseEntity.ok()
                .body(usuarioLaminasResponse);
    }

    // Listar Usuario-Láminas Faltantes - Get
    @GetMapping(value = "lamina/listar/{id}/faltantes", produces = "application/json")
    public ResponseEntity<Object> getLaminaFaltante(@PathVariable long id) {
        UsuarioLaminasResponse usuarioLaminasResponse = new UsuarioLaminasResponse();
        usuarioLaminasResponse.setStatus(200);
        usuarioLaminasResponse.setMessage("Láminas faltantes listadas correctamente");
        usuarioLaminasResponse.setUsuarioLaminas(usuarioLaminaService.buscarFaltantes(id));

        return ResponseEntity.ok()
                .body(usuarioLaminasResponse);
    }

    // Actualizar Usuario-Lámina por Id - Put
    @PutMapping(value = "lamina/actualizar/{id}", produces = "application/json")
    public ResponseEntity<Object> setLamina(@PathVariable long id, @RequestBody UsuarioLamina usuarioLaminaRequest) {
        UsuarioLaminaResponse usuarioLaminaResponse = new UsuarioLaminaResponse();
        UsuarioLamina usuarioLamina = new UsuarioLamina();
        usuarioLamina = usuarioLaminaService.buscar(id);
        usuarioLamina.setUsuLamId(usuarioLaminaRequest.getUsuLamId());
        usuarioLamina.setUsuLamAlbum(usuarioLaminaRequest.getUsuLamAlbum());
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
