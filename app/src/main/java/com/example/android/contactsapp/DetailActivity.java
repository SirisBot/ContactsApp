package com.example.android.contactsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.img_detail_contact) ImageView imgContact;
    @BindView(R.id.text_name) TextView tvName;
    @BindView(R.id.text_company) TextView tvCompany;
    @BindView(R.id.text_phone) TextView tvPhone;
    @BindView(R.id.text_address) TextView tvAddress;
    @BindView(R.id.text_email) TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Contact contact = (Contact) getIntent().getSerializableExtra("c");

        Glide.with(this).load(contact.getLargeImageURL()).into(imgContact);
        tvName.setText(contact.getName());
        tvCompany.setText(contact.getCompany());
        tvPhone.setText(contact.getPhone().getHome());

        StringBuilder sb = new StringBuilder();
        sb.append(contact.getAddress().getStreet() + "\n");
        sb.append(contact.getAddress().getCity() + ", " + contact.getAddress().getState() + " " + contact.getAddress().getZip());
        tvAddress.setText(sb);
        tvEmail.setText(contact.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
