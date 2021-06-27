package com.br.undcon.ui.fragment.sector;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.br.undcon.dao.SectorDAO;
import com.br.undcon.databinding.FragmentSectorBinding;
import com.br.undcon.model.SectorEntity;
import com.br.undcon.ui.activity.MainActivity;
import com.br.undcon.ui.activity.ProductReadingActivity;
import com.br.undcon.ui.activity.SelectInventoryActivity;
import com.br.undcon.ui.fragment.dialog.SectorDialog;
import com.br.undcon.utils.UserCache;
import com.google.gson.Gson;

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
        sectorList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        arrayAdapterSector = new ArrayAdapter<SectorEntity>(getActivity(), android.R.layout.simple_list_item_multiple_choice, sectorEntities);
        sectorList.setAdapter(arrayAdapterSector);
        sectorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SectorEntity clickedItem = (SectorEntity) sectorList.getItemAtPosition(position);
                Intent intent =  new Intent(getActivity(), ProductReadingActivity.class);
                Gson gson = new Gson();

                intent.putExtra("sector", gson.toJson(clickedItem));
                getActivity().startActivity(intent);
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
        Integer number = Integer.parseInt(name.split(" ")[1]);
        long id = sectorDAO.insert(new SectorEntity(number));
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