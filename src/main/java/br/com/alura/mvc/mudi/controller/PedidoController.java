package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.Repository.PedidosRepository;
import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidosRepository pedidosRepository;

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
		
		Pedido pedido = requisicaoNovoPedido.toPedido();		
		pedidosRepository.save(pedido);
		
		return "redirect:/home";
	}
}
