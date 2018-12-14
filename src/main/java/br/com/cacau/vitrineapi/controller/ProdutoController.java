package br.com.cacau.vitrineapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cacau.vitrineapi.persistence.model.ProdutoVitrine;
import br.com.cacau.vitrineapi.persistence.repository.ProdutoRepository;
import br.com.cacau.vitrineapi.persistence.repository.VitrineRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/produto/")
/**
 * 
 * @author joaopaulo
 * API Para a Sr. Cacau gerenciar sua vitrine virtual
 */
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	VitrineRepository vitrineRepository;
	
	@PostMapping("add")
	public void addProdutoEstoque( @RequestBody ProdutoVitrine estoque) {
		produtoRepository.create(estoque);
		log.info("Estoque Produto add: {}", estoque);
	}

	@PostMapping("update")
	public void updateProdutoEstoque(@RequestBody ProdutoVitrine estoque) {
		produtoRepository.update(estoque);
		log.info("Estoque Produto add: {}", estoque);
	}

	@PostMapping("deleteid")
	public void deleteByIdProdutoEstoque(@RequestBody long id) {
		produtoRepository.deletebyId(id);
		log.info("Estoque Produto delete: {}", id);
	}

	@PostMapping("delete")
	public void deleteProdutoEstoque(@RequestBody ProdutoVitrine ProdutoEstoque) {
		produtoRepository.delete(ProdutoEstoque);
		log.info("Estoque Produto delete: {}", ProdutoEstoque);
	}

	@GetMapping("find{id}")
	public ProdutoVitrine findByIdProdutoEstoque(@PathVariable("id") Long id) {
		log.info("Estoque Produto find: id={}", id);
		return produtoRepository.findOne(id);
	}

	@GetMapping("findAll")
	public List<ProdutoVitrine> findAllProdutoEstoque() {
		log.info("Estoque Produto findAll");
		return produtoRepository.findAll();
	}

	@GetMapping("/find/tipo/{tipo_produto}")
	public List<ProdutoVitrine> findTipoProduto(@PathVariable("tipo_produto") String tipo_produto) {
		log.info("Estoque Produto find tipo produto:={}", tipo_produto);
		return produtoRepository.findTipoProduto(tipo_produto);
	}
	
}
