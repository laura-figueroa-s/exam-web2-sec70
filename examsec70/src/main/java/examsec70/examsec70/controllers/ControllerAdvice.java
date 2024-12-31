package examsec70.examsec70.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import examsec70.examsec70.responses.AlbumResponse;

public class ControllerAdvice {

    @ExceptionHandler(value=RuntimeException.class)
    public ResponseEntity<Object> RuntimeExceptionHandler(RuntimeException ex){
        AlbumResponse albumResponse = new AlbumResponse(); 
        albumResponse.setStatus(400);
        albumResponse.setMessage(ex.getMessage());

        return ResponseEntity.badRequest()
        .body(albumResponse);
    }
}
