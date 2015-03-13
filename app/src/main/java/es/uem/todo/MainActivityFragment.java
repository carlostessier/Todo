package es.uem.todo;

/**
 * Created by carlosfernandez on 12/03/15.
 */

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import es.uem.todo.model.Task;
import es.uem.todo.model.TaskDataSource;

/**
 * A placeholder fragment containing a simple view.
 */
public  class MainActivityFragment extends ListFragment {

    ArrayList<Task> mTareas;
    ArrayAdapter<Task> adaptador;
    // miembro para gestionar la BBDD
    TaskDataSource taskDataSource;

    private static final int SAVE_RESULT_CODE = 1;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        taskDataSource = new TaskDataSource(getActivity());

        setHasOptionsMenu(true);

        // leer las tareas de la base de datos
        mTareas = taskDataSource.readAllTasks();


        adaptador = new ArrayAdapter<Task>(
                getActivity(),
                android.R.layout.simple_list_item_1,
        mTareas);

        setListAdapter(adaptador);


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;
        }
        if (id == R.id.add) {
            Intent intent = new
                    Intent(getActivity(),
                    AddTask.class);
            startActivityForResult(intent,
            SAVE_RESULT_CODE);

            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SAVE_RESULT_CODE){
            if(resultCode==getActivity().RESULT_OK){
                Task task = (Task) data.getExtras().get("TASK");
                // guardalo en la BBDD
                long id = taskDataSource.saveTask(task);
                task.setId((int)id);
                adaptador.add(task);

            }
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(
                getActivity(),DetailActivity.class);

        intent.putExtra("task",mTareas.get(position));

        startActivity(intent);


    }
}
