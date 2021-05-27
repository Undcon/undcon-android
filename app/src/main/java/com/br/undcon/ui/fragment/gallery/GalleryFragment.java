package com.br.undcon.ui.fragment.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.br.undcon.databinding.FragmentGalleryBinding;
import com.br.undcon.ui.fragment.dialog.SectorDialog;

public class GalleryFragment extends Fragment implements View.OnClickListener, SectorDialog.SectorDialogListner {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.addSectorFab.setOnClickListener(this);

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
        Toast.makeText(getActivity().getApplicationContext(), name, Toast.LENGTH_LONG).show();
    }

    @Override
    public String sugestionName() {
        return "Setor 2";
    }
}