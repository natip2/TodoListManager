package il.ac.huji.todolist;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Date;

public class TodoListManagerActivity extends ActionBarActivity {
    private DBHelper helper;
    private static final int ADD_REQUEST = 42;
    private ArrayAdapter<ItemOfTodo> adapterTodo;
    ArrayList<ItemOfTodo> theList = new ArrayList<ItemOfTodo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new  DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);

        //set the adapter
        ListView list = (ListView) findViewById(R.id.lstTodoItems);
        adapterTodo = new CustomAdapter(this, R.layout.todo_and_date_layout, theList);
        list.setAdapter(adapterTodo);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            //return true iff has phone number
            private boolean hasCallFunction(String str){
                return (str.toLowerCase().startsWith("call "));
            }
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, final long id) {
            final AlertDialog.Builder b = new AlertDialog.Builder(TodoListManagerActivity.this);
            b.setIcon(android.R.drawable.ic_dialog_alert);
            final int positionToRemove = pos;
            String data =  theList.get(pos).getTxt();
            b.setMessage(data);
            b.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    theList.remove(positionToRemove);
                    adapterTodo.notifyDataSetChanged();
                }
            });
            String msg = theList.get(pos).getTxt();
            if (hasCallFunction(msg)){
                b.setNegativeButton("call", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String num = theList.get(positionToRemove).getTxt();
                        num = num.substring(4);
                        Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+num));
                        startActivity(dial);


                    }
                });
            }
            b.show();
            return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_list_manager, menu);
//        Intent intent = new Intent(getApplicationContext(), AddNewTodoItemActivity.class);
//        startActivityForResult(intent,ADD_REQUEST);
        return true;
    }

    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        if (reqCode == ADD_REQUEST) {
            if(resCode == RESULT_OK){
                String stringText = data.getStringExtra("data");
                Date date = (Date) data.getSerializableExtra("date");
                if (!stringText.equals("")) {
                    theList.add(new ItemOfTodo(stringText,date));
                    adapterTodo.notifyDataSetChanged();
                }
            }
            if (resCode == RESULT_CANCELED) {
                //add item canceled
            }
        }

    }

    @Override //if clicked on the add button
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_settings) {
            Intent intent = new Intent(this, AddNewTodoItemActivity.class);
            startActivityForResult(intent,ADD_REQUEST);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
