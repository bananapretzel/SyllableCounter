import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * An application that will take words from a file, line by line, and produce numbers correlating to the number of
 * syllables per word.
 *
 * @author Haani Somerville, Sam Hansard, Max Petre, and Casey Cotton.
 */
public class Syllables {


    /**
     * Start point of the program. A file is needed to be read in as input. The StandardCharsets parameter for the
     * Scanner object will need to be switched to another format like UTF-16 if the output seems nonsensical.
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        List<String> inputList = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (line.isEmpty()) {
                continue;
            } else {
                inputList.add(line);
            }
        }
        scan.close();
        for (String s : inputList) {
            System.out.print(s + " --- ");
            System.out.print(countSyllableGroups(s));
            System.out.println();
           //System.out.println(countSyllableGroups(s));
        }
    }

    /**
     * The primary method of the class which does all the computing for syllable counting. A String is given as a
     * parameter and the method then checks, character by character, if a vowel is detected. If one has been detected
     * it will then further check if it satisfies the criteria needed for it to be counted as a syllable.
     *
     * @param str a string representing the word for which to count syllables.
     * @return an int representing an estimate of the number of vowels detected in the string.
     */
    public static int countSyllableGroups(String str) {
        boolean seq = false;
        int count = 0;
        int pos = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = Character.toLowerCase(str.charAt(i));
            pos++;
            // if vowel or 'y'
            if (c == 'a' | c == 'e' | c == 'i' | c == 'o' | c == 'u' | c == 'y') {
                if (!seq) {
                    seq = true;
                    // if last char and the char is e and count is greater than one, do nothing
                    if (pos == str.length() && c == 'e' && count >= 1) {
                        continue;
                    } else if (i >= 1 && str.charAt(i - 1) == 'a' | str.charAt(i - 1) == 'e' |
                            str.charAt(i - 1) == 'i' | str.charAt(i - 1) == 'o' | str.charAt(i - 1) == 'u' |
                            str.charAt(i - 1) == 'y') {
                        continue;
                    } else {
                        count++;
                    }
                } else { //else switch seq to false
                    seq = false;
                }
            } else {
                seq = false;
            }
        }
        if (str.length() >= 4) {
            String lastFour = str.substring(str.length() - 4);
            if (lastFour.substring(2, 4).equals("le")) {
                if (!(lastFour.charAt(1) == 'a' || lastFour.charAt(1) == 'e' || lastFour.charAt(1) == 'i' ||
                        lastFour.charAt(1) == 'o' || lastFour.charAt(1) == 'u' || lastFour.charAt(1) == 'y')) {
                    count++;
                }
            }
            if (lastFour.substring(1, 4).equals("ing")) {
                if (lastFour.charAt(0) == 'a' || lastFour.charAt(0) == 'e' || lastFour.charAt(0) == 'i' ||
                        lastFour.charAt(0) == 'o' || lastFour.charAt(0) == 'u' || lastFour.charAt(0) == 'y') {
                    count++;
                }
            }
            if (lastFour.substring(2, 4).equals("es")) {
                if (lastFour.charAt(1) == 't' || lastFour.charAt(1) == 'p' || lastFour.charAt(1) == 'd')
                count--;
            }
            if (lastFour.equals("ised")) {
                count--;
            }
        }
        return count;
    }
}
