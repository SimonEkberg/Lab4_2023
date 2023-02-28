package domainModell.person;

import java.time.Year;

public class PersonBirthYear {
    private int birthYear;
    private int currentYear = Year.now().getValue();

    public PersonBirthYear(int birthYear) {
        setBirthYear(birthYear);
    }

    public int getBirthYear() {
        return this.birthYear;
    }
    public void setBirthYear(int birthYear){

        if(birthYear > currentYear || (currentYear - birthYear) > 120) {
            throw new IllegalArgumentException("Birth year out of range: " +(currentYear-120) + " < Birth year < "+ (currentYear+1));
        }
        this.birthYear = birthYear;
    }

}
