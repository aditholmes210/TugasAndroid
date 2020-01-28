package com.aditas.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DynamicFragment extends AppCompatActivity {
//    private Button btnMove;
//    private Button btnBack;
    private Button btnRep;
    private int idFr;
    private SimpleFragment simple;
    private TambahFragment tambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment);
//        btnMove = findViewById(R.id.btn_move);
//        btnBack = findViewById(R.id.btn_back);
        btnRep = findViewById(R.id.btn_rep);
        idFr = 1;
        final int frag = R.id.fl_dyna;
        simple = new SimpleFragment();
        tambah = new TambahFragment();

        //FragmentManager
        final FragmentManager manager = getSupportFragmentManager();

        //Buat obj fragment transaction
        FragmentTransaction trans = manager.beginTransaction();
        //Tambahkan obj simple fragment (obj) ke frame layout
        trans.add(R.id.fl_dyna, simple);
        //kemudian commit
        trans.commit();
//Cara 1
//        btnMove.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                //Ketika btn pindah di klik, akan pindah ke fragment lain
//                FragmentTransaction  trans = getSupportFragmentManager().beginTransaction();
//                //trans.add(R.id.fl_dyna, new TambahFragment());
//Klo hide nyimpen activity yg sedang dilakukan / Klo remove akan destroy activity yg sedang dilakukan
//                if(tambah.isAdded()){
//                   trans.show(tambah);
//                   trans.hide(simple);
//                   Toast.makeText(getApplicationContext(), "Fragment has been added", Toast.show());
//                } else {
//                      trans.replace(R.id.fl_dyna);
//                }
//                trans.replace(R.id.fl_dyna, tambah);
//                trans.addToBackStack("Simple Fragment");
//                trans.commit();
//
//                btnBack.setVisibility(View.VISIBLE);
//                btnMove.setVisibility(View.GONE);
//            }
//        });
//        btnBack.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                //Ketika btn pindah di klik, akan pindah ke fragment lain
//                FragmentTransaction  trans = getSupportFragmentManager().beginTransaction();
//                //trans.add(R.id.fl_dyna, new TambahFragment());
//                trans.replace(R.id.fl_dyna, simple);
//                trans.commit();
//
//                btnBack.setVisibility(View.GONE);
//                btnMove.setVisibility(View.VISIBLE);
//            }
//        });

//Cara 2
        btnRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(idFr){
                    case 1:
                        manager.beginTransaction().replace(frag, simple).commit();
                        idFr = 2;
                        break;
                    case 2:
                        manager.beginTransaction().replace(frag, tambah).commit();
                        idFr = 1;
                        break;
                }
            }
        });
    }
}
