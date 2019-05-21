/*
Tanggal Pengerjaan : 21-05-2019
NIM                : 10116115
Nama               : Aditya Komara Ramadhan
Kelas              : IF-3
 */
package com.corporation.fallen.uts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuTeman extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    EditText et_name,et_email,et_nim,et_kelas,et_telepon,et_sosmed,et_update_name,et_update_email,et_update_nim,et_update_kelas,et_update_telepon,et_update_sosmed;
    Button add,btn_update,btn_cancel;
    RecyclerView recyclerView;
    MyAdapter adapter;

    List<UserData> list = new ArrayList<>();

    AlertDialog.Builder builder;

    AlertDialog dialog;

    String name,email,nim,kelas,telepon,sosmed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman);

        dl = (DrawerLayout)findViewById(R.id.activity_teman);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.profil:
                        Intent i = new Intent(MenuTeman.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.kontak:
                        Intent j = new Intent(MenuTeman.this, MenuKontak.class);
                        startActivity(j);
                        break;
                    case R.id.daftarteman:
                        Intent k = new Intent(MenuTeman.this, MenuTeman.class);
                        startActivity(k);
                        break;
                    default:
                        return true;
                }


                return true;

            }
        });

        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_nim = (EditText) findViewById(R.id.et_nim);
        et_kelas = (EditText) findViewById(R.id.et_kelas);
        et_telepon = (EditText) findViewById(R.id.et_telepon);
        et_sosmed = (EditText) findViewById(R.id.et_sosmed);

        et_email.setVisibility(View.INVISIBLE);
        et_nim.setVisibility(View.INVISIBLE);
        et_kelas.setVisibility(View.INVISIBLE);
        et_telepon.setVisibility(View.INVISIBLE);
        et_sosmed.setVisibility(View.INVISIBLE);

        add = (Button) findViewById(R.id.btn_add);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = et_name.getText().toString();
                email = et_email.getText().toString();
                nim = et_nim.getText().toString();
                kelas = et_kelas.getText().toString();
                telepon = et_telepon.getText().toString();
                sosmed = et_sosmed.getText().toString();

                UserData userData = new UserData();

                userData.setName(name);
                userData.setEmail(email);
                userData.setNim(nim);
                userData.setNim(kelas);
                userData.setNim(telepon);
                userData.setNim(sosmed);

                list.add(userData);
                adapter.notifyDataSetChanged();
                Toast.makeText(MenuTeman.this,"User Add Success...",Toast.LENGTH_SHORT).show();

                et_name.setText("");
                et_email.setText("");
                et_nim.setText("");
                et_kelas.setText("");
                et_telepon.setText("");
                et_sosmed.setText("");

            }
        });
        adapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClick(int position, UserData userData) {

                builder = new AlertDialog.Builder(MenuTeman.this);
                builder.setTitle("Update User Info");
                builder.setCancelable(false);
                View view = LayoutInflater.from(MenuTeman.this).inflate(R.layout.dialog_update,null,false);
                InitUpdateDialog(position,view);
                builder.setView(view);
                dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void InitUpdateDialog(final int position, View view) {

        et_update_name = view.findViewById(R.id.et_update_name);
        et_update_email = view.findViewById(R.id.et_update_email);
        et_update_nim = view.findViewById(R.id.et_update_nim);
        et_update_kelas = view.findViewById(R.id.et_update_kelas);
        et_update_telepon = view.findViewById(R.id.et_update_telepon);
        et_update_sosmed = view.findViewById(R.id.et_update_sosmed);
        btn_update = view.findViewById(R.id.btn_update_user);
        btn_cancel = view.findViewById(R.id.btn_update_cancel);

        et_update_name.setText(name);
        et_update_email.setText(email);
        et_update_nim.setText(nim);
        et_update_kelas.setText(kelas);
        et_update_telepon.setText(telepon);
        et_update_sosmed.setText(sosmed);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = et_update_name.getText().toString();
                email = et_update_email.getText().toString();
                nim = et_update_nim.getText().toString();
                kelas = et_update_kelas.getText().toString();
                telepon = et_update_telepon.getText().toString();
                sosmed = et_update_sosmed.getText().toString();

                UserData userData = new UserData();

                userData.setName(name);
                userData.setEmail(email);
                userData.setNim(nim);
                userData.setKelas(kelas);
                userData.setTelepon(telepon);
                userData.setSosmed(sosmed);

                adapter.UpdateData(position,userData);
                Toast.makeText(MenuTeman.this,"User Updated..",Toast.LENGTH_SHORT).show();

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}