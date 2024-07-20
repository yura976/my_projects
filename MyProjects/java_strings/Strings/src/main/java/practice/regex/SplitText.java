package practice.regex;

public class SplitText {

  public static void main(String[] args) {
    String text = "John, Jack, Bob, Bill ";
    System.out.println(splitTextIntoWords(text));

  }

  public static String splitTextIntoWords(String text) {
    String subText = text.replaceAll("[^A-Za-z\\s]", "");
    String [] textString = subText.split("\\s+");
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < textString.length; i++) {
      builder.append(textString[i]);
      if (i != textString.length - 1) {
        builder.append(System.lineSeparator());
      }
    }
    return builder.toString();
  }
}