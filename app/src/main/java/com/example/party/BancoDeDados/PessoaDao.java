package com.example.party.BancoDeDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PessoaDao {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public PessoaDao(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Pessoa pessoa) {
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("idade", pessoa.getIdade());
        return banco.insert("pessoa", null,values);
    }

    public List<Pessoa> obterTodos() {
        List<Pessoa> pessoasEncontradas = new ArrayList<>();
        Cursor cursor = banco.query("pessoa", new String[]{"id","nome","idade"},
                null,null,null,null,null);
        while (cursor.moveToNext()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(cursor.getInt(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setIdade(cursor.getString(2));
            pessoasEncontradas.add(pessoa);
        }
        return pessoasEncontradas;
    }

    public void excluir(Pessoa pessoa) {
        banco.delete("pessoa","id = ?", new String[]{pessoa.getId().toString()});
    }

    public void atualizar(Pessoa pessoa) {
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("idade", pessoa.getIdade());
        banco.update("pessoa",values,
                "id = ?", new String[]{pessoa.getId().toString()});
    }
}
