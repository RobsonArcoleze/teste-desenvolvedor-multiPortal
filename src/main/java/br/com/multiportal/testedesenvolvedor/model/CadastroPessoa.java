package br.com.multiportal.testedesenvolvedor.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_cadastro_pessoa", indexes = {
        @Index(name = "idx_cadastropessoa_id_nome", columnList = "id, nome")
})
public class CadastroPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ultimo_nome")
    private String ultimoNome;

    @Column(name = "email")
    private String email;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "ip_acesso")
    private String ipAcesso;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "nascimento")
    private LocalDate nascimento;

}
