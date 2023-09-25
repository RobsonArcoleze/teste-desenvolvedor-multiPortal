package br.com.multiportal.testedesenvolvedor.dto;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class AnalisePessoaDTO {

    private Long totalHomens;
    private Long totalMulheres;
    private String mediaIdadeHomens;
    private String mediaIdadeMulheres;
    private String novoCSV;

}
