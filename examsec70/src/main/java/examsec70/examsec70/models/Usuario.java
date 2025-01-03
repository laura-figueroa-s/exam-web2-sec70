package examsec70.examsec70.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Usuario {
    @Id 
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long usuId;
    private String usuNombreCompleto;
    private String usuApodo;
    private String usuCorreo;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuAlbUsuario", cascade = { CascadeType.ALL})
    @JsonIgnore
    private List<UsuarioAlbum> usuAlbum;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuLamUsuario", cascade = { CascadeType.ALL})
    @JsonIgnore
    private List<UsuarioLamina> usuLamina;

}
