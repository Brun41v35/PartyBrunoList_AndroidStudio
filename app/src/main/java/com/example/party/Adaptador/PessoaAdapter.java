package com.example.party.Adaptador;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.party.BancoDeDados.Pessoa;
import com.example.party.R;

import java.util.List;

public class PessoaAdapter extends BaseAdapter {

    private List<Pessoa> pessoas;
    private Activity activity;

    public PessoaAdapter(Activity activity,List<Pessoa> pessoas) {
        super();
        this.activity = activity;
        this.pessoas = pessoas;
    }

    @Override
    public int getCount() {

        return pessoas.size();
    }

    @Override
    public Object getItem(int position) {

        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {

        return pessoas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.linha, parent, false);
        TextView nome = view.findViewById(R.id.nomeTXTBruno);
        TextView idade = view.findViewById(R.id.idadetxt);

        Pessoa pessoa = pessoas.get(position);

        nome.setText(pessoa.getNome());
        idade.setText(pessoa.getIdade());
        return view;
    }
}
