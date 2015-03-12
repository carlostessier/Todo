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

/**
 * A placeholder fragment containing a simple view.
 */
public  class MainActivityFragment extends ListFragment {

    ArrayList<Task> mTareas;
    ArrayAdapter<Task> adaptador;

    private static final int SAVE_RESULT_CODE = 1;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        setHasOptionsMenu(true);

        mTareas = new ArrayList<>();


        adaptador = new ArrayAdapter<Task>(
                getActivity(),android.R.layout.simple_list_item_1,
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
                adaptador.add(task);

            }
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

    }
}
