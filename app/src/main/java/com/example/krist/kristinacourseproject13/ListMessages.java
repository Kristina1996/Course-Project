package com.example.krist.kristinacourseproject13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
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

    String roomid = "";
    String name = "";

    private FirebaseListAdapter<Message> adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_message = database.getReference("Message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_messages);

        edtText = (EditText) findViewById(R.id.edtText);

        btnSend = (Button) findViewById(R.id.btnSend);

        roomid = getIntent().getStringExtra("House");
        name = getIntent().getStringExtra("Name");

        displayChat();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.edtText);
                FirebaseDatabase.getInstance().getReference("Message").push()
                        .setValue(new Message(input.getText().toString(), name, roomid));
                input.setText("");
            }
        });
    }

    private void displayChat() {

        ListView listMessages = (ListView)findViewById(R.id.listView2);
        adapter = new FirebaseListAdapter<Message>(this, Message.class, R.layout.item_message, table_message.orderByChild("roomId").equalTo(roomid)) {
            @Override
            protected void populateView(View v, Message model, int position) {

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
}
