package es.uem.todo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import es.uem.todo.model.Task;
import es.uem.todo.model.TaskDataSource;


public class DetailActivity extends Activity {

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datil);

        TaskDataSource taskDataSource = new TaskDataSource(this);

        task = (Task) getIntent().getExtras().get("task");

        Task taskBD = taskDataSource.readTask(task.getId());


            TextView eNombre = (TextView) findViewById(R.id.nombre);
            TextView eFecha = (TextView) findViewById(R.id.fecha);
            RatingBar rbPrioridad = (RatingBar) findViewById(R.id.ratingBar);
            rbPrioridad.setEnabled(false);

            eNombre.setText(taskBD.getNombre());
            eFecha.setText(taskBD.getFecha());
            rbPrioridad.setRating(taskBD.getPrioridad());


    }



}
