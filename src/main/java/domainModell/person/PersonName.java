package domainModell.person;

public class PersonName {
    private String name;
    private final int MAX_LENGTH = 50;
    private final int MIN_LENGTH = 0;

    public PersonName(String name) {
        setName(name);
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name){
        if(name == null || name.length() == MIN_LENGTH || name.isBlank() || name.length() > MAX_LENGTH)
            throw new IllegalArgumentException("Name is too long or null!");
        if(!isNameAlphaSpace(name))
            throw new IllegalArgumentException("Name contain illegal characters!");
        this.name = name;
    }
    private boolean isNameAlphaSpace(String name) {
        int nameLength = name.length();
        for (int i = 0; i < nameLength; i++) {
            if ((Character.isLetter(name.charAt(i)) == false) && (name.charAt(i) != ' ')) {
                return false;
            }
        }
        return true;
    }
}
