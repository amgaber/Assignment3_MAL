package com.mal.android.assignmentthree;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mal.android.assignmentthree.model.Facebook;
import com.google.gson.Gson;
import com.mal.android.assignmentthree.AsyncTaskListener;
import com.mal.android.assignmentthree.FacebookAdapter;
import com.mal.android.assignmentthree.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainListActivityFragment extends Fragment implements AsyncTaskListener {

    private static final String TAG = MainListActivityFragment.class.getSimpleName();
    private RecyclerView facebookRecyclerView;
    private LinearLayoutManager facebookLayoutManager;

    //Initializing arraylist for facebook data
    private List<Facebook> facebookDataList = new ArrayList<Facebook>();

    private FacebookAdapter facebookAdapter;

    public MainListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_list, container, false);
        facebookRecyclerView = (RecyclerView) v.findViewById(R.id.facebook_recycler_view);

        // the layout size of the RecyclerView
        facebookRecyclerView.setHasFixedSize(true);

        // using a linear layout manager
        facebookLayoutManager = new LinearLayoutManager(getActivity());
        facebookRecyclerView.setLayoutManager(facebookLayoutManager);


        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomAsyncTask customAsyncTask = new CustomAsyncTask(this);
        customAsyncTask.execute();
    }

    @Override
    public void notifyUpdate(String data) {
        Log.v(TAG, "Implementing Interface to use AsyncTASK: " + data);
        facebookDataList.clear();

        //Initialize arrayList if null
        if (null == facebookDataList) {
            facebookDataList = new ArrayList<Facebook>();
        }


        try {
            JSONArray jsonArray=new JSONArray(data);
            for (int k =0; k < jsonArray.length();k++) {
                JSONObject face=new JSONObject(jsonArray.get(k).toString());
                Gson gson=new Gson();
                Facebook facebook = gson.fromJson(String.valueOf(face), Facebook.class);
                facebookDataList.add(facebook);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Sending data to the adapter
        facebookAdapter = new FacebookAdapter(getActivity(),facebookDataList);
        // using an adapter
        facebookRecyclerView.setAdapter(facebookAdapter);


    }

    public void message(String data) {
        Toast.makeText(getActivity(), "There is something wrong in parsing: " + data, Toast.LENGTH_LONG).show();
    }
}
