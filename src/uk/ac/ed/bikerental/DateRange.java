package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public class DateRange {
    private LocalDate start, end;

    /**
     * Constructs a new DateRange object to reflect a date period by
     * specifying a start and an end date
     * @param start the start of a date period
     * @param end the end of a date period
     */
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    /**
     *Getter method that returns start date of current DateRange object
     * @return Returns the starting date of DateRange
     */
    public LocalDate getStart() {
        return this.start;
    }

    /**
     *Getter method that returns end date of current DateRange object
     * @return Returns the end date of DateRange
     */
    public LocalDate getEnd() {
        return this.end;
    }

    /**
     *Returns the total number of years between the start date and the end date
     * @return Returns the years between this.getStart() and this.getEnd()
     */
    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }
    /**
     *Returns the total number of days between the start date and the end date
     * @return Returns the days between this.getStart() and this.getEnd()
     */
    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }

    /**
     * Method that checks if the current duration overlaps another
     * @param other the other DateRange duration to be compared with
     * @return Returns true if the two durations overlap, and false if otherwise
     */
    public Boolean overlaps(DateRange other) {
        //If the end of date one intersects with the start date of the second
        if (other.getEnd().equals(start) || other.getStart().equals(end)){
            return true;
        }
        else return start.compareTo(other.getEnd()) < 0 && other.getStart().compareTo(end) < 0;
    }

    /**
     * hashCode method allowing use in collections
     * @return Returns the hashcode value from the result of the Objects.hash(end, start) call
     */
    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(end, start);
    }

    /**
     * Equals method for testing equality in tests
     * @param obj object to be tested
     * @return Returns true if they contain the same value, false if otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateRange other = (DateRange) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }
    
}
