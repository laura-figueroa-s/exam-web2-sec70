package examsec70.examsec70.responses;

import java.util.List;

import examsec70.examsec70.models.Usuario;
import lombok.Data;

@Data
public class UsuariosResponse {
    private int status;
    private String message;
    private List<Usuario> usuario;
}
