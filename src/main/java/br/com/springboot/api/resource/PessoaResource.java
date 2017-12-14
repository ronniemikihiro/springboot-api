package br.com.springboot.api.resource;

import br.com.springboot.api.model.Categoria;
import br.com.springboot.api.model.Pessoa;
import br.com.springboot.api.repository.CategoriaRepository;
import br.com.springboot.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    public void insert(@Valid @RequestBody Pessoa pessoa) {
        final Pessoa p = pessoaRepository.save(pessoa);
    }

    @GetMapping("/{codigo}")
    public Pessoa findById(@PathVariable  final Long codigo) {
        return pessoaRepository.findOne(codigo);
    }
}
