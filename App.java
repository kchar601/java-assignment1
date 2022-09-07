import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
public class App {

    public static String promptBride(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter the name of the Bride: ");
        String brideName = scnr.next();
        return brideName;
    }

    public static String promptGroom(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter the name of the Groom: ");
        String groomName = scnr.next();
        return groomName;
    }

    public static int promptGuests(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter the number of guests attending: ");
        int numGuests = scnr.nextInt();
        return numGuests;
    }

    public static double promptSqft(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter the Square footage of the location: ");
        double sqFt = scnr.nextDouble();
        return sqFt;
    }

    public static ArrayList<String> promptSongs(){
        Scanner scnr = new Scanner(System.in);
        ArrayList<String> songList = new ArrayList<String>();
        boolean done = false;
        String songName;
        while(!done){
            System.out.println("Enter the name of the song you would like to add to the playlist (Enter -1 to quit): ");
            songName = scnr.nextLine();
            if(songName.equals("-1")){done = true;}
            else{
                songList.add(songName);
                System.out.println("\nAdded: " + songName);}
        }
        return songList;
    }

    public static double getSQFTperPerson(int guests, double sqFt){
        double sqftPerGuest = sqFt / (double) guests;
        return sqftPerGuest;
    }

    public static void writeToFile(String bride, String groom, int numGuests, double sqFootage, double sqFtPer, ArrayList<String> songList){
        String file = "wedding.txt";
    try{
        FileOutputStream fileStream = new FileOutputStream(file);
        PrintWriter outFS = new PrintWriter(fileStream); 
        outFS.println(bride);
        outFS.println(groom);
        outFS.println(numGuests);
        outFS.println(sqFootage);
        outFS.println(sqFtPer);
        for (int y = 0; y < songList.size(); y++) { 
            outFS.println(songList.get(y));
            }
        outFS.close();
    }
    catch(FileNotFoundException f)
        {
        String errorMsg = f.getMessage();
        System.out.println(errorMsg);
        }
    }

    public static void readAndPrint(){
        String fileName = "wedding.txt";
        int counter = 0;
        try{
            FileInputStream fileByteStream = new FileInputStream(fileName);
            Scanner inFS = new Scanner(fileByteStream); 
            while (inFS.hasNextLine()) { 
                switch (counter) {
                    case 0:
                        System.out.println("\nBride: " + inFS.nextLine());
                        break;
                
                    case 1:
                        System.out.println("Groom: " + inFS.nextLine());    
                        break;
                    case 2: 
                        System.out.println("Number of Guests Attending: " + inFS.nextLine());  
                        break;
                    case 3:
                        System.out.println("Square Footage of the Location: " + inFS.nextLine() + " sqft");
                        break;
                    case 4:
                        System.out.println("Square Footage per Guest: " + inFS.nextLine());
                        break;
                    case 5:
                        System.out.print("List of songs in the Playlist: " + inFS.nextLine() + ", ");
                        break;
                    default:
                        System.out.print(inFS.nextLine() + ", ");
                        break;
                }
                counter++;
            }
            inFS.close();
        }
        
        catch(FileNotFoundException notFound)
            {
            String errorMsg = notFound.getMessage();
            System.out.println(errorMsg);
            }
    }

    public static void main(String[] args) {
        
        String bride = promptBride();
        String groom = promptGroom();
        int numGuests = promptGuests();
        double sqFootage = promptSqft();
        ArrayList<String> playlist = promptSongs();
        double sqFtPer = getSQFTperPerson(numGuests, sqFootage);
        writeToFile(bride, groom, numGuests, sqFootage, sqFtPer, playlist);
        readAndPrint();
    }
}