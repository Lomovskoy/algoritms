package sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getReader();

        String stringOne = reader.readLine();
        String stringTwo = reader.readLine();

        boolean equalLength = false;
        String maxString;
        String minString;
        if (stringOne.length() != stringTwo.length()) {
            maxString = stringOne.length() > stringTwo.length() ? stringOne : stringTwo;
            minString = stringOne.length() < stringTwo.length() ? stringOne : stringTwo;
        } else {
            equalLength = true;
            maxString = stringOne;
            minString = stringTwo;
        }

        if (maxString.length() - minString.length() > 1) {
            System.out.println("FAIL");
        } else {
            boolean flag = true;
            for (int i = 0; i < minString.length(); i++) {
                if (minString.charAt(i) != maxString.charAt(i)) {
                    flag = false;
                    if (equalLength) {
                        if (minString.substring(i + 1).equals(maxString.substring(i + 1))) {
                            System.out.println("OK");
                            break;
                        } else {
                            System.out.println("FAIL");
                            break;
                        }
                    } else {
                        if (minString.substring(i).equals(maxString.substring(i + 1))) {
                            System.out.println("OK");
                            break;
                        } else {
                            System.out.println("FAIL");
                            break;
                        }
                    }
                }
            }
            if (flag) {
                System.out.println("OK");
            }
        }

    }

    private static BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}