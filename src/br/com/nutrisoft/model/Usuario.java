package br.com.nutrisoft.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

    private long id;
    private String email;
    private String senha;
}
