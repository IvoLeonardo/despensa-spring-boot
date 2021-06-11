package br.com.ilsc.despensa.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.ilsc.despensa.spring.model.Fornecedor;
import br.com.ilsc.despensa.spring.repository.FornecedorRepository;

@Controller
public class FornecedorController {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@GetMapping("/cadastroFornecedor")
	public ModelAndView Fornecedor() {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroFornecedor");

		modelAndView.addObject("fornecedorObj", new Fornecedor());
		modelAndView.addObject("fornecedores", fornecedorRepository.findAll());
		return modelAndView;
	}

	@PostMapping("**/salvarFornecedor")
	public ModelAndView salvar(Fornecedor fornecedor) {

		fornecedorRepository.save(fornecedor);

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroFornecedor");
		Iterable<Fornecedor> fornecedores = fornecedorRepository.findAll();
		modelAndView.addObject("fornecedorObj", new Fornecedor());
		modelAndView.addObject("fornecedores", fornecedores);

		return modelAndView;
	}

	@GetMapping("/listaFornecedores")
	public ModelAndView listaFornecedor() {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroFornecedor");

		Iterable<Fornecedor> fornecedores = fornecedorRepository.findAll();
		modelAndView.addObject("fornecedores", fornecedores);
		modelAndView.addObject("fornecedorObj", new Fornecedor());

		return modelAndView;
	}

	@GetMapping("/editarFornecedor/{fornecedorId}")
	public ModelAndView editar(@PathVariable("fornecedorId") Long fornecedorId) {

		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(fornecedorId);

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroFornecedor");
		modelAndView.addObject("fornecedorObj", fornecedor.get());

		return modelAndView;
	}

	@GetMapping("/removerFornecedor/{fornecedorId}")
	public ModelAndView excluir(@PathVariable("fornecedorId") Long fornecedorId) {

		fornecedorRepository.deleteById(fornecedorId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("fornecedores", fornecedorRepository.findAll());
		modelAndView.addObject("fornecedorObj", new Fornecedor());

		return modelAndView;
	}

	@PostMapping("**/pesquisarFornecedor")
	public ModelAndView pesquisarNome(@RequestParam("nomePesquisa") String nome) {

		ModelAndView mv = new ModelAndView("cadastro/cadastroFornecedor");
		mv.addObject("fornecedorObj", new Fornecedor());
		mv.addObject("fornecedores", fornecedorRepository.findBynomeEmpresaContaining(nome));
		return mv;
	}
}
