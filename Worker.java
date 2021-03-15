import java.util.Scanner;

public class Worker {

    protected void launch() {
        String userInputString = readInputString();
        if (userInputString != null) {
            if (new StringValidator(userInputString).checkValidity()) {
                StringDecoder decoder = new StringDecoder(userInputString);
                if (decoder.launch()) {
                    try {
                        System.out.println(decoder.getDecodedString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String readInputString() {
        try {
            Scanner scanner = new Scanner(System.in);
            String userInp = scanner.nextLine();
            scanner.close();
            return userInp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
