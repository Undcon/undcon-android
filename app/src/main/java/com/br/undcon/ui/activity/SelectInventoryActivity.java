package com.br.undcon.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.br.undcon.dao.InventoryProductDAO;
import com.br.undcon.databinding.ActivitySelectInventoryBinding;
import com.br.undcon.dto.InventoryDto;
import com.br.undcon.model.InventoryEntity;
import com.br.undcon.model.InventoryProductEntity;
import com.br.undcon.model.UserEntity;
import com.br.undcon.ui.adapter.InventoryAdapter;
import com.br.undcon.utils.UserCache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SelectInventoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivitySelectInventoryBinding binding;
    private ListView inventoriesListView;
    private List<InventoryDto> inventoriesList;
    private InventoryProductDAO inventoryProductDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        config();
        loadInventoriesList();

        inventoryProductDAO = new InventoryProductDAO(this);
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

            String message = "Agora está alterando o inventário " + ie.getId();
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

            importInventoryProduct();

            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }

    public void importInventoryProduct() {
        InventoryProductEntity entity = new InventoryProductEntity(null, new UserEntity(new Long(2)), new InventoryEntity(new Long(2)), "123456789", "ASDFFDV", "Produto Qualquer");
        long id = inventoryProductDAO.insert(entity);
        Toast.makeText(getApplicationContext(), "Inserido ID: " + id, Toast.LENGTH_LONG).show();
    }
}