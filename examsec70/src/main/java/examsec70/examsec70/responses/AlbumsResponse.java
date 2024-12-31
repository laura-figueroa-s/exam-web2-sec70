package examsec70.examsec70.responses;

import java.util.List;

import examsec70.examsec70.models.Album;
import lombok.Data;

@Data
public class AlbumsResponse {
    private int status;
    private String message;
    private List<Album> album;

}
