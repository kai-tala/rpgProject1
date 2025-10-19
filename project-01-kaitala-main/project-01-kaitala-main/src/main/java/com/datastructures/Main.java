/**
 * Class: 44-242 Data Structures
 * Author: Kai Tala
 * Description: Program that simulates a pen and paper RPG game
 * Due: 12/oct/2025
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any other student.
   I have not given my code to any other student and will not share this code
   with anyone under any circumstances.
*/

package com.datastructures;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void compete(Character char1, Character char2, String event){
        int char1Roll;
        int char2Roll;
        
        switch (event) {
            case "Hacking":
                char1Roll = char1.skillRoll("programming", "smarts");
                char2Roll = char2.skillRoll("programming", "smarts");
                break;
            case "Foosball":
                char1Roll = char1.skillRoll("sports", "buffness");
                char2Roll = char2.skillRoll("sports", "buffness");
                break;
            case "Politicking":
                char1Roll = char1.skillRoll("bluffing", "smarminess");
                char2Roll = char2.skillRoll("bluffing", "smarminess");
                break;
            case "Crossing the Street":
                char1Roll = char1.skillRoll("sprinting", "common_sense");
                char2Roll = char2.skillRoll("sprinting", "common_sense");
                break;
            default:
                char1Roll = char1.attrRoll("liveness");
                char2Roll = char2.attrRoll("liveness");
                break;
        }
        
        if(char1Roll > char2Roll){
            char1.addWin(event);
            char2.addLoss(event);
        }
        else if(char1Roll < char2Roll){
            char2.addWin(event);
            char1.addLoss(event);
        }
        
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        ArrayList<Character> players = new ArrayList<>();
        ArrayList<String> events = new ArrayList<>();
        int wins;
        
        Character fry = new Character("fry.txt");
        players.add(fry);
        Character leela = new Character("leela.txt");
        players.add(leela);
        Character zoidburg = new Character("zoidberg.txt");
        players.add(zoidburg);
        
        events.add("Hacking");
        events.add("Foosball");
        events.add("Politicking");
        events.add("Crossing the Street");
        events.add("Staring Contest");
        
        for (int i = 0; i < players.size(); i++) {
            
            for (int j = i+1; j < players.size(); j++) {
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        compete(players.get(i), players.get(j), events.get(k));
                    }
                }
            }
        }
        
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < events.size(); j++) {
                wins = players.get(i).countWins(events.get(j));
                
                for (int achCount = 1; achCount < wins; achCount+=2){
                    System.out.println(players.get(i).getName() + " got the " + events.get(j) + " " + achCount + " achievement");
                }
            }
        }
        
    }
}