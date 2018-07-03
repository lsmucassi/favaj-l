package com.avaj.launcher.tower;
/*
 **  Author   : lmucassi
 **  Project  : avaj-launcher
 **  school   : wethinkcode.co.za
 */

import com.avaj.launcher.aircraft.Flyable;
import java.util.ArrayList;

public class Tower {
    private ArrayList<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable) {
        if (observers.contains(flyable))
            return ;
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionChanged() {
        for (int i = 0; i < observers.size(); i++)
            observers.get(i).updateCondition();
    }
}
