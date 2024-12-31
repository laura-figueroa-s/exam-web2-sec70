package examsec70.examsec70.responses;

import java.util.List;

import examsec70.examsec70.models.UsuarioLamina;
import lombok.Data;

@Data
public class UsuarioLaminasResponse {
    private int status;
    private String message;
    private List<UsuarioLamina> usuarioLaminas;
}
