package br.com.ilsc.despensa.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ilsc.despensa.spring.model.Usuario;
import br.com.ilsc.despensa.spring.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/cadastroUsuario")
	public ModelAndView cadastraUsuario() {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroUsuario");
		modelAndView.addObject("usuarios", usuarioRepository.findAll());
		modelAndView.addObject("usuarioObj", new Usuario());

		return modelAndView;
	}

	@PostMapping("**/salvarUsuario")
	public ModelAndView salvar(Usuario usuario) {

		usuarioRepository.save(usuario);

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroUsuario");
		modelAndView.addObject("usuarioObj", new Usuario());
		modelAndView.addObject("usuarios", usuarioRepository.findAll());

		return modelAndView;
	}

	@GetMapping("/listaUsuarios")
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroUsuario");
		modelAndView.addObject("usuarios", usuarioRepository.findAll());
		modelAndView.addObject("usuarioObj", new Usuario());
		return modelAndView;
	}

	@GetMapping("/editarUsuario/{idUsuario}")
	public ModelAndView editar(@PathVariable("idUsuario") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroUsuario");
		modelAndView.addObject("usuarioObj", usuario.get());

		return modelAndView;
	}

	@GetMapping("/removeUsuario/{idUsuario}")
	public ModelAndView excluir(@PathVariable("idUsuario") Long id) {

		usuarioRepository.deleteById(id);

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroUsuario");
		modelAndView.addObject("usuarioObj", new Usuario());
		modelAndView.addObject("usuarios", usuarioRepository.findAll());

		return modelAndView;
	}
}
