package examsec70.examsec70.models;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Album {
    @Id 
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long albId;
    private String albNombre;
    private String albDescripcion;
    private long cantLaminas; 

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lamAlbum", cascade = { CascadeType.ALL})
    private List<Lamina> albLamina;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuAlbAlbum", cascade = { CascadeType.ALL})
    private List<UsuarioAlbum> albUsuarioAlbum;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuLamAlbum", cascade = { CascadeType.ALL})
    private List<UsuarioLamina> albUsuarioLamina;

    @CreatedDate
    private LocalDate fecha_crea;
    @LastModifiedDate
    private LocalDate fecha_modi;

}
