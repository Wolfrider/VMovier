package com.oliver.vmovier.core.lifecycle;

enum LifecycleEvent {

    ON_CREATE("ON_CREATE"),
    ON_START("ON_START"),
    ON_RESUME("ON_RESUME"),
    ON_PAUSE("ON_PAUSE"),
    ON_STOP("ON_STOP"),
    ON_DESTROY("ON_DESTROY");

    private String mName;

    LifecycleEvent(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }

}
