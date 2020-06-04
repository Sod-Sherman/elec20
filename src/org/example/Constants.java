package org.example;

import java.util.HashSet;
import java.util.Set;

public class Constants {
    private static Set<String> PARTIES = new HashSet<>();

    public static Set<String> getPARTIES() {
        return PARTIES;
    }

    public static void setPARTIES(Set<String> PARTIES) {
        Constants.PARTIES = PARTIES;
    }

    public static void addParty(String party){
        PARTIES.add(party);
    }
}
