package com.example.odontofast.controller;

import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/admin/monitoring")
public class MonitoringController {

    private final HealthEndpoint healthEndpoint;
    private final InfoEndpoint infoEndpoint;
    private final MetricsEndpoint metricsEndpoint;

    public MonitoringController(HealthEndpoint healthEndpoint,
                                InfoEndpoint infoEndpoint,
                                MetricsEndpoint metricsEndpoint) {
        this.healthEndpoint = healthEndpoint;
        this.infoEndpoint = infoEndpoint;
        this.metricsEndpoint = metricsEndpoint;
    }

    @GetMapping
    public String monitoring(Model model) {
        // Obter as informações do actuator
        HealthComponent health = healthEndpoint.health();
        Map<String, Object> info = infoEndpoint.info();
        Object metricsNames = metricsEndpoint.listNames();

        // Adicionar o objeto health e os componentes separadamente para o template
        model.addAttribute("health", health);

        // Verificar se o health é uma instância de CompositeHealth e adicionar seus componentes
        if (health instanceof org.springframework.boot.actuate.health.CompositeHealth) {
            Map<String, HealthComponent> components =
                    ((org.springframework.boot.actuate.health.CompositeHealth) health).getComponents();
            model.addAttribute("healthComponents", components);
        }

        model.addAttribute("info", info);
        model.addAttribute("metricsNames", metricsNames);

        return "admin-monitoring";
    }

    @GetMapping("/metric")
    public String getMetric(@RequestParam String name, Model model) {
        Object metricData = metricsEndpoint.metric(name, null);
        model.addAttribute("metricData", metricData);
        model.addAttribute("metricName", name);
        return "fragments/metric-details :: metricDetails";
    }
}