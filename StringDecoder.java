class StringDecoder {

    private final String stringToDecode;
    private String decodedString;

    public String getDecodedString() {
        return decodedString;
    }

    protected StringDecoder(String stringToDecode) {
        this.stringToDecode = stringToDecode;
    }

    protected boolean launch() {
        try {
            decodedString = decode(stringToDecode);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private String decode(String inpStr) {
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
                    String reWorked = decode(inpStr.substring(begIndex + 1, i));
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
