package com.pthomasdesigns.contactsapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pthomasdesigns.libcontactandroid.Contact;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<Contact> mData;
    private static final String TAG = "ContactsAdapter";

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvFirstName;
        public TextView tvLastName;
        public TextView tvPhoneNumber;

        public ViewHolder(View view, List<Contact> data) {

            super(view);
            final List<Contact> mData = data;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            tvFirstName = (TextView) view.findViewById(R.id.row_item_firstname);
            tvLastName = (TextView) view.findViewById(R.id.row_item_lastname);
            tvPhoneNumber = (TextView) view.findViewById(R.id.row_item_phonenumber);
        }
    }

    public void setData(List<Contact> data) {
        mData = data;
    }

    // Create new views
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_item_contact, viewGroup, false);

        return new ContactsAdapter.ViewHolder(view, mData);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position);

        viewHolder.tvFirstName.setText(mData.get(position).getFirstName());
        viewHolder.tvLastName.setText(mData.get(position).getLastName());
        viewHolder.tvPhoneNumber.setText(mData.get(position).getPhoneNumber());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }
}
