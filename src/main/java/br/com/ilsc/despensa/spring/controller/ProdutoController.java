package br.com.ilsc.despensa.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.ilsc.despensa.spring.model.Produto;
import br.com.ilsc.despensa.spring.repository.CategoriaRepository;
import br.com.ilsc.despensa.spring.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("cadastraProduto")
	public ModelAndView inicio() {
		ModelAndView mv = new ModelAndView("cadastro/cadastroProduto");
		mv.addObject("produtoObj", new Produto());
		mv.addObject("produtos", produtoRepository.findAll());
		mv.addObject("categorias", categoriaRepository.findAll());
		return mv;
	}

	@PostMapping("**/salvarProduto")
	public ModelAndView salvar(Produto produto) {

		produtoRepository.save(produto);

		ModelAndView mv = new ModelAndView("cadastro/cadastroProduto");
		mv.addObject("produtos", produtoRepository.findAll());
		mv.addObject("produtoObj", new Produto());
		mv.addObject("categorias", categoriaRepository.findAll());

		return mv;
	}

	@GetMapping("**/editarProduto/{prodId}")
	public ModelAndView editar(@PathVariable("prodId") Long idProduto) {

		Optional<Produto> produto = produtoRepository.findById(idProduto);

		ModelAndView mv = new ModelAndView("cadastro/cadastroProduto");
		mv.addObject("produtoObj", produto.get());
		mv.addObject("categorias", categoriaRepository.findAll());

		return mv;
	}

	@GetMapping("/excluirProduto/{prodId}")
	public ModelAndView excluir(@PathVariable("prodId") Long idProduto) {

		produtoRepository.deleteById(idProduto);
		ModelAndView mv = new ModelAndView("cadastro/cadastroProduto");
		mv.addObject("produtoObj", new Produto());
		mv.addObject("produtos", produtoRepository.findAll());

		return mv;
	}

	@GetMapping("/listaProdutos")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("cadastro/cadastroProduto");
		mv.addObject("produtos", produtoRepository.findAll());
		mv.addObject("produtoObj", new Produto());

		return mv;
	}

	@PostMapping("**/pesquisarNome")
	public ModelAndView pesquisar(@RequestParam("nomePesquisa") String nome) {

		ModelAndView mv = new ModelAndView("cadastro/cadastroProduto");
		mv.addObject("produtoObj", new Produto());
		mv.addObject("produtos", produtoRepository.findByNomeContaining(nome));
		return mv;
	}
}
