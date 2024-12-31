package examsec70.examsec70.responses;
import examsec70.examsec70.models.Lamina;
import lombok.Data;

@Data
public class LaminaResponse {
    private int status;
    private String message;
    private Lamina lamina;
}
