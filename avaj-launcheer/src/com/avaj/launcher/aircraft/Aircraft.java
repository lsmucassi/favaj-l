package com.avaj.launcher.aircraft;
/*
 **  Author   : lmucassi
 **  Project  : avaj-launcher
 **  school   : wethinkcode.co.za
 */

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter;

    public Aircraft(String name, Coordinates coordinates) {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextId() {
        return ++(Aircraft.idCounter);
    }
}
