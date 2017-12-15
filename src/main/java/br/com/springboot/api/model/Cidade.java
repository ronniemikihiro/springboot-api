package br.com.springboot.api.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "cidade")
public class Cidade implements br.com.springboot.api.model.Entity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 100)
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado")
    private Estado estado;
}
