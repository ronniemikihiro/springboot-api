package br.com.springboot.api.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "estado")
public class Estado implements br.com.springboot.api.model.Entity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 50)
    private String nome;

    @Size(min = 2, max = 2)
    private String sigla;
}
