package co.edu.uptc.controllers;

import java.io.IOException;

import co.edu.uptc.services.*;

public class Control {
    public void run(){
        JsonConvertorService jcs = new JsonConvertorService();
        PropiertiesService p = new PropiertiesService();
        readTxTDataService rdt = new readTxTDataService();
        OrganizePeople op = new OrganizePeople();
        rdt.readPeopleDataTxt();
        try {
            jcs.personToJson(rdt.getPeople(), p.getValue("peopleJsonPath"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            op.readPeopleDataJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        op.writePeopleDataJson();
    }
}