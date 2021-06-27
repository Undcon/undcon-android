package com.br.undcon.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.br.undcon.dto.InventoryDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.br.undcon.R;
import com.br.undcon.utils.TranslateEnum;

public class InventoryAdapter extends ArrayAdapter<InventoryDto> {
    private final Context context;
    private final List<InventoryDto> inventories;

    public InventoryAdapter(Context context, List<InventoryDto> inventories) {
        super(context, R.layout.adapter_inventory_list, inventories);
        this.context = context;
        this.inventories = inventories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_inventory_list, parent, false);

        TextView number = (TextView) rowView.findViewById(R.id.number);
        TextView status = (TextView) rowView.findViewById(R.id.status);

        number.setText(this.inventories.get(position).getLabel());
        status.setText(TranslateEnum.getInventoryStatus(this.inventories.get(position).getStatus().toString()));

        return rowView;
    }
}
