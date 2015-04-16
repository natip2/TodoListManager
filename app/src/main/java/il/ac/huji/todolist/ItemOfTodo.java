package il.ac.huji.todolist;

import java.util.Date;

/**
 * Created by natip2 on 27/03/2015.
 */
public class ItemOfTodo {
    public ItemOfTodo(String txt, Date time){
        this.time = time;
        this.txt = txt;
    }
    public String getTxt(){
        return txt;
    }

    public Date getTime(){
        return time;
    }
    private String txt;
    private Date time;
}
