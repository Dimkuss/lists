package com.example.listi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter listContentAdapter = null;
    private final List<HashMap<String, String>> list = new ArrayList<>();
    private SharedPreferences mySharedPref;
    private final static String TEXT_PAR = "note_text";
    private String mText;
    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listss);
        BaseAdapter listContentAdapter = createAdapter();
        listView.setAdapter(listContentAdapter);
        mText = getString(R.string.large_text);

        saveText();
        listViewInit(this);
        loadText();
    }
    private void listViewInit(final Context context) {
        prepareContent();
        listContentAdapter = createAdapter();
        listView.setAdapter(listContentAdapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                listContentAdapter.notifyDataSetChanged();

            }
        });
    }

    @NonNull
    private SimpleAdapter createAdapter() {
        int[] to = {android.R.id.text1,android.R.id.text2};
        String[] from = {"text","length"};


        return new SimpleAdapter(this, list, android.R.layout.simple_list_item_2,from,to);
    }

    @NonNull
    private List<HashMap<String, String>> prepareContent() {

         HashMap<String, String> map;
        String[] arrayContent = getString(R.string.large_text).split("\n\n");
        for (String s : arrayContent) {
            map = new HashMap<>();

            map.put("text", s);
            map.put("length", s.length() + "");
            list.add(map);


        }
        arrayContent = mySharedPref.getString("TEXT_PAR",mText).split("\n\n");


        return list;
    }
    private void saveText(){
        mySharedPref = getSharedPreferences("MyText",MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPref.edit();
        myEditor.putString(TEXT_PAR,mText);
        myEditor.apply();


    }
    private void loadText(){
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                prepareContent();
                createAdapter();

            }
        });
    }

    }
