package com.br.undcon.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.br.undcon.ui.activity.MenuActivity;
import com.br.undcon.dto.LoginRequestDto;
import com.br.undcon.dto.LoginResponseDto;
import com.br.undcon.rest.service.LoginService;
import com.br.undcon.databinding.ActivityLoginBinding;
import com.br.undcon.utils.UserCredential;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressBar loadingProgressBar;

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
        loadingProgressBar = binding.loading;
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
//            loadingProgressBar.setVisibility(View.VISIBLE);
//            actionLoginButton();
            navigateToMain();
        }
    }

    private void actionLoginButton() {
        LoginService loginService = new LoginService();
        LoginResponseDto res = loginService.login(new LoginRequestDto(binding.username.getText().toString(), binding.password.getText().toString()));
        if (res != null) {
            UserCredential.getInstance().setUser(res);
            Toast.makeText(getApplicationContext(), "Usuário encontrado", Toast.LENGTH_LONG).show();
            navigateToMain();
        } else {
            Toast.makeText(getApplicationContext(), "Nenhum usuário encontrado", Toast.LENGTH_LONG).show();
        }
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, MenuActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}