package com.projettransversal.api.DTO;

import java.io.Serializable;

public class MicrobitResponseDTO implements Serializable {

    private Boolean code;

    public MicrobitResponseDTO() {
    }

    public MicrobitResponseDTO(Boolean code) {
        this.code = code;
    }

    public Boolean getCode() {
        return code;
    }

    public void setCode(Boolean code) {
        this.code = code;
    }
}
