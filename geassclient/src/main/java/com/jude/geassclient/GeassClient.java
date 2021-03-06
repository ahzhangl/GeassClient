package com.jude.geassclient;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhuchenxi on 16/11/4.
 */

public class GeassClient implements Cloneable{

    private Dispatcher dispatcher;
    private ShellPool shellPool;

    public GeassClient() {
        dispatcher = new Dispatcher();
        shellPool = new ShellPool();
    }

    public void setTimeOut(long timeout,TimeUnit unit){
        if (timeout < 0) throw new IllegalArgumentException("timeout < 0");
        if (unit == null) throw new IllegalArgumentException("unit == null");
        long millis = unit.toMillis(timeout);
        if (millis > Integer.MAX_VALUE) throw new IllegalArgumentException("Timeout too large.");
        if (millis == 0 && timeout > 0) throw new IllegalArgumentException("Timeout too small.");
    }

    public GeassClient setDispatcher(Dispatcher dispatcher) {
        if (dispatcher == null) throw new IllegalArgumentException("dispatcher == null");
        this.dispatcher = dispatcher;
        return this;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public ShellPool getShellPool() {
        return shellPool;
    }

    public Call newCall(Command command) {
        return new Call(this, command);
    }

}
