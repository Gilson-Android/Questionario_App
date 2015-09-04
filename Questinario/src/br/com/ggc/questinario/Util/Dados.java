package br.com.ggc.questinario.Util;

import android.app.Activity;
import android.content.Context;

public class Dados {
	public static long getShared(Context _context,String _NomeTAG, long _defValue){
		return _context.getSharedPreferences("questinario", Activity.MODE_PRIVATE).getLong(_NomeTAG, _defValue);
	}
	
	public static int getShared(Context _context,String _NomeTAG, int _defValue){
		return _context.getSharedPreferences("questinario", Activity.MODE_PRIVATE).getInt(_NomeTAG, _defValue);
	}
	
	public static String getShared(Context _context,String _NomeTAG, String _defValue){
		return _context.getSharedPreferences("questinario", Activity.MODE_PRIVATE).getString(_NomeTAG, _defValue);
	}
	
	public static boolean getShared(Context _context,String _NomeTAG, boolean _defValue){
		return _context.getSharedPreferences("questinario", Activity.MODE_PRIVATE).getBoolean(_NomeTAG, _defValue);
	}
}
