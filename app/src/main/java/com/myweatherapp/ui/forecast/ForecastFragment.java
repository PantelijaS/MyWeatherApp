package com.myweatherapp.ui.forecast;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myweatherapp.Common;
import com.myweatherapp.R;
import com.myweatherapp.adapter.ForecastAdapter;
import com.myweatherapp.model.Forcast;
import com.myweatherapp.remote.APIService;
import com.myweatherapp.remote.RestApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ForecastFragment extends Fragment {

    private ForecastViewModel forcastViewModel;

    CompositeDisposable compositeDisposable;
    APIService apiService;

    private Unbinder unbinder;

    @BindView(R.id.recycleview_forcast)
    public RecyclerView recyclerView;

    private ForecastAdapter forcastAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forcastViewModel = ViewModelProviders.of(this).get(ForecastViewModel.class);
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        unbinder = ButterKnife.bind(this, view);

        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RestApiClient.getInstance();
        apiService = retrofit.create(APIService.class);

        getForcastFragment();

        return view;
    }

    private void getForcastFragment() {
        compositeDisposable.add(apiService.getForcastModel("Belgrade",String.valueOf(Common.API_ID),"metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Forcast>() {
                    @Override
                    public void accept(Forcast forcast) throws Exception {
                        forcastAdapter = new ForecastAdapter(getActivity(),forcast);
                        recyclerView.setAdapter(forcastAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    Log.e("error",throwable.getMessage());
                    }
                }));
    }

    // When binding a fragment in onCreateView, set the views to null in onDestroyView.
    // ButterKnife returns an Unbinder on the initial binding that has an unbind method to do this automatically.
    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
