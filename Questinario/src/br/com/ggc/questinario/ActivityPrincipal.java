package br.com.ggc.questinario;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.ggc.questinario.Util.CircleProgressView;
import br.com.ggc.questinario.Util.Dados;
import br.com.ggc.questinario.Util.Util;
import br.com.ggc.questinario.segundoplano.Enviar;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ActivityPrincipal extends AppCompatActivity {

	private TextView mTitulo;
	private EditText mNome;
	private EditText mEmail;
	private Toolbar mToolbar;
	private LinearLayout mEnvio;
	private TabLayout mTabLayout;
	private CircleProgressView mCircleView;
	private FloatingActionButton mFabProximo;
	private FloatingActionButton mFabVoltar;
	private ArrayList<String> mItens;
	private SharedPreferences preferences;
	private int posicao;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		// Carrega as views
		carregarViews();

		setSupportActionBar(mToolbar);

		carregarTabs();

		mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

					@Override
					public void onTabUnselected(Tab arg0) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onTabSelected(Tab arg0) {
						// TODO Auto-generated method stub
						posicao = arg0.getPosition();
						carregarTab(arg0.getPosition());
					}

					@Override
					public void onTabReselected(Tab arg0) {
						posicao = arg0.getPosition();
						if (posicao < mItens.size() -1) {
							carregarTab(arg0.getPosition());
						}
					}
				});

		preferences = getSharedPreferences("questinario",Context.MODE_WORLD_WRITEABLE);

		if (!Dados.getShared(this, "brirBoasVinda", false)) {

			SharedPreferences.Editor editor = preferences.edit();
			editor.putBoolean("brirBoasVinda", true);
			editor.apply();
			editor = null;
		
			Intent it = new Intent(this, ActivityBemVindo.class);
			startActivity(it);
		}
	}

	public void onClickFabProximo(View v) {

		if (posicao == mItens.size() - 1) {
			if (validarCampos()) {
				Enviar envio = new Enviar(this, mEmail.getText().toString());
				mTabLayout.getTabAt(posicao).setIcon(R.drawable.ic_done_white_18dp);
				
				SharedPreferences.Editor editor = preferences.edit();
				editor.putString("nome", mNome.getText().toString());
				editor.putString("email",mEmail.getText().toString());
				editor.apply();
				editor = null;
				
				salvarTab();
				envio.execute();
			}
		} else {
			mTabLayout.getTabAt(posicao).setIcon(R.drawable.ic_done_white_18dp);
			salvarTab();
			mTabLayout.getTabAt(posicao + 1).select();
		}
	}

	public void onClickFabVoltar(View v) {

		if (posicao > 0) {
			mTabLayout.getTabAt(posicao - 1).select();
			carregarTab(posicao);
		}
	}

	private void carregarViews() {
		mFabVoltar = (FloatingActionButton) findViewById(R.id.fabVoltar);
		mFabProximo = (FloatingActionButton) findViewById(R.id.fabProximo);
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
		mCircleView = (CircleProgressView) findViewById(R.id.circleView);
		mTitulo = (TextView) findViewById(R.id.textCategoria);
		mEnvio = (LinearLayout) findViewById(R.id.llEnvio);
		mNome = (EditText) findViewById(R.id.edNome);
		mEmail = (EditText) findViewById(R.id.edEmail);
	}

	private void carregarTab(int index) {
		if (index == 0) {
			mFabVoltar.setVisibility(View.GONE);
		} else {
			mFabVoltar.setVisibility(View.VISIBLE);
		}
		mTitulo.setText(mItens.get(index).toString());
		if (index == mItens.size() - 1) {
			posicao = index;
			mEnvio.setVisibility(View.VISIBLE);
			mCircleView.setVisibility(View.GONE);
			mTabLayout.getTabAt(index).select();
			
		} else {
			mEnvio.setVisibility(View.GONE);
			mCircleView.setVisibility(View.VISIBLE);
			mCircleView.setValue(Dados.getShared(ActivityPrincipal.this, "Tab"+ index, (int) 0));
			mCircleView.setValueAnimated(Dados.getShared(ActivityPrincipal.this,"Tab" + index, (int) 0));
		}
	}

	private void salvarTab() {
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt("Tab" + posicao, mCircleView.getValue() + 1);
		editor.putBoolean("Tab_" + posicao, true);
		editor.apply();
		editor = null;
	}

	private void carregarTabs() {
		mItens = new ArrayList<String>();
		mItens.add("HTML");
		mItens.add("CSS");
		mItens.add("Javascript");
		mItens.add("Python");
		mItens.add("Django");
		mItens.add("Desenvolvimento iOS");
		mItens.add("Desenvolvimento Android");
		mItens.add("Envio");
		for (int i = 0; i < mItens.size(); i++) {
			if (i != mItens.size() - 1) {
				mTabLayout.addTab(mTabLayout.newTab().setText("Item - " +String.valueOf(i + 1)));
			} else {
				mTabLayout.addTab(mTabLayout.newTab().setText(String.valueOf("Envio")));
			}

			if (Dados.getShared(ActivityPrincipal.this, "Tab_" + i, false)) {
				mTabLayout.getTabAt(i).setIcon(R.drawable.ic_done_white_18dp);
			}
		}

		if (Dados.getShared(ActivityPrincipal.this, "Tab_"+ (mItens.size() - 1), false)) {
			carregarTab(mItens.size() - 1);
		} else {
			carregarTab(0);
		}
		mNome.setText(Dados.getShared(ActivityPrincipal.this, "nome", ""));
		mEmail.setText(Dados.getShared(ActivityPrincipal.this, "email", ""));

		mCircleView.setMaxValue(11);
		mCircleView.setTextSize(20);
		mCircleView.setUnitSize(15);
		mCircleView.setAutoTextSize(true);
		mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

	}
	
	private boolean validarCampos(){
		boolean erro = false;
		mNome.setError(null);
		mEmail.setError(null);
		
		for (int i = 0; i < mItens.size()-1; i++) {
			if (!Dados.getShared(ActivityPrincipal.this, "Tab_" + i, false)) {
				mTabLayout.getTabAt(i).setIcon(R.drawable.ic_error);
				erro = true;
			} 
		}
		if (erro) {
			Util.exibirMensagem("Pend�ncias", "Por favor, defina seu nivel de conhecimento nos itens em destaque vemelho.", this, true, true);
			return false;
		}
		
		if (mNome.getText().toString().trim().length() == 0) {
			mNome.setError("Informe seu nome");
			return false;
		}
		if (mEmail.getText().toString().trim().length() == 0) {
			mEmail.setError("Informe seu Email");
			return false;
		}
		if (!Util.validarEmail(mEmail.getText().toString())) {
			mEmail.setError("Informe um Email v�lido.");
			return false;
		}
		return true;
	}

	public static void retornoServidor(boolean _b, Activity _activity) {
		// TODO Auto-generated method stub
		if (_b) {
			Util.exibirMensagem("Envio - Ok", "Seu question�rio foi enviado com sucesso", _activity, true, true);
		}else {
			Util.exibirMensagem("Envio - Erro", "Ops! Algum problema aconteceu, tente novamente mais tarde.", _activity, true, true);
		}
		
	}

	
}