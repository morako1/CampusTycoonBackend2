package CampusTycoon.UI.Components;

import java.io.*;
import java.util.*;


//Couldn't work out how to get a text file to willingly connect so here is the leaderboard hashmap generator and update functions.
//Once I have worked out why I can't read and write to a text file this all works fine.
//Also need to implement a text field in the save score area have not looked into that just yet.

public class Leaderboard {


    // Load the leaderboard from the file
    private static Map<String, Integer> loadLeaderboard() throws IOException {
        Map<String, Integer> leaderboard = new HashMap<>();
        File file = new File("leaderboard.txt");

        //If the file doesn't exist, create it
        if(!file.exists()) {
            file.createNewFile();
            return leaderboard;

        }

        //File did exist so read from it
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split leaderboard contents into key and value pairs
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    leaderboard.put(name, score);
                }
            }
        }

        return leaderboard;
    }

    // Save the leaderboard back to the file
    private static void saveLeaderboard(Map<String, Integer> leaderboard) throws IOException {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(leaderboard.entrySet());

        //Sort the entries by score in descending order
        sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("leaderboard.txt"))) {
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        }
    }

    //Display the leaderboard in a readable format
    private static void displayLeaderboard(Map<String, Integer> leaderboard) {
        // Sort the entries by score in descending order
        List<Map.Entry<String, Integer>> sortedEntries = leaderboard.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
            .toList();

        // Display each entry with its ranking
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println(rank + ". " + entry.getKey() + ": " + entry.getValue());
            rank++;
        }
    }


    // Update a player's score (or add a new player if they are new)
    private static void updateScore(Map<String, Integer> leaderboard, String player, int newScore) {
        if (leaderboard.containsKey(player)){
            int currentScore = leaderboard.get(player);
            if(newScore > currentScore){
                //if score is greater than previous score
                leaderboard.put(player, newScore);
            }else{
                //if player does not exist
                leaderboard.put(player,newScore);
            }

        }
    }
}
