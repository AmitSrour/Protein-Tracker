package il.co.amits.proteintracker.food;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import il.co.amits.proteintracker.R;


/**
 * Created by Administrator on 19/03/2016.
 */
public class ItemListFragment extends ListFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //    View view = inflater.inflate(R.layout.fragment_food_list, container, false);
    //   return view;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1,ResourceHelper.NAMES);

        /** Setting the list adapter for the ListFragment */
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);

    }
    //@Override
  //  public void onActivityCreated(Bundle savedInstanceState) {
    //    super.onActivityCreated(savedInstanceState);
    //    ArrayList<String> listNames = new ArrayList<String>(Arrays.asList(ResourceHelper.NAMES));

        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), android.R.layout.simple_list_item_1,listNames);
     //   ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,listNames);
     //   setListAdapter(adapter);
       // getListView().setOnItemClickListener(this);
   // }

   // @Override
  //  public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
   //     Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
   // }

}
