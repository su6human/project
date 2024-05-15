import java.util.*;

public class Main {

    private static Random random = new Random();
    private static HashMap<Integer, String> words = new HashMap<>();
    private static HashMap<String, String> descriptionsMap = new HashMap<>();

    static {
        words.put(0, "солнце");
        words.put(1, "пушка");
        words.put(2, "лампочка");
        words.put(3, "год");
        words.put(4, "время");
        words.put(5, "яйца");
        words.put(6, "ветер");
        words.put(7, "чайник");
        words.put(8, "книга");
        words.put(9, "кактус");

        descriptionsMap.put("солнце", "Самая близкая звезда к земле? ");
        descriptionsMap.put("пушка", "Артилерия стреляет из?");
        descriptionsMap.put("лампочка", "Весит груша нельзя скушать?");
        descriptionsMap.put("год", "Стоит дуб,\n" +
                "В нем двенадцать гнезд,\n" +
                "В каждом гнезде\n" +
                "По четыре яйца,\n" +
                "В каждом яйце\n" +
                "По семи цыпленков.");
        descriptionsMap.put("время", "Пожирает всё кругом:\n" +
                "Зверя, птицу, лес и дом.\n" +
                "Сталь сгрызёт, железо сгложет,\n" +
                "Крепкий камень уничтожит,\n" +
                "Власть его всего сильней,\n" +
                "Даже власти королей.");
        descriptionsMap.put("яйцо", "Без замка, без крышки\n" +
                "Сделан сундучок,\n" +
                "А внутри хранится\n" +
                "Золота кусок.");
        descriptionsMap.put("ветер", "Без голоса кричит,\n" +
                "Без крыльев — а летает,\n" +
                "И безо рта свистит,\n" +
                "И без зубов кусает.");
        descriptionsMap.put("чайник", "В брюшке — баня,\n" +
                "В носу — решето,\n" +
                "Нос — хоботок,\n" +
                "На голове — пупок,\n" +
                "Всего одна рука\n" +
                "Без пальчиков,\n" +
                "И та — на спине\n" +
                "Калачиком.");
        descriptionsMap.put("книга", "Страну чудес откроем мы\n" +
                "И встретимся с героями\n" +
                "В строчках,\n" +
                "На листочках,\n" +
                "Где станции на точках.");
        descriptionsMap.put("кактус", "Ёжик странный у Егорки\n" +
                "На окне сидит в ведерке.\n" +
                "День и ночь он дремлет,\n" +
                "Спрятав ножки в землю.");

    }

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("First player please, enter your name: ");
        String a = sc.nextLine();
        System.out.print("Second player please, enter your name: ");
        String b = sc.nextLine();
        System.out.print("First: " + a + "  Second: " + b);

        String wordA = getRandomWord();
        String wordB = getRandomWord();

        playGame(a, wordA, b, wordB, sc);
    }

    private static void playGame(String playerA, String wordA, String playerB, String wordB, Scanner sc) {
        boolean gameFinished = false;
        Set<Character> guessedLetters = new HashSet<>();

        while (!gameFinished) {
            playerTurn(playerA, wordA, playerB, guessedLetters, sc);
            if (isWordGuessed(wordA, guessedLetters)) {
                System.out.println(playerA + ", you guessed the word! The word is: " + wordA);
                gameFinished = true;
            }

            playerTurn(playerB, wordB, playerA, guessedLetters, sc);
            if (isWordGuessed(wordB, guessedLetters)) {
                System.out.println(playerB + ", you guessed the word! The word is: " + wordB);
                gameFinished = true;
            }
        }
    }

    private static void playerTurn(String currentPlayer, String currentWord, String nextPlayer, Set<Character> guessedLetters, Scanner sc) {
        StringBuilder currentGuessedWord = new StringBuilder("_".repeat(currentWord.length()));

        while (true) {
            System.out.println(currentPlayer + ", try to guess the word: " + currentGuessedWord);
            System.out.println("Question: " + descriptionsMap.get(currentWord));
            System.out.println("Guessed letters: " + guessedLetters);
            System.out.print("Enter a letter: ");
            char guess = sc.next().toLowerCase().charAt(0);

            if (!guessedLetters.add(guess)) {
                System.out.println("You already guessed that letter. Try again.");
                continue;
            }

            boolean found = false;
            for (int i = 0; i < currentWord.length(); i++) {
                if (currentWord.charAt(i) == guess) {
                    currentGuessedWord.setCharAt(i, guess);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Wrong. Turn goes to " + nextPlayer + "!");
                break;
            }

            System.out.println("Word: " + currentGuessedWord);

            if (currentGuessedWord.toString().equals(currentWord)) {
                System.out.println(currentPlayer + ", you guessed the word! The word is: " + currentWord);
                break;
            }
        }
    }

    private static boolean isWordGuessed(String word, Set<Character> guessedLetters) {
        for (char letter : word.toCharArray()) {
            if (Character.isAlphabetic(letter) && !guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }

    public static String getRandomWord() {
        return words.get(random.nextInt(10));
    }
}