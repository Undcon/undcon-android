package com.br.undcon.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.br.undcon.controller.InventoryProductController;
import com.br.undcon.databinding.ActivitySelectInventoryBinding;
import com.br.undcon.dto.InventoryDto;
import com.br.undcon.ui.adapter.InventoryAdapter;
import com.br.undcon.ui.utils.LoadingDialog;
import com.br.undcon.utils.UserCache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SelectInventoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivitySelectInventoryBinding binding;
    private ListView inventoriesListView;
    private List<InventoryDto> inventoriesList;
    private InventoryProductController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        controller = new InventoryProductController(this);

        config();
        loadInventoriesList();
    }

    private void config() {
        inventoriesListView = binding.inventoriesList;
        inventoriesListView.setOnItemClickListener(this);
    }

    private void loadInventoriesList() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<InventoryDto>>(){}.getType();
            inventoriesList = gson.fromJson(extras.getString("inventories"), listType);
        }

        inventoriesListView.setAdapter(new InventoryAdapter(this, inventoriesList));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == inventoriesListView.getId()) {
            InventoryDto ie = inventoriesList.get(position);
            UserCache.getInstance().setInventory(ie);

            String message = "Agora está alterando o inventário " + ie.getLabel();
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

            LoadingDialog loadingDialog = new LoadingDialog(this);
            loadingDialog.show("Aguarde, estamos carregando os produtos do inventário.");

            importInventoryProduct();
            loadingDialog.hide();

            Intent intent = new Intent(this, MainActivity.class);
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