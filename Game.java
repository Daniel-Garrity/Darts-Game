import java.util.Scanner;
public class Game{
    final private Player player1;//used for player 1 score
    final private Player player2;//used for player 2 score
    private boolean isRunning;//checks game is running
    final private Scanner scanner;//scanner for user input
    private Player currentPlayer;//both current and other player used for swapping whos go it is
    private Player otherPlayer;//^^
    private Player winner;
    //Constructor for Game
    public Game(Player player1, Player player2){
        this.player1 =player1;
        this.player2=player2;
        this.isRunning=true;
        this.scanner= new Scanner(System.in);
    }
    //Method to start and end the game
    public void startGame(){
        System.out.println("Hello and Welcome to darts 501!");
                System.out.println("Would you like to Bull up or choose who throws first, 1 for bull up 2 for choosing to throw first.");
                int playerChoice =scanner.nextInt();
                //checking if players whant to bull up or choose who throws first
                if(playerChoice==1){
                    currentPlayer=bullUp();
                }else if(playerChoice==2){
                    currentPlayer= throwFirst();
                }

                if (currentPlayer == player1) {
                    otherPlayer = player2;
                } else {
                    otherPlayer = player1;
                }
                //Keeps the game going until the break
                 while(isRunning){
        playTurn(currentPlayer);

        if (!isRunning) break; // someone won, stop the game

        // Swap players for the next turn
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
    }
                endGame(winner);//calls method to end game
    }
    
    //Method which lets players choose who to throw up
    public Player throwFirst(){
        Player firstPlayer = null;

    while (firstPlayer == null) { // keep asking until valid input
        System.out.println("Choose who to throw first: 1 for " + player1.getName() + " or 2 for " + player2.getName());
        int playerTurn = scanner.nextInt();

        if (playerTurn == 1) {
            firstPlayer = player1;
        } else if (playerTurn == 2) {
            firstPlayer = player2;
        } else {
            System.out.println("Invalid choice. Try again.");
        }
    }
        System.out.println(firstPlayer.getName()+ "to throw first, GAME ON!");
        return firstPlayer;
    }

    //Method which lets players bull up to decide who throws first
   public Player bullUp() {
    boolean bullUpFinished = false;
    Player firstPlayer = null;

    while (!bullUpFinished) {//keep going until bull up is completed
        System.out.println("--- Bull Up Phase ---");

        int throw1 = getBullThrow(player1);
        int throw2 = getBullThrow(player2);

        
        if (throw1 > throw2) {
            firstPlayer = player1;
            System.out.println(player1.getName() + " to throw first, GAME ON!");
            bullUpFinished = true;
        } else if (throw2 > throw1) {
            firstPlayer = player2;
            System.out.println(player2.getName() + " to throw first, GAME ON!");
            bullUpFinished = true;
        } else {
            System.out.println("It's a tie! Throw again.");//if both darts are outside bull or 25 continue bull up phase
        }
    }

    return firstPlayer;//return the starting player
}

//Helper method with logic to work out which player got closest to bull
private int getBullThrow(Player player) {
    System.out.println(player.getName() + ", choose your bull throw:");
    System.out.println("1 -> Outer Bull (25 points)");
    System.out.println("2 -> Inner Bull (50 points)");
    System.out.println("3 -> Miss / Outside (0 points)");

    while (true) {
        System.out.print("Enter 1, 2, or 3: ");

        // Check if input is an integer
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number (1, 2, or 3).");
            scanner.next(); // consume invalid input
            continue;
        }

        int choice = scanner.nextInt();

    switch (choice) {
        case 1: return 25;   // outer bull
        case 2: return 50;   // inner bull
        case 3: return 0;    // missed
        default:
                System.out.println("Invalid choice! Please enter 1, 2, or 3.");
    }
    }
}
//Method to take in users 3 dart score and change whos throw it is.
private void playTurn(Player player) {
    System.out.println("\n" + player.getName() + "'s turn! Current score: " + player.getScore());
    int startScore = player.getScore(); // save score at start of turn
    int turnTotal = -1;

    // Keep asking until a valid turn total is entered
    while (true) {
        System.out.print("Enter total score for this turn (sum of 3 darts, 1-180): ");

        // Validate input is an integer
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number between 1 and 180.");
            scanner.next(); // consume invalid input
            continue;
        }

        turnTotal = scanner.nextInt();

        // Validate turn total range
        if (turnTotal < 1 || turnTotal > 180) {
            System.out.println("Invalid turn total! Must be between 1 and 180. Try again.");
            continue; // loop again for input
        }

        // valid input
        break;
    }

    int newScore = player.getScore() - turnTotal;

    // Bust check
    if (newScore < 0) {
        System.out.println("Bust! Score resets to " + startScore);
        player.setScore(startScore);
    } else {
        player.setScore(newScore);
    }
    // Table display for players
    System.out.println("\n--- Turn Summary ---");
    System.out.printf("| %-10s | %-5s |\n", "Player", "Turn Total");
    System.out.println("----------------------");
    System.out.printf("| %-10s | %-5d |\n", player.getName(), turnTotal);
    System.out.println("----------------------");
    System.out.println(player.getName() + "'s new score: " + player.getScore());

    // Win condition
    if (player.getScore() == 0) {
        winner=player;
        isRunning = false;
    
}
}

public void endGame(Player winner){
System.out.println("Congratulations! "+ winner.getName()+ " wins!");
    //add in some player stats at some stage e.g 3 dart average etc
}
}

