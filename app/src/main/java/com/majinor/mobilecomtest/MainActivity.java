package com.majinor.mobilecomtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.majinor.mobilecomtest.apihelper.ApiInterface;
import com.majinor.mobilecomtest.apihelper.UtilsApi;
import com.majinor.mobilecomtest.model.ResponseCity;
import com.majinor.mobilecomtest.model.ResponseProvince;
import com.majinor.mobilecomtest.model.SemuacityItem;
import com.majinor.mobilecomtest.model.SemuaprovinceItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerProvince, spinnerCity;
    EditText etNamaMatkul;
    Button btnSimpanMatkul;
    String prov,city;
    ProgressDialog loading;

    Context mContext;
    ApiInterface mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinnerProvince = (Spinner) findViewById(R.id.spin_province);
        spinnerCity = (Spinner) findViewById(R.id.spin_city);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        initSpinnerProvince();

        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedName = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerProvince() {

        mApiService.getSemuaProvince().enqueue(new Callback<ResponseProvince>() {
            @Override
            public void onResponse(Call<ResponseProvince> call, Response<ResponseProvince> response) {
                if (response.isSuccessful()) {
                    List<SemuaprovinceItem> semuaprovinceItems = response.body().getValue();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < semuaprovinceItems.size(); i++){
                        prov = semuaprovinceItems.get(i).getProvinceName();
                        listSpinner.add(semuaprovinceItems.get(i).getProvinceID());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerProvince.setAdapter(adapter);

                    initSpinnerCity();


                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Province", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseProvince> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerCity() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ProvinceID", prov);
        Call<ResponseCity> calls = mApiService.getSemuaCity(jsonObject);
        //calling the api
        calls.enqueue(new Callback<ResponseCity>() {
            @Override
            public void onResponse(Call<ResponseCity> calls, Response<ResponseCity> response) {
                if (response.isSuccessful()) {
                    List<SemuacityItem> semuacityItems = response.body().getValue();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < semuacityItems.size(); i++){
                        city = semuacityItems.get(i).getCityID();
                        listSpinner.add(semuacityItems.get(i).getCityName());
                    }
                    // Set hasil result json ke dalam adapter spinner
                    ArrayAdapter<String> adapters = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(adapters);

                } else {
                    Toast.makeText(mContext, "Gagal mengambil data city", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCity> calls, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
