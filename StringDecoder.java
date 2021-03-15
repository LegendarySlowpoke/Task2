package server.workTask2;

import java.util.Scanner;

public class StringDecoder {

    private String usInput;

    StringDecoder() {
        usInput = new Scanner(System.in).nextLine();
    }

    protected void launch() {
        try {
            if (checkValidity(usInput)) {
                System.out.println(worker(usInput));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkValidity(String strToCheck) {
        if (checkBraces(strToCheck) && checkSymbols(strToCheck)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkBraces(String strToCheck) {
        int braceChecker = 0;
        //Checking braces order
        for (int i = 0; i < strToCheck.length(); i++) {
            if (String.valueOf(strToCheck.charAt(i)).equals("[")) {
                braceChecker++;
            } else if (String.valueOf(strToCheck.charAt(i)).equals("]")) {
                braceChecker--;
            } else {
            }

            if (braceChecker < 0) {
                return false;
            }
        }
        //Checking if there is equal quantity of opening and closing braces.
        if (braceChecker != 0) {
            return false;
        }
        return true;
    }

    private boolean checkSymbols(String strToCheck) {
        //Checking if there is only latin letters, digits and braces '[' & ']'.
        strToCheck = strToCheck.replace('[', ' ');
        strToCheck = strToCheck.replace(']', ' ');
        strToCheck = strToCheck.replaceAll(" ", "");
        strToCheck = strToCheck.replaceAll("[A-Za-z0-9]*", "");
        if (strToCheck.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private String worker(String inpStr) {
        String data = "";

        for (int i = 0; i < inpStr.length(); i++) {
            if (String.valueOf(inpStr.charAt(i)).matches("\\d")) {
                String index = String.valueOf(inpStr.charAt(i));
                while (String.valueOf(inpStr.charAt(i + 1)).matches("\\d")) {
                    index = index + inpStr.charAt(i + 1);
                    i++;
                }

                int begIndex = i + 1;
                int opBracesCounter = 0;
                int clBracesCounter = 0;

                if (String.valueOf(inpStr.charAt(i + 1)).equals("[")) {
                    opBracesCounter++;
                    i++;
                    while (opBracesCounter != clBracesCounter) {
                        if (String.valueOf(inpStr.charAt(i + 1)).equals("[")) {
                            opBracesCounter++;
                            i++;
                        } else if (String.valueOf(inpStr.charAt(i + 1)).equals("]")) {
                            clBracesCounter++;
                            i++;
                        } else {
                            i++;
                        }
                    }
                }

                if (opBracesCounter == 1) {
                    for (int j = 0; j < Integer.parseInt(index); j++) {
                        data += inpStr.substring(begIndex + 1, i);
                    }
                } else {
                    String reWorked = worker(inpStr.substring(begIndex + 1, i));
                    for (int j = 0; j < Integer.parseInt(index); j++) {
                        data += reWorked;
                    }
                }

            } else {
                while (String.valueOf(inpStr.charAt(i)).matches("[A-Za-z]")) {
                    data = data + inpStr.charAt(i);
                    if ((i + 1) < inpStr.length() - 1 &&
                            String.valueOf(inpStr.charAt(i + 1)).matches("[A-Za-z]")) {
                        i++;
                    } else {
                        break;
                    }
                }
            }
        }
        return data;
    }
}
