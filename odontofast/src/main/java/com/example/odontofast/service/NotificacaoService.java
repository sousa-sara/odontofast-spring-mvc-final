package com.example.odontofast.service;

import com.example.odontofast.model.Notificacao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class NotificacaoService {

  // Armazenamento em memória para as notificações
  private final List<Notificacao> notificacoes = new ArrayList<>();

  public NotificacaoService(MensageriaConsumerService consumerService) {
    // Registre este serviço como ouvinte das mensagens RabbitMQ
    consumerService.setNotificacaoService(this);
  }

  public void adicionarNotificacao(Map<String, Object> mensagem) {
    String tipo = (String) mensagem.getOrDefault("tipo", "INFO");

    StringBuilder mensagemFormatada = new StringBuilder();

    if (tipo.equals("NOVO_CADASTRO")) {
      String dentista = (String) mensagem.getOrDefault("dentista", "");
      String email = (String) mensagem.getOrDefault("email", "");
      mensagemFormatada.append("Novo dentista cadastrado: ").append(dentista);
      if (!email.isEmpty()) {
        mensagemFormatada.append(" (").append(email).append(")");
      }
    } else if (tipo.equals("PERFIL_ATUALIZADO")) {
      String dentista = (String) mensagem.getOrDefault("dentista", "");
      mensagemFormatada.append("Perfil atualizado: ").append(dentista);
    } else if (tipo.equals("TESTE")) {
      mensagemFormatada.append(mensagem.getOrDefault("mensagem", "Mensagem de teste"));
    } else {
      mensagemFormatada.append(mensagem.toString());
    }

    Notificacao notificacao = new Notificacao(tipo, mensagemFormatada.toString());
    notificacoes.add(0, notificacao); // Adiciona no início para as mais recentes aparecerem primeiro

    // Limita o tamanho da lista para não consumir muita memória
    if (notificacoes.size() > 100) {
      notificacoes.remove(notificacoes.size() - 1);
    }
  }

  public List<Notificacao> getNotificacoes() {
    return Collections.unmodifiableList(notificacoes);
  }
}