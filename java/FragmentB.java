package com.apps.frontendsiswa.fitursiswa.rekomendasi;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.frontendsiswa.R;
import com.apps.frontendsiswa.api.APIRequestData;
import com.apps.frontendsiswa.api.RetroServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.apps.frontendsiswa.fitursiswa.rekomendasi.FragmentActivity.ambilLatitude;
import static com.apps.frontendsiswa.fitursiswa.rekomendasi.FragmentActivity.ambilLongitude;


public class FragmentB extends Fragment {
    private String KEY_NAMELATITUDE = "AMBILLATITUDE";
    private String KEY_NAMELONGITUDE = "AMBILLONGITUDE";
    private String KEY_ID = "AMBILID";

//    private String KEY_TITLE = "AMBILTITLE";
    TextView tarikData;
    Toolbar toolbar;
//    private String ambilLatitude, ambilLongitude;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterRekomendasi adapterRekomendasi;
    private List<Rekomendasi> rekomendasiList;
    APIRequestData apiRequestData;
    ProgressBar progressBar;
    AdapterRekomendasi.RecyclerViewClickListener listener;


//    RecyclerView recyclerView;
//    ListView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_rekomendasi, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        String[] items = getResources().getStringArray(R.array.tab_A);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(items);
//        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);

//        Bundle extras = getActivity().getIntent().getExtras();
//        ambilLatitude = extras.getString(KEY_NAMELATITUDE);
//        ambilLongitude = extras.getString(KEY_NAMELONGITUDE);

//        if (getArguments() != null) {
//            ambilLatitude = getArguments().getString("KEY_NAMELATITUDE");
//            ambilLongitude = getArguments().getString("KEY_NAMELONGITUDE");
//        }

//        progressBar = view.findViewById(R.id.progressbar);
        apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        recyclerView = view.findViewById(R.id.recycler_view);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        Call<List<Rekomendasi>> call = apiRequestData.getRekomendasi(ambilLatitude, ambilLongitude, "TKJ");
        call.enqueue(new Callback<List<Rekomendasi>>() {
            @Override
            public void onResponse(Call<List<Rekomendasi>> call, Response<List<Rekomendasi>> response) {
//                progressBar.setVisibility(View.GONE);
                rekomendasiList = response.body();
//                Log.i(RekomendasiActivity.class.getSimpleName(), response.body().toString());
                adapterRekomendasi = new AdapterRekomendasi(rekomendasiList, getActivity(), (AdapterRekomendasi.RecyclerViewClickListener) listener);
                recyclerView.setAdapter(adapterRekomendasi);
                adapterRekomendasi.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Rekomendasi>> call, Throwable t) {
                Toast.makeText(getActivity(), "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        listener = new AdapterRekomendasi.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DirectionMaps.class);
                intent.putExtra(KEY_NAMELATITUDE, rekomendasiList.get(position).getAlamat_lat() );
                intent.putExtra(KEY_NAMELONGITUDE, rekomendasiList.get(position).getAlamat_lng());
                intent.putExtra(KEY_ID, rekomendasiList.get(position).getId_rekomendasi());

                startActivity(intent);

            }
        };



    }
}