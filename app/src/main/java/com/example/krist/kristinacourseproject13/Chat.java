package com.example.krist.kristinacourseproject13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chat extends AppCompatActivity {

    private RecyclerView mRoomList;
    TextView edtHome, tvStreet, tvHouse;
    EditText edtStreet, edtHouse;
    Button btnAddRoom, btnShowChat;
    String Home = "";
    //private FirebaseListAdapter<Room> adapter;
    private FirebaseRecyclerAdapter<Room, ChatViewHolder> adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_room = database.getReference("Room");

    //Инициализируем Firebase
    /**FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_user = database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Home = getIntent().getStringExtra("House");

        edtHome = (TextView)findViewById(R.id.edtHome);

        edtHome.setText(Home);

    }**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        edtStreet = (EditText) findViewById(R.id.edtStreet);
        edtHouse = (EditText) findViewById(R.id.edtHouse);

        btnAddRoom = (Button) findViewById(R.id.btnAddRoom);

        btnShowChat = (Button)findViewById(R.id.btnShowChat);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_room = database.getReference("Room");

        mRoomList = (RecyclerView)findViewById(R.id.room_list);

        displayChat();

        Home = getIntent().getStringExtra("House");

        getroom(Home);

        //String roomid = firebaseRef.child("Room").orderByChild("House").equalTo(Home).


        btnShowChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home = getIntent().getStringExtra("House");



                table_room.orderByChild("house").equalTo(Home);

                String a = table_room.getKey().toString();

                Intent list = new Intent(Chat.this,ListMessages.class);
                list.putExtra("roomId", table_room.getKey());
                startActivity(list);
            }
        });

        //table_room.orderByChild("key").equalTo(Home);

        //добавление новой комнаты
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Room room = new Room( edtStreet.getText().toString(), edtHouse.getText().toString());
                //table_room.child(edtPhone.getText().toString()).setValue(user);
                //Toast.makeText(Chat.this, "Room added", Toast.LENGTH_SHORT).show();
                //finish();

                EditText input = (EditText) findViewById(R.id.edtStreet);
                EditText input1 = (EditText) findViewById(R.id.edtHouse);

                FirebaseDatabase.getInstance().getReference("Room").push()
                        .setValue(new Room(input.getText().toString(), input1.getText().toString()));
                input.setText("");

                displayChat();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Room, ChatViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Room, ChatViewHolder>(
                Room.class,
                R.layout.item,
                ChatViewHolder.class,
                table_room
        ) {
            @Override
            protected void populateViewHolder(ChatViewHolder viewHolder, Room model, int position) {
                viewHolder.setStreet(model.getStreet());
                viewHolder.setHouse(model.getHouse());
            }
        };
        mRoomList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public TextView tvStreet, tvHouse;

        public ChatViewHolder(View itemView){
            super(itemView);

            tvStreet = (TextView)itemView.findViewById(R.id.tvStreet);
            tvHouse = (TextView)itemView.findViewById(R.id.tvHouse);
            mView = itemView;
        }

        public void setStreet(String Street) {
            TextView tvStreet = (TextView) mView.findViewById(R.id.tvStreet);
            tvStreet.setText(Street);
        }

        public void setHouse(String House) {
            TextView tvHouse = (TextView) mView.findViewById(R.id.tvHouse);
            tvHouse.setText(House);
        }

    }

    private void getroom(String House) {
        table_room.orderByChild("House").equalTo(House).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Room room = dataSnapshot.getValue(Room.class);
                String a = dataSnapshot.getKey();
                String b = room.getStreet();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**private void displayChat() {

        //вывод списка комнат
        ListView listRooms = (ListView) findViewById(R.id.listView);
        adapter = new FirebaseListAdapter<Room>(this, Room.class, R.layout.item, FirebaseDatabase.getInstance().getReference("Room")) {
            @Override
            protected void populateView(View v, Room model, int position) {

                TextView tvStreet, tvHouse;
                tvStreet = (TextView) v.findViewById(R.id.tvStreet);
                tvHouse = (TextView) v.findViewById(R.id.tvHouse);

                tvStreet.setText(model.getStreet());
                tvHouse.setText(model.getHouse());
            }
        };
        listRooms.setAdapter(adapter);
    }**/

    private void displayChat() {
        adapter = new FirebaseRecyclerAdapter<Room, ChatViewHolder>(Room.class,
                R.layout.item,
                ChatViewHolder.class,
                table_room) {
            @Override
            protected void populateViewHolder(ChatViewHolder viewHolder, Room model, int position){
                viewHolder.tvStreet.setText(model.getStreet());
                viewHolder.tvHouse.setText(model.getHouse());
                final Room local = model;

            }

        };

        mRoomList.setAdapter(adapter);

    }





}
