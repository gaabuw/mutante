package com.example.mutante.controller;

import com.example.mutante.dto.DnaRequest;
import com.example.mutante.service.DnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class DnaController {

    @Autowired
    private DnaService dnaService;

    @PostMapping("/")
    public ResponseEntity<Void> detectMutant(@RequestBody DnaRequest dnaRequest) {
        boolean isMutant = dnaService.analyze(dnaRequest.getDna());

        if (isMutant) {
            //200
            return ResponseEntity.ok().build();
        } else {
            //403
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
