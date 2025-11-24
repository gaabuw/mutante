package com.example.mutante.controller;

import com.example.mutante.dto.DnaRequest;
import com.example.mutante.service.DnaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DnaController.class)
public class DnaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DnaService dnaService;
    @Test
    public void testDetectMutant_Returns200() throws Exception {
        // Simulamos que el servicio dice "ES MUTANTE"
        when(dnaService.analyze(any())).thenReturn(true);

        String json = "{\"dna\":[\"AAAA\",\"CCCC\",\"TCAG\",\"GGTC\"]}";

        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk()); // Esperamos 200 OK
    }

    @Test
    public void testDetectMutant_Returns403() throws Exception {
        // Simulamos que el servicio dice "ES HUMANO"
        when(dnaService.analyze(any())).thenReturn(false);

        String json = "{\"dna\":[\"AAAT\",\"AACC\",\"AAAC\",\"CGGG\"]}";

        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isForbidden());
    }
}
