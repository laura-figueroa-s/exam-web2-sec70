package examsec70.examsec70.models;

import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuAlbUsuario", cascade = { CascadeType.ALL})
    private List<UsuarioAlbum> usuAlbum;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuLamUsuario", cascade = { CascadeType.ALL})
    private List<UsuarioLamina> usuLamina;

}
