package domainModell.person;

public class Person{
    private int id;
    private PersonName personName;
    private PersonBirthYear personBirthYear;
    private int siteId;



    public Person(int id, String personName, int personBirthYear) {
        this(id,personName,personBirthYear,0);
    }
    public Person(int id, String personName, int personBirthYear, int siteId){
        this.id = id;
        this.personName = new PersonName(personName);
        this.personBirthYear = new PersonBirthYear(personBirthYear);
        setSiteId(siteId);
    }
    public Person(String name, int birthYear, int siteId){
        this(0,name,birthYear,siteId);
    }
    public Person(String name, int birthYear){
        this(0,name,birthYear,0);
    }
    public void setSiteId(int siteId){
       /* if(SiteId.getInstance().addEntitySiteId(siteId))
            this.siteId = siteId;*/
        this.siteId = siteId;
    }

    public int getSiteId() {
        return this.siteId;
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

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
      Person person = (Person) o;
      return getId() == person.getId() &&
              getBirthYear() == person.getBirthYear() &&
              getPersonName() == person.getPersonName() &&
              getSiteId() == person.getSiteId();
  }

    @Override
    public String toString() {
        return "Person{" +
                "Id: " + id +
                ", Namn: " + personName.getName() +
                ", Födelseår: " + personBirthYear.getBirthYear() +
                ", SiteId: " + siteId +
                '}';
    }
}
