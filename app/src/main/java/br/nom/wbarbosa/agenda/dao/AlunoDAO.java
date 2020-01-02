package br.nom.wbarbosa.agenda.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.nom.wbarbosa.agenda.model.Aluno;

public class AlunoDAO {

    private static int ProximoIdAluno = 1;

    private final static List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno novoAluno) {
        novoAluno.setId(ProximoIdAluno);
        alunos.add(novoAluno);
        ProximoIdAluno++;
        Log.i("DAO", "salvar: Aluno atribuído com id " + novoAluno.getId());
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void atualizar(Aluno aluno) {
        for (Aluno a : alunos
        ) {
            if (a.getId() == aluno.getId()) {
                int posicao = alunos.indexOf(a);
                Log.i("UPDATE", "atualizar: " + posicao);
                alunos.set(posicao, aluno);
                break;
            }
        }
    }

    public void remover(Aluno aluno) {
        for (Aluno a : alunos
        ) {
            if (a.getId() == aluno.getId()) {
                int posicao = alunos.indexOf(a);
                Log.i("DELETE", "excluir: " + posicao);
                alunos.remove(a);
                break;
            }
        }

    }
}
