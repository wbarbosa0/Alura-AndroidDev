package br.nom.wbarbosa.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.nom.wbarbosa.R;
import br.nom.wbarbosa.agenda.dao.AlunoDAO;
import br.nom.wbarbosa.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private final static AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lista de alunos");
        //Toast.makeText(this, "Oie!", Toast.LENGTH_LONG).show();

        FloatingActionButton fabNovoAluno = findViewById(R.id.activity_lista_alunos_novo_aluno);

        fabNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaAlunosActivity.this,
                        FormularioAlunoActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        AlunoDAO dao = new AlunoDAO();

        ListView lvAlunos = findViewById(R.id.activity_lista_alunos_lista);

        lvAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));
    }
}
