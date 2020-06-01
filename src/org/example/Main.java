package org.example;

public class Main {

    public static void main(String[] args) {
//        // write your code here
//        for (String arg : args) {
//            System.out.println("arg = " + arg);
//        }
//
//        System.out.println("App.gettingFiltersFromArgs(args) = " + App.gettingFiltersFromArgs(args));
//        for (boolean b : App.gettingFiltersFromArgs(args)
//        ) {
//            System.out.println(b);
//
//        }
        Service service = new Service();
        String actual = service.getOruulagch("Bold.xlsx");
        System.out.println("oruulagch = " + actual);

        String party = service.getParty("Bold.xlsx");
        System.out.println("party = " + party);


    }
}
