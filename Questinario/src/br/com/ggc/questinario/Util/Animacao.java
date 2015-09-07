package br.com.ggc.questinario.Util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

public class Animacao {

	public static void animar(boolean mostrar, View view)
	{
		AnimationSet set = new AnimationSet(true);
		Animation animation = null;
		if (mostrar)
		{
			view.setVisibility(View.VISIBLE);
			animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		}
		else
		{    
			view.setVisibility(View.GONE);
			 animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
		}
                //duración en milisegundos
		animation.setDuration(500);
		set.addAnimation(animation);

		view.startAnimation(animation);
		
	}
}
