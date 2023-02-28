package domainModell.person;

public class Person{
    private int id;
    private PersonName personName;
    private PersonBirthYear personBirthYear;



    public Person(int id,String personName, int personBirthYear) {
        this.id = id;
        this.personName = new PersonName(personName);
        this.personBirthYear = new PersonBirthYear(personBirthYear);
    }
    public Person(String name, int birthYear){
        this(0,name,birthYear);
    }

    public String getPersonName() {
        return personName.getName().toString();
    }

    public void setPersonName(String personName) {
        this.personName = new PersonName(personName);
    }

    public int getId() {
        return id;
    }

    public int getBirthYear() {
        return this.personBirthYear.getBirthYear();
    }

    public void setBirthYear(int birthYear) {
        this.personBirthYear = new PersonBirthYear(birthYear);
    }


  /*  @Override
    public boolean equals(Object p){
        return p instanceof Person && hashCode() == p.hashCode();
    }*/
  @Override
  public boolean equals(Object o) {
      if (this == o) { //if same object
          return true;
      }
      if (o == null || getClass() != o.getClass()) { //if null or not the same class
          return false;
      }
      Person person = (Person) o;
      return getId() == person.getId() &&
              getBirthYear() == person.getBirthYear() &&
              getPersonName() == person.getPersonName();
  }

 /* @Override
  public int hashCode() {
      int result = 17;
      result = 31 * result + id;
      result = 31 * result + personName.hashCode();
      result = 31 * result + personBirthYear.hashCode();
      return result;
  }*/
   /*  @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.personName);
        hash = 29 * hash + Objects.hashCode(Integer.valueOf(personBirthYear.getBirthYear()));
        return hash;
    }*/


    @Override
    public String toString() {
        return "Person{" +
                "Id: " + id +
                ", Namn: " + personName.getName() +
                ", Födelseår: " + personBirthYear.getBirthYear() +
                '}';
    }
}
