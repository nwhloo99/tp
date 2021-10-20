package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAppointment.ALICE_APPOINTMENT;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.UniquePersonList;

public class AppointmentTest {

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(ALICE_APPOINTMENT.equals(ALICE_APPOINTMENT));

        // null -> returns false
        assertFalse(ALICE_APPOINTMENT.equals(null));

        UniquePersonList testClients = new UniquePersonList();
        testClients.add(ALICE);

        // same client, different attributes
        Appointment editedAliceAppointment = new Appointment(testClients,
                new Address("369 Geylang Street"),
                LocalDate.of(2021, 12, 25),
                LocalTime.of(21, 30),
                "Talk about sales");
        assertFalse(ALICE_APPOINTMENT.equals(editedAliceAppointment));
    }
}
