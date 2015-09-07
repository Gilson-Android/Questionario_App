package br.com.ggc.questinario;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;

public class ActivityBemVindo extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bem_vindo);
		

		TranslateAnimation translation2;
		findViewById(R.id.llRodape).clearAnimation();
		translation2 = new TranslateAnimation(0f, 0F, 100f, findViewById(R.id.llRodape).getPivotY());
		translation2.setStartOffset(500);
		translation2.setDuration(2000);
		translation2.setFillAfter(true);
		translation2.setInterpolator(new BounceInterpolator());
		findViewById(R.id.llRodape).startAnimation(translation2);

		TranslateAnimation translation1;
		findViewById(R.id.tvConteudo).clearAnimation();
		translation1 = new TranslateAnimation(0f, 0F, -100f, 100);
		translation1.setStartOffset(500);
		translation1.setDuration(2000);
		translation1.setFillAfter(true);
		translation1.setInterpolator(new BounceInterpolator());
		findViewById(R.id.tvConteudo).startAnimation(translation1);


	}

	public void onClickFab(View v) {
		finish();
	}
}
