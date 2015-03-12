package es.uem.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;


public class AddTask extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }



    public void save(View view){

        EditText nombre = (EditText)
              findViewById(R.id.nombre);

        EditText fecha = (EditText)
                findViewById(R.id.fecha);

        RatingBar rPrioridad =(RatingBar)
                findViewById(R.id.prioridad);
        try {
            String sNombre = nombre.getText().toString();
            String sFecha = fecha.getText().toString();
            int prioridad = (int) rPrioridad.getRating();

            Task task = new Task(prioridad, sNombre, sFecha);
            Intent intent = new Intent();
            intent.putExtra("TASK",task);
            setResult(RESULT_OK,intent);

        } catch(Exception e){
            setResult(-1);
        }

        finish();

    }
}
