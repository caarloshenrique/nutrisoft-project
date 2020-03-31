package br.com.nutrisoft.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Servico {

    private long id;
    private String nome;
    private String descricao;
    private LocalDateTime data;
    private double valor;
    private long usuarioId;

    @Override
    public String toString() {
        return id + " " + nome + " "
                + descricao + " " + formataData()
                + " " + valor;
    }

    public String formataData() {
        String st;
        DateTimeFormatter df = DateTimeFormatter.
                ofPattern("dd/MM/yyyy HH:mm");
        st = df.format(data);
        return st;
    }

}