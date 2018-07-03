package com.avaj.launcher.aircraft;
/*
**  Author   : lmucassi
**  Project  : avaj-launcher
**  school   : wethinkcode.co.za
*/

import com.avaj.launcher.tower.WeatherTower;

public interface Flyable {
    public void updateCondition();
    public void registerTower(WeatherTower weatherTower);
}
