package com.example.frank.bestpricefinder;

import android.content.Context;

import java.io.Serializable;

public class ScanResultsSerialized implements Serializable {

    Context m_context;
    String UPC;


    public ScanResultsSerialized(Context m_context, String UPC) {
        this.m_context = m_context;
        this.UPC = UPC;
    }

    public Context getM_context() {
        return m_context;
    }

    public void setM_context(Context m_context) {
        this.m_context = m_context;
    }

    public String getUPC() {
        return UPC;
    }

    public void setUPC(String UPC) {
        this.UPC = UPC;
    }
}
