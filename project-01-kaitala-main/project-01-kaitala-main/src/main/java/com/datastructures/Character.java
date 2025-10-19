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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Character extends ObjectNotOwnedException{
    String name;
    ArrayList<String> inventory = new ArrayList<>();
    LinkedList<String> winHistory = new LinkedList<>();
    LinkedList<String> lossHistory = new LinkedList<>();
    HashSet<String> achievements = new HashSet<>();
    HashMap<String, Integer> attributes = new HashMap<>();
    HashMap<String, Integer> skills = new HashMap<>();
    // attributes

    public Character() {
    }

    public Character(String fname) throws FileNotFoundException {
        readCharacterSheet(fname);
    }

    public void readCharacterSheet(String filename) throws FileNotFoundException {
        File chrSh = new File(filename);
        Scanner readSheet = new Scanner(chrSh);
        
        while(readSheet.hasNextLine()){
            String line = readSheet.nextLine();
            if(line.contains("--name"))
                name = readSheet.nextLine();
            
            if(line.contains("--inventory"))
                inventory.add(readSheet.nextLine());
            
            if(line.contains("--attr")){
                String[] split = readSheet.nextLine().split(" ");
                attributes.put(split[0], Integer.valueOf(split[1]));
            }
        }

        


        
    }

    public String getName() {
        return name;
    }

    public void setName(String newname) {
        name = newname;
    }

    public void obtain(String thing) {
        inventory.add(thing);
    }

    public void consume(String thing) {
        if(this.count(thing) == 0)
            throw new ObjectNotOwnedException();
        else
            inventory.remove(thing);
    }

    public int count(String thing) {
        int count = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if(thing.equals(inventory.get(i)))
                count++;
        }
        
        return count;
    }

    public void addWin(String event) {
        winHistory.add(event);
    }

    public void addLoss(String event) {
        lossHistory.add(event);
    }

    public int countWins(String event) {
        int count = 0;
        for (int i = 0; i < winHistory.size(); i++) {
            if(event.equals(winHistory.get(i)))
                count++;
        }
        return count;
    }

    public int countLosses(String event) {
        int count = 0;
        for (int i = 0; i < lossHistory.size(); i++) {
            if(event.equals(lossHistory.get(i)))
                count++;
        }
        return count;
    }

    public Boolean earnAchievement(String achievement) {
        if(achievements.contains(achievement))
            return false;            
        else{
            achievements.add(achievement);
            System.out.println(name + " got the " + achievement + " achievement");
            return true;
        }
    }

    public HashSet<String> achievements() {
        return achievements;
    }

    // The following methods may be helpful for Milestone 8
    
    public int d(int numSides){
        Random rand = new Random();
        
        return rand.nextInt(1, numSides+1);
    }

    public int getAttr(String attribute) {
        if(attributes.containsKey(attribute))
            return attributes.get(attribute);
        return 0;

    }

    public int getSkill(String skill) {
        if(skills.containsKey(skill))
            return skills.get(skill);
        return 0;
    }

    public int attrRoll(String attribute) {
        int attrVal = getAttr(attribute);
        return (attrVal / 5+1) * d(attrVal / 2+1) + (attrVal - 10) / 3;
    }

    public int skillRoll(String skill, String attr) {
        int skillVal = getSkill(skill);
        return attrRoll(attr) + (skillVal / 3);
    }

}
