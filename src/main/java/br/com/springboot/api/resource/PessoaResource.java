package br.com.springboot.api.resource;

import br.com.springboot.api.event.ResourceCreatedEvent;
import br.com.springboot.api.model.Pessoa;
import br.com.springboot.api.repository.PessoaRepository;
import br.com.springboot.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoa> insert(@Valid @RequestBody Pessoa pessoa, final HttpServletResponse response) {
        final Pessoa p = pessoaRepository.save(pessoa);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, p.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @PutMapping
    public ResponseEntity<Pessoa> update(@Valid @RequestBody final Pessoa pessoa) {
        final Pessoa p = pessoaService.update(pessoa);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {
        pessoaRepository.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable  final Long id) {
        final Pessoa p = pessoaRepository.findOne(id);
        return ObjectUtils.isEmpty(p) ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
    }

}
