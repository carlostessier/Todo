package es.uem.todo.model;

import android.provider.BaseColumns;

/**
 * Created by carlosfernandez on 13/03/15.
 */
public class TaskContract {

    public static abstract class TaskEntry implements BaseColumns {

        public final static String TASK_TABLE = "task";
        public final static String COLUMN_ID = "_id";
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_DATE = "date";
        public final static String COLUMN_PRIORITY = "priority";

    }
}
