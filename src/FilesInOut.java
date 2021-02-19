import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOut {

    public static void main(String[] args) {

        String outputMaker= ""; //using this string to pass it on to the output file


        //reading the content of the desired input file (with the middle initial)
        try {
            File inputFile = new File(args[1]); //the input file
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String firstName = "";
                String lastName = "";
                String middleInitial = "";
                String date = "";

                String words[] = data.split(" "); //create an array that will contain elements:
                // first name, last name, date, and middle initial if necessary

                if (words.length == 3) { //if there is no middle initial
                    firstName = words[0];
                    lastName = words[1];
                    date = words[2];

                    SimpleDateFormat d = new SimpleDateFormat("ddMMyyyy"); //format the date
                    Date newDate = d.parse(date);
                    SimpleDateFormat dd = new SimpleDateFormat("dd/MM/yyyy");

                    //capitalize the first letter of the first name
                    firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
                    //capitalize the first letter of the last name
                    lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
                    //print the newly formatted title case name
//                    System.out.println(firstNameM + " " + lastNameM + " " + dd.format(newDate)); //used for checking the output
                    //string containing all of the inputs that get passed on to the output file
                    outputMaker = outputMaker.concat(firstName + " " + lastName + " " + dd.format(newDate) + "\n");
                }
                else { //if there is a middle initial
                    firstName = words[0];
                    middleInitial = words[1];
                    lastName = words[2];
                    date = words[3];

                    SimpleDateFormat d = new SimpleDateFormat("ddMMyyyy");
                    Date newDate = d.parse(date);
                    SimpleDateFormat dd = new SimpleDateFormat("dd/MM/yyyy");

                    //capitalize the first letter of the first name
                    firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
                    //capitalize the middle initial and add a period to it
                    middleInitial = middleInitial.toUpperCase() + ".";
                    //capitalize the first letter of the last name
                    lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
//                    System.out.println(firstName + " " + middleInitial + " " + lastName + " " + dd.format(newDate)); //used for checking the output
                    outputMaker = outputMaker.concat(firstName + " " + middleInitial + " " + lastName + " " + dd.format(newDate) + "\n");
                }
            }
            myReader.close();
        } catch (FileNotFoundException | ParseException e) { //exception for the parse
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //creating the output file
        try {
            PrintWriter outputFile = new PrintWriter(args[2]); //the output file

//            if (outputFile.createNewFile()) { //code for creating the output file
//                System.out.println("File created: " + outputFile.getName());
//                outputFile.pr
//            } else {
//                System.out.println("File already exists.");
//            }

            if (args[0].equals("-u")) {
                outputFile.format("%S",outputMaker); //to capitalize the firstname and lastname
            }
            if (args[0].equals("-tc")){
                outputFile.println(outputMaker); //to output title case
            }
            outputFile.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    } // main

} // FilesInOut
