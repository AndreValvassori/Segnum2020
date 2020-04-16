package com.andrevalvassori.segnum2020.Singleton;

import android.content.Context;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.andrevalvassori.segnum2020.Adapter.EventListAdapter;
import com.andrevalvassori.segnum2020.Adapter.SectionsPageAdapter;
import com.andrevalvassori.segnum2020.Controller.MainFragments.EventsFragment;
import com.andrevalvassori.segnum2020.DTO.event.EventDTO;
import com.andrevalvassori.segnum2020.DTO.event.EventNewSimplifyDTO;
import com.andrevalvassori.segnum2020.DTO.location.LocationNewDTO;
import com.andrevalvassori.segnum2020.DTO.user.CredentialsDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserNewDTO;
import com.andrevalvassori.segnum2020.Model.EventType;
import com.andrevalvassori.segnum2020.retrofift.RetrofitInitialization;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataStore {
    private static DataStore instance = null;
    private Context context;

    private UserDTO usuario;
    public boolean enterWithLogin = false;


    public Boolean eventChange = true;
    public Boolean myEventChange = true;
    public List<EventDTO> currentEvents = new ArrayList<EventDTO>();
    public List<EventDTO> currentMyEvents = new ArrayList<EventDTO>();
    public List<EventType> eventTypes = new ArrayList<EventType>();
    protected DataStore(){}

    public static DataStore sharedInstance(){
        if(instance == null)
            instance = new DataStore();

        return instance;
    }

    public void setContext(Context context) {

        this.context = context;
    }

    public Context getContext()
    {
        return this.context;
    }
    public int UserLogin(String login, String senha)
    {
        CredentialsDTO myCredentials = new CredentialsDTO(login,senha);

        Call<UserDTO> objectCall = new RetrofitInitialization().getUserService().postUserLogin(myCredentials);

        UserDTO result = null;
        try
        {
            result = objectCall.execute().body();
            System.out.println(result);
            DataStore.sharedInstance().setUser(result);
            DataStore.sharedInstance().loadAllEvents();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        if(result != null)
            return 1;
        else
            return 0;

//
//        objectCall.execute (new Callback<UserDTO>() {
//
//            @Override
//            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
//                if(response.code() == 200 || response.code() == 201)
//                {
//                    Toast.makeText (context, "Logado com sucesso!", Toast.LENGTH_LONG).show();
//                    DataStorage.sharedInstance().setUser(response.body());
//
//                    /*Intent intentMain = new Intent(Main2Activity.this, LoginActivity.class);
//                    intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |
//                            Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
////                    intentMain.putExtra("evento_id", evento.getId());
//                    DataStorage.sharedInstance().context.startActivity(intentMain);
//                    DataStorage.sharedInstance().finish();*/
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserDTO> call, Throwable t) {
//                Toast.makeText (context, "Falha ao registrar!", Toast.LENGTH_LONG).show();
//            }
//        });
//        return 1;
    }

    public void setUser(UserDTO user)
    {
        this.usuario = user;
    }

    public UserDTO getUser()
    {
        return this.usuario;
    }

    public boolean LoadUser(int id)
    {
        final Call<UserDTO> userCall = new RetrofitInitialization().getUserService().getUser(1);
        userCall.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response)
            {
                setUser(response.body());
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                usuario = null;
            }
        });
        return (usuario != null);
    }

    public void RegisterUser(UserNewDTO user)
    {
        Call<Void> objectCall = new RetrofitInitialization().getUserService().postRegister(user);

        objectCall.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200 || response.code() == 201)
                {
                    Toast.makeText (context, "Registrado com sucesso!", Toast.LENGTH_LONG).show();
                    DataStore.sharedInstance().UserLogin(user.getEmail(),user.getPassword());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText (context, "Falha ao registrar!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadEventTypes()
    {
        eventTypes = new ArrayList<EventType>();

        EventType eventAssassinato = new EventType();
        eventAssassinato.setId(3);
        eventAssassinato.setDescription("Assassinato");
        eventAssassinato.setName("Assassinato");
        eventTypes.add(eventAssassinato);

        EventType eventFurto = new EventType();
        eventFurto.setId(2);
        eventFurto.setDescription("Furto");
        eventFurto.setName("Furto");
        eventTypes.add(eventFurto);

        EventType eventRoubo = new EventType();
        eventRoubo.setId(1);
        eventRoubo.setDescription("Roubo");
        eventRoubo.setName("Roubo");
        eventTypes.add(eventRoubo);
    }

    public void LoadAndNotifyEventsa(RecyclerView recyclerAdapter)
    {
        Log.d("DataStorage","LoadAndNotifyEvents");
        final Call<List<EventDTO>> userCall = new RetrofitInitialization().getEventService().getAllEvents();
        userCall.enqueue(new Callback<List<EventDTO>>() {
            @Override
            public void onResponse(Call<List<EventDTO>> call, Response<List<EventDTO>> response) {
                List<EventDTO> oldEvents = DataStore.sharedInstance().currentEvents;

                if(oldEvents == null)
                {
                    DataStore.sharedInstance().currentEvents = response.body();
                    DataStore.sharedInstance().eventChange = true;
                }
                else
                if(!oldEvents.equals(response.body()))
                {
                    DataStore.sharedInstance().currentEvents = response.body();
                    DataStore.sharedInstance().eventChange = true;
                    //recyclerAdapter.getAdapter().notifyDataSetChanged();
                }
                Log.d("DataStorage","Events Loaded");
                Log.d("DataStorage",currentEvents.toString());
            }

            @Override
            public void onFailure(Call<List<EventDTO>> call, Throwable t) {
                DataStore.sharedInstance().eventChange = false;
                Log.d("DataStorage","Failed to get Events - " + t.getMessage());
            }
        });
    }

    public void loadAllEvents()
    {
        Log.d("DataStorage","loadAllEvents");
        final Call<List<EventDTO>> userCall = new RetrofitInitialization().getEventService().getAllEvents();
        userCall.enqueue(new Callback<List<EventDTO>>() {
            @Override
            public void onResponse(Call<List<EventDTO>> call, Response<List<EventDTO>> response) {
                List<EventDTO> oldEvents = DataStore.sharedInstance().currentEvents;

                if(oldEvents == null)
                {
                    DataStore.sharedInstance().currentEvents = response.body();
                    DataStore.sharedInstance().eventChange = true;
                }
                else
                if(!oldEvents.equals(response.body()))
                {
                    DataStore.sharedInstance().currentEvents = response.body();
                    DataStore.sharedInstance().eventChange = true;
                }
                Log.d("DataStorage","Events Loaded");
                Log.d("DataStorage",currentEvents.toString());
            }

            @Override
            public void onFailure(Call<List<EventDTO>> call, Throwable t) {
                DataStore.sharedInstance().eventChange = false;
                Log.d("DataStorage","Failed to get Events - " + t.getMessage());
            }
        });
    }

    public void loadMyEvents()
    {
        Log.d("DataStorage","loadMyEvents");
        final Call<List<EventDTO>> userCall = new RetrofitInitialization().getEventService().getAllEvents();
        userCall.enqueue(new Callback<List<EventDTO>>() {
            @Override
            public void onResponse(Call<List<EventDTO>> call, Response<List<EventDTO>> response) {
                List<EventDTO> oldEvents = DataStore.sharedInstance().currentMyEvents;

                if(oldEvents == null)
                {
                    DataStore.sharedInstance().currentMyEvents = response.body();
                    DataStore.sharedInstance().myEventChange = true;
                }
                else
                if(!oldEvents.equals(response.body()))
                {
                    DataStore.sharedInstance().currentMyEvents = response.body();
                    DataStore.sharedInstance().myEventChange = true;
                }
                Log.d("DataStorage","Events Loaded");
                Log.d("DataStorage",currentEvents.toString());
            }

            @Override
            public void onFailure(Call<List<EventDTO>> call, Throwable t) {
                DataStore.sharedInstance().eventChange = false;
                Log.d("DataStorage","Failed to get Events - " + t.getMessage());
            }
        });
    }

    public void sendEvent(EventNewSimplifyDTO event, LatLng local)
    {
        Call<Void> objectCall = new RetrofitInitialization().getEventService().postEvent(event);

        objectCall.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200 || response.code() == 201)
                {
                    Toast.makeText (context, "Evento criado com sucesso!", Toast.LENGTH_LONG).show();
                    Log.d("DataStorage",response.headers().values("location").toString().replace("]",""));
                    String[] eventId =  response.headers().values("location").toString().replace("]","").split("/"); //alunoRecebido.split(";");
                    int insertedID = Integer.parseInt(eventId[eventId.length-1]);

                    sendLocation(insertedID,event, local);
                }
                else
                {
                    Toast.makeText (context, "Erro ao Criar evento! Código:"+response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText (context, "Falha ao enviar evento!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sendLocation(int eventID, EventNewSimplifyDTO event, LatLng local)
    {
        LocationNewDTO newLocation;
        newLocation = new LocationNewDTO();

        newLocation.setId(0);
        newLocation.setLx(String.valueOf(local.longitude));
        newLocation.setLy(String.valueOf(local.latitude));
        newLocation.setName(event.getName());
        newLocation.setType(0);

        Call<Void> objectCall = new RetrofitInitialization().getLocationService().postLocation(eventID,newLocation);

        objectCall.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200 || response.code() == 201)
                {
                    Toast.makeText (context, "Localização criada com sucesso!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText (context, "Falha ao criar localização!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public List<EventDTO> getEvents() {
        return currentEvents;
    }
}
