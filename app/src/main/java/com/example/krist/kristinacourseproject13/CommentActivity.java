package com.example.krist.kristinacourseproject13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CommentActivity extends AppCompatActivity {

    String id = "";
    String name = "";
    String street = "";
    String house = "";
    String role = "";

    Button button2;
    EditText editText;

    private FirebaseListAdapter<Comment> adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_comment = database.getReference("Comment");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        street = getIntent().getStringExtra("street");
        house = getIntent().getStringExtra("house");
        role = getIntent().getStringExtra("role");

        editText = (EditText) findViewById(R.id.editText);

        button2 = (Button) findViewById(R.id.button2);

        displayComment();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText)findViewById(R.id.editText);
                FirebaseDatabase.getInstance().getReference("Comment").push()
                        .setValue(new Comment(input.getText().toString(), name, id));
                input.setText("");
            }
        });
    }

    private void displayComment() {

        ListView listComments = (ListView)findViewById(R.id.listComment);
        adapter = new FirebaseListAdapter<Comment>(this, Comment.class, R.layout.item_comment, table_comment.orderByChild("idVote").equalTo(id)) {
            @Override
            protected void populateView(View v, Comment model, int position) {

                //adapter.getRef(position).getKey();

                TextView tvText, tvAuthor;

                tvText = (TextView)v.findViewById(R.id.tvText);
                tvAuthor = (TextView)v.findViewById(R.id.tvAuthor);

                tvText.setText(model.getText());
                tvAuthor.setText(model.getAuthor());
            }
        };
        listComments.setAdapter(adapter);
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
            Intent sign = new Intent(CommentActivity.this, SignIn.class);
            startActivity(sign);
        }

        if (item.getItemId() == R.id.menu_message)
        {
            Intent vote = new Intent(CommentActivity.this, ListMessages.class);
            vote.putExtra("House", house);
            vote.putExtra("Name", name);
            startActivity(vote);
        }

        if (item.getItemId() == R.id.menu_vote)
        {
            Intent vote = new Intent(CommentActivity.this, ActivityVote.class);
            vote.putExtra("House", house);
            vote.putExtra("Role", role);
            vote.putExtra("Name", name);
            vote.putExtra("Street", street);
            startActivity(vote);
        }
        return true;
    }
}
