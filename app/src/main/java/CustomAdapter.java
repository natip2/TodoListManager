///**
// * Created by natip2 on 16/03/2015.
// */
//import android.content.Context;
//import android.graphics.Color;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//
//import java.util.List;
//
//public class CustomAdapter extends ArrayAdapter<String> {
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = super.getView(position, convertView, parent);
//        if (position % 2 == 1) {
//            view.setBackgroundColor(Color.BLUE);
//        } else {
//            view.setBackgroundColor(Color.CYAN);
//        }
//
//        return view;
//    }
//
//
//    public CustomAdapter(Context context, int resource) {
//        super(context, resource);
//    }
//}
