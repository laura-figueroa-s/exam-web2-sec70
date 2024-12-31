package examsec70.examsec70.responses;

import examsec70.examsec70.models.Usuario;
import lombok.Data;

@Data
public class UsuarioResponse {
    private int status;
    private String message;
    private Usuario usuario;
}
