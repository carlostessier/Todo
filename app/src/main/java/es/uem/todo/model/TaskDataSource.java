package es.uem.todo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import es.uem.todo.model.TaskContract.TaskEntry;

/**
 * Created by carlosfernandez on 13/03/15.
 */
public class TaskDataSource {

    private TaskSQLIteHelper mTaskSQLIteHelper;
    private Context mContext;

    public TaskDataSource(Context context){
        mContext = context;
        mTaskSQLIteHelper = new TaskSQLIteHelper(context);
    }

    public SQLiteDatabase openReadable(){
       return mTaskSQLIteHelper.getReadableDatabase();
    }

    public SQLiteDatabase openWritable(){
        return mTaskSQLIteHelper.getWritableDatabase();
    }

    public void closeDatabase(){
        mTaskSQLIteHelper.close();
    }

    public long saveTask(Task task){
        SQLiteDatabase database = openWritable();
        database.beginTransaction();
        ContentValues taskValues = new ContentValues();
        taskValues.put(TaskEntry.COLUMN_NAME,task.getNombre());
        taskValues.put(TaskEntry.COLUMN_DATE,task.getFecha());
        taskValues.put(TaskEntry.COLUMN_PRIORITY,task.getPrioridad());

        long taskId = database.insert(TaskEntry.TASK_TABLE,
                null,taskValues);
        database.setTransactionSuccessful();
        database.endTransaction();
        closeDatabase();

        return taskId;

    }

    public  Task readTask(int id){
        SQLiteDatabase database = openReadable();
        Task task = new Task();
        Cursor cursor = database.query(
                TaskEntry.TASK_TABLE,
                new String[] {
                        TaskEntry.COLUMN_ID,
                        TaskEntry.COLUMN_NAME,
                        TaskEntry.COLUMN_DATE,
                        TaskEntry.COLUMN_PRIORITY},
                String.format("%s=%d",TaskEntry.COLUMN_ID,id),
                null,null,null,null);
/*
         Cursor cursor2 = database.rawQuery("" +
                "SELECT * FROM "+TaskEntry.TASK_TABLE +
                "WHERE "+TaskEntry.COLUMN_ID+" = ?",
                 new String[]{Integer.toString(id)});

                    Cursor cursor3 = database.rawQuery("" +
                "SELECT * FROM "+TaskEntry.TASK_TABLE +
                "WHERE "+TaskEntry.COLUMN_ID+" = "+id,
                 null);
*/
        if(cursor.moveToFirst()){

            String nombre =getStringFromColumnName(cursor,
                    TaskEntry.COLUMN_NAME);
            String fecha = getStringFromColumnName(cursor,
                    TaskEntry.COLUMN_DATE);
            int prioridad =getIntFromColumnName(cursor,
                    TaskEntry.COLUMN_PRIORITY);

             task = new Task(id,prioridad,nombre,fecha);

        }
        return task;
    }

    public ArrayList<Task> readAllTasks(){
        SQLiteDatabase database = openReadable();

        ArrayList<Task> tasks = new ArrayList<>();
        /*
        Cursor cursor = database.query(
                TaskEntry.TASK_TABLE,
                new String[] {
                        TaskEntry.COLUMN_ID,
                        TaskEntry.COLUMN_NAME,
                        TaskEntry.COLUMN_DATE,
                        TaskEntry.COLUMN_PRIORITY},
                null,null,null,null,null);

        */
        Cursor cursor = database.rawQuery("" +
                "SELECT * FROM "+TaskEntry.TASK_TABLE,null);

        if(cursor.moveToFirst()){
            do{
                int id = getIntFromColumnName(cursor,
                        TaskEntry.COLUMN_ID);
                String nombre =getStringFromColumnName(cursor,
                        TaskEntry.COLUMN_NAME);
                String fecha = getStringFromColumnName(cursor,
                        TaskEntry.COLUMN_DATE);
                int prioridad =getIntFromColumnName(cursor,
                        TaskEntry.COLUMN_PRIORITY);

                Task task = new Task(id,prioridad,nombre,fecha);
                tasks.add(task);

                }
            while(cursor.moveToNext());
        }

        cursor.close();
        closeDatabase();

        return tasks;
    }

    private int getIntFromColumnName(Cursor cursor,
                                     String column){
        int columnIndex = cursor.getColumnIndex(column);
        return cursor.getInt(columnIndex);
    }

    private String getStringFromColumnName(Cursor cursor,
                                           String column){
        int columnIndex = cursor.getColumnIndex(column);
        return cursor.getString(columnIndex);
    }



}
