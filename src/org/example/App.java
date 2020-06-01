package org.example;

import java.io.IOException;
import java.util.Stack;

public class App {

    public static void main(String[] args) throws IOException {

        Service myService = new Service();
        long start = 0L, stop1 = 0L, stop2 = 0L;

        System.out.println("Loading file .... wait!");
        try {
            start = System.currentTimeMillis();

            // myService.setFilters(new boolean[]{true, true, true, true, false, false});
            myService.setFilters(gettingFiltersFromArgs(args));

            myService.readFilesInFolder(myService);
            myService.printFilesInFolder();

            System.out.println("Example records: ");
            myService.printFirstRows(10, myService.getFilesInFolder().get(0));
//            myService.printFirstRows(10, myService.getFilesInFolder().get(1));

//            myService.generateDataSet();

            //myService.printDuplicatePerson();
            //myService.printExtraOrdinaryPerson();

            stop1 = System.currentTimeMillis();

            myService.writeOutputFile();

            stop2 = System.currentTimeMillis();


        } catch (IOException e) {
            System.out.println("Алдаа: " + e);
            e.printStackTrace();
        } finally {

            System.out.println("Total time consumption for reading and comparing (ms): " + (stop1 - start));
            System.out.println("Total time consumption for writing to file (ms): " + (stop2 - stop1));

            System.out.println("Program is done! to exit press any key!");
        }

    }

    public static boolean[] gettingFiltersFromArgs(String[] args) {

        boolean[] filters = new boolean[7];
        Stack<Boolean> stack = new Stack<>();
        int i = 0;

        for (String arg : args) {
            System.out.println("arg = " + arg);
            try {
                int num = Integer.parseInt(arg);
                while (num != 0) {
                    stack.push(num % 10 != 0);
                    num /= 10;
                }
                //stack.forEach(System.out::println);
                while (!stack.empty()) {
                    filters[i++] = stack.pop();
                }
                System.out.println("filters = " + filters);
            } catch (Exception e) {
                System.out.println("Please insert Numbers!");
                System.exit(1);
            }

        }
        return filters;
    }
}
