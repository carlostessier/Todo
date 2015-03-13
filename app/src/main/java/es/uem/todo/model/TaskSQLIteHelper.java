package es.uem.todo.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import es.uem.todo.model.TaskContract.TaskEntry;
/**
 * Created by carlosfernandez on 13/03/15.
 */
public class TaskSQLIteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "task.db";
    private static final int DB_VERSION = 1 ;

    private final static String CREATE_TABLE_TASK =
            "CREATE TABLE "+TaskEntry.TASK_TABLE +" ( "+
                    TaskEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    TaskEntry.COLUMN_NAME + " TEXT NOT NULL,"+
                    TaskEntry.COLUMN_DATE + " TEXT,"+
                    TaskEntry.COLUMN_PRIORITY + " INTEGER );"
            ;

    public TaskSQLIteHelper(Context context){
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
