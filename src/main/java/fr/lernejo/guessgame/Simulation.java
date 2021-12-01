package fr.lernejo.guessgame;

import fr.lernejo.logger.*;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Simulation {

    private final Logger logger = LoggerFactory.getLogger("simulation");
    private final Player player;
    private long numberToGuess;

    public Simulation(Player player) {
        this.player = player;
    }

    public void initialize(long numberToGuess) {
        this.numberToGuess = numberToGuess;
    }

    /**
     * @return true if the player have guessed the right number
     */
    private boolean nextRound() {
        long numberUser = player.askNextGuess();
        if(numberUser == this.numberToGuess) {
            return true;
        } else if(numberUser <= this.numberToGuess) {
            player.respond(true);
        } else {
            player.respond(false);
        }
        return false;
    }

    /**
     * old code
     */
    /*public void loopUntilPlayerSucceed() {
        while (!nextRound());
    }*/

    public void loopUntilPlayerSucceed(long maxLoop) {
        boolean end = nextRound();
        int nbLoop = 1;
        long debTime = System.currentTimeMillis();
        while (!end && nbLoop < maxLoop){
            end = nextRound();
            nbLoop++;
        }
        long endTime = System.currentTimeMillis();
        String message;
        if (end) message = "\tYou won!";
        else message = "\tYou failed... Next time maybe!";
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");

        String playTime = sdf.format(endTime - debTime);
        logger.log(message + "\nThe play takes " + playTime);
    }
}
