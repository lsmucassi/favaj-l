package com.avaj.launcher.aircraft;
/*
 **  Author   : lmucassi
 **  Project  : avaj-launcher
 **  school   : wethinkcode.co.za
 */

import com.avaj.launcher.Simulator;
import com.avaj.launcher.tower.WeatherTower;
import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {
     public WeatherTower opTower;

     JetPlane(String name, Coordinates coordinates) {
         super(name, coordinates);
     }

     public void updateCondition() {
         String weather = opTower.getWeather(this.coordinates);
         HashMap<String, String> mes = new HashMap<String, String>() {{
             put("SUN", "Yes sun bring a clear view for me, I like it");
             put("RAIN", "I really have to get above the clouds, bloody damnful rain!");
             put("FOG", "Speeding will be a challenge in this fog");
             put("SNOW", "Not even snow can stop this beast from porting");
         }};

         if (weather.equals("SUN"))
             this.coordinates = new Coordinates(
                     coordinates.getLongitude() + 0,
                     coordinates.getLatitude() + 10,
                     coordinates.getHeight() + 2);
         else if (weather.equals("RAIN"))
             this.coordinates = new Coordinates(
                     coordinates.getLongitude() + 0,
                     coordinates.getLatitude() + 5,
                     coordinates.getHeight() + 0);
         else if (weather.equals("FOG"))
             this.coordinates = new Coordinates(
                     coordinates.getLongitude() + 0,
                     coordinates.getLatitude() + 1,
                     coordinates.getHeight() + 0);
         if (weather.equals("SNOW"))
             this.coordinates = new Coordinates(
                     coordinates.getLongitude() + 0,
                     coordinates.getLatitude() + 0,
                     coordinates.getHeight() - 7);

         Simulator.printer.println("Tower Says JetPlane " +
            this.name +" ID: ["+ this.id +"] "+ mes.get(weather));
         if (this.coordinates.getHeight() == 0) {
             Simulator.printer.println("Tower Says JetPlane " +
                     this.name + " ID: [" + this.id + "] is landing");
             this.opTower.unregister( this );
             Simulator.printer.println("Tower Says JetPlane " +
                     this.name +" ID: ["+ this.id +"] is unregistered from Tower");
         }
     }

    public void registerTower(WeatherTower weatherTower) {
         this.opTower = weatherTower;
         this.opTower.register( this );
         Simulator.printer.println("Tower Says JetPlane " +
                 this.name +" ID: ["+ this.id +"] is registered to Tower");
     }
}
