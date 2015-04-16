package il.ac.huji.todolist; /**
* Created by natip2 on 16/03/2015.
*/
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import il.ac.huji.todolist.ItemOfTodo;
import il.ac.huji.todolist.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<ItemOfTodo> {

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.todo_and_date_layout,parent,false);

        TextView txtView =(TextView) view.findViewById(R.id.titleId);
        TextView dateView = (TextView) view.findViewById(R.id.dateId);
        ItemOfTodo item = getItem(position);
        txtView.setText(item.getTxt());
        if (item.getTime() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dateView.setText(sdf.format(item.getTime()));
            if (isPassed(item.getTime())){
                txtView.setTextColor(Color.RED);
                dateView.setTextColor(Color.RED);
            }
        }
        else{
            dateView.setText("no due date");
        }

        return view;
    }
    //return true iff date is pass
    private boolean isPassed(Date d){
        Calendar todayDate = Calendar.getInstance();
        todayDate.set(Calendar.HOUR_OF_DAY, 0);
        todayDate.set(Calendar.MINUTE, 0);
        todayDate.set(Calendar.SECOND, 0);
        todayDate.set(Calendar.MILLISECOND, 0);
        Calendar dueCal = Calendar.getInstance();
        dueCal.setTime(d);
        return dueCal.before(todayDate);
    }


    public CustomAdapter(Context context, int resource,ArrayList<ItemOfTodo> arr) {

        super(context, resource,arr);

    }
}
