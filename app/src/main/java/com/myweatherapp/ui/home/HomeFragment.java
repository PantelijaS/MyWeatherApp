package com.myweatherapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.myweatherapp.units.Common;
import com.myweatherapp.R;
import com.myweatherapp.model.WeatherModel;
import com.myweatherapp.remote.APIService;
import com.myweatherapp.remote.RestApiClient;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    @BindView(R.id.img_weather)
    ImageView img_weather;
    @BindView(R.id.text_city_name)
    TextView text_city_name;
    @BindView(R.id.text_city_time)
    TextView text_city_time;
    @BindView(R.id.text_city_temperature)
    TextView text_city_temperature;
    @BindView(R.id.text_city_description)
    TextView text_city_description;
    @BindView(R.id.text_city_wind)
    TextView text_city_wind;
    @BindView(R.id.text_city_pressure)
    TextView text_city_pressure;
    @BindView(R.id.text_city_humidity)
    TextView text_city_humidity;
    @BindView(R.id.text_city_sunrise)
    TextView text_city_sunrise;
    @BindView(R.id.text_city_sunset)
    TextView text_city_sunset;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private Unbinder unbinder;

    private HomeViewModel homeViewModel;

    CompositeDisposable compositeDisposable;
    APIService apiService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, homeView);

        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RestApiClient.getInstance();
        apiService = retrofit.create(APIService.class);

        getWeather();

        return homeView;
    }

    // When binding a fragment in onCreateView, set the views to null in onDestroyView.
    // ButterKnife returns an Unbinder on the initial binding that has an unbind method to do this automatically.
    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void getWeather(){

        compositeDisposable.add(apiService.getWeatherModel("Belgrade",String.valueOf(Common.API_ID),"metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherModel>() {
                    @Override
                    public void accept(WeatherModel weatherModel) throws Exception {
                        //load icon
                        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/wn/")
                                .append(weatherModel.weather.get(0).icon)
                                .append(".png").toString()).into(img_weather);

                        text_city_name.setText(weatherModel.name);
                        text_city_time.setText(Common.convertToDate(weatherModel.dt));
                        text_city_temperature.setText(new StringBuilder(String.valueOf(weatherModel.main.temp)).append(" °C"));
                        text_city_description.setText(new StringBuilder(weatherModel.weather.get(0).description).toString());
                        text_city_pressure.setText(new StringBuilder(String.valueOf(weatherModel.main.pressure)).append(" hpa"));
                        text_city_humidity.setText(new StringBuilder(String.valueOf(weatherModel.main.humidity)).append(" %"));
                        text_city_sunrise.setText(Common.convertToTime(weatherModel.sys.sunrise));
                        text_city_sunset.setText(Common.convertToTime(weatherModel.sys.sunset));
                        text_city_wind.setText(new StringBuffer(String.valueOf(weatherModel.wind.speed)).append("km/h"));
                        progressBar.setVisibility(View.GONE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("Message", throwable.getMessage());
                        Toast.makeText(getActivity(), ""+throwable.getMessage(),Toast.LENGTH_LONG);
                    }
                }));
    }
}
