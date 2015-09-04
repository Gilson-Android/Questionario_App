package br.com.ggc.questinario.segundoplano;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Window;
import br.com.ggc.questinario.ActivityPrincipal;
import br.com.ggc.questinario.R;
import br.com.ggc.questinario.Util.Dados;
import br.com.ggc.questinario.Util.Util;
import br.com.ggc.questinario.email.Mail;

public class Enviar extends AsyncTask<String, String, integer> {
	private Activity mActivity;
	private ProgressDialog mDialog;
	private String mEmail;

	public Enviar(Activity _activity, String _email) {
		mActivity = _activity;
		mEmail = _email;
	}

	protected void onPreExecute() {
		Log.i("Envio", "onPreExecute()");
		Util.travarTela(mActivity, true);
		mDialog = new ProgressDialog(mActivity);

		mDialog.setMessage("Enviando dados...");
		mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mDialog.setIndeterminate(true);
		mDialog.setCancelable(false);
		mDialog.show();

	}

	@Override
	protected void onPostExecute(integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Log.i("Envio", "onPostExecute");
		mDialog.dismiss();
		if (mActivity != null) {
			if (mActivity.getClass().equals(ActivityPrincipal.class)) {
				ActivityPrincipal.retornoServidor(true, mActivity);
			}
		}
	}

	@Override
	protected integer doInBackground(String... params) {
		// TODO Auto-generated method stub
		Log.i("Envio", "doInBackground");

		enviar(mActivity, mEmail);

		return null;
	}

	private void enviar(Context context, String email) {
		Log.i("Envio", "doInBackground -> enviar");
		final Mail m = new Mail();
		boolean enviouEmail = false;
		// Eviar email tipo Front-End?
		if (Dados.getShared(context, "Tab0", (int) 0) >= 7 && Dados.getShared(context, "Tab1", (int) 0) >= 7 && Dados.getShared(context, "Tab2", (int) 0) >= 7) {
			m.setTitulo(context.getString(R.string.email_Titulo));
			m.setBody(context.getString(R.string.email_FrontEnd));
			m.setDestinatario(email);
			enviar(m);
			enviouEmail = true;
		}

		final Mail m1 = new Mail();
		// Eviar email tipo Back-End?
		if (Dados.getShared(context, "Tab3", (int) 0) >= 7 && Dados.getShared(context, "Tab4", (int) 0) >= 7) {
			m1.setTitulo(context.getString(R.string.email_Titulo));
			m1.setBody(context.getString(R.string.email_BackEnd));
			m1.setDestinatario(email);
			enviar(m1);
			enviouEmail = true;
		}

		final Mail m2 = new Mail();
		// Eviar email tipo Mobile?
		if (Dados.getShared(context, "Tab5", (int) 0) >= 7 && Dados.getShared(context, "Tab6", (int) 0) >= 7) {
			m2.setTitulo(context.getString(R.string.email_Titulo));
			m2.setBody(context.getString(R.string.email_Mobile));
			m2.setDestinatario(email);
			enviar(m2);
			enviouEmail = true;
		}

		final Mail m3 = new Mail();
		// Eviar email tipo Generico?
		if (!enviouEmail) {
			m3.setTitulo(context.getString(R.string.email_Titulo));
			m3.setBody(context.getString(R.string.email_Gennrico));
			m3.setDestinatario(email);
			enviar(m3);
		}

	}

	public static void enviar(final Mail m) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					m.send();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.e("Envio", "Erro no Envio", e);
				}
			}
		});

		t.start();
	}
}