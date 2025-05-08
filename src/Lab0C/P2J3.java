package Lab0C;

import java.util.ArrayList;

public class P2J3 {
    public static String pancakeScramble(String text) {
        int length = text.length();

        if (length == 0) return "";

        boolean evenLength = (length & 1) == 0;
        char[] pancakeText = new char[length];

        for (int i = 0; i < length ; i++) {
            char c = text.charAt(i);

            boolean evenIndex = (i & 1) == 0;

            if (evenIndex && evenLength) {
                pancakeText[length / 2 + i / 2] = c;
            } else if (!evenIndex && evenLength) {
                pancakeText[length / 2 - i / 2 - 1] = c;
            } else if (evenIndex) {
                pancakeText[length / 2 - i / 2] = c;
            } else {
                pancakeText[length / 2 + i / 2 + 1] = c;
            }
        }

        return new String(pancakeText);
    }

    public static void reverseAscendingSubarrays(int[] items) {
        if (items.length == 0) return;

        int maxIndex = 0;
        int startIndex = 0;

        while (startIndex < items.length - 1) {
            // Find max index before new sequence starts
            for (int currentIndex = startIndex + 1; currentIndex < items.length; currentIndex++) {
                // Break if descending
                if (items[currentIndex - 1] > items[currentIndex]) {
                    maxIndex = currentIndex - 1;
                    break;
                }

                maxIndex++;
            }

            int medianIndex = maxIndex - (maxIndex - startIndex) / 2;

            // Reverse array segment
            for (int swapIndex = startIndex; swapIndex < medianIndex; swapIndex++) {
                int offsetIndex = maxIndex - swapIndex + startIndex;
                int temp = items[offsetIndex];

                items[offsetIndex] = items[swapIndex];
                items[swapIndex] = temp;
            }

            startIndex = ++maxIndex;
        }
    }

    public static String reverseVowels(String text) {
        int length = text.length();

        if (length == 0) return "";

        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            char c = Character.toLowerCase(text.charAt(i));

            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') continue;

            indices.add(i);
        }

        int indicesCount = indices.size();
        char[] reversedVowelsText = text.toCharArray();

        for (int i = 0; i < indicesCount / 2; i++) {
            int startSwapIndex = indices.get(i);
            int endSwapIndex = indices.get(indicesCount - i - 1);

            char startChar = text.charAt(startSwapIndex);
            char endChar = text.charAt(endSwapIndex);

            reversedVowelsText[startSwapIndex] = Character.isUpperCase(startChar) ?
                            Character.toUpperCase(endChar) : Character.toLowerCase(endChar);

            reversedVowelsText[endSwapIndex] = Character.isUpperCase(endChar) ?
                    Character.toUpperCase(startChar) : Character.toLowerCase(startChar);
        }

        return new String(reversedVowelsText);
    }
}
