package com.projettransversal.api.DTO;

import java.io.Serializable;

public class MicrobitRequestDTO implements Serializable {

    private String data;

    public MicrobitRequestDTO() {
    }

    public MicrobitRequestDTO(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
