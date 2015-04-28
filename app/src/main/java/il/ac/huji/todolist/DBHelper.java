package il.ac.huji.todolist;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "todo_db", null, 1);
    }

    public Cursor delete(long rowId) {
        getWritableDatabase().delete("todolist", "_id = " + rowId, null);
        return getWritableDatabase().query("todolist", null, null, null, null, null, null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table todolist ( _id integer primary key autoincrement, title text, due long );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor insert(String title, Date dueDate) {
        ContentValues newTodoItem = new ContentValues();
        newTodoItem.put("title", title);
        newTodoItem.put("due", dueDate.getTime());

        getWritableDatabase().insert("todolist", null, newTodoItem);
        return getWritableDatabase().query("todolist", null, null, null, null, null, null);
    }
}