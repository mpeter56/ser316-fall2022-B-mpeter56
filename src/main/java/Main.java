public class Main {
    
    /**
     * prints the results.
     * @param round the current round
     * @param xpGained the amount of experience gained
     * @param gamePlay the current gamePlay
     */
    private static void printResults(int round, int xpGained, GamePlay gamePlay) {
        System.out.println("\tRound " + round);
        if (gamePlay.player.health > 0) {
            System.out.println("\t\tYou gained " + gamePlay.play() 
                + " experience points during this round!!!!\n");
        }
        if (gamePlay.player.health <= 0) {
            System.out.println("\t\tBut your player died. Better luck next time.\n\n");
        }
    }
    
    /**
     * plays the game.
     * @param gamePlay the gamePlay to be played
     */
    private static void playGame(GamePlay gamePlay) {
        for (int round = 1; round < 9; round++) {
            int xp = gamePlay.play();
            printResults(round, xp, gamePlay);
            if (gamePlay.player.health <= 0) {
                break;
            }
        }
    }
    
    /**
     * this is the main method which plays different scenarios of the game.
     * @param args input arguments
     */
    public static void main(String[] args) {

        System.out.println("**** GAME A ****");
        GamePlay gamePlay = new GamePlay();
        playGame(gamePlay);

        System.out.println("**** GAME B ****");
        GamePlay gamePlay1 = new GamePlay(new Wizard());
        gamePlay1.player.health = 10;
        playGame(gamePlay1);

        System.out.println("**** GAME C ****");
        GamePlay gamePlay2 = new GamePlay(new Barbarian(), new Wizard());
        gamePlay2.opponents.get(0).health = 5;
        playGame(gamePlay2);

    }
    
}