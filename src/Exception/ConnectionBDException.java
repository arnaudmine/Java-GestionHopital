package Exception;

public class ConnectionBDException extends Exception {
    public String getMessage() {
        return "Erreur connexion base de donnée";
    }
}
