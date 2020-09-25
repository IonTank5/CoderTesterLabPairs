package com.company;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.*;
public class Main {
    public static String getName(int x){
        try(Stream<String> lines = Files.lines(Paths.get("C:\\Users\\jaked\\Documents\\People.txt"))){
            String name = lines.skip(x).findFirst().get();
            return name;
        }
        catch(Exception f){
            return f.toString();
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try{

            File people = new File("C:\\Users\\jaked\\Documents\\People.txt");
            Scanner read = new Scanner(people);
            int counter = 0, counter2 = 0;
            System.out.println("Would you like it to be coder or tester first? (coder first by default)\n1: coder first\n2. tester first");
            int input = in.nextInt();
            boolean block = false;
            boolean coderTester = true;
            if(input==2){
                coderTester = false;
            }
            System.out.println("Would you like it to be pairs within both classes or pairs within same block? (both classes by default)\n1: both classes\n2: same block");
            input = in.nextInt();
            if(input==2){
                block=true;
            }
            String name;
            while(read.hasNextLine()){
                String temp = read.nextLine();
                counter++;
            }
            read = new Scanner(people);
            System.out.println("There are " + counter + " people");
            boolean[] checker1 = new boolean[counter];
            boolean[] checker2 = new boolean[counter];
            int[] pairs = new int[counter];
            String[] names1 = new String[counter];
            String[] names2 = new String[counter];
            int[] block1 = new int[counter];
            int person1, person2;
            String name1, name2;
            Assign assigner = new Assign(counter);
            while(true){
                person1 = assigner.generation();
                person2 = assigner.generation();
                name1 = getName(person1);
                name2  = getName(person2);

                if(counter==counter2){
                    System.out.println("Everyone has a coder and a tester");
                    break;
                }
                else if(block==true){
                    if((checker1[person1]==false&&checker2[person2]==false)&&(!(person1==person2))&&name1.charAt(0)==name2.charAt(0)){
                        checker1[person1]=true;
                        checker2[person2]=true;
                        pairs[person1] = person2;
                        counter2++;
                        block1[person1] = Character.getNumericValue(getName(person1).charAt(0));
                        names1[person1] = (name1.substring(name1.indexOf(",", name1.indexOf(",")+1)+1) + " " + name1.substring(name1.indexOf(",")+1, (name1.indexOf(",", name1.indexOf(",")+1))));
                        names2[person1] = name2.substring(name2.indexOf(",", name2.indexOf(",")+1)+1) + " " + name2.substring(name2.indexOf(",")+1, (name2.indexOf(",", name2.indexOf(",")+1)));
                    }
                }
                else if((checker1[person1]==false&&checker2[person2]==false)&&(!(person1==person2))){
                    checker1[person1]=true;
                    checker2[person2]=true;
                    pairs[person1] = person2;
                    counter2++;
                    names1[person1] = (name1.substring(name1.indexOf(",", name1.indexOf(",")+1)+1) + " " + name1.substring(name1.indexOf(",")+1, (name1.indexOf(",", name1.indexOf(",")+1))));
                    names2[person1] = name2.substring(name2.indexOf(",", name2.indexOf(",")+1)+1) + " " + name2.substring(name2.indexOf(",")+1, (name2.indexOf(",", name2.indexOf(",")+1)));

                }
            }
            String temp;
            int tempNum;
            for (int i = 0; i < counter; i++) {
                for (int j = i+1; j < counter; j++) {
                    if (names1[i].compareToIgnoreCase(names1[j])>0){
                        temp = names1[i];
                        names1[i] = names1[j];
                        names1[j] = temp;
                        temp = names2[i];
                        names2[i] = names2[j];
                        names2[j] = temp;
                        tempNum = block1[i];
                        block1[i]=block1[j];
                        block1[j] = tempNum;
                    }
                }
            }
            int blockPrint = 1;
            if(block) {
                System.out.println("Which block would you like to print out? (default is both)\n1. both\n2. only 1\n3. only 2");
                blockPrint = in.nextInt();
                if (blockPrint==2&&coderTester==true){
                    System.out.format("%-30s %-30s","Coder", "Tester");
                    System.out.println();
                    for (int i = 0; i < counter; i++) {
                        if(block1[i]==1&&!(Character.isWhitespace(names1[i].charAt(0)))) {
                            System.out.format("%-30s %-30s", names1[i], names2[i]);
                            System.out.println();
                        }
                    }
                }
                else if (blockPrint==3&&coderTester==false){
                    System.out.format("%-30s %-30s","Tester", "Coder");
                    System.out.println();
                    for (int i = 0; i < counter; i++) {
                        if(block1[i]==2&&!(Character.isWhitespace(names1[i].charAt(0)))) {
                            System.out.format("%-30s %-30s", names2[i], names1[i]);
                            System.out.println();
                        }
                    }
                }
                else if (blockPrint==3&&coderTester==true){
                    System.out.format("%-30s %-30s","Coder", "Tester");
                    System.out.println();
                    for (int i = 0; i < counter; i++) {
                        if(block1[i]==2&&!(Character.isWhitespace(names1[i].charAt(0))))
                            System.out.format("%-30s %-30s", names1[i], names2[i]);
                            System.out.println();
                    }
                }
                else if (blockPrint==2&&coderTester==false){
                    System.out.format("%-30s %-30s","Tester", "Coder");
                    System.out.println();
                    for (int i = 0; i < counter; i++) {
                        if(block1[i]==1&&!(Character.isWhitespace(names1[i].charAt(0)))) {
                            System.out.format("%-30s %-30s", names2[i], names1[i]);
                            System.out.println();
                        }
                    }
                }
                else if (coderTester==true){
                    System.out.format("%-30s %-30s","Coder", "Tester");
                    System.out.println();
                    for (int i = 0; i < counter; i++) {
                        if (!(Character.isWhitespace(names1[i].charAt(0)))) {
                            System.out.format("%-30s %-30s", names1[i], names2[i]);
                            System.out.println();
                        }
                    }
                }
                else if (coderTester==false){
                    System.out.format("%-30s %-30s","Tester", "Coder");
                    System.out.println();
                    for (int i = 0; i < counter; i++) {
                        if (!(Character.isWhitespace(names1[i].charAt(0)))) {
                            System.out.format("%-30s %-30s", names2[i], names1[i]);
                            System.out.println();
                        }
                    }
                }
                else{
                    System.out.println("Uh oh stinky");
                }

            }
            else{
                if (coderTester==true){
                    System.out.format("%-30s %-30s","Coder", "Tester");
                    System.out.println();
                    for (int i = 0; i < counter; i++) {
                        if (!(Character.isWhitespace(names1[i].charAt(0)))) {
                            System.out.format("%-30s %-30s", names1[i], names2[i]);
                            System.out.println();
                        }
                    }
                }
                else if (coderTester==false){
                    System.out.format("%-30s %-30s","Tester", "Coder");
                    System.out.println();
                    for (int i = 0; i < counter; i++) {
                        if (!(Character.isWhitespace(names1[i].charAt(0)))) {
                            System.out.format("%-30s %-30s", names2[i], names1[i]);
                            System.out.println();
                        }
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }

    }
}
