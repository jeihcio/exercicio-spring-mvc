package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.Repository.PedidosRepository;
import br.com.alura.mvc.mudi.Repository.UserRepository;
import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidosRepository pedidosRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido requisicaoNovoPedido) {
		return "pedido/formulario";
	}
	
	@GetMapping("novo")
	public String novoRedirecionamento(RequisicaoNovoPedido requisicaoNovoPedido) {
		return "redirect:/pedido/formulario";
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicaoNovoPedido, BindingResult result) {			
		if(result.hasErrors()) {
			return "pedido/formulario";	
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByusername(username);
		
		Pedido pedido = requisicaoNovoPedido.toPedido();	
		pedido.setUser(user);
		
		pedidosRepository.save(pedido);		
		return "redirect:/home";
	}
}
