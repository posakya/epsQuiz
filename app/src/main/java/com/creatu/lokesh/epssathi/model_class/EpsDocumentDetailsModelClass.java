package com.creatu.lokesh.epssathi.model_class;

public class EpsDocumentDetailsModelClass {

    private String sn;
    private String dob;
    private String name;
    private String gender;
    private String address;
    private String contact_before;

    public EpsDocumentDetailsModelClass(){
        //empty constructor
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_before() {
        return contact_before;
    }

    public void setContact_before(String contact_before) {
        this.contact_before = contact_before;
    }
}
