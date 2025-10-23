package br.com.osa.stander.controller;

import br.com.osa.stander.dto.AgenciaRequest;
import br.com.osa.stander.dto.DistanceResponse;
import br.com.osa.stander.service.AgenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/desafio", produces = MediaType.APPLICATION_JSON_VALUE)
public class DesafioController {

    private final AgenciaService service;

    public DesafioController(AgenciaService service) {
        this.service = service;
    }

    /** POST /desafio/cadastrar */
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrar(@Valid @RequestBody AgenciaRequest body) {
        Long id = service.cadastrar(body);
        return "{\"id\":" + id + "}";
    }

    /** GET /desafio/distancia?posX=10&posY=5 */
    @GetMapping("/distancia")
    public List<DistanceResponse> distancia(@RequestParam int posX, @RequestParam int posY) {
        return service.calcularDistanciasOrdenadas(posX, posY);
    }
}
