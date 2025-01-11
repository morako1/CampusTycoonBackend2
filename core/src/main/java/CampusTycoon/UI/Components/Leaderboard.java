package CampusTycoon.UI.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.io.*;
import java.util.*;


//Couldn't work out how to get a text file to willingly connect so here is the leaderboard hashmap generator and update functions.
//Once I have worked out why I can't read and write to a text file this all works fine.
//Also need to implement a text field in the save score area have not looked into that just yet.

public class Leaderboard {

    public static Map<String,Integer> leaderboardmap;


    public static Map<String,Integer> StringToMap(String input){

        if(input.isEmpty()){
            return new HashMap<>();

        }

        // Create variable to store result
        Map<String, Integer> map = new HashMap<>();

        // Remove the brackets
        String trimmedInput = input.substring(1, input.length() - 1);

        // Split the string by commas
        String[] pairs = trimmedInput.split(", ");
        for (String pair : pairs) {
            // Split each pair by "=" to separate key and value
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                int value = Integer.parseInt(keyValue[1].trim());
                // Put the key-value pair into the map
                map.put(key, value);
            }
        }


        return  map;
        }



    // Load the leaderboard from the file
    public static void loadLeaderboard() throws IOException {

        Preferences prefs = Gdx.app.getPreferences("scores");
        String leaderboardString = prefs.getString("scores","");
        System.out.println(leaderboardString+"Saved");
        leaderboardmap =StringToMap(leaderboardString);


    }

    // Save the leaderboard back to the file
    public static void saveLeaderboard() throws IOException {
        Preferences prefs = Gdx.app.getPreferences("scores");
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(leaderboardmap.entrySet());

        //Sort the entries by score in descending order
        sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        prefs.putString("scores",sortedEntries.toString());

        prefs.flush();

    }

    //Display the leaderboard in a readable format
    public static String displayLeaderboard() {
        String output="";

        // Sort the entries by score in descending order
        List<Map.Entry<String, Integer>> sortedEntries = leaderboardmap.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
            .toList();

        // Display each entry with its ranking
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            output+=(rank + ". " + entry.getKey() + ": " + entry.getValue()+"\n");
            //System.out.println(rank + ". " + entry.getKey() + ": " + entry.getValue());
            rank++;
        }
        return output;
    }


    // Update a player's score (or add a new player if they are new)
    public static void updateScore( String player, int newScore) {

        if (leaderboardmap.containsKey(player)){
            int currentScore = leaderboardmap.get(player);
            if(newScore > currentScore){
                //if score is greater than previous score
                leaderboardmap.put(player, newScore);
            }
            else{
                //if  score is less than previous score
                leaderboardmap.put(player,currentScore);
            }

        }
        else{

            leaderboardmap.put(player,newScore);

        }

    }
}
