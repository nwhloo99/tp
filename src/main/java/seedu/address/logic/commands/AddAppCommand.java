package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.schedule.Appointment;

/**
 * Creates an appointment with an existing person in PlaceBook
 */
public class AddAppCommand extends Command {

    public static final String COMMAND_WORD = "addApp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to PlaceBook. "
            + "Parameters: "
            + "INDEX "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_DATE + "DATE (dd-MM-yyyy) "
            + PREFIX_TIME + "TIME (hhmm)"
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ADDRESS + "Starbucks @ Raffles City "
            + PREFIX_DATE + "14-12-2021"
            + PREFIX_TIME + "1400"
            + PREFIX_DESCRIPTION + "discuss marketing strategies";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";

    private final Index index;
    private final Address location;
    private final LocalDate date;
    private final LocalTime time;
    private final String description;

    /**
     * Creates an AddAppCommand
     * @param index The index of the person to be met during the appointment
     * @param location The location of the appointment
     * @param date The date of the appointment
     * @param time The time of the appointment
     * @param description The description of the appointment
     */
    public AddAppCommand(Index index, Address location, LocalDate date, LocalTime time, String description) {
        requireNonNull(index);
        requireNonNull(location);
        requireNonNull(date);
        requireNonNull(time);
        requireNonNull(description);

        this.index = index;
        this.location = location;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person client = lastShownList.get(index.getZeroBased());
        UniquePersonList clients = new UniquePersonList();
        clients.add(client);
        Appointment newAppointment = new Appointment(clients, location, date, time, description);

        model.addAppointment(newAppointment);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newAppointment));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAppCommand)) {
            return false;
        }

        AddAppCommand aa = (AddAppCommand) other;
        return this.index.equals(aa.index)
                && this.location.equals(aa.location)
                && this.date.equals(aa.date)
                && this.time.equals(aa.time)
                && this.description.equals(aa.description);
    }
}
