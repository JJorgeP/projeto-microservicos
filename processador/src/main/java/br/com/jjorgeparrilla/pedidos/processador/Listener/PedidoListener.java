package br.com.jjorgeparrilla.pedidos.processador.Listener;

import br.com.jjorgeparrilla.pedidos.processador.entity.Pedido;
import br.com.jjorgeparrilla.pedidos.processador.enums.Status;
import br.com.jjorgeparrilla.pedidos.processador.service.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private final Logger logger = LoggerFactory.getLogger(PedidoListener.class);

    private final PedidoService pedidoService;

    public PedidoListener(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @RabbitListener(queues = "pedido.v1.pedido-criado-gerar-processamento")
    public void salvarPedido(Pedido pedido) {
        pedido.setStatus(Status.PROCESSADO);
        pedidoService.save(pedido);
    }
}
