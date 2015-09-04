package br.com.ggc.questinario.objs;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemTab implements Parcelable{
	String 	ds_Item;
	int		qt_Valor;
	
	public String getDs_Item() {
		return ds_Item;
	}
	public void setDs_Item(String ds_Item) {
		this.ds_Item = ds_Item;
	}
	public int getQt_Valor() {
		return qt_Valor;
	}
	public void setQt_Valor(int qt_Valor) {
		this.qt_Valor = qt_Valor;
	}
	public ItemTab(String ds_Item, int qt_Valor) {
		super();
		this.ds_Item = ds_Item;
		this.qt_Valor = qt_Valor;
	}
	
	public ItemTab() {
		super();
		this.ds_Item = "";
		this.qt_Valor = 0;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel itemTab, int arg1) {
		// TODO Auto-generated method stub
		itemTab.writeStringArray(new String[]{
		            String.valueOf(this.ds_Item),
		            String.valueOf(this.qt_Valor)
		     });
	}
	
}
