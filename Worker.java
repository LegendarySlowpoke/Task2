import java.util.Scanner;

public class Worker {

    private boolean readerSuccess = false;
    private String userInputString;

    protected void launch() {
        readInputString();
        if (readerSuccess) {
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

    private void readInputString() {
        try {
            Scanner scanner = new Scanner(System.in);
            userInputString = scanner.nextLine();
            scanner.close();
            readerSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
