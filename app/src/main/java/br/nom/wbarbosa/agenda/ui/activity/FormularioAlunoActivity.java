package br.nom.wbarbosa.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        final EditText campoNome = findViewById(R.id.activity_formulario_aluno_input_nome);
        final EditText campoTelefone = findViewById(R.id.activity_formulario_aluno_input_telefone);
        final EditText campoEmail = findViewById(R.id.activity_formulario_aluno_input_email);


        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno novoAluno = new Aluno(campoNome.getText().toString(), campoTelefone.getText().toString(), campoEmail.getText().toString());

                if (!novoAluno.toString().isEmpty()) {
                    dao.salvar(novoAluno);
                    finish();
                } else {
                    Toast.makeText(FormularioAlunoActivity.this, "Preencha pelo nome do novo aluno!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
