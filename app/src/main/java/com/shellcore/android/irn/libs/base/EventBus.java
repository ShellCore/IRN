package com.shellcore.android.irn.libs.base;

/**
 * Created by Cesar on 01/08/2017.
 */

public interface EventBus {

    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
