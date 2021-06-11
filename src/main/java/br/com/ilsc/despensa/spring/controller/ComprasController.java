package br.com.ilsc.despensa.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ilsc.despensa.spring.model.Compra;
import br.com.ilsc.despensa.spring.model.Produto;
import br.com.ilsc.despensa.spring.repository.ProdutoRepository;

@Controller
public class ComprasController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/compras")
	public ModelAndView inicio() {

		ModelAndView mv = new ModelAndView("cadastro/compras");
		mv.addObject("produtoObj", new Produto());
		mv.addObject("produtos", produtoRepository.findAll());
		return mv;
	}

	@PostMapping("/salvarCompra")
	public String cadastrarCompra(Compra compra) {

		return "redirect:/cadastro/compras";
	}

	@GetMapping("/selecionarProduto/{idProduto}")
	public ModelAndView adicionaProduto(@PathVariable("idProduto") Long idProduto) {

		Optional<Produto> produto = produtoRepository.findById(idProduto);

		ModelAndView mv = new ModelAndView("cadastro/compras");
		mv.addObject("produtoObj", produto.get());
		return mv;
	}
}
