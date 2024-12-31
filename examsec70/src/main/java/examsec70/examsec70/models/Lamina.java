package examsec70.examsec70.models;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Lamina {
    @Id 
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long lamId;
    private long lamNumero;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "albId")
    private Album lamAlbum;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuLamLamina", cascade = { CascadeType.ALL})
    private List<UsuarioLamina> lamUsuLamLamina;
}
