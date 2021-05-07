package com.br.undcon.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.br.undcon.databinding.FragmentHomeBinding;
import com.br.undcon.dto.LoginRequestDto;
import com.br.undcon.dto.LoginResponseDto;
import com.br.undcon.model.InventoryEntity;
import com.br.undcon.rest.api.InventoryAPI;
import com.br.undcon.rest.service.ServiceGenerator;
import com.br.undcon.rest.api.LoginAPI;
import com.br.undcon.utils.UserCredential;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button btn_eventos_confirmar = (Button) binding.button2;
        btn_eventos_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginAPI service = ServiceGenerator.createService(LoginAPI.class);


                Call<LoginResponseDto> call = service.login(new LoginRequestDto("gs@cliente1", "12345678"));

                call.enqueue(new Callback<LoginResponseDto>() {
                    @Override
                    public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {

                        if (response.isSuccessful()) {

                            LoginResponseDto respostaServidor = response.body();

                            //verifica aqui se o corpo da resposta não é nulo
                            if (respostaServidor != null) {
                                UserCredential.getInstance().setUser(respostaServidor);
                                LoginResponseDto a = UserCredential.getInstance().getUser();
                                System.out.println(a);

//                                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = sharedPref.edit();
//                                Gson gson = new Gson();
//                                String json = gson.toJson(respostaServidor);
//                                editor.putString("user", json);
//                                editor.commit();
//
//                                gson = new Gson();
//                                json = sharedPref.getString("user", "");
//                                LoginResponseDto obj = gson.fromJson(json, LoginResponseDto.class);

//                        if(respostaServidor.isValid()) {
//
//                            resposta.setFrom_type(respostaServidor.getFrom_type());
//                            resposta.setFrom_value(respostaServidor.getFrom_value());
//                            resposta.setResult(respostaServidor.getResult());
//                            resposta.setTo_type(respostaServidor.getTo_type());
//                            resposta.setValid(respostaServidor.isValid());
//
//                            progress.dismiss();
//                            setaValores();
//
//                        } else{
//
//                            Toast.makeText(getApplicationContext(),"Insira unidade e valores válidos", Toast.LENGTH_SHORT).show();
//                        }

                            } else {

//                        Toast.makeText(getApplicationContext(),"Resposta nula do servidor", Toast.LENGTH_SHORT).show();
                            }

                        } else {

//                    Toast.makeText(getApplicationContext(),"Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
//                    // segura os erros de requisição
//                    ResponseBody errorBody = response.errorBody();
                        }

//                progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<LoginResponseDto> call, Throwable t) {

//                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        Button btnTeste = (Button) binding.button;
        btnTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InventoryAPI service = ServiceGenerator.createService(InventoryAPI.class);


                Call<List<InventoryEntity>> call = service.get();

                call.enqueue(new Callback<List<InventoryEntity>>() {
                    @Override
                    public void onResponse(Call<List<InventoryEntity>> call, Response<List<InventoryEntity>> response) {
                        if (response.isSuccessful()) {
                            List<InventoryEntity> respostaServidor = response.body();
                            if (respostaServidor != null) {
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<InventoryEntity>> call, Throwable t) {
                    }
                });
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}