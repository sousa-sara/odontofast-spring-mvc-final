package com.example.odontofast.model;

import java.util.Date;

public class Notificacao {
  private String tipo;
  private String mensagem;
  private Date data;

  public Notificacao(String tipo, String mensagem) {
    this.tipo = tipo;
    this.mensagem = mensagem;
    this.data = new Date();
  }

  // Getters e Setters
  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }
}