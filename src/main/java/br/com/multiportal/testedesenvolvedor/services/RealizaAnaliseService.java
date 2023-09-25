package br.com.multiportal.testedesenvolvedor.services;

import br.com.multiportal.testedesenvolvedor.dto.AnalisePessoaDTO;
import br.com.multiportal.testedesenvolvedor.dto.LerCsvDTO;
import br.com.multiportal.testedesenvolvedor.model.CadastroPessoa;
import br.com.multiportal.testedesenvolvedor.repositories.CadastroPessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class RealizaAnaliseService {

    private CadastroPessoaRepository pessoaRepository;
    private ProcessaCSVService processaCSVService;
    private ResourceLoader resourceLoader;

    public AnalisePessoaDTO realizaAnalise(InputStreamReader fileReader) throws IOException {
        List<LerCsvDTO> dtoList = processaCSV(fileReader);

        DecimalFormat df = new DecimalFormat("#.##");
        
        long quantidadeHomens = dtoList.stream().filter(it -> it.getSexo().equals("Male")).count();
        long quantidadeMulheres = dtoList.stream().filter(it -> it.getSexo().equals("Female")).count();

        double mediaIdadeHomens = dtoList.stream()
                .filter(it -> it.getSexo().equals("Male"))
                .mapToDouble(it -> it.getIdade()).sum() / quantidadeHomens;

        double mediaIdadeMulheres = dtoList.stream()
                .filter(it -> it.getSexo().equals("Female"))
                .mapToDouble(it -> it.getIdade()).sum() / quantidadeMulheres;

        List<LerCsvDTO> listaOrdenadaPorNome = dtoList.stream().sorted(Comparator.comparing(LerCsvDTO::getNome)).toList();

        persisteEntidade(listaOrdenadaPorNome);
        File novoCSV = geraNovoCSV("novo-aquivo.csv", listaOrdenadaPorNome);

        AnalisePessoaDTO analisePessoaDTO = AnalisePessoaDTO.builder()
                .totalHomens(quantidadeHomens)
                .totalMulheres(quantidadeMulheres)
                .mediaIdadeHomens(String.format("%.2f", mediaIdadeHomens))
                .mediaIdadeMulheres(String.format("%.2f", mediaIdadeMulheres))
                .novoCSV(novoCSV.getAbsolutePath())
                .build();

        return analisePessoaDTO;

    }

    private File geraNovoCSV(String nomeCSV, List<LerCsvDTO> dados) throws IOException {

        try(FileWriter writer = new FileWriter(nomeCSV);
            BufferedWriter bufferedWriter = new BufferedWriter(writer)){
            bufferedWriter.write("Nome,UltimoNome,Email,Sexo,IpAcesso,idade,Nascimento");
            bufferedWriter.newLine();

            for(LerCsvDTO dto: dados){
                bufferedWriter.write(
                        dto.getNome() + ","
                            + dto.getUltimoNome() + ","
                            + dto.getEmail() + ","
                            + dto.getSexo() + ","
                            + dto.getIpAcesso() + ","
                            + dto.getIdade() + ","
                            + dto.getNascimento());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Problema para gerar um novo csv: ", e);
        }
        return  new File(nomeCSV);
    }

    private void persisteEntidade(List<LerCsvDTO> listaOrdenadaPorNome) {
        List<CadastroPessoa> cadastroPessoaList = new ArrayList<>();
        listaOrdenadaPorNome.stream().forEach(it -> {
            CadastroPessoa pessoa = CadastroPessoa.builder()
                    .nome(it.getNome())
                    .ultimoNome(it.getUltimoNome())
                    .sexo(it.getSexo())
                    .ipAcesso(it.getIpAcesso())
                    .email(it.getEmail())
                    .idade(it.getIdade())
                    .nascimento(it.getNascimento())
                    .build();
            cadastroPessoaList.add(pessoa);
        });
        
        pessoaRepository.saveAll(cadastroPessoaList);
    }
    
    public List<LerCsvDTO> processaCSV(InputStreamReader fileReader){
        return processaCSVService.processaECorrigeCSV(fileReader);
    }
}
