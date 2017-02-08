package com.example.android.contactsapp;

import android.content.Context;
import android.provider.Telephony;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Siris on 2/7/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    List<Contact> contactList;
    Context context;
    ContactOnClickListener listener;

    public ContactAdapter(List<Contact> contactList, Context context, ContactOnClickListener listener) {
        this.contactList = contactList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_list_view, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        Glide.with(context).load(contact.getSmallImageURL()).into(holder.iv);
        holder.tvName.setText(contact.getName());
        holder.tvNumber.setText(contact.getPhone().getHome());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv;
        TextView tvName;
        TextView tvNumber;


        public ContactViewHolder(View itemView) {
            super(itemView);

            iv = (ImageView) itemView.findViewById(R.id.image_contact);
            tvName = (TextView) itemView.findViewById(R.id.text_name);
            tvNumber = (TextView) itemView.findViewById(R.id.text_number);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            listener.onItemClick(clickedPosition);
        }
    }
}
