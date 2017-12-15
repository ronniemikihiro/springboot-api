package br.com.springboot.api.resource;

import br.com.springboot.api.event.ResourceCreatedEvent;
import br.com.springboot.api.model.Categoria;
import br.com.springboot.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> insert(@Valid @RequestBody Categoria categoria, final HttpServletResponse response) {
        final Categoria c = categoriaRepository.save(categoria);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, c.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable  final Long id) {
        final Categoria c = categoriaRepository.findOne(id);
        return c == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }
}
