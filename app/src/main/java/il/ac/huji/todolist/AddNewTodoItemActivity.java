package il.ac.huji.todolist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

//example
public class AddNewTodoItemActivity extends Activity {

    void oKClickPushed(){
        Intent returnIntent = new Intent();
        //get the date
        DatePicker date = (DatePicker) this.findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, date.getYear());
        cal.set(Calendar.MONTH, date.getMonth());
        cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
        Date dateObject = cal.getTime();
        returnIntent.putExtra("date", dateObject);
        //get the string
        EditText string = (EditText) this.findViewById(R.id.edtNewItem);
        String s = string.getText().toString();
        returnIntent.putExtra("data", s);
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    void cancelBottomClicked(){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo_item);
        //ok click listener
        final Button ok = (Button) this.findViewById(R.id.btnOK);
        ok.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      oKClickPushed();
                                  }
                              }
        );

        //cancel click listener
        Button cancel = (Button) this.findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      cancelBottomClicked();
                                  }
                              }
        );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_todo_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
