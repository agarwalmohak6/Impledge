import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\Mohak\\IdeaProjects\\Impledge\\src\\Input_01.txt";
        long startTime = System.currentTimeMillis();
        List<String> words = getFromFile(inputFile);
        Set<String> wordSet = new HashSet<>(words);
        List<String> compound_Words = helper(words, wordSet);

        compound_Words.sort(Comparator.comparing(String::length).reversed());

        long endTime = System.currentTimeMillis();

        System.out.println("Longest Compound Word: " + compound_Words.get(0));
        System.out.println("Second Longest Compound Word: " + compound_Words.get(1));
        System.out.println("Time taken to process file " + inputFile + ": " + (endTime - startTime) + " milliseconds");
    }
    private static List<String> getFromFile(String inputFile) {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
    private static List<String> helper(List<String> words, Set<String> wordSet) {
        List<String> compound_Words = new ArrayList<>();
        for (String word : words) 
            if (isCompounded_Word(word, wordSet)) 
                compound_Words.add(word);
        return compound_Words;
    }
    private static boolean isCompounded_Word(String word, Set<String> wordSet) {
        int N=word.length();
        for (int i = 1; i < N; i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
            if ((wordSet.contains(suffix) || isCompounded_Word(suffix, wordSet)) && wordSet.contains(prefix)) 
                return true;
        }
        return false;
    }
}