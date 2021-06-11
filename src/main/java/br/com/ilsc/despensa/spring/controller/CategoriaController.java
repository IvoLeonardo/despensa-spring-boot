package br.com.ilsc.despensa.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.ilsc.despensa.spring.model.Categoria;
import br.com.ilsc.despensa.spring.repository.CategoriaRepository;

@Controller
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("/categoriaInicio")
	public ModelAndView inicio() {

		ModelAndView mv = new ModelAndView("cadastro/cadastroCategoria");
		mv.addObject("categoriaObj", new Categoria());
		mv.addObject("categorias", categoriaRepository.findAll());
		return mv;
	}

//	@PostMapping("/salvarCategoria")
//	public String salvar(Categoria categoria) {
//
//		categoriaRepository.save(categoria);
//
//		return "cadastro/cadastroCategoria";
//	}

	@PostMapping("**/salvarCategoria")
	public ModelAndView salvar(Categoria categoria) {

		categoriaRepository.save(categoria);

		ModelAndView mv = new ModelAndView("cadastro/cadastroCategoria");
		mv.addObject("categorias", categoriaRepository.findAll());
		mv.addObject("categoriaObj", new Categoria());

		return mv;
	}

	@GetMapping("/editarCategoria/{categoriaId}")
	public ModelAndView editar(@PathVariable("categoriaId") Long idCategoria) {

		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);

		ModelAndView mv = new ModelAndView("cadastro/cadastroCategoria");
		mv.addObject("categoriaObj", categoria.get());
		return mv;
	}

	@GetMapping("/excuirCategoria/{categoriaId}")
	public ModelAndView excluir(@PathVariable("categoriaId") Long idCategoria) {

		categoriaRepository.deleteById(idCategoria);

		ModelAndView mv = new ModelAndView("cadastro/cadastroCategoria");
		mv.addObject("categorias", categoriaRepository.findAll());
		mv.addObject("categoriaObj", new Categoria());
		return mv;
	}

	@PostMapping("**/pesquisaNomeCategoria")
	public ModelAndView pesquisar(@RequestParam("nomePesquisa") String nome) {

		ModelAndView mv = new ModelAndView("cadastro/cadastroCategoria");
		mv.addObject("categoriaObj", new Categoria());
		mv.addObject("categorias", categoriaRepository.findByNomeContaining(nome));

		return mv;
	}

	@GetMapping("/listaCategorias")
	public ModelAndView listar() {

		ModelAndView mv = new ModelAndView("cadastro/cadastroCategoria");
		mv.addObject("categoriaObj", new Categoria());
		mv.addObject("categorias", categoriaRepository.findAll());

		return mv;
	}
}
