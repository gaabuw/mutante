package com.example.mutante.controller;

import com.example.mutante.dto.StatsResponse;
import com.example.mutante.service.DnaService;
import com.example.mutante.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping
    public StatsResponse getStats(){
        return statsService.getStats();
    }
}
