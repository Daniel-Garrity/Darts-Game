import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        // Ask for player names
        System.out.print("Enter name for Player 1: ");
        String name1 = scanner.nextLine();

        System.out.print("Enter name for Player 2: ");
        String name2 = scanner.nextLine();

        // Create player objects
        Player player1 = new Player(name1, 501);
        Player player2 = new Player(name2, 501);

        // Start the game
        Game game = new Game(player1, player2);
        game.startGame();

        scanner.close();
    }
}
