package Utils;

public class StringManager {

    public static String convertFullNameIntoHeadDptID(String fullName){
        StringBuilder id = new StringBuilder();

        for(int i = 0; i < fullName.length();i++){
            if(fullName.charAt(i) == ' '){
                id.append(fullName.charAt(i+1));
            }
        }
        id.append(fullName.charAt(0));

        return id.toString().toUpperCase();
    }

    public static String convertFullNameIntoNurseId(String fullName) {
        StringBuilder id = new StringBuilder();

        id.append(fullName.charAt(0));
        id.append(fullName.charAt(1));

        for (int i = 0; i < fullName.length(); i++) {
            if (fullName.charAt(i) == ' ') {
                id.append(fullName.charAt(i + 1));
                id.append(fullName.charAt(i + 2));
                i = fullName.length();
            }
        }

        for (int j = 0; j < fullName.length(); j++) {

            if (fullName.charAt(j) == '(') {
                id.append(fullName.charAt(j + 1));
                id.append(fullName.charAt(j + 2));
                j = fullName.length();
            }
        }

        return id.toString().toUpperCase();
    }

    public static String convertFullNameIntoPatientId(String fullName) {
        StringBuilder id = new StringBuilder();

        for(int i = 0; i < fullName.length(); i++) {
            if(fullName.charAt(i) == ' ') {
                id.append(fullName.substring(i+1, i+3));
            }
        }
        id.append(fullName.substring(0,2));

        return id.toString().toUpperCase();
    }

    public static String convertFullNameIntoExpertId(String fullName) {
        StringBuilder id = new StringBuilder();
        id.append(fullName.substring(0,1));

        for (int i = 0; i < fullName.length(); i++) {
            if (fullName.charAt(i) == ' ') {
                id.append(fullName.charAt(i + 1));
                i = fullName.length();
            }
        }

        for (int j = 0; j < fullName.length(); j++) {

            if (fullName.charAt(j) == '(') {
                id.append(fullName.charAt(j + 1));
                j = fullName.length();
            }
        }
        return id.toString().toUpperCase();
    }
}