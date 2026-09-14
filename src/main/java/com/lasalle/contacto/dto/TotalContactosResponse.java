package com.lasalle.contacto.dto;

public class TotalContactosResponse {

    private long total;

    public TotalContactosResponse() {
    }

    public TotalContactosResponse(long total) {
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}