package examsec70.examsec70.responses;

import java.util.List;

import examsec70.examsec70.models.UsuarioAlbum;
import lombok.Data;

@Data
public class UsuarioAlbumsResponse {
    private int status;
    private String message;
    private List<UsuarioAlbum> usuarioAlbums;
}
