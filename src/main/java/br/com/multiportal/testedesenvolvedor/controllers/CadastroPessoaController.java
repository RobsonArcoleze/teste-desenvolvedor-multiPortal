package br.com.multiportal.testedesenvolvedor.controllers;

import br.com.multiportal.testedesenvolvedor.dto.AnalisePessoaDTO;
import br.com.multiportal.testedesenvolvedor.services.RealizaAnaliseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

@Controller
@AllArgsConstructor
public class CadastroPessoaController {

    private RealizaAnaliseService realizaAnaliseService;

    @GetMapping(value = "/home")
    private String home(){
        return "/home";
    }


    @PostMapping(value = "/csv")
    private String inputCSV(@RequestParam(value = "file", required = true) MultipartFile multipartFile, Model model){
        if(!multipartFile.isEmpty()){
            try {
                AnalisePessoaDTO analisePessoaDTO = realizaAnaliseService.realizaAnalise(new InputStreamReader(multipartFile.getInputStream()));
                model.addAttribute("analisePessoaDTO", analisePessoaDTO);
                return "/home";
            } catch (Exception e) {
                throw new RuntimeException("Não foi possivel ler o arquivo: " + e.getMessage());
            }
        }else{
            throw new RuntimeException("Não foi enviado arquivo: " );
        }
    }

}
