package com.example.android.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements ContactOnClickListener {

    List<Contact> contactList;
    Type listType = new TypeToken<List<Contact>>() {}.getType();

    RecyclerView contactRv;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();
        contactRv = (RecyclerView) findViewById(R.id.recycler_view);

        new GetContactsAsync().execute();
    }

    void initRecyclerView() {
        contactRv.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter(contactList, this, this);
        contactRv.setAdapter(contactAdapter);
    }

    @Override
    public void onItemClick(int item) {
        Contact contact = contactList.get(item);
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("c", contact);
        startActivity(i);
    }

    public class GetContactsAsync extends AsyncTask<Void, Void, List<Contact>> {

        @Override
        protected List<Contact> doInBackground(Void... params) {

            Gson gson = new Gson();
            String contactsResponse = null;

            try {
                contactsResponse = run("https://s3.amazonaws.com/technical-challenge/Contacts.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return gson.fromJson(contactsResponse, listType);
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {
            super.onPostExecute(contacts);

            contactList = contacts;
            initRecyclerView();
        }
    }

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
