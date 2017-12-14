package br.com.springboot.api.resource;

import br.com.springboot.api.model.Categoria;
import br.com.springboot.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public void insert(@Valid @RequestBody Categoria categoria) {
        final Categoria c = categoriaRepository.save(categoria);
    }

    @GetMapping("/{codigo}")
    public Categoria findById(@PathVariable  final Long codigo) {
        return categoriaRepository.findOne(codigo);
    }
}
