package br.nom.wbarbosa.agenda.app;

import android.app.Application;

import br.nom.wbarbosa.agenda.dao.AlunoDAO;
import br.nom.wbarbosa.agenda.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AdicionarDadosExemploNoDAO();

    }

    private void AdicionarDadosExemploNoDAO() {
        AlunoDAO dao = new AlunoDAO();
        Aluno a;
        a = new Aluno("Huguinho", "555-0123", "hugh.duck@disney.com");
        dao.salvar(a);
        a = new Aluno("Zezinho", "555-9634", "dewey.duck@disney.com");
        dao.salvar(a);
        a = new Aluno("Luisinho", "555-7208", "louie.duck@disney.com");
        dao.salvar(a);
        a = new Aluno("Donald", "555-4313", "donald.duck@disney.com");
        dao.salvar(a);
    }
}
