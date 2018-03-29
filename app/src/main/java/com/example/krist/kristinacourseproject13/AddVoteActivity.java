package com.example.krist.kristinacourseproject13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddVoteActivity extends AppCompatActivity {

    Button btnAddVote;
    EditText edtAnswer1, edtAnswer2, edtText;

    String name = "";
    String house = "";
    String street = "";
    String role = "";

    private FirebaseListAdapter<Message> adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_vote = database.getReference("Vote");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vote);

        name = getIntent().getStringExtra("Name");
        house = getIntent().getStringExtra("House");
        street = getIntent().getStringExtra("Street");
        role = getIntent().getStringExtra("Role");

        edtText = (EditText) findViewById(R.id.edtText);
        edtAnswer1 = (EditText) findViewById(R.id.edtAnswer1);
        edtAnswer2 = (EditText) findViewById(R.id.edtAnswer2);

        btnAddVote = (Button) findViewById(R.id.btnAddVote);

        btnAddVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.edtText);
                EditText input1 = (EditText)findViewById(R.id.edtAnswer1);
                EditText input2 = (EditText)findViewById(R.id.edtAnswer2);
                FirebaseDatabase.getInstance().getReference("Vote").push()
                        .setValue(new Vote(input.getText().toString(), name, input1.getText().toString(),input2.getText().toString(), street, house));


                Intent intent = new Intent(AddVoteActivity.this, ActivityVote.class);
                intent.putExtra("Role", role);
                intent.putExtra("House", house);
                intent.putExtra("Name", name);
                startActivity(intent);
            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_signout)
        {
            Intent sign = new Intent(AddVoteActivity.this, SignIn.class);
            startActivity(sign);
        }

        if (item.getItemId() == R.id.menu_message)
        {
            Intent vote = new Intent(AddVoteActivity.this, ListMessages.class);
            vote.putExtra("House", house);
            startActivity(vote);
        }

        if (item.getItemId() == R.id.menu_vote)
        {
            Intent vote = new Intent(AddVoteActivity.this, ActivityVote.class);
            vote.putExtra("House", house);
            vote.putExtra("Role", role);
            vote.putExtra("Name", name);
            vote.putExtra("Street", street);
            startActivity(vote);
        }
        return true;
    }
}
