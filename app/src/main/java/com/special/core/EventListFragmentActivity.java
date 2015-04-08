package com.special.core;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.special.R;
import com.special.messageDefinition.Event.GetEventRequest;
import com.special.utils.Utilities;

//import com.special.messageDefinition.Event.Event.GetEventRequest;

/**
 * Created by CrimsonKing on 7.4.2015.
 */
public class EventListFragmentActivity extends Fragment {

    private View parentView;
    private ListView listView;
    private Utilities utilities;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.eventr_intheaters_list, container, false);
        listView = (ListView) parentView.findViewById(R.id.listView);

        utilities = new Utilities();
        utilities.showLoadingPanel(getActivity());

        //ApiConnector<GetEventRequest,GetEventResponse> apiConnector = new ApiRequest();

        GetEventRequest request = new GetEventRequest();
        request.Id = 12;


        /*apiConnector.Fetch("GetEvent", request, new ApiCallBack<GetEventResponse>() {
            @Override
            public void onComplete(GetEventResponse result) {

            }
        });*/


        return parentView;
    }
}
