package model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_coleta")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Coleta {

    @JsonProperty
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "COLETA_SEQ"
    )
    @SequenceGenerator(
            name = "COLETA_SEQ",
            sequenceName = "COLETA_SEQ",
            allocationSize = 1
    )
    private Long id;

    @JsonProperty
    private String endereco;

    @JsonProperty
    @Column(name = "qtd_residuos")
    private Integer quantidadeResiduos;

    public Coleta(Long id, String endereco, Integer quantidadeResiduos) {
        this.id = id;
        this.endereco = endereco;
        this.quantidadeResiduos = quantidadeResiduos;
    }
    public Coleta() {
    }
    public Coleta(Coleta coleta) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getQuantidadeResiduos() {
        return quantidadeResiduos;
    }

    public void setQuantidadeResiduos(Integer quantidadeResiduos) {
        this.quantidadeResiduos = quantidadeResiduos;
    }
}
