package br.com.multiportal.testedesenvolvedor.services;

import br.com.multiportal.testedesenvolvedor.dto.LerCsvDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class ProcessaCSVService {

    public List<LerCsvDTO> processaECorrigeCSV(InputStreamReader fileReader){

        try(BufferedReader bufferedReader = new BufferedReader(fileReader)){

            List<LerCsvDTO> lerCsvDTOS = new ArrayList<>();
            Iterator<String> iterator = bufferedReader.lines().iterator();
            iterator.next();
            List<LerCsvDTO> csvDTOS = new ArrayList<>();

            while (iterator.hasNext()){
                String[] it = iterator.next().split(",");
                LerCsvDTO lerCsvDTO = LerCsvDTO.builder()
                        .nome(it[0])
                        .ultimoNome(it[1])
                        .email(it[2])
                        .sexo(it[3])
                        .ipAcesso(it[4])
                        .idade(Integer.valueOf(it[5]))
                        .build();
                lerCsvDTO.setNascimento(ajusteData(it[6], lerCsvDTO.getIdade()));
                csvDTOS.add(lerCsvDTO);
            }

            return csvDTOS;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer corrigeIdade(LocalDate nascimento) {
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(nascimento, dataAtual);

        return periodo.getYears();
    }

    private LocalDate ajusteData(String data, Integer idade){
         String[] partes = data.split("/");
         int dia = Integer.parseInt(partes[0]);
         int mes = Integer.parseInt(partes[1]);
         int ano = LocalDate.now().getYear() - idade;

         return LocalDate.of(ano, mes, dia);
    }

}
