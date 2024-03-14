//import the respective packages
import java.io.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

//driver class
public class LabTest0423
{
    //Driver method
    public static void main(String[]args) throws IOException
    {
        //Instantiate the object of DecimalFormat
        DecimalFormat dF = new DecimalFormat("0.00");

        try
        {
            //Set the input/output file
            //input file
            BufferedReader inputFile = new BufferedReader(new
                    FileReader("listOrder.txt"));

            //2 output files
            PrintWriter withDisc = new PrintWriter(new
                    FileWriter("withDiscount.txt"));
            PrintWriter withoutDisc = new PrintWriter(new
                    FileWriter("withoutDiscount.txt"));

            //Declare the variables
            String inputData = null;
            String custName,custBisc;
            double custPrice,totPrice, priceAftDisc;

            
            double discRate;
            int custQuan;

            //Write the title of the patient details to the withDiscount.txt
            withDisc.println("List of biscuits and the customers who get the discount rates");
            withDisc.println("\nBiscuit's Name\t\tTotal Price(RM)\t\tPrice after Discout(RM)\t\tCustomer Name");
            withDisc.println("----------------------------------------------------------------------------------------------------");

            //Write the title of the patient details to the
            //withoutDiscount.txt
            withoutDisc.println("List of customer");
            withoutDisc.println("\nCustomer Name\t\tTotalPrice(RM)\t\tBiscuit's Name");
            withoutDisc.println("----------------------------------------------------------------------------------------------------");

            int countWithDisc = 0;
            int countWithoutDisc = 0;

            while((inputData = inputFile.readLine()) != null)
            {
                //INstantiate the object reference of the StringTokenizer
                //class
                //to pass the string line (input data) & to set the delimeter
                StringTokenizer sT = new StringTokenizer(inputData,";");
                //to pass the string line &delimeter

                //Break into tokens and assign to the appropriate variables
                custName = sT.nextToken();
                custBisc = sT.nextToken();

                //or

                custPrice =Double.parseDouble(sT.nextToken());
                custQuan =Integer.parseInt(sT.nextToken());

                //String str = sT.nextToken();
                //custQuan = Integer.parseInt(str);

                //To test for the negative number
                if(custPrice < 0)
                    throw new IllegalArgumentException();

                //Calculate the total price
                totPrice = custPrice * custQuan;

                //Initialize the category to null
                String category =null;
                if(totPrice < 50)
                {
                    //Calculate discount
                    discRate = 0.0;
                    priceAftDisc = totPrice;
                    withoutDisc.println(custName + "\t\t" +
                        dF.format(totPrice) + "\t\t\t" + custBisc);
                }
                else if (totPrice >= 50 && totPrice <= 200)
                {
                    discRate = 0.1;
                    priceAftDisc = totPrice * (1 - 0.9);
                    withDisc.println(custBisc + "\t\t" + dF.format(totPrice)+
                        "\t\t\t" + dF.format(priceAftDisc) + "\t\t\t\t" +
                        custName);
                }
                else

                 

                {
                    discRate = 0.2;
                    priceAftDisc = totPrice * (1 - discRate);
                    withDisc.println(custBisc + "\t\t" + dF.format(totPrice)+
                        "\t\t\t" + dF.format(priceAftDisc) + "\t\t\t\t" +
                        custName);
                }
            }//end of while loop

            //close all the input/output files
            //Close the input/output file
            inputFile.close();
            withDisc.close();
            withoutDisc.close();
        }//end of try block
        catch(FileNotFoundException ex)
        {
            String output="File not found";
            JOptionPane.showMessageDialog(null, output);
        }//end of catch
        catch(IllegalArgumentException iae)
        {
            String output="Invalid input! The price must be a positive number";
            JOptionPane.showMessageDialog(null, output);
        }//end of catch
    }//end of main
}//end of class