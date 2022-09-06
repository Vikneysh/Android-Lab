package com.example.vikslibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserViewActivity extends AppCompatActivity {
    private ListView userList;
    private DBHandler dbHandler;
    private int pos=0;
    private String selectedUser;
    private ArrayAdapter adapter;
    private ArrayList users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        dbHandler = new DBHandler(UserViewActivity.this);
        userList=findViewById(R.id.listView);
        users=dbHandler.getAllUsers();
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,users);
        userList.setAdapter(adapter);
        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedUser= (String) adapterView.getItemAtPosition(i);
                return false;
            }
        });
        registerForContextMenu(userList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_context_menu, menu);
        menu.setHeaderTitle("Select Action");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.viewBooksTaken){
            String userId=selectedUser.substring(0,1);
            dbHandler.getBooksLentToUser(userId);
            Toast.makeText(getApplicationContext(),"Displaying Books Lent to User", Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.delete){
            String userId=selectedUser.substring(0,1);
            dbHandler.deleteUser(userId);
            finish();
            startActivity(getIntent());
            Toast.makeText(getApplicationContext(),"User Deleted",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }

}
