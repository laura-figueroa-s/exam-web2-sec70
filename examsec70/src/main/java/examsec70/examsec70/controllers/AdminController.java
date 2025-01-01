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

import examsec70.examsec70.models.Album;
import examsec70.examsec70.models.Lamina;
import examsec70.examsec70.responses.AlbumResponse;
import examsec70.examsec70.responses.AlbumsResponse;
import examsec70.examsec70.responses.LaminaResponse;
import examsec70.examsec70.responses.LaminasResponse;

import examsec70.examsec70.services.AlbumService;
import examsec70.examsec70.services.LaminaService;

@Controller
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    AlbumService albumService;
    @Autowired
    LaminaService laminaService;

    // Crear Álbum - Post
    @PostMapping(value = "album/crear", produces = "application/json")
    public ResponseEntity<Object> createAlbum(@RequestBody Album album) {

        if (album.getAlbNombre().isEmpty() || album.getAlbNombre() == null) {
            throw new RuntimeException("El nombre del Álbum es requerido");
        }
        albumService.crear(album);

        // Estructura de Respuesta
        AlbumResponse albumResponse = new AlbumResponse();
        albumResponse.setStatus(200);
        albumResponse.setMessage("Álbum creado correctamente");
        albumResponse.setAlbum(album);

        return ResponseEntity.ok()
                .body(albumResponse);

    }

    // Listar Álbums - Get
    @GetMapping(value = "album/listar", produces = "application/json")
    public ResponseEntity<Object> getAlbum() {
        AlbumsResponse albumsResponse = new AlbumsResponse();
        albumsResponse.setStatus(200);
        albumsResponse.setMessage("Álbums listados correctamente");
        albumsResponse.setAlbum(albumService.ListarTodos());

        return ResponseEntity.ok()
                .body(albumsResponse);
    }

    // Actualizar Álbum por Id - Put
    @PutMapping(value = "album/actualizar/{id}", produces = "application/json")
    public ResponseEntity<Object> setAlbum(@PathVariable long id, @RequestBody Album albumRequest) {
        AlbumResponse albumResponse = new AlbumResponse();
        Album album = new Album();
        album = albumService.buscar(id);
        album.setAlbNombre(albumRequest.getAlbNombre());
        album.setAlbDescripcion(albumRequest.getAlbDescripcion());
        album.setCantLaminas(albumRequest.getCantLaminas());
        albumService.crear(album);

        albumResponse.setStatus(200);
        albumResponse.setMessage("Álbum actualizado exitosamente");
        albumResponse.setAlbum(album);
        return ResponseEntity.ok()
                .body(albumResponse);
    }

    // Eliminar Álbum por Id - Delete
    @DeleteMapping(value = "album/eliminar/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteAlbum(@PathVariable long id) {
        AlbumResponse albumResponse = new AlbumResponse();
        albumService.eliminar(id);
        albumResponse.setStatus(200);
        albumResponse.setMessage("Álbum eliminado exitosamente");
        return ResponseEntity.ok()
                .body(albumResponse);

    }

    // Crear Láminas - Post
    @PostMapping(value = "lamina/crear", produces = "application/json")
    public ResponseEntity<Object> createLamina(@RequestBody Lamina lamina) {

        if (lamina.getLamAlbum() == null) {
            throw new RuntimeException("Indicar un Álbum es requerido");
        }
        laminaService.crear(lamina);

        // Estructura de Respuesta
        LaminaResponse laminaResponse = new LaminaResponse();
        laminaResponse.setStatus(200);
        laminaResponse.setMessage("Lámina creada correctamente");
        laminaResponse.setLamina(lamina);

        return ResponseEntity.ok()
                .body(laminaResponse);
    }

    // Listar Láminas - Get
    @GetMapping(value = "lamina/listar", produces = "application/json")
    public ResponseEntity<Object> getLamina() {
        LaminasResponse laminasResponse = new LaminasResponse();
        laminasResponse.setStatus(200);
        laminasResponse.setMessage("Láminas listadas correctamente");
        laminasResponse.setLamina(laminaService.ListarTodos());

        return ResponseEntity.ok()
                .body(laminasResponse);
    }

    // Actualizar Láminas por Id - Put
    @PutMapping(value = "lamina/actualizar/{id}", produces = "application/json")
    public ResponseEntity<Object> setLamina(@PathVariable long id, @RequestBody Lamina laminaRequest) {
        LaminaResponse laminaResponse = new LaminaResponse();
        Lamina lamina = new Lamina();
        lamina = laminaService.buscar(id);
        lamina.setLamNumero(laminaRequest.getLamNumero());
        lamina.setLamAlbum(laminaRequest.getLamAlbum());
        laminaService.crear(lamina);

        laminaResponse.setStatus(200);
        laminaResponse.setMessage("Lámina actualizada exitosamente");
        laminaResponse.setLamina(lamina);
        return ResponseEntity.ok()
                .body(laminaResponse);
    }

    // Eliminar Lámina por Id - Delete
    @DeleteMapping(value = "lamina/eliminar/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteLamina(@PathVariable long id) {
        LaminaResponse laminaResponse = new LaminaResponse();
        laminaService.eliminar(id);
        laminaResponse.setStatus(200);
        laminaResponse.setMessage("Lámina eliminada exitosamente");
        return ResponseEntity.ok()
                .body(laminaResponse);

    }

}
