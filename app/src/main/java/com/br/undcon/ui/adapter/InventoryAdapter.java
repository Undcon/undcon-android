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

public class InventoryAdapter extends ArrayAdapter<InventoryDto> {
    private final Context context;
    private final List<InventoryDto> inventories;

    public InventoryAdapter(Context context, List<InventoryDto> inventories) {
        super(context, R.layout.inventory_list, inventories);
        this.context = context;
        this.inventories = inventories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.inventory_list, parent, false);

//        LayoutInflater.from(this).inflate(android.R.layout.inventory_list,null);


        TextView number = (TextView) rowView.findViewById(R.id.number);
        TextView status = (TextView) rowView.findViewById(R.id.status);
//        TextView dataForeseen = (TextView) rowView.findViewById(R.id.dataFore);

        number.setText(this.inventories.get(position).getId().toString());
        status.setText(this.inventories.get(position).getStatus().toString());

        Date date = this.inventories.get(position).getDateForeseen();
        String formatDate = "";
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat();
//            sdf.format(date, "dd/MM/yyyy");
            formatDate = date.toString();
        }
//        dataForeseen.setText(formatDate);

        return rowView;
    }
}
