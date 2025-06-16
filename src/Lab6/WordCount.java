package Lab6;

import java.util.ArrayList;
import java.util.List;

public class WordCount extends FileProcessor<List<Integer>> {
    private int charCount;
    private int wordCount;
    private int lineCount;

    @Override
    protected void startFile() {
        charCount = 0;
        wordCount = 0;
        lineCount = 0;
    }

    @Override
    protected void processLine(String line) {
        lineCount++;
        charCount += line.length();

        boolean prevWasWhitespace = true;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (!Character.isWhitespace(c) && prevWasWhitespace) {
                wordCount++;
            }

            prevWasWhitespace = Character.isWhitespace(c);
        }
    }

    @Override
    protected List<Integer> endFile() {
        List<Integer> result = new ArrayList<>(3);

        result.add(charCount);
        result.add(wordCount);
        result.add(lineCount);

        return result;
    }
}

