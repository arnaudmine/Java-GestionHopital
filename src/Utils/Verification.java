package Utils;

import Model.Consultation;
import Model.Patient;
import Model.UpdatePatientModel;
//import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class Verification {

    private static Pattern pattern;

    public static boolean emailVerification(String email) {
        return pattern.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", email) || email.equals("");
    }
    public static boolean nameVerification(String name){

        return Pattern.matches( "[a-zA-zéèàêÉÈ]+([ -][a-zéèàêA-Zéèàê]+)*",name) || name.equals("");
    }

    public static  boolean onlyLetterAndSpace(String text){
        return Pattern.matches("([a-zA-zéèàêÉÈ]+\s*){1,49}", text) || text.equals("");
    }

    public static boolean priceVerification(String price){

        return Pattern.matches("\\d+[.]?+\\d{0,2}",price);
    }
    public static Patient createPatient(String lastName, String firstName, String address, String disease, String bedroom, String email, String insurance, String infoInfirmiere) throws Exception {

        Boolean containNullField = false;
        StringBuilder nullFieldsStringList = new StringBuilder();
        nullFieldsStringList.append("Veuillez completer le(s) champ(s) suivant(s) : \n");
        if(lastName.isBlank() || lastName.isEmpty() ){
            nullFieldsStringList.append("- Nom\n");
            containNullField = true;
        }
        if(firstName.isBlank() || firstName.isEmpty()){
            nullFieldsStringList.append("- Prénom\n");
            containNullField = true;
        }
        if(address.isBlank() || address.isEmpty()){
            nullFieldsStringList.append("- Adresse\n");
            containNullField = true;
        }
        if(disease.isBlank() || disease.isEmpty()){
            nullFieldsStringList.append("- Maladie(s) du patient\n");
            containNullField = true;
        }


        //Other Exception
        Boolean haveOtherException = false;
        StringBuilder otherExceptionMessage = new StringBuilder();
        otherExceptionMessage.append("Veuilliez corriger les erreurs suivantes : \n");

        if(!emailVerification(email)){
            otherExceptionMessage.append("- Le champ email n'est pas valide\n");
            haveOtherException = true;
        }
        if(!nameVerification(firstName)){
            otherExceptionMessage.append("- Le champ prénom n'est pas valide\n");
            haveOtherException = true;
        }
        if(!nameVerification(lastName)){
            otherExceptionMessage.append("- Le champ nom n'est pas valide\n");
            haveOtherException = true;
        }


        //Message
        if(haveOtherException || containNullField)
        {
            StringBuilder exceptionMessage = new StringBuilder();

            if(containNullField){
                exceptionMessage.append(nullFieldsStringList);
            }
            if(haveOtherException){
                exceptionMessage.append(otherExceptionMessage);
            }
            throw new Exception(exceptionMessage.toString());
        }
        //else {
        //    JOptionPane.showMessageDialog(null,"Appuiez sur OK pour confirmer.");
        //}

        String id = firstName.substring(0,2) + lastName.substring(0,2);

        int bedroomNumber = Integer.parseInt(bedroom);
        String idNurse = StringManager.convertFullNameIntoNurseId(infoInfirmiere);

        return new Patient(id.toUpperCase(),lastName,firstName,new GregorianCalendar(),address,disease,0,idNurse, bedroomNumber, false,email, insurance);
    }

    public static Consultation createConsultation(String type, GregorianCalendar calendar, String price, String note, String idPatient, String idExpert) throws Exception {

        Boolean containNullField = false;
        StringBuilder nullFieldsStringList = new StringBuilder();
        nullFieldsStringList.append("Veuillez completer le(s) champ(s) suivant(s) : \n");
        if(price.isBlank() || price.isEmpty() || price.equals("0")){
            nullFieldsStringList.append("- Prix\n");
            containNullField = true;
        }
        if(type.isBlank() || type.isEmpty()){
            nullFieldsStringList.append("- Type de consultation\n");
            containNullField = true;
        }



        //Other Exception
        Boolean haveOtherException = false;
        StringBuilder otherExceptionMessage = new StringBuilder();
        otherExceptionMessage.append("Veuilliez corriger les erreurs suivantes : \n");
        GregorianCalendar today = new GregorianCalendar();
        if(today.after(calendar)){
            otherExceptionMessage.append("- La date de la consultation ne peut pas être dans le passé ni aujourd'hui\n");
            haveOtherException = true;
        }
        if(!onlyLetterAndSpace(type)){
            otherExceptionMessage.append("- Le champ type de consultation n'est pas valide\n");
            haveOtherException = true;
        }
        if(!priceVerification(price)){
            otherExceptionMessage.append("- Le champ prix n'est pas valide (xx.xx)\n");
            haveOtherException = true;
        }

        //Message
        if(haveOtherException || containNullField)
        {
            StringBuilder exceptionMessage = new StringBuilder();

            if(containNullField){
                exceptionMessage.append(nullFieldsStringList);
            }
            if(haveOtherException){
                //exceptionMessage.append("\n");
                exceptionMessage.append(otherExceptionMessage);
            }
            throw new Exception(exceptionMessage.toString());
        }

        Double parsedPrice = Double.parseDouble(price);
        return new Consultation(type,calendar,parsedPrice,note,idPatient,idExpert);

    }

    public static UpdatePatientModel createUpdatePatient(String id, String address, String service, String nurse, String bedroom, Boolean canleave, String insurance, String email)throws Exception{
        System.out.println(id + " address : " + address + " nurse:" +nurse+ " email :"+email );
        Boolean containNullField = false;
        StringBuilder nullFieldsStringList = new StringBuilder();
        nullFieldsStringList.append("Veuillez completer le(s) champ(s) suivant(s) : \n");
        if(address.isBlank() || address.isEmpty()){
            nullFieldsStringList.append("- Adresse\n");
            containNullField = true;
        }


        //Other Exception
        Boolean haveOtherException = false;
        StringBuilder otherExceptionMessage = new StringBuilder();
        otherExceptionMessage.append("Veuilliez corriger les erreurs suivantes : \n");

        if(!emailVerification(email)){
            otherExceptionMessage.append("- Le champ email n'est pas valide\n");
            haveOtherException = true;
        }





        //Message
        if(haveOtherException || containNullField)
        {
            StringBuilder exceptionMessage = new StringBuilder();

            if(containNullField){
                exceptionMessage.append(nullFieldsStringList);
            }
            if(haveOtherException){
                exceptionMessage.append("\n");
                exceptionMessage.append(otherExceptionMessage);
            }
            throw new Exception(exceptionMessage.toString());
        }

        int bedroomNumber = Integer.parseInt(bedroom);
        UpdatePatientModel updatePatientModel = new UpdatePatientModel(id, address,nurse,bedroomNumber, canleave,email,insurance);

        return updatePatientModel;
    }
}
