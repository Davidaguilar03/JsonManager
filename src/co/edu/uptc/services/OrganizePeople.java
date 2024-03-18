package co.edu.uptc.services;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.models.Person;

public class OrganizePeople {
    PropiertiesService p = new PropiertiesService();
    JsonConvertorService jcs = new JsonConvertorService();
    ArrayList<Person> people = new ArrayList<>();
    String inputFileJson = p.getValue("peopleJsonPath");

    public void readPeopleDataJson() throws IOException{
      this.people = jcs.jsonToPerson(inputFileJson);
    }

    private double averageSalary() {
        double auxSalary = 0;
        for (Person person : people) {
            auxSalary += person.getSalary();
        }
        double averageSalary = (auxSalary / people.size());
        return averageSalary;
    }

    public void writePeopleDataJson() {
        this.writeUpperSalaryPeople();
        this.writeLowerSalaryPeople();
        System.out.println("Se ha escrito la informacion con exito");
    }

    private void writeUpperSalaryPeople() {
        ArrayList<Person> upperSalaryPeople = new ArrayList<>();
        for (Person person : people) {
            if(person.getSalary()>this.averageSalary()){
                upperSalaryPeople.add(person);
            }
        }
        try {
            jcs.personToJson(upperSalaryPeople, p.getValue("PeopleUpperSalary"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLowerSalaryPeople() {
        ArrayList<Person> lowerSalaryPeople = new ArrayList<>();
        for (Person person : people) {
            if(person.getSalary()<this.averageSalary()){
                lowerSalaryPeople.add(person);
            }
        }
        try {
            jcs.personToJson(lowerSalaryPeople, p.getValue("PeopleLowerSalary"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
}