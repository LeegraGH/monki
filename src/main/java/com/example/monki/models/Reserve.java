package com.example.monki.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserve {
    private String name;
    private String date;
    private String email;
    private String phone;
    private int numOfGuests;

    @Override
    public String toString() {
        return "Имя = " + name + "\n"
                + "Почта = " + email + "\n"
                + "Телефон = " + phone + "\n"
                + "Дата и время = " + date + "\n"
                + "Количество гостей = " + numOfGuests;
    }
}
