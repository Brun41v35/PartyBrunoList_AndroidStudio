package com.example.party.Adaptador;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.party.BancoDeDados.Pessoa;
import com.example.party.BancoDeDados.PessoaDao;
import com.example.party.CadastroPessoaActivity;
import com.example.party.R;

import java.util.ArrayList;
import java.util.List;

public class ListarPessoaActivity extends AppCompatActivity {

    private ListView listView;
    private PessoaDao dao;
    private List<Pessoa> pessoas;
    private List<Pessoa> pessoasFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pessoa);

        listView = findViewById(R.id.idListaPessoas);
        dao = new PessoaDao(this);
        pessoas = dao.obterTodos();
        pessoasFiltradas.addAll(pessoas);
        //ArrayAdapter<Pessoa> adaptador  = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, pessoasFiltradas);
        PessoaAdapter adaptador = new PessoaAdapter(this,pessoasFiltradas);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal,menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procuraPessoa(newText);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto,menu);
    }

    public void  procuraPessoa (String nome) {
        pessoasFiltradas.clear();
        for  (Pessoa p : pessoas) {
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                pessoasFiltradas.add(p);
            }
        }
        listView.invalidateViews();
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Pessoa atualizarPessoa = pessoasFiltradas.get(menuInfo.position);
        Intent intencao = new Intent(this, CadastroPessoaActivity.class);
        intencao.putExtra("pessoa", atualizarPessoa);
        startActivity(intencao);
    }

    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Pessoa pessoaExlcuir = pessoasFiltradas.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Quer mesmo excluir essa pessoa ? ")
                .setNegativeButton("NÃO",null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pessoasFiltradas.remove(pessoaExlcuir);
                        pessoas.remove(pessoaExlcuir);
                        dao.excluir(pessoaExlcuir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void cadastrar (MenuItem item) {
        Intent intencao = new Intent(this,CadastroPessoaActivity.class);
        startActivity(intencao);
    }

    @Override
    public void onResume() {
        super.onResume();
        pessoas = dao.obterTodos();
        pessoasFiltradas.clear();
        pessoasFiltradas.addAll(pessoas);
        listView.invalidateViews();
    }
}
