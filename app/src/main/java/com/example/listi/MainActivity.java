package com.example.listi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String key1 = "key1";
    private static final String key2 = "key2";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = findViewById(R.id.listss);
        BaseAdapter listContentAdapter = createAdapter();
        list.setAdapter(listContentAdapter);








    }

    @NonNull
    private BaseAdapter createAdapter() {
        int[] to = {android.R.id.text1,android.R.id.text2};
        String[] from = {"text","length"};

        return new SimpleAdapter(this, prepareContent(), android.R.layout.simple_list_item_2,from,to);
    }

    @NonNull
    private List<Map<String, String>> prepareContent() {
        List<Map<String,String>> data = new ArrayList<>();
        HashMap<String, String> map;
        String[] arrayContent = getString(R.string.large_text).split("\n\n");
        for (String s : arrayContent) {
            map = new HashMap<>();
            String a = s;
            map.put("text", s);
            map.put("length", s.length() + "");
            data.add(map);

//        for (int i =0;i<arrayContent.length;i++){
//            String a = arrayContent[i];
//            map.put(arrayContent[i],a);

        }
//        }
        return data;
    }
    }
