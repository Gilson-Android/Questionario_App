package br.com.ggc.questinario;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressWarnings("deprecation")
public class ActivityBemVindo extends FragmentActivity {

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

	private int getDisplayHeight() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}

	public void onClickFab(View v) {
		finish();
	}
}