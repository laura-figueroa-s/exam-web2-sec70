package examsec70.examsec70.responses;

import java.util.List;

import examsec70.examsec70.models.Lamina;
import lombok.Data;

@Data
public class LaminasResponse {
    private int status;
    private String message;
    private List<Lamina> lamina;
}
