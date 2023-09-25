package br.com.multiportal.testedesenvolvedor.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LerCsvDTO {

    private String nome;
    private String ultimoNome;
    private String email;
    private String sexo;
    private String ipAcesso;
    private Integer idade;
    private LocalDate nascimento;
}
