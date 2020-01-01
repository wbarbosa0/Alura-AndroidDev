package br.nom.wbarbosa.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.nom.wbarbosa.R;
import br.nom.wbarbosa.agenda.dao.AlunoDAO;
import br.nom.wbarbosa.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle("Novo Aluno");

        final AlunoDAO dao = new AlunoDAO();
        final Aluno aluno;

        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        final EditText campoNome = findViewById(R.id.activity_formulario_aluno_input_nome);
        final EditText campoTelefone = findViewById(R.id.activity_formulario_aluno_input_telefone);
        final EditText campoEmail = findViewById(R.id.activity_formulario_aluno_input_email);


        final Intent intent = getIntent();

        if (intent.hasExtra("aluno")) {
            aluno = (Aluno) intent.getSerializableExtra("aluno");

            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
            aluno = new Aluno();
        }

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aluno.setNome(campoNome.getText().toString());
                aluno.setTelefone(campoTelefone.getText().toString());
                aluno.setEmail(campoEmail.getText().toString());

                if (!aluno.toString().isEmpty()) {
                    if (!aluno.temIdValido()) {
                        dao.salvar(aluno);
                    } else {
                        dao.atualizar(aluno);
                    }
                    finish();
                } else {
                    Toast.makeText(FormularioAlunoActivity.this, "Preencha pelo nome do aluno para poder salva-lo!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
