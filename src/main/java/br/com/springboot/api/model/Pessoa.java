package br.com.springboot.api.model;

import br.com.springboot.api.enums.EnumAtivo;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    @Size(min = 1, max = 50)
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Size(min = 1, max = 1)
    private EnumAtivo ativo;

    @Embedded
    private Endereco endereco;

}
