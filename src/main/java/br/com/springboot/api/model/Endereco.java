package br.com.springboot.api.model;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {

    @NotNull
    @Size(min = 1, max = 50)
    private String logradouro;

    @NotNull
    @Size(min = 1, max = 50)
    private String complemento;

    @NotNull
    @Size(min = 1, max = 10)
    private String numero;

    @NotNull
    @Size(min = 1, max = 30)
    private String bairro;

    @NotNull
    @Size(min = 1, max = 8)
    private Long cep;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_cidade")
    private Cidade cidade;
}
