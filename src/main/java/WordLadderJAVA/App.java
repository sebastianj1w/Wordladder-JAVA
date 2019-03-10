package WordLadderJAVA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class App 
{
    public static void main(String[] args) throws Exception
    {
        Set<String> wordSet = new TreeSet<String>();

        String path = "./dictionary.txt";
        File dictFile = new File(path);
        if (!dictFile.exists()) {
            System.out.println("dict not exits");
        }

        BufferedReader reader = new BufferedReader(new FileReader(dictFile));
        String tempString = null;
        int line = 1;
        while ((tempString = reader.readLine()) != null) {
//            words[line] = tempString;
            wordSet.add(tempString);
            line++;
        }
//        while (line >= 0) {
//            System.out.println("line " + line + ": " + words[line]);
//            line--;
//        }
        reader.close();

//        Arrays.sort(words);

        String startingWord, endingWord;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter starting word");
        startingWord = br.readLine();
        System.out.println("Enter ending word");
        endingWord = br.readLine();
        if (!wordSet.contains(endingWord)) {
            System.out.println("ending word not exits");
            return;
        }
        System.out.printf("starting word:%s\n",startingWord);
        System.out.printf("ending word:%s\n",endingWord);

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
                        System.out.println("found");
                        curList.push(newWord);
                        System.out.println(curList);
                        return;
                    }
                    if (wordSet.contains(newWord)) {
                        Stack<String> newList = (Stack<String>) curList.clone();
                        newList.push(newWord);
                        queue.offer(newList);
                    }
                }
            }
        }
        System.out.println("not found");
    }
}
