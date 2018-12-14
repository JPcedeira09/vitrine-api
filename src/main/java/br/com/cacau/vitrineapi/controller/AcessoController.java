package br.com.cacau.vitrineapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cacau.vitrineapi.persistence.model.Acesso;
import br.com.cacau.vitrineapi.persistence.repository.AcessoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/acesso")
public class AcessoController {

	@Autowired
	AcessoRepository acessoRepository;

	@GetMapping("/login/{email}/{senha}")
	public Acesso login(@PathVariable("email") String email, @PathVariable("senha") String senha) {
		log.info("Acesso LOGIN - email: {} com senha:{}", email, senha);
		return acessoRepository.login(email, senha);
	}
	
    @GetMapping("/")
    public String index() {
		log.info("Acessando pagina ajax");
        return "/vitrine-api/src/main/resources/templates/ajax.html";
    }
}
