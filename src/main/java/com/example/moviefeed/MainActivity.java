package com.example.moviefeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.moviefeed.di.App;
import com.example.moviefeed.movies.ListAdapter;
import com.example.moviefeed.movies.MoviesMVP;
import com.example.moviefeed.movies.ViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviesMVP.View {

    private final String TAG = MainActivity.class.getName();
    private ListAdapter listaAdapter;
    private List<ViewModel> resultList = new ArrayList<>();

    @BindView(R.id.activity_root_view)
    LinearLayout rootView;

    @BindView(R.id.recycler_view_movies)
    RecyclerView reclycledView;

    @Inject
    MoviesMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);

        listaAdapter = new ListAdapter(resultList);
        reclycledView.setAdapter(listaAdapter);
        reclycledView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        reclycledView.setItemAnimator(new DefaultItemAnimator());
        reclycledView.setHasFixedSize(true);
        reclycledView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxJavaUnsuscribe();
        resultList.clear();
        listaAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateData(ViewModel viewModel) {
        resultList.add(viewModel);
        listaAdapter.notifyItemChanged(  (resultList.size()-1) );
        Log.e(TAG, viewModel.getName());
    }

    @Override
    public void showSnackbar(String s) {
        Snackbar.make(rootView, s, Snackbar.LENGTH_SHORT).show();
    }
}
