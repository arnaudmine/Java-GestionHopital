package UnitTest;

import Business.PatientManager;
import Model.Patient;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class PatientManagerTest {

    private PatientManager patientManager;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Exception {
        patientManager = new PatientManager();
    }


    @org.junit.jupiter.api.Test
    void getFinalBillVerification() throws Exception {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2022, Calendar.MAY, 22);
        Patient patient = new Patient("CICTIM", "Ciciotti", "Timo", calendar, "12, rue du des fleurs", "opération du genoux", 20, "BALACH", 2, true, "timcic@gmail.com", "DKV");
        assertEquals(35, patientManager.getFinalBill(patient));
    }
}