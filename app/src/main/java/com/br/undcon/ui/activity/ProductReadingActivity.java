package com.br.undcon.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.br.undcon.controller.InventoryProductController;
import com.br.undcon.controller.InventoryReadingControler;
import com.br.undcon.databinding.ActivityProductReadingBinding;
import com.br.undcon.dto.InventoryDto;
import com.br.undcon.dto.InventoryProductDto;
import com.br.undcon.dto.InventoryReadingDto;
import com.br.undcon.model.InventoryProductEntity;
import com.br.undcon.model.SectorEntity;
import com.br.undcon.ui.fragment.home.Capture;
import com.br.undcon.utils.UserCache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductReadingActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityProductReadingBinding binding;
    private EditText etCodeNumer;
    private ImageButton btnSave;
    private ImageButton btnScan;
    private InventoryProductController controller;
    private InventoryReadingControler inventoryReadingControler;
    private List<InventoryReadingDto> readingsEntities;
    private ArrayAdapter<InventoryReadingDto> arrayAdapterReadings;
    private SectorEntity sectorEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductReadingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadSector();

        controller = new InventoryProductController(this);
        inventoryReadingControler = new InventoryReadingControler(this);

        readingsEntities = inventoryReadingControler.getAll();
        final ListView productsList = binding.listViewProducts;
        arrayAdapterReadings = new ArrayAdapter<InventoryReadingDto>(this, android.R.layout.simple_list_item_1, readingsEntities);
        productsList.setAdapter(arrayAdapterReadings);
        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                SectorEntity clickedItem = (SectorEntity) productsList.getItemAtPosition(position);
//                Intent intent =  new Intent(getActivity(), ProductReadingActivity.class);
//                startActivity(intent);
            }
        });

        setBindings();
    }

    private void loadSector() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Gson gson = new Gson();
            Type sector = new TypeToken<SectorEntity>(){}.getType();
            sectorEntity = gson.fromJson(extras.getString("sector"), sector);
        }
    }

    private void setBindings() {
        etCodeNumer = binding.etCodeNumer;
        btnSave = binding.btnSave;
        btnScan = binding.btnScan;

        btnSave.setOnClickListener(this);
        btnScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btnSave.getId()) {
            addReading();
        } else if (v.getId() == binding.btnScan.getId()) {
            createScanner();
        }
    }

    private void addReading() {
        InventoryProductEntity entity = controller.getFindByCode(etCodeNumer.getText().toString());
        if (entity != null)  {
            InventoryProductDto inventoryProductDto =
                    new InventoryProductDto(entity.getId(), entity.getGtin(), entity.getInternCode(), entity.getDescription());
            InventoryReadingDto inventoryReadingDto =
                    new InventoryReadingDto(inventoryProductDto, new Long(0), sectorEntity.getName(),
                            new InventoryDto(new Long(UserCache.getInstance().getInventory().getId())));

            inventoryReadingControler.insert(inventoryReadingDto, etCodeNumer.getText().toString());

            arrayAdapterReadings.clear();
            readingsEntities = inventoryReadingControler.getAll();
            arrayAdapterReadings.addAll(readingsEntities);
            arrayAdapterReadings.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Código encontrado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Código não encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    private void createScanner() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Flash");
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(Capture.class);
        intentIntegrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(intentResult.getContents() != null) {
            etCodeNumer.setText(intentResult.getContents());
        } else {
            Toast.makeText(getApplicationContext(), "OPSSS", Toast.LENGTH_SHORT).show();
        }

    }
}