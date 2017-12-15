package br.com.springboot.api.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Embeddable
public class Endereco {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String logradouro;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String complemento;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 10)
    private String numero;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 30)
    private String bairro;

    @NotNull
    private Long cep;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;
}
