package com.example.menudemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
    int pos=0;
    String contacts[]={"Ajit","Jebas","Mani","Priyan","Vikneysh"};
    ArrayList<String> cont= new ArrayList<>(Arrays.asList(contacts));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,cont);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos=i;
                return false;
            }
        });
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        menu.setHeaderTitle("Select Action");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.edit){
            SparseBooleanArray checkedNames=listView.getCheckedItemPositions();
            if(checkedNames.size()==0){
                Toast.makeText(getApplicationContext(),"No item selected!", Toast.LENGTH_LONG).show();
            }
            if(checkedNames.size()>1){
                Toast.makeText(getApplicationContext(),"Select only one item to edit!", Toast.LENGTH_LONG).show();
                checkedNames.clear();
            }
            if(checkedNames.size()==1){
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Edit");
                alert.setMessage("Enter new Name: ");
                final EditText input = new EditText(this);
                alert.setView(input);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        cont.set(checkedNames.keyAt(0),value);
                        adapter.notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });

                alert.show();
                Toast.makeText(getApplicationContext(),"Selected", Toast.LENGTH_LONG).show();
                checkedNames.clear();

            }
        }
        if(item.getItemId()==R.id.delete) {
            SparseBooleanArray checkedNames=listView.getCheckedItemPositions();
            if(checkedNames.size()==0){
                Toast.makeText(getApplicationContext(),"No item selected!", Toast.LENGTH_LONG).show();
            }
            if(checkedNames.size()>0){
                ArrayList<Integer> arr=new ArrayList<>();
                for(int i=checkedNames.size()-1;i>=0;i--){
                    arr.add(checkedNames.keyAt(i));
                    cont.remove(checkedNames.keyAt(i));
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"The Selected items have been deleted", Toast.LENGTH_LONG).show();
                checkedNames.clear();
            }

        }
        if(item.getItemId()==R.id.selectAll){
            for(int i=0;i<cont.size();i++)
                listView.setItemChecked(i,true);
        }
        if(item.getItemId()==R.id.unselectAll){
            for(int i=0;i<cont.size();i++)
                listView.setItemChecked(i,false);
        }
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.cut){
            cont.remove(pos);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(),"The selected element has been cut", Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.copy){
            Toast.makeText(getApplicationContext(),"Copied!",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;
    }
}