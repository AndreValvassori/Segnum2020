package com.andrevalvassori.segnum2020.Controller.UserProfileFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.andrevalvassori.segnum2020.DTO.location.LocationDTO;
import com.andrevalvassori.segnum2020.DTO.location.LocationNewDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserDTO;
import com.andrevalvassori.segnum2020.Model.Enums.LocationType;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;
import com.andrevalvassori.segnum2020.retrofift.RetrofitInitialization;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAddressFragment extends Fragment {

    EditText etHomeAddress;
    EditText etWorkAddress;
    EditText etSchoolAddress;
    Button btnSave;

    LocationDTO homeLocation = new LocationDTO();
    LocationDTO workLocation = new LocationDTO();
    LocationDTO schoolLocation = new LocationDTO();

    String mapsURI = "https://maps.googleapis.com/maps/api/geocode/json";

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_address,container,false);

        etHomeAddress = view.findViewById(R.id.et_my_address_home);
        etWorkAddress = view.findViewById(R.id.et_my_address_work);
        etSchoolAddress = view.findViewById(R.id.et_my_address_school);
        btnSave = view.findViewById(R.id.btn_salvar_enderecos);
        btnSave.setOnClickListener(v -> {
            if(homeLocation != null && homeLocation.getId() != 0) {
                translateXYAndUpdate(etHomeAddress.getText().toString(), homeLocation);
            } else {
                translateXYAndInsert(etHomeAddress.getText().toString(), homeLocation, LocationType.HOME);
            }
            if(workLocation != null && workLocation.getId() != 0) {
                translateXYAndUpdate(etWorkAddress.getText().toString(), workLocation);
            } else {
                translateXYAndInsert(etWorkAddress.getText().toString(), workLocation, LocationType.WORK);
            }
            if(schoolLocation != null && schoolLocation.getId() != 0) {
                translateXYAndUpdate(etSchoolAddress.getText().toString(),schoolLocation);
            } else {
                translateXYAndInsert(etSchoolAddress.getText().toString(), schoolLocation, LocationType.STUDY);
            }
        });

        UserDTO user = DataStore.sharedInstance().getUser();
        if (user != null && user.getId() != 0) {
            Call<List<LocationDTO>> myEvents = new RetrofitInitialization().getUserService().getMyLocations(user.getId());
            myEvents.enqueue(new Callback<List<LocationDTO>>() {
                @Override
                public void onResponse(Call<List<LocationDTO>> call, Response<List<LocationDTO>> response) {
                    List<LocationDTO> locations = response.body();
                    locations.forEach(location -> {
                        if (LocationType.toEnum(location.getType().getId()) == LocationType.HOME) homeLocation = location;
                        else if (LocationType.toEnum(location.getType().getId()) == LocationType.WORK) workLocation = location;
                        else if (LocationType.toEnum(location.getType().getId()) == LocationType.STUDY) schoolLocation = location;
                    });
                    translateFromXY(homeLocation.getLx(), homeLocation.getLy(), etHomeAddress);
                    translateFromXY(workLocation.getLx(), workLocation.getLy(), etWorkAddress);
                    translateFromXY(schoolLocation.getLx(), schoolLocation.getLy(), etSchoolAddress);
                }

                @Override
                public void onFailure(Call<List<LocationDTO>> call, Throwable t) {

                }
            });
        }

        return view;
    }

    private void translateFromXY(String lx, String ly, EditText et) {
        // Request
        // preencher o et
    }

    private void translateXYAndInsert(String endereco, LocationDTO locationDTO, LocationType locationType) {
        Call<JSONObject> jsonObjectCall = new RetrofitInitialization(mapsURI).getGoogleMapsAPIService().transateToXY(endereco);
        jsonObjectCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                try {
                    JSONArray results = response.body().getJSONArray("results");
                    JSONObject firstResult = results.getJSONObject(0);
                    JSONObject geometry = firstResult.getJSONObject("geometry");
                    geometry.getJSONObject("location");
                    double lat = geometry.getDouble("lat");
                    double lng = geometry.getDouble("lng");
                    locationDTO.setLx(String.valueOf(lng));
                    locationDTO.setLy(String.valueOf(lat));
                    insertLocation(locationDTO, locationType);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        });
    }

    private void translateXYAndUpdate(String endereco, LocationDTO locationDTO) {
        Call<JSONObject> jsonObjectCall = new RetrofitInitialization(mapsURI).getGoogleMapsAPIService().transateToXY(endereco);
        jsonObjectCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                try {
                    JSONArray results = response.body().getJSONArray("results");
                    JSONObject firstResult = results.getJSONObject(0);
                    JSONObject geometry = firstResult.getJSONObject("geometry");
                    geometry.getJSONObject("location");
                    double lat = geometry.getDouble("lat");
                    double lng = geometry.getDouble("lng");
                    locationDTO.setLx(String.valueOf(lng));
                    locationDTO.setLy(String.valueOf(lat));
                    updateLocation(locationDTO);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        });
    }

    private void updateLocation(LocationDTO locationDTO) {
        UserDTO user = DataStore.sharedInstance().getUser();
        LocationNewDTO locationNew = new LocationNewDTO(locationDTO.getId(), locationDTO.getName(), locationDTO.getLx(), locationDTO.getLy(), locationDTO.getId());
        Call<Void> voidCall = new RetrofitInitialization().getLocationService().putLocation(locationNew.getId(), locationNew);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void insertLocation(LocationDTO locationDTO, LocationType locationType) {
        UserDTO user = DataStore.sharedInstance().getUser();

        LocationNewDTO locationNew = new LocationNewDTO(locationDTO.getId(), locationType.getDescription(), locationDTO.getLx(), locationDTO.getLy(), locationType.getCode());
        Call<Void> voidCall = new RetrofitInitialization().getLocationService().postUserLocation(user.getId(), locationNew);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }



}
