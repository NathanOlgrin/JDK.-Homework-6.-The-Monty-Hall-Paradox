package org.example;
import java.util.HashMap;
import java.util.Random;
public class MontyHallParadox {

    private static final int NUM_DOORS = 3;
    private static final int CAR_DOORS = 1;

    HashMap<Integer, String> resultsMap;

    private final Random random;

    public MontyHallParadox() {
        random= new Random();
        resultsMap = new HashMap<>();
    }

    public int chooseDoor(){
        return random.nextInt(NUM_DOORS) +1;
    }

    public int openDoor(int chosenDoor){
        int openedDoor;
        do{
            openedDoor = random.nextInt(NUM_DOORS)+1;
        } while (openedDoor == chosenDoor || openedDoor == CAR_DOORS);
        return openedDoor;
    }

    public int shitchDoor(int chosenDoor, int openedDoor){
        int newDoor;
        do{
            newDoor = random.nextInt(NUM_DOORS)+1;
        } while (newDoor == chosenDoor || newDoor == openedDoor);
        return  newDoor;
    }

    public void playGame(int iterations){
        int switchWins = 0;
        int stayWins = 0;
        for (int i = 0; i < iterations; i++) {
            int chosenDoor = chooseDoor();
            int openedDoor = openDoor(chosenDoor);
            int shitchedDoor = shitchDoor(chosenDoor, openedDoor);

            if(chosenDoor == CAR_DOORS){
                stayWins++;
            } else if(shitchedDoor == CAR_DOORS){
                switchWins++;
            }

            if(shitchedDoor == CAR_DOORS){
                resultsMap.put(i, "player won");
            } else {
                resultsMap.put(i, "player lost");
            }
        }
        getResult(iterations, switchWins, stayWins);
    }

    public void getResult(int iterations, int switchWins, int stayWins){
        double switchWintPercent = (switchWins * 100.0) / iterations;
        double stayWinPercent = (stayWins * 100.0) / iterations;

        System.out.println("При оставлении начальной двери игрок победил: " + stayWins + " раз из " + iterations + ". В процентах: " + stayWinPercent + "%");
        System.out.println("При смене двери игрок победил: " + switchWins + " раз из " + iterations + ". В процентах: " + switchWintPercent + "%");
    }
}
