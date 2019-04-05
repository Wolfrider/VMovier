package com.oliver.vmovier.core.rxext;

import android.support.annotation.NonNull;
import com.oliver.vmovier.core.function.ActionDelegate;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class CompleteAndErrorAction {

    private ActionDelegate mAction;

    public CompleteAndErrorAction(@NonNull ActionDelegate action) {
        mAction = action;
    }

    public Action toComplete() {
        return new Action() {
            @Override
            public void run() throws Exception {
                mAction.exec();
            }
        };
    }

    public Consumer<Throwable> toError() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mAction.exec();
            }
        };
    }
}
