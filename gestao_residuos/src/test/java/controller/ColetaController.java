package controller;

import model.Coleta;
import steps.service.ColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ColetaController {

    @Autowired
    private ColetaService coletaService;

    @PostMapping("/coletas")
    @ResponseStatus(HttpStatus.CREATED)
    public Coleta gravar(@RequestBody @Valid Coleta coleta) {
        return coletaService.gravar(coleta);
    }

    @GetMapping("/coletas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Coleta buscarPorId(@PathVariable Long id) {
        return coletaService.buscarPorId(id);
    }

    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Page<Coleta> listarTodosOsContatos(Pageable paginacao) {
        return coletaService.listarTodosAsColetas(paginacao);
    }

    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        coletaService.excluir(id);
    }

    @PutMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Coleta atualizar(@RequestBody Coleta coleta) {
        return coletaService.atualizar(coleta);
    }

}
