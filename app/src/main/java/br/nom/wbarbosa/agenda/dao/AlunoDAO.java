package br.nom.wbarbosa.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.nom.wbarbosa.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno novoAluno) {
        alunos.add(novoAluno);
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
