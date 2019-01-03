package br.com.cacau.vitrineapi;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.cacau.vitrineapi.persistence.model.ProdutoVitrine;
import br.com.cacau.vitrineapi.persistence.model.Vitrine;
import br.com.cacau.vitrineapi.persistence.repository.ProdutoRepository;
import br.com.cacau.vitrineapi.persistence.repository.VitrineRepository;

@SpringBootApplication
@EnableScheduling
public class VitrineApiApplication {

	@Autowired
	private VitrineRepository userRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(VitrineApiApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	userRepository.create((new Vitrine(1,"Loja Rua Protógenes")));
			ClassPathResource jsaCoverImgFile = new ClassPathResource("META-INF/imagens/bolooreo.jpeg");
			byte[] arrayData = new byte[(int) jsaCoverImgFile.contentLength()];
			jsaCoverImgFile.getInputStream().read(arrayData);
			ProdutoVitrine produtoVitrine = new ProdutoVitrine("Bolo Oreo", "Bolo", "Bolo recheado de oreo e creme branco", 79.0, 1.3, "Kg", arrayData);
	    	produtoRepository.create(produtoVitrine);

			ClassPathResource jsaVisionImgFile = new ClassPathResource("META-INF/imagens/brigadeiro.jpeg");
			byte[] arrayData2 = new byte[(int) jsaVisionImgFile.contentLength()];
			jsaVisionImgFile.getInputStream().read(arrayData2);
			ProdutoVitrine produtoVitrine2 = new ProdutoVitrine("Brigadeiro Belga", "Docinho", "Docinho de Brigadeiro Belga", 5.0, 12.0, "UN", arrayData2);
	    	produtoRepository.create(produtoVitrine2);

			ClassPathResource jsaAboutFile = new ClassPathResource("META-INF/imagens/redvelvet.jpeg");
			byte[] arrayData3 = new byte[(int) jsaAboutFile.contentLength()];
			jsaAboutFile.getInputStream().read(arrayData2);
			ProdutoVitrine produtoVitrine3 = new ProdutoVitrine("Bolo Redvelvet", "Bolo", "Bolo Vermelho mesmo", 85.0, 1.2, "Kg", arrayData3);
	    	produtoRepository.create(produtoVitrine3);

	      };
	   }
	
}