package org.example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String registerNum;
    private String sureName;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    // 1,1,1,0,0,0 ---> regNum, SureName, fName must be same
    private boolean[] flags = new boolean[]{true, true, true, true, true, true};
    private MyValue myValue;
    private String party;
    private String oruulagch;


    public Person(Builder builder) {
        this.registerNum = builder.registerNum;
        this.sureName = builder.sureName;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phone = builder.sex;
        this.address = builder.address;
        this.flags = builder.flags;
        this.myValue = builder.myValue;
        this.party = builder.party;
        this.oruulagch = builder.oruulagch;
    }


    public static class Builder {
        private String registerNum;
        private String sureName;
        private String firstName;
        private String lastName;
        private String sex;
        private String address;
        private boolean[] flags = new boolean[]{true, true, true, true, true, true};
        private MyValue myValue;
        private String party;
        private String oruulagch;

        public Builder withRegisterNum(String reg) {
            this.registerNum = reg;
            return this;
        }

        public Builder withSureName(String sureName) {
            this.sureName = sureName;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withSex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withFlags(boolean[] flags) {
            this.flags = flags;
            return this;
        }

        public Builder withMyValue(MyValue myValue) {
            this.myValue = myValue;
            return this;
        }


        public Builder withParty(String party) {
            this.party = party;
            return this;
        }


        public Builder withOruulagch(String oruulagch) {
            this.oruulagch = oruulagch;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }


    public void setFlags(boolean[] flags) {
        this.flags = flags;
    }

    public String getRegisterNum() {
        return registerNum;
    }

    public void setRegisterNum(String registerNum) {
        this.registerNum = registerNum;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MyValue getMyValue() {
        return myValue;
    }

    public void setMyValue(MyValue myValue) {
        this.myValue = myValue;
    }

    public String getParty() {
        return party;
    }

    public String getOruulagch() {
        return oruulagch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return (!flags[0] || Objects.equals(registerNum, person.registerNum)) &&
                (!flags[1] || Objects.equals(sureName, person.sureName)) &&
                (!flags[2] || Objects.equals(firstName, person.firstName)) &&
                (!flags[3] || Objects.equals(lastName, person.lastName)) &&
                (!flags[4] || Objects.equals(phone, person.phone)) &&
                (!flags[5] || Objects.equals(address, person.address));
    }

    @Override
    public int hashCode() {
        List<Object> objects = new LinkedList<>();
        if (flags[0]) objects.add(registerNum);
        if (flags[1]) objects.add(sureName);
        if (flags[2]) objects.add(firstName);
        if (flags[3]) objects.add(lastName);
        if (flags[4]) objects.add(phone);
        if (flags[5]) objects.add(address);

        return Objects.hash(objects);
    }

    @Override
    public String toString() {
        return "\nPerson{" +
                "\nregisterNum='" + registerNum + '\'' +
                "\n, sureName='" + sureName + '\'' +
                "\n, firstName='" + firstName + '\'' +
                "\n, lastName='" + lastName + '\'' +
                "\n, sex='" + phone + '\'' +
                "\n, address='" + address + '\'' +
                "\n, flags=" + Arrays.toString(flags) +
                "}\n";
    }
}
