package com.example.krist.kristinacourseproject13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListMessages extends AppCompatActivity {

    EditText edtText;
    Button btnSend;

    String house = "";
    String name = "";
    String role = "";
    String street = "";

    private FirebaseListAdapter<Message> adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_message = database.getReference("Message");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_messages);

        edtText = (EditText) findViewById(R.id.edtText);

        btnSend = (Button) findViewById(R.id.btnSend);

        house = getIntent().getStringExtra("House");
        name = getIntent().getStringExtra("Name");
        role = getIntent().getStringExtra("Role");
        street = getIntent().getStringExtra("Street");

        displayChat();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.edtText);
                FirebaseDatabase.getInstance().getReference("Message").push()
                        .setValue(new Message(input.getText().toString(), name, house));
                input.setText("");
            }
        });
    }

    private void displayChat() {

        ListView listMessages = (ListView)findViewById(R.id.listView2);
        adapter = new FirebaseListAdapter<Message>(this, Message.class, R.layout.item_message, table_message.orderByChild("roomId").equalTo(house)) {
            @Override
            protected void populateView(View v, Message model, int position) {

                //adapter.getRef(position).getKey();

                TextView textMessage, author, timeMessage;
                textMessage = (TextView)v.findViewById(R.id.tvMessage);
                author = (TextView)v.findViewById(R.id.tvUser);
                timeMessage = (TextView)v.findViewById(R.id.tvTime);

                textMessage.setText(model.getTextMessage());
                author.setText(model.getAuthor());
                timeMessage.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getTimeMessage()));
            }
        };
        listMessages.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_signout)
        {
            Intent sign = new Intent(ListMessages.this, SignIn.class);
            startActivity(sign);
        }

        if (item.getItemId() == R.id.menu_message)
        {
            Intent message = new Intent(ListMessages.this, ListMessages.class);
            message.putExtra("House", house);
            message.putExtra("Name", name);
            startActivity(message);
        }

        if (item.getItemId() == R.id.menu_vote)
        {
            Intent vote = new Intent(ListMessages.this, ActivityVote.class);
            vote.putExtra("House", house);
            vote.putExtra("Role", role);
            vote.putExtra("Name", name);
            vote.putExtra("Street", street);
            startActivity(vote);
        }
        return true;
    }

}
