package br.nom.wbarbosa.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.nom.wbarbosa.R;
import br.nom.wbarbosa.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {
    final private List<Aluno> alunos = new ArrayList<>();
    private Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_aluno, parent, false);

        TextView nome = view.findViewById(R.id.item_aluno_nome);
        TextView telefone = view.findViewById(R.id.item_aluno_telefone);

        nome.setText(alunos.get(position).getNome());
        telefone.setText(alunos.get(position).getTelefone());

        return view;
    }

    private void addAll(List<Aluno> todos) {
        alunos.addAll(todos);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }

    public void atualiza(List<Aluno> todos) {
        this.clear();
        this.addAll(todos);
        notifyDataSetChanged();
    }

    private void clear() {
        alunos.clear();
    }
}
