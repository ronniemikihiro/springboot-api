package br.com.springboot.api.repository;

import br.com.springboot.api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
