package br.com.alura.mvc.mudi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.Repository.PedidosRepository;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {
	
	@Autowired
	private PedidosRepository pedidosRepository;

	@GetMapping("aguardando")
	public List<Pedido> getPedidosAguardandoOfertas(){	
		Sort sort = Sort.by("id").descending();
		PageRequest paginacao = PageRequest.of(0, 10, sort);
		
		return pedidosRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);
	}
}
