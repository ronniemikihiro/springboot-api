package br.com.springboot.api.model;

import br.com.springboot.api.enums.EnumAtivo;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa implements br.com.springboot.api.model.Entity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumAtivo ativo;

    @Valid
    @Embedded
    private Endereco endereco;

}
