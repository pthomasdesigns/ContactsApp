package com.pthomasdesigns.contactsapp;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pthomasdesigns.libcontactandroid.Contact;
import com.pthomasdesigns.libcontactandroid.ContactSdk;

public class AddContactActivity extends AppCompatActivity {
    private static final String TAG = "AddContactActivity";
    private TextInputEditText tiFirstName;
    private TextInputEditText tiLastName;
    private TextInputEditText tiPhoneNumber;
    private Button btAddContact;
    private ContactSdk sdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        sdk = ((ContactsApp)getApplicationContext()).getContactSdk();

        tiFirstName = (TextInputEditText) findViewById(R.id.first_name);
        tiLastName = (TextInputEditText) findViewById(R.id.last_name);
        tiPhoneNumber = (TextInputEditText) findViewById(R.id.phone_number);
        btAddContact = (Button) findViewById(R.id.add_contact);

        btAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact();
            }
        });
    }

    private void addContact() {
        String firstName = tiFirstName.getText().toString();
        String lastName = tiLastName.getText().toString();
        String phoneNumber = tiPhoneNumber.getText().toString();
        Contact contact = new Contact(firstName, lastName, phoneNumber);
        sdk.addContact(contact, new ContactSdk.AddContactListener() {
            @Override
            public void onContactAdded(Contact contact) {
                finish();
                Toast.makeText(getApplicationContext(), "Add success", Toast.LENGTH_LONG).show();
            }
        });

    }
}