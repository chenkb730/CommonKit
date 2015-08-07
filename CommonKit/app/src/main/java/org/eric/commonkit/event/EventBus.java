package org.eric.commonkit.event;

import android.app.Activity;
import android.app.Application;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件
 */
public class EventBus extends Bus {

    private static Map<String, EventBus> map = new HashMap<String, EventBus>();

    private static Application application;

    public static void registerApp(Application app) {
        application = app;
    }

    public EventBus() {
        super(ThreadEnforcer.ANY);
    }

    public EventBus(ThreadEnforcer type) {
        super(type);
    }

    public synchronized static EventBus getEventBus(String name) {
        EventBus eventBus = map.get(name);
        if (eventBus == null) {
            map.put(name, eventBus = new EventBusProxy());
        }
        return eventBus;
    }

    public synchronized static EventBus getEventBus(String name,
                                                    ThreadEnforcer type) {
        EventBus eventBus = map.get(name);
        if (eventBus == null) {
            map.put(name, eventBus = new EventBusProxy(type));
        }
        return eventBus;
    }

    static class EventBusProxy extends EventBus {

        private ThreadEnforcer type = ThreadEnforcer.ANY;

        public ThreadEnforcer getType() {
            return type;
        }

        public void setType(ThreadEnforcer type) {
            this.type = type;
        }

        public void post(final EventMessage message) {

            if (type == ThreadEnforcer.MAIN) {
                ((Activity) application.getApplicationContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EventBusProxy.super.post(message);
                    }
                });
                return;
            }
            EventBusProxy.super.post(message);
        }

        public EventBusProxy() {
            super();
        }

        public EventBusProxy(ThreadEnforcer type) {
            super(type);
            this.type = type;
        }

    }

}
