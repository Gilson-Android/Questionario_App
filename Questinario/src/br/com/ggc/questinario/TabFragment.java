package br.com.ggc.questinario;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.ggc.questinario.Util.CircleProgressView;
import br.com.ggc.questinario.Util.Dados;
 
public class TabFragment extends Fragment {
 
    private int position;
    private TextView content;
    private CircleProgressView mCircleView;
    private FloatingActionButton mFab;
    private int mPosicao;
    private String mItemTab;
    private SharedPreferences preferences;
 
    public static Fragment getInstance(int posicao,String itemTab) {
        TabFragment f = new TabFragment();
        Bundle args = new Bundle();
        args.putString("itemTab", itemTab);
        args.putInt("posicao", posicao);
        f.setArguments(args);
        return f;
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get data from Argument
        preferences = TabFragment.this.getContext().getSharedPreferences("questinario", Context.MODE_WORLD_WRITEABLE);	 
        mPosicao = getArguments().getInt("posicao");
        mItemTab = getArguments().getString("itemTab");
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment, container, false);
    }
    
  
    @TargetApi(Build.VERSION_CODES.HONEYCOMB) 
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       
        mCircleView = (CircleProgressView) view.findViewById(R.id.circleView);
        content = (TextView) view.findViewById(R.id.textCategoria);
        content.setText(mItemTab);
        mCircleView.setMaxValue(11);
        mCircleView.setValue(Dados.getShared(TabFragment.this.getContext(), "Tab"+mPosicao, (int)0));
        mCircleView.setValueAnimated(Dados.getShared(TabFragment.this.getContext(), "Tab"+mPosicao, (int)0));

        mCircleView.setTextSize(20);
        mCircleView.setUnitSize(15);

        mCircleView.setAutoTextSize(true); 
        
    }
 
}
