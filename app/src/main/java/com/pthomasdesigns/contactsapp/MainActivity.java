package com.pthomasdesigns.contactsapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pthomasdesigns.libcontactandroid.Contact;
import com.pthomasdesigns.libcontactandroid.ContactSdk;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView contactsView;
    private ContactsAdapter contactsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ContactSdk contactSdk;
    boolean isVisble = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactSdk = ((ContactsApp)getApplicationContext()).getContactSdk();
        contactsView = (RecyclerView) findViewById(R.id.recycle_view_contacts);
        contactsAdapter = new ContactsAdapter();
        contactsView.setAdapter(contactsAdapter);
        setRecyclerViewLayoutManager();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);
                startActivity(intent);
            }
        });

        setUpdateContactListener();
    }

     private void setUpdateContactListener() {
        contactSdk.setUpdateContactListener(new ContactSdk.UpdateContactListener() {
            @Override
            public void onContactUpdated(Contact newContact, Contact oldContact) {
                Log.i(TAG, "onContactUpdated " + newContact + oldContact);
                getAllContacts();
                if (MainActivity.this.isVisble) {
                    Toast.makeText(getApplicationContext(), "Update:" + newContact.getFirstName() + ":" +
                            oldContact.getPhoneNumber() + " -> " + newContact.getPhoneNumber(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        isVisble = true;
        getAllContacts();
    }

    @Override
    public void onStop() {
        super.onStop();
        isVisble = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setRecyclerViewLayoutManager() {
        layoutManager = new LinearLayoutManager(this);
        contactsView.setLayoutManager(layoutManager);
    }

    private void getAllContacts() {
        contactSdk.getAllContacts(new ContactSdk.GetAllContactsListener() {
            @Override
            public void onSuccess(List<Contact> allContacts) {
                Log.i(TAG, "onSuccess" + allContacts.toString());
                contactsAdapter.setData(allContacts);
                contactsAdapter.notifyDataSetChanged();
             }
        });
    }
}
