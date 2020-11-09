package com.ea.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ea.Adapter.CarsAdapter;
import com.ea.Interface.MainInterface;
import com.ea.Model.Car;
import com.ea.Model.EmirateAuctionCarsResponse;
import com.ea.Presenter.MainPresenter;
import com.ea.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends BaseActivity implements MainInterface {

    private MainPresenter presenter;
    private SwipeRefreshLayout refresh;
    private RecyclerView list;
    private RelativeLayout progress;
    private ArrayList<Car> cars = new ArrayList<>();
    private CarsAdapter adapter;
    private ShimmerFrameLayout shimmer;
    private LinearLayout grid, sort;
    private Handler myHandler = new Handler();
    private int refreshInterval = 60000;
    private String ticks = "0";
    private ArrayList<String> sortOptions = new ArrayList<String>();
    private CharSequence[] charSequenceItems;
    private boolean isRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        init();
        setListener();
        setDialogOptions();
    }

    private void setDialogOptions() {
        sortOptions.add("Sort by Year");
        sortOptions.add("Sort by End Date");
        sortOptions.add("Sort by Price");
        sortOptions.add("English");
        sortOptions.add("Arabic");
        charSequenceItems = sortOptions.toArray(new CharSequence[sortOptions.size()]);
    }

    private void setListener() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                presenter.getCars(ticks);
            }
        });
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void bindViews() {
        list = findViewById(R.id.car_list);
        refresh = findViewById(R.id.refresh);
        shimmer = findViewById(R.id.shimmer);
        grid = findViewById(R.id.grid);
        sort = findViewById(R.id.sort);
        progress = findViewById(R.id.progress);
    }

    private void init() {
        presenter = new MainPresenter(this, this);
        presenter.getCars(ticks);
        adapter = new CarsAdapter(this, cars);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        list.setLayoutManager(manager);
        list.setAdapter(adapter);
        myHandler.postDelayed(new Runnable() {
            public void run() {
                presenter.getCars(ticks);
                myHandler.postDelayed(this, refreshInterval);
            }
        }, refreshInterval);
    }

    @Override
    public void success(EmirateAuctionCarsResponse carsResponse, String Ticks) {
        if (carsResponse != null) {
            refreshInterval = carsResponse.getRefreshInterval() * 1000;
            ticks = carsResponse.getTicks();

            if (!TextUtils.isEmpty(Ticks) && Ticks.equalsIgnoreCase("0")) {
                //First Time Case
                cars.clear();
                cars.addAll(carsResponse.getCars());
            } else {
                if (carsResponse.getCars() != null && carsResponse.getCars().size() > 0) {
                    for (Car updatedCar : carsResponse.getCars()) {
                        for (Car localCar : cars) {
                            if (updatedCar.getCarID() == localCar.getCarID()) {
                                localCar = updatedCar;
                                localCar.setUpdate(true);
                            }
                        }
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, "Error : " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void progress(boolean flag) {
        if (flag) {
            if (!isRefresh)
                progress.setVisibility(View.VISIBLE);
        } else {
            refresh.setRefreshing(false);
            shimmer.stopShimmer();
            shimmer.setVisibility(View.GONE);
            progress.setVisibility(View.GONE);
            isRefresh = false;
        }
    }

    private void LTR() {
        //ENGLISH
        for (Car car : cars) {
            car.setEn(true);
        }
        adapter.notifyDataSetChanged();
    }

    private void RTL() {
        //ARABIC
        for (Car car : cars) {
            car.setEn(false);
        }
        adapter.notifyDataSetChanged();
    }

    private void showDialog() {
        new MaterialAlertDialogBuilder(this)
                .setItems(charSequenceItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                //sort year wise
                                Collections.sort(cars, new Comparator<Car>() {
                                    public int compare(Car o1, Car o2) {
                                        return o1.getYear().compareTo(o2.getYear());
                                    }
                                });
                                adapter.notifyDataSetChanged();
                                break;
                            case 1:
                                //sort date wise
                                Collections.sort(cars, new Comparator<Car>() {
                                    public int compare(Car o1, Car o2) {
                                        return o1.getAuctionInfo().getEndDate().compareTo(o2.getAuctionInfo().getEndDate());
                                    }
                                });
                                adapter.notifyDataSetChanged();
                                break;
                            case 2:
                                //sort price wise
                                Collections.sort(cars, new Comparator<Car>() {
                                    public int compare(Car o1, Car o2) {
                                        return o1.getAuctionInfo().getCurrentPrice().compareTo(o2.getAuctionInfo().getCurrentPrice());
                                    }
                                });
                                adapter.notifyDataSetChanged();
                                break;
                            case 3:
                                // LTR
                                LTR();
                                break;
                            case 4:
                                // RTL
                                RTL();
                                break;
                        }
                        dialog.dismiss();
                    }
                }).show();
    }

}