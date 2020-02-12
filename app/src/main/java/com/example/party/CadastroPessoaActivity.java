package com.example.party;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.party.BancoDeDados.Pessoa;
import com.example.party.BancoDeDados.PessoaDao;

public class CadastroPessoaActivity extends AppCompatActivity {

    private EditText nome;
    private EditText idade;
    private PessoaDao dao;
    private Pessoa pessoa = null;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastropessoa);

        lottieAnimationView = (LottieAnimationView) findViewById(R.id.idFila);
        startu();

        nome = findViewById(R.id.editNome);
        idade = findViewById(R.id.editIdade);
        dao = new PessoaDao(this);

        Intent intencao = getIntent();
        if (intencao.hasExtra("pessoa")) {
            pessoa = (Pessoa) intencao.getSerializableExtra("pessoa");
            nome.setText(pessoa.getNome());
            idade.setText(pessoa.getIdade());
        }
    }

    private void startu() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lottieAnimationView.setProgress((Float) valueAnimator.getAnimatedValue());
            }
        });
        if (lottieAnimationView.getProgress() == 0f) {
            animator.start();
        } else {
            lottieAnimationView.setProgress(0f);
        }
    }

    public void salvar(View view) {

        if(pessoa == null) {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome.getText().toString());
            pessoa.setIdade(idade.getText().toString());
            long id = dao.inserir(pessoa);
            Toast.makeText(this, "Pessoa inserida com sucesso", Toast.LENGTH_SHORT).show();
        } else {
            pessoa.setNome(nome.getText().toString());
            pessoa.setIdade(idade.getText().toString());
            dao.atualizar(pessoa);
            Toast.makeText(this, "Pessoa atualizada com Sucesso", Toast.LENGTH_SHORT).show();
        }
    }
}
