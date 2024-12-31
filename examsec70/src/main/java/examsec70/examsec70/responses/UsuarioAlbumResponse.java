package examsec70.examsec70.responses;

import examsec70.examsec70.models.UsuarioAlbum;
import lombok.Data;

@Data
public class UsuarioAlbumResponse {
    private int status;
    private String message;
    private UsuarioAlbum usuarioAlbum;
}
