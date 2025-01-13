package CampusTycoon.UI.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.io.*;
import java.util.*;




public class Leaderboard {

    public static Map<String,Integer> leaderboardmap;


    /**
     *
     * Assessment 2
     *
     * Converts a string into a Map<String, Integer>
     * The input string should be in the format "{key1=value1, key2=value2, etc.}"
     * This is done as we can only save with primitive data types
     * @param input the string representation of the nao
     * @return a Map<String, Integer> containing the parsed key-value pairs
     */
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


    /**
     *
     * Assessment 2
     *
     * Loads the leaderboard data from storage into the game.
     *
     * leaderboard data in the
     * preferences file, is converted into a map of player names and scores,
     * and assigned to the `leaderboardmap` field. The saved data is stored and
     * retrieved using the name "scores".
     *
     * @throws IOException if an error occurs during the loading process.
     */
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


    /**
     * Assessment 2
     *
     * Updates the score of a player in the leaderboard.
     * If the player already exists and their new score is higher than the currently saved score,
     * the score is updated. If the player does not exist, they are added with the specified score.
     *
     * @param player   the name of the player whose score is being updated
     * @param newScore the new score to be potentially added or updated for the player
     */
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
