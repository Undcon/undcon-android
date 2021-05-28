package com.br.undcon.ui.fragment.sector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.br.undcon.R;
import com.br.undcon.dao.InventoryProductDAO;
import com.br.undcon.dao.SectorDAO;
import com.br.undcon.databinding.FragmentSectorBinding;
import com.br.undcon.model.SectorEntity;
import com.br.undcon.ui.fragment.dialog.SectorDialog;

import java.util.ArrayList;
import java.util.List;

public class SectorFragment extends Fragment implements View.OnClickListener, SectorDialog.SectorDialogListner {

    private FragmentSectorBinding binding;
    private SectorDAO sectorDAO;
    private List<SectorEntity> sectorEntities;
    private ArrayAdapter<SectorEntity> arrayAdapterSector;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSectorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.addSectorFab.setOnClickListener(this);

        sectorDAO = new SectorDAO(getActivity());
        sectorEntities = sectorDAO.getAll();

        final ListView sectorList = binding.sectorList;
        arrayAdapterSector = new ArrayAdapter<SectorEntity>(getActivity(), android.R.layout.simple_list_item_1, sectorEntities);
        sectorList.setAdapter(arrayAdapterSector);
        sectorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) sectorList.getItemAtPosition(position);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.addSectorFab.getId()) {
            SectorDialog sectorDialog = new SectorDialog();
            sectorDialog.show(getChildFragmentManager(), "Setor");
        }
    }

    @Override
    public void setName(String name) {
        long id = sectorDAO.insert(new SectorEntity(name));
        Toast.makeText(getActivity().getApplicationContext(), name, Toast.LENGTH_LONG).show();
        arrayAdapterSector.clear();
        sectorEntities = sectorDAO.getAll();
        arrayAdapterSector.addAll(sectorEntities);
        arrayAdapterSector.notifyDataSetChanged();
    }

    @Override
    public String sugestionName() {
        return "Setor " + (sectorEntities.size() + 1);
    }
}