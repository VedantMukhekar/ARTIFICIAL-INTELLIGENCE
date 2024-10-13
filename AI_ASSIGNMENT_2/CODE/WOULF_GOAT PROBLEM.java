import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class wolf_goat_cabbage_problem {

    private static Set<String> leftSide = new HashSet<>();
    private static Set<String> rightSide = new HashSet<>();
    private static String farmerSide = "left";

    public static void main(String[] args) {
        initializeGame();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Wolf, Goat, and Cabbage game!");
        System.out.println();
        displayState();

        while (!isGameWon()) {
            System.out.print("What do you want to move? (wolf, goat, cabbage, none): ");
            String move = scanner.nextLine().trim().toLowerCase();

            if (move.equals("none")) {
                move = null;
            } else if (!move.equals("wolf") && !move.equals("goat") && !move.equals("cabbage")) {
                System.out.println("Invalid item! Please choose wolf, goat, cabbage, or none.");
                continue;
            }

            String result = moveItem(move);
            System.out.println(result);
            displayState();

            if (result.startsWith("Move not allowed")) {
                break;
            }
        }

        if (isGameWon()) {
            System.out.println("Congratulations! You won the game!");
        } else {
            System.out.println("Game over! Try again.");
        }

        scanner.close();
    }

    private static void initializeGame() {
        leftSide.add("farmer");
        leftSide.add("wolf");
        leftSide.add("goat");
        leftSide.add("cabbage");
    }

    private static boolean isSafe(Set<String> side) {
        return !(side.contains("wolf") && side.contains("goat") && !side.contains("farmer")) &&
               !(side.contains("goat") && side.contains("cabbage") && !side.contains("farmer"));
    }

    private static String moveItem(String item) {
        if (farmerSide.equals("left")) {
            if (item != null) {
                if (!leftSide.contains(item)) {
                    return "Invalid move!";
                }
                leftSide.remove(item);
                rightSide.add(item);
            }
            farmerSide = "right";
            leftSide.remove("farmer");
            rightSide.add("farmer");
        } else {
            if (item != null) {
                if (!rightSide.contains(item)) {
                    return "Invalid move!";
                }
                rightSide.remove(item);
                leftSide.add(item);
            }
            farmerSide = "left";
            rightSide.remove("farmer");
            leftSide.add("farmer");
        }

        if (!isSafe(leftSide) || !isSafe(rightSide)) {
            return "Move not allowed! Items left alone unsafely.";
        }
        return "Move successful!\n";
    }

    private static void displayState() {
        // System.out.println("Left Side: " + leftSide);
        // System.out.println("Right Side: " + rightSide);
        System.out.print("Left Side: ");
        for(String s: leftSide){
            System.out.print(s+", ");
        }
        System.out.print("  |  Right Side:  ");
        for(String s: rightSide){
            System.out.print(s+", ");
        }
        System.out.println();
        System.out.println("Farmer is on the " + farmerSide + " side.");
    }

    private static boolean isGameWon() {
        return leftSide.isEmpty();
    }
}
