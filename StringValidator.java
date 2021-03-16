    public class StringValidator {

        private String strToCheck;

        protected StringValidator(String strToCheck) {
            this.strToCheck = strToCheck;
        }

        protected boolean checkValidity() {
            if (checkBracesAndOrder() && checkSymbols()) {
                return true;
            } else {
                return false;
            }
        }

        private boolean checkBracesAndOrder() {
            int braceChecker = 0;
            for (int i = 0; i < strToCheck.length(); i++) {
                if (String.valueOf(strToCheck.charAt(i)).equals("[")) {
                    braceChecker++;
                } else if (String.valueOf(strToCheck.charAt(i)).equals("]")) {
                    braceChecker--;
                } else {
                    if (String.valueOf(strToCheck.charAt(i)).matches("\\d") &&
                            (i + 1) < strToCheck.length() - 1) {
                        if (!String.valueOf(strToCheck.charAt(i + 1)).equals("[") &&
                                !String.valueOf(strToCheck.charAt(i + 1)).matches("\\d")) {
                            String str = String.valueOf(strToCheck.charAt(i + 1));
                            return false;
                        }
                    }
                }
                if (braceChecker < 0) {
                    return false;
                }
            }
            if (braceChecker != 0) {
                return false;
            }
            return true;
        }

        private boolean checkSymbols() {
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
    }