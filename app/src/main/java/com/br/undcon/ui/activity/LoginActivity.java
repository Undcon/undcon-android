package com.br.undcon.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.undcon.controller.InventoryProductController;
import com.br.undcon.dto.InventoryDto;
import com.br.undcon.dto.LoginRequestDto;
import com.br.undcon.dto.LoginResponseDto;
import com.br.undcon.rest.service.InventoryOperatorService;
import com.br.undcon.rest.service.LoginService;
import com.br.undcon.databinding.ActivityLoginBinding;
import com.br.undcon.ui.utils.LoadingDialog;
import com.br.undcon.utils.UserCache;
import com.google.gson.Gson;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private InventoryProductController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        controller = new InventoryProductController(this);

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
            UserCache.getInstance().setLogin(res);
            Toast.makeText(getApplicationContext(), "Usu??rio encontrado", Toast.LENGTH_LONG).show();
            navigateToMain();
        } else {
            Toast.makeText(getApplicationContext(), "Nenhum usu??rio encontrado", Toast.LENGTH_LONG).show();
        }
    }

    private void navigateToMain() {
        InventoryOperatorService inventoryService = new InventoryOperatorService();
        List<InventoryDto> inventories = inventoryService.findByCurrentOperator();
        if (inventories.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Dados n??o encontrados, por favor contact o respons??vel", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = null;
            if (inventories.size() == 1) {
                InventoryDto dto = inventories.get(0);
                UserCache.getInstance().setInventory(dto);

                String message = "Agora est?? alterando o invent??rio: " + dto.getLabel();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                LoadingDialog loadingDialog = new LoadingDialog(this);
                loadingDialog.show("Aguarde, estamos carregando os produtos do invent??rio.");

                importInventoryProduct();
                loadingDialog.hide();

                intent = new Intent(this, MainActivity.class);
            } else {
                intent = new Intent(this, SelectInventoryActivity.class);
                Gson gson = new Gson();
                intent.putExtra("inventories", gson.toJson(inventories));
            }
            startActivity(intent);
        }
    }

    public boolean importInventoryProduct() {
//        InventoryProductEntity entity = new InventoryProductEntity(null, new UserEntity(new Long(2)), new InventoryEntity(new Long(2)), "123456789", "ASDFFDV", "Produto Qualquer");
//        long id = controller.insert(entity);
//        Toast.makeText(getApplicationContext(), "Inserido ID: " + id, Toast.LENGTH_LONG).show();
        return controller.loadItens();
    }
}