package br.com.springboot.api.model;

import java.io.Serializable;

public interface Entity<ID extends Serializable> extends Serializable {
    ID getId();

    void setId(final ID id);
}
