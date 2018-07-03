package com.avaj.launcher;
/*
 **  Author   : lmucassi
 **  Project  : avaj-launcher
 **  school   : wethinkcode.co.za
 */

import com.avaj.launcher.IOFException.FileIOException;
import com.avaj.launcher.aircraft.AircraftFactory;
import com.avaj.launcher.tower.WeatherTower;

import java.io.*;


public class Simulator extends FileIOException {

    public static PrintWriter printer;
    public static int iter;

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Error: program needs one file name passed as an argument");
            System.out.println("Try: $javac -sourcepath @sources.txt");
            return ;
        }

        AircraftFactory aCraft = new AircraftFactory();
        WeatherTower opTower = new WeatherTower();

        String scenarioFile = args[0];
        File simFile = new File("simulation.txt");

        try {
            printer = new PrintWriter(simFile);
        } catch (FileNotFoundException e) {
            System.out.println("Error: couldn't print to file");
            System.out.println("Error: " + e.getMessage());
            return ;
        }

        if (simFile.exists() && simFile.isDirectory())
            printer.println("");

        try {
            FileInputStream fstream = new FileInputStream(scenarioFile);
            BufferedReader breader = new BufferedReader(new InputStreamReader(fstream));
            String fLine;
            int line = 1;
            String[] splits;

            while ((fLine = breader.readLine()) != null) {
                if (line == 1)
                    try {
                        iter = Integer.parseInt(fLine);
                        if (iter < 0) {
                            System.out.println("Error: line must be an integer and must be positive");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: expected a positive integer");
                        return;
                    }
                else {
                    splits = fLine.split(" ");

                    if (splits.length == 1 && splits[0].isEmpty()) continue;
                    if (splits.length != 5)
                        throw new Exception("Error: ["+ line +"] - must have % parameters only");

                    try {
                        aCraft.newAircraft(splits[0], splits[1], Integer.parseInt(splits[2]),
                                Integer.parseInt(splits[3]),
                                Integer.parseInt(splits[4])).registerTower(opTower);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: ["+ line +"] - parameters 3 to 5 must be positive integers");
                        return ;
                    }
                }
                line++;
            }
            breader.close();
        }
        catch (FileIOException e) {
            System.out.println("Error: "+ e.getMessage());
            return ;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ;
        }

        while (iter > 0) {
            opTower.changeWeather();
            iter--;
        }
        printer.close();
    }
}
