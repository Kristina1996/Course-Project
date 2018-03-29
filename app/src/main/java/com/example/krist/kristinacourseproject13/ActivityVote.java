package com.example.krist.kristinacourseproject13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityVote extends AppCompatActivity {

    String house = "";
    String role = "";
    String name = "";
    String street = "";

    Button btnAnswer1, btnAnswer2, btnAdd;
    TextView tvCount1, tvCount2;

    private int mCount = 0;
    private int Count = 0;

    private FirebaseListAdapter<Vote> adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_vote = database.getReference("Vote");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        role = getIntent().getStringExtra("Role");
        name = getIntent().getStringExtra("Name");
        street = getIntent().getStringExtra("Street");
        house = getIntent().getStringExtra("House");

        btnAdd = (Button) findViewById(R.id.btnAdd);
        if (role.equalsIgnoreCase("Житель")) {btnAdd.setVisibility(View.INVISIBLE);}

        displayVote();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vote = new Intent(ActivityVote.this, AddVoteActivity.class);
                vote.putExtra("Name", name);
                vote.putExtra("House", house);
                vote.putExtra("Street", street);
                vote.putExtra("Role", role);
                startActivity(vote);
            }
        });

        /**btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAnswer1 = (Button) findViewById(R.id.btnAnswer1);
                btnAnswer2 = (Button) findViewById(R.id.btnAnswer2);

                tvCount1 = (TextView) findViewById(R.id.tvCount1);
                tvCount2 = (TextView) findViewById(R.id.tvCount2);

               // btnAnswer1.setEnabled(false);
                //btnAnswer2.setEnabled(false);

                //Integer count1 = +1;
                //btnAnswer1.setText();

                tvCount1.setText("Голосов:" + ++mCount);
                tvCount2.setText("Голосов:" + Count);
            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAnswer1 = (Button) findViewById(R.id.btnAnswer1);
                btnAnswer2 = (Button) findViewById(R.id.btnAnswer2);

                tvCount1 = (TextView) findViewById(R.id.tvCount1);
                tvCount2 = (TextView) findViewById(R.id.tvCount2);

                //btnAnswer1.setEnabled(false);
                //btnAnswer2.setEnabled(false);

                tvCount1.setText("Голосов:" + mCount);
                tvCount2.setText("Голосов:" + ++Count);
            }
        }); **/
    }

    private void displayVote() {

        ListView listVotes = (ListView) findViewById(R.id.listView3);

        adapter = new FirebaseListAdapter<Vote>(this, Vote.class, R.layout.item_vote, table_vote.orderByChild("house").equalTo(house)) {
            @Override
            protected void populateView(View v, final Vote model, int position) {



                TextView tvAuthor, tvTime, tvText;
                final Button btnAnswer1, btnAnswer2, btnComment;

                tvAuthor = (TextView) v.findViewById(R.id.tvAuthor);
                tvTime = (TextView) v.findViewById(R.id.tvTime);
                tvText = (TextView) v.findViewById(R.id.tvText);

                btnAnswer1 = (Button) v.findViewById(R.id.btnAnswer1);
                btnAnswer2 = (Button) v.findViewById(R.id.btnAnswer2);

                btnComment = (Button) v.findViewById(R.id.btnComment);

                tvCount1 = (TextView) findViewById(R.id.tvCount1);
                tvCount2 = (TextView) findViewById(R.id.tvCount2);

                tvAuthor.setText(model.getAuthor());
                tvTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getTime()));
                tvText.setText(model.getText());

                btnAnswer1.setText(model.getAnswer1());
                btnAnswer2.setText(model.getAnswer2());

                final String voteid = adapter.getRef(position).getKey();

                btnComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent com = new Intent(ActivityVote.this, CommentActivity.class);
                        com.putExtra("House", house);
                        com.putExtra("Role", role);
                        com.putExtra("Street", street);
                        com.putExtra("id", voteid);
                        com.putExtra("name", name);
                        startActivity(com);
                    }
                });

                btnAnswer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //btnAnswer1 = (Button) findViewById(R.id.btnAnswer1);
                        //btnAnswer2 = (Button) findViewById(R.id.btnAnswer2);

                        //tvCount1 = (TextView) findViewById(R.id.tvCount1);
                        //tvCount2 = (TextView) findViewById(R.id.tvCount2);

                        btnAnswer1.setEnabled(false);
                        btnAnswer2.setEnabled(false);

                        //Integer count1 = +1;
                        //btnAnswer1.setText();
                        int a = model.getCount1();
                        //int b = model.getCount1()
                        a = a + 1;
                        //FirebaseDatabase.getInstance().getReference("Vote").push().setValue(new Vote(a, ));
                        table_vote.child(voteid).child("Count1").setValue(a);
                        //model.setCount1(a);
                        tvCount1.setText("Голосов: " + a);

                        //tvCount1.setText("Голосов:" + ++mCount);
                        tvCount2.setText("Голосов:" + model.getCount2());
                    }
                });

                btnAnswer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //tvCount1 = (TextView) findViewById(R.id.tvCount1);
                        //tvCount2 = (TextView) findViewById(R.id.tvCount2);

                        btnAnswer1.setEnabled(false);
                        btnAnswer2.setEnabled(false);

                        int b = model.getCount2();

                        b = b + 1;

                        table_vote.child(voteid).child("Count2").setValue(b);

                        tvCount1.setText("Голосов: " + model.getCount1());
                        tvCount2.setText("Голосов:" + b);

                    }
                });

            }
        };
        listVotes.setAdapter(adapter);

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
            Intent sign = new Intent(ActivityVote.this, SignIn.class);
            startActivity(sign);
        }

        if (item.getItemId() == R.id.menu_message)
        {
            Intent vote = new Intent(ActivityVote.this, ListMessages.class);
            vote.putExtra("House", house);
            vote.putExtra("Name", name);
            startActivity(vote);
        }

        if (item.getItemId() == R.id.menu_vote)
        {
            Intent vote = new Intent(ActivityVote.this, ActivityVote.class);
            vote.putExtra("House", house);
            vote.putExtra("Role", role);
            vote.putExtra("Name", name);
            vote.putExtra("Street", street);
            startActivity(vote);
        }
        return true;
    }


}
