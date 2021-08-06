package com.viktoria.simplifier;

public class TextSimplifierApp {

    public static void main(String[] args) {
//        System.out.println(removeC("success"));
//        System.out.println(removeC("clock"));

//        System.out.println(removeDoubleLetter("ooo"));
//        System.out.println(removeDoubleLetter("oou"));
//        System.out.println(removeDoubleLetter("iee"));
//        System.out.println(removeDoubleLetter("coffee"));
//        System.out.println(removeDoubleLetter("cofffffffffee"));

//        System.out.println(removeTailE("The beautiful table e"));

        System.out.println(simplifyText("cacao and the coffffee"));
    }

    public static String simplifyText(String input) {
        String originalText = input;

        input = removeC(input);
        input = removeDoubleLetter(input);
        input = removeTailE(input);
        input = removeArticles(input, originalText);

        return input;
    }

    private static String removeC(String input) {
        while (true) {
            int cIndex = -1;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'c' || input.charAt(i) == 'C') {
                    String output = input.substring(0, i);

                    String part = "" + input.charAt(i);
                    if (i + 1 < input.length()) {
                        part += input.charAt(i + 1);
                    }

                    int tailIndex = i + 2;
                    if (part.equals("ci")) {
                        output += "si";
                    } else if (part.equals("ce")) {
                        output += "se";
                    } else if (part.equals("ck")) {
                        output += "k";
                    } else {
                        output += "k";
                        tailIndex = i + 1;
                    }

                    if (tailIndex < input.length()) {
                        output += input.substring(tailIndex);
                    }

                    input = output;
                    cIndex = i;

                    break;
                }
            }


            if (cIndex == -1) {
                return input;
            }
        }
    }

    public static String removeDoubleLetter(String input) {
        while (true) {
            int doubleIndex = -1;
            for (int i = 0; i < input.length() - 1; i++) {
                char currChar = input.charAt(i);
                char nextChar = input.charAt(i + 1);
                if (currChar == nextChar) {
                    String output = input.substring(0, i);

                    if (currChar == 'e') {
                        output += "i";
                    } else if (currChar == 'o') {
                        output += "u";
                    } else {
                        output += currChar;
                    }

                    int tailIndex = i + 2;
                    if (tailIndex < input.length()) {
                        output += input.substring(tailIndex);
                    }

                    input = output;
                    doubleIndex = i;

                    break;
                }
            }

            if (doubleIndex == -1) {
                return input;
            }
        }

//        int doubleIndex;
//        do {
//            doubleIndex = -1;
//            for (int i = 0; i < input.length() - 1; i++) {
//                char currChar = input.charAt(i);
//                char nextChar = input.charAt(i + 1);
//                if (currChar == nextChar) {
//                    String output = input.substring(0, i);
//
//                    if (currChar == 'e') {
//                        output += "i";
//                    } else if (currChar == 'o') {
//                        output += "u";
//                    } else {
//                        output += currChar;
//                    }
//
//                    int tailIndex = i + 2;
//                    if (tailIndex < input.length()) {
//                        output += input.substring(tailIndex);
//                    }
//
//                    input = output;
//                    doubleIndex = i;
//
//                    break;
//                }
//            }
//
//        } while (doubleIndex != -1);
//
//        return input;
    }

    public static String removeTailE(String input) {
        // "The beautiful table e" ---> [The, table, e]

        StringBuilder builder = new StringBuilder();
        String[] words = input.split(" ");
        for (String word : words) {
            if (word.endsWith("e") && word.length() > 1) {
                word = word.substring(0, word.length() - 1);
            }

            builder.append(word);
            builder.append(" ");
        }

        String output = builder.toString();
        output = output.trim();

        return output;
    }

    public static String removeArticles(String input, String originalText) {
        String[] originalWords = originalText.split(" ");
        String[] inputWords = input.split(" ");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < inputWords.length; i++) {
            String inputWord = inputWords[i];
            String originalWord = originalWords[i];

            if ("a".equals(inputWord) || "an".equals(inputWord)) {
                continue;
            }

            if ("th".equals(inputWord) && "the".equals(originalWord)) {
                continue;
            }

            builder.append(inputWord).append(" ");
        }

        String output = builder.toString().trim();

        return output;
    }
}
