package br.nom.wbarbosa.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.nom.wbarbosa.R;
import br.nom.wbarbosa.agenda.dao.AlunoDAO;
import br.nom.wbarbosa.agenda.ui.adapter.ListaAlunosAdapter;


public class ListaAlunosActivity<adapterLV> extends AppCompatActivity implements ConstantActivities {

    private final static AlunoDAO alunoDAO = new AlunoDAO();
    public static final String LISTA_DE_ALUNOS = "Lista de alunos";
    FloatingActionButton fabNovoAluno;
    ListView lvAlunos;
    AlunoDAO dao;
    private ListaAlunosAdapter adapterLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(LISTA_DE_ALUNOS);

        fabNovoAluno = findViewById(R.id.activity_lista_alunos_novo_aluno);
        lvAlunos = findViewById(R.id.activity_lista_alunos_lista);
        dao = new AlunoDAO();
        final Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);


        configuraFAB(intent);
        configuraListView(intent);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.activity_lista_aluno_menu_remover) {
            adapterLV.remove(dao.todos().get(menuInfo.position));
            dao.remover(dao.todos().get(menuInfo.position));
        }

        return super.onContextItemSelected(item);
    }

    private void configuraListView(final Intent intent) {
        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Click on List", "onItemClick: " + position + "/" + id);
                intent.putExtra(CHAVE_EXTRA_ALUNO, dao.todos().get(position));
                startActivity(intent);

            }
        });

        registerForContextMenu(lvAlunos);
        lvAlunos.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
            }
        });

        adapterLV = new ListaAlunosAdapter(this);
        lvAlunos.setAdapter(this.adapterLV);
    }

    private void configuraFAB(final Intent intent) {
        fabNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.hasExtra(CHAVE_EXTRA_ALUNO)) {
                    Log.i("INSERT", "onClick: Tinha EXTRA!");
                    intent.removeExtra(CHAVE_EXTRA_ALUNO);
                }
                startActivity(intent);

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapterLV.atualiza(dao.todos());
    }
}
