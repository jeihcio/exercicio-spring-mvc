package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.Repository.PedidosRepository;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PedidosRepository repository;
	
	@GetMapping
	public String home(Model model, Principal principal) {
		List<Pedido> pedidos = repository.findByStatus(StatusPedido.ENTREGUE);			
		model.addAttribute("pedidos", pedidos);		
		return "home";
	}
}