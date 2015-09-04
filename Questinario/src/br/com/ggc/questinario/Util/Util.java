package br.com.ggc.questinario.Util;

import java.util.regex.Pattern;

import br.com.ggc.questinario.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Util {
	public static void travarTela(Activity contexto, boolean travar){
		if(travar){
			int currentOrientation = contexto.getResources().getConfiguration().orientation; 
			if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
				contexto.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
			}
			else {
				contexto.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
			}
		} else {
			contexto.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
		}
	}
	
	// Exibe mensagem na tela
		public static void exibirMensagem(String titulo, String conteudo, Activity activity, boolean travar, boolean longo){
			if(travar){
				final Activity activityFinal = activity;
				travarTela(activityFinal, true);
				final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
				mensagem.setTitle(titulo);
				mensagem.setMessage(conteudo);
				mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						travarTela(activityFinal, false);
					}
				});
				mensagem.setCancelable(false);
				mensagem.show();
			} else {
				LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.toast, null);
				TextView texto = (TextView) layout.findViewById(R.id.toast_textView_texto1);
				texto.setText(conteudo);
				Toast toast = new Toast(activity);
				//toast.setGravity(Gravity.CENTER, 0, 0);
				if(longo){
					toast.setDuration(Toast.LENGTH_LONG);
				} else {
					toast.setDuration(Toast.LENGTH_SHORT);
				}
				toast.setView(layout);
				toast.show();
			}
		}
		
		// Verifica a formatação do email
		public static boolean validarEmail(String email) {

			Pattern EMAIL_ADDRESS_PATTERN = Pattern
					.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
							+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
							+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

			return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
		}
}
