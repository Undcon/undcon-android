package com.br.undcon.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.br.undcon.dto.InventoryDto;
import com.br.undcon.dto.LoginRequestDto;
import com.br.undcon.dto.LoginResponseDto;
import com.br.undcon.rest.service.InventoryOperatorService;
import com.br.undcon.rest.service.LoginService;
import com.br.undcon.databinding.ActivityLoginBinding;
import com.br.undcon.utils.UserCache;
import com.google.gson.Gson;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setBindings();
        setListeners();
    }

    private void setBindings() {
        usernameEditText = binding.username;
        passwordEditText = binding.password;
        loginButton = binding.login;
    }

    private void setListeners() {
        loginButton.setEnabled(true);
        loginButton.setOnClickListener(this);
        usernameEditText.setText("gs@cliente1");
        passwordEditText.setText("12345678");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.login.getId()) {
            actionLoginButton();
        }
    }

    private void actionLoginButton() {
        LoginService loginService = new LoginService();
        LoginResponseDto res = loginService.login(new LoginRequestDto(binding.username.getText().toString(), binding.password.getText().toString()));
        if (res != null) {
            UserCache.getInstance().setUser(res);
            Toast.makeText(getApplicationContext(), "Usuário encontrado", Toast.LENGTH_LONG).show();
            navigateToMain();
        } else {
            Toast.makeText(getApplicationContext(), "Nenhum usuário encontrado", Toast.LENGTH_LONG).show();
        }
    }

    private void navigateToMain() {
        InventoryOperatorService inventoryService = new InventoryOperatorService();
        List<InventoryDto> inventories = inventoryService.findByCurrentOperator();
        if (inventories.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Dados não encontrados, por favor contact o responsável", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = null;
            if (inventories.size() == 1) {
                UserCache.getInstance().setInventory(inventories.get(0));

                String message = "Agora está alterando o inventário " + inventories.get(0).getId();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                intent = new Intent(this, MenuActivity.class);
            } else {
                intent = new Intent(this, SelectInventoryActivity.class);
                Gson gson = new Gson();
                intent.putExtra("inventories", gson.toJson(inventories));
            }
            startActivity(intent);
        }
    }
}