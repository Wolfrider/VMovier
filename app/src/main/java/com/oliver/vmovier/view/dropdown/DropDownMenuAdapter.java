package com.oliver.vmovier.view.dropdown;

import java.util.Observable;

import android.support.v7.widget.RecyclerView;

public abstract class DropDownMenuAdapter extends Observable {

    public abstract RecyclerView getPopupView(int position);

    public abstract String getItemTitle(int position);

    public abstract int getItemCount();

    protected void notifyDataChanged() {
        setChanged();
        notifyObservers();
        clearChanged();
    }

}
