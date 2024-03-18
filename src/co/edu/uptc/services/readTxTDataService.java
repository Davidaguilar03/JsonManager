package co.edu.uptc.services;

import java.util.ArrayList;

import co.edu.uptc.models.Person;

public class readTxTDataService {
    
    PropiertiesService p = new PropiertiesService();
    Myfile f = new Myfile(p.getValue("peopleTxtPath"));
    ArrayList<Person> people = new ArrayList<>();

    public void readPeopleDataTxt() {
        f.open('r');
        ArrayList<String> peopleData = f.read();
        ArrayList<String> cleanData = new ArrayList<>();
        for (String string : peopleData) {
            String cleanString = string.trim();
            cleanData.add(cleanString.replaceAll("\\s{2,}", ";"));
        }

        for (String cad : cleanData) {
            String[] cads = cad.split(";");
            String[] ageAndName = cads[0].split("(?<=\\d)(?=\\D)");
            int age = Integer.parseInt(ageAndName[0]);
            String name = ageAndName[1];
            String lastName = cads[1];
            int salary = Integer.parseInt(cads[2]);
            Person person = new Person(name, lastName, age, salary);
            people.add(person);
        }
    }
    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

}
