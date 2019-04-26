package com.example.frank.bestpricefinder;

import android.app.Application;
import android.content.Context;

public class GlobalAppArgs extends Application  {


    Context m_context;

    public GlobalAppArgs(Context m_context) {
        this.m_context = m_context;
    }

    public Context getM_context() {
        return m_context;
    }

    public void setM_context(Context m_context) {
        this.m_context = m_context;
    }
}
