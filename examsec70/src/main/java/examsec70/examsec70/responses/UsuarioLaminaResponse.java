package examsec70.examsec70.responses;

import examsec70.examsec70.models.UsuarioLamina;
import lombok.Data;

@Data
public class UsuarioLaminaResponse {
    private int status;
    private String message;
    private UsuarioLamina usuarioLamina;
}
