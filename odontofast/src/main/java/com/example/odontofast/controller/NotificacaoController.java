package com.example.odontofast.controller;

import com.example.odontofast.service.MensageriaProducerService;
import com.example.odontofast.service.NotificacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/notificacoes")
public class NotificacaoController {

  private final NotificacaoService notificacaoService;
  private final MensageriaProducerService mensageriaProducerService;

  public NotificacaoController(NotificacaoService notificacaoService,
      MensageriaProducerService mensageriaProducerService) {
    this.notificacaoService = notificacaoService;
    this.mensageriaProducerService = mensageriaProducerService;
  }

  @GetMapping
  public String mostrarNotificacoes(Model model) {
    model.addAttribute("notificacoes", notificacaoService.getNotificacoes());
    return "notificacoes";
  }

  @PostMapping("/enviar-teste")
  public String enviarNotificacaoTeste(@RequestParam String mensagem) {
    Map<String, Object> notificacao = new HashMap<>();
    notificacao.put("tipo", "TESTE");
    notificacao.put("mensagem", mensagem);
    notificacao.put("data", new Date().toString());

    mensageriaProducerService.enviarNotificacaoEmail(notificacao);
    return "redirect:/notificacoes";
  }
}