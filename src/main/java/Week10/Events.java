package Week10;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Events {
    private static final String[] eventTypes = {"Login", "Logout", "Purchase", "ViewPage", "Error"};

    public void generateEventsFile(String filename, int numRecords){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            Random random = new Random();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for(int i = 0; i < numRecords; i++){
                long currentTime = System.currentTimeMillis();
                long randomTime = currentTime - (long)(random.nextDouble() * 30 * 24 * 60 * 60 * 1000);
                Date randomDate = new Date(randomTime);
                String timestamp = dateFormat.format(randomDate);
                String eventType = eventTypes[random.nextInt(eventTypes.length)];
                int userId = random.nextInt(1000);

                writer.write(timestamp + "| Event Type: " + eventType + "| User ID: " + userId + "\n");
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void printEventsFromFile(String filename){
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<String> readEventsFromFile(String filename){
        ArrayList<String> events = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = reader.readLine()) != null){
                events.add(line);
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        return events;
    }

    public static void main(String[] args){
        Events events1 = new Events();

        String filename = "events.txt";
        int numRecords = 5;
//        events1.generateEventsFile(filename, numRecords);
//        events1.printEventsFromFile(filename);
        ArrayList<String> eventRecords = events1.readEventsFromFile(filename);
        for(String event : eventRecords){
            System.out.println(event);
        }
    }
}
