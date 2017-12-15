package br.com.springboot.api.service;

import br.com.springboot.api.model.Pessoa;
import br.com.springboot.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa update(final Pessoa pessoa) {
        final Pessoa pessoaDataBase = pessoaRepository.findOne(pessoa.getId());
        if(ObjectUtils.isEmpty(pessoaDataBase)) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(pessoa, pessoaDataBase);
        return pessoaRepository.save(pessoaDataBase);
    }
}
