package br.com.alura.mvc.mudi.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.Repository.PedidosRepository;
import br.com.alura.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

	@Autowired
	private PedidosRepository pedidosRepository;

	@PostMapping
	public Oferta criarOferta(RequisicaoNovaOferta requisicao) {

		Optional<Pedido> pedidoBuscado = pedidosRepository.findById(requisicao.getPedidoId());
		if (!pedidoBuscado.isPresent()) {
			return null;
		}

		Pedido pedido = pedidoBuscado.get();
		Oferta nova = requisicao.toOferta();

		nova.setPedido(pedido);
		pedido.getOfertas().add(nova);
		
		pedidosRepository.save(pedido);
		return nova;
	}
}
