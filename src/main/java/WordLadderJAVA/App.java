package WordLadderJAVA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class App
{
    public static void main(String[] args) throws Exception {
        System.out.println("Loading dict file");
        Set<String> wordSet = loadDict("./dictionary.txt");

        String startingWord = readWord(0);
        String endingWord  = readWord(1);

        if (checkValidity(startingWord,endingWord,wordSet) == 1) {
            return;
        }

        System.out.printf("starting word:%s\n",startingWord);
        System.out.printf("ending word:%s\n",endingWord);

        Stack<String> ans = findLadder(startingWord, endingWord, wordSet);
        if (ans.isEmpty()) {
            System.out.println("not found");
        }
        else {
            System.out.println("found");
            System.out.println(ans);
        }
    }

    public static Set<String> loadDict(String path) throws Exception{
        File dictFile = new File(path);

        if (!dictFile.exists()) {
            System.out.println("dict not exits");
            return new TreeSet<>();
        }

        Set<String> wordSet = new TreeSet<String>();

        BufferedReader reader = new BufferedReader(new FileReader(dictFile));
        String tempString = null;
        int line = 1;

        while ((tempString = reader.readLine()) != null) {
            wordSet.add(tempString);
            line++;
        }

        reader.close();
        return wordSet;
    }

    public static String readWord(int mode) throws Exception{
        String word;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if (mode == 0) {
            System.out.println("Enter starting word");
            word = br.readLine();
        }
        else {
            System.out.println("Enter ending word");
            word = br.readLine();
        }
        return word;
    }

    public static Stack<String> findLadder(String startingWord, String endingWord, Set<String> wordSet) {
        Queue<Stack<String>> queue = new LinkedList<Stack<String>>();
        Stack<String> beginningList = new Stack<String>();
        beginningList.push(startingWord);
        queue.offer(beginningList);
        int len = startingWord.length();

        while (!queue.isEmpty()) {
            Stack<String> curList = queue.poll();
            String curWord = curList.peek();
            for (int n = 0; n < len; n++) {
                for (int i = 0; i < 26; i++) {
                    if ((char)(i+97) == curWord.charAt(n)) {
                        continue;
                    }
                    String newWord = curWord.replaceFirst(String.valueOf(curWord.charAt(n)),String.valueOf((char)(i+97)));
                    if (newWord.equals(endingWord)) {
//                        System.out.println("found");
                        curList.push(newWord);
//                        System.out.println(curList);
                        return curList;
                    }
                    if (wordSet.contains(newWord)) {
                        Stack<String> newList = (Stack<String>) curList.clone();
                        newList.push(newWord);
                        queue.offer(newList);
                    }
                }
            }
        }
//        System.out.println("not found");
        return new Stack<String>();
    }

    public static int checkValidity(String start, String end, Set<String> wordSet) {
        if (!wordSet.contains(start)) {
            System.out.println("starting word not exits");
            return 1;
        }

        if (!wordSet.contains(end)) {
            System.out.println("ending word not exits");
            return 1;
        }

        if (start.equals(end)) {
            System.out.println("ending word is the same as starting word");
            return 1;
        }

        if (start.length() != end.length()) {
            System.out.println("length of two words are not equal");
            return 1;
        }

        return 0;
    }
}

