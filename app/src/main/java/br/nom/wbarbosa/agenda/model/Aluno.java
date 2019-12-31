package br.nom.wbarbosa.agenda.model;

import androidx.annotation.NonNull;

public class Aluno {

    private final String telefone;
    private final String email;
    private final String nome;


    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public Aluno(String nome, String telefone, String email) {

        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
}
