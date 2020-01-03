package br.nom.wbarbosa.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.nom.wbarbosa.R;
import br.nom.wbarbosa.agenda.dao.AlunoDAO;
import br.nom.wbarbosa.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity implements ConstantActivities {

    private static final String EDITAR_ALUNO = "Editar Aluno";
    private static final String NOVO_ALUNO = "Novo Aluno";
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    //Button botaoSalvar;
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_lista_aluno_menu_salvar) {
            AtualizarAluno(aluno, campoNome, campoTelefone, campoEmail);

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
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        //botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        campoNome = findViewById(R.id.activity_formulario_aluno_input_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_input_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_input_email);


        final Intent intent = getIntent();

        if (intent.hasExtra(CHAVE_EXTRA_ALUNO)) {
            setTitle(EDITAR_ALUNO);
            aluno = (Aluno) intent.getSerializableExtra(CHAVE_EXTRA_ALUNO);
            assert aluno != null;
            AtualizarCamposForm(aluno, campoNome, campoTelefone, campoEmail);
        } else {
            setTitle(NOVO_ALUNO);
            aluno = new Aluno();
        }

    }

    private void AtualizarCamposForm(Aluno aluno, EditText campoNome, EditText campoTelefone, EditText campoEmail) {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void AtualizarAluno(Aluno aluno, EditText campoNome, EditText campoTelefone, EditText campoEmail) {
        aluno.setNome(campoNome.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
    }
}
