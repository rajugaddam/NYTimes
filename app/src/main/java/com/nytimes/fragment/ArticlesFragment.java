package com.nytimes.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.nytimes.NYTimesApplication;
import com.nytimes.NYTimesArticleList;
import com.nytimes.R;
import com.nytimes.adapter.ArticlesAdapter;
import com.nytimes.model.Response;
import com.nytimes.network.NetworkError;
import com.nytimes.network.Service;
import com.nytimes.utils.NYTimesProgressDialog;

import javax.inject.Inject;



public class ArticlesFragment extends Fragment {
    @Inject
    Service service;
    private NYTimesProgressDialog nyTimesProgressDialog;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private NYTimesApplication application;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         /* injecting dependency */
        application = (NYTimesApplication) getActivity().getApplication();
        (application).getAppComponent().inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_list, container, false);
        recyclerView =  view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        nyTimesProgressDialog = new NYTimesProgressDialog(getActivity());
        nyTimesProgressDialog.showDialog();

        service.getBaseURL(new Service.ResponseCallback<Response>() {
            @Override
            public void onSuccess(Response response) {
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);

                mAdapter = new ArticlesAdapter(response, (NYTimesArticleList) getActivity(), getFragmentManager());
                recyclerView.setAdapter(mAdapter);
                nyTimesProgressDialog.dismissDialog();
            }

            @Override
            public void onError(NetworkError networkError) {
                nyTimesProgressDialog.dismiss();
                Toast.makeText(getActivity(), " Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
