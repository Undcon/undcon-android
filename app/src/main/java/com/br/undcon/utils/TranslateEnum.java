package com.br.undcon.utils;

import com.br.undcon.model.InventoryStatus;

public class TranslateEnum {
    public static String getInventoryStatus(String status) {
        switch (status) {
            case "CREATED": return "Criado";
            case "SCHEDULED": return "Agendado";
            case "IN_PROGRESS": return "Em andamento";
            case "CANCELED": return "Cancelado";
            default: return "Finalizado";
        }
    }
}
