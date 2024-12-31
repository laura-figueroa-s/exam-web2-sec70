package examsec70.examsec70.responses;

import examsec70.examsec70.models.Album;
import lombok.Data;

@Data
public class AlbumResponse {

    private int status;
    private String message;
    private Album album;
}
