package com.example.odontofast.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service
public class MensageriaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(MensageriaConsumerService.class);
    private NotificacaoService notificacaoService;

    public void setNotificacaoService(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @RabbitListener(queues = "agendamento.queue")
    public void receberAgendamento(Map<String, Object> mensagem) {
        logger.info("Recebida mensagem de agendamento: {}", mensagem);
        // Processar o agendamento recebido
        if (notificacaoService != null) {
            notificacaoService.adicionarNotificacao(mensagem);
        }
    }

    @RabbitListener(queues = "notificacao.queue")
    public void receberNotificacao(Map<String, Object> mensagem) {
        logger.info("Recebida mensagem de notificação: {}", mensagem);
        // Processar a notificação a ser enviada
        if (notificacaoService != null) {
            notificacaoService.adicionarNotificacao(mensagem);
        }
    }
}