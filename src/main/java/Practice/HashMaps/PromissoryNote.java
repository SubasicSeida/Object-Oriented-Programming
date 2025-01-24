package Practice.HashMaps;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PromissoryNote {
    private HashMap<String, List<Double>> promissoryNote;

    public PromissoryNote(){
        promissoryNote = new HashMap<>();
    }

    public PromissoryNote(HashMap<String, List<Double>> hashmap){
        promissoryNote = hashmap;
    }

    public void setLoan(String toWhom, double value){
        if(promissoryNote.containsKey(toWhom)){
            List<Double> loans = promissoryNote.get(toWhom);
            loans.add(value);
        } else {
            List<Double> loans = new ArrayList<>();
            loans.add(value);
            promissoryNote.put(toWhom, loans);
        }

    }

    public double howMuchIsTheDebt(String whose){
        if(promissoryNote.containsKey(whose)){
            List<Double> loans = promissoryNote.get(whose);
            return loans.stream()
                    .mapToDouble(loan -> loan)
                    .sum();
        } else return 0.0;
    }

    public String removeLoan(String whose, double value){
        if(promissoryNote.containsKey(whose)){
            final double EPSILON = 1e-9;
            List<Double> loans = promissoryNote.get(whose);
            boolean removed = loans.removeIf(loan -> Math.abs(loan - value) < EPSILON);
            if (removed) {
                return "Loan removed.";
            } else {
                return "Loan value not found.";
            }
        }
        return "Loan not found.";
    }

    public void printAllLoans(){
        for(HashMap.Entry<String, List<Double>> entry : promissoryNote.entrySet()){
            System.out.println("Name : " + entry.getKey() + ", Loans : " + entry.getValue());
        }
    }

    public double getTotalLoanAmount(){
        double sum = 0.0;
        for(HashMap.Entry<String, List<Double>> entry : promissoryNote.entrySet()){
            sum += entry.getValue().stream()
                    .mapToDouble(loan -> loan)
                    .sum();
        }
        return sum;
    }

    public String getLargestLoan(){
        if(promissoryNote.isEmpty()) return "No loans available.";

        double largestLoan = -1;
        String name = "";

        for(HashMap.Entry<String, List<Double>> entry : promissoryNote.entrySet()){
            for(double loan : entry.getValue()){
                if(largestLoan < loan){
                    largestLoan = loan;
                    name = entry.getKey();
                }
            }
        }
        return name;
    }

    public void addInterest(double interest){
        for(HashMap.Entry<String, List<Double>> entry : promissoryNote.entrySet()){
            List<Double> updatedLoans = entry.getValue().stream()
                    .map(loan -> loan + loan * interest)
                    .collect(Collectors.toList());
            entry.setValue(updatedLoans);
        }
    }

    public boolean hasLoan(String who){
        return promissoryNote.containsKey(who);
    }

    public void clearAllLoans(){
        promissoryNote.clear();
        System.out.println("All loans have been cleared.");
    }

    public String mergeAllLoans(String name){
        if(!promissoryNote.containsKey(name)) return "Name not found.";

        for(HashMap.Entry<String, List<Double>> entry : promissoryNote.entrySet()){
            double sum = entry.getValue().stream()
                    .mapToDouble(loan -> loan)
                    .sum();
            entry.setValue(List.of(sum));
        }
        return "Loans merged.";
    }

    public void loanSummary(){
        System.out.println("Total number of people with loans : " + promissoryNote.size());
        System.out.println("Total amount loaned : " + getTotalLoanAmount());
        System.out.println("Person with largest loan : " + getLargestLoan());
    }

    public void saveToFile(String filename){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for(HashMap.Entry<String, List<Double>> entry : promissoryNote.entrySet()){
                writer.write(entry.getKey() + " : " +
                        entry.getValue().toString().replace("[", "").replace("]", ""));
                writer.newLine();
            }
        } catch(IOException e){
            System.out.println("Exception while saving to file : " + e);
        }
    }

    public static HashMap<String, List<Double>> loadLoansFromFile(String filename){
        HashMap<String, List<Double>> loansFromFile = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line = "";
            while((line = reader.readLine()) != null){
                String[] parts = line.split(":");
                if(parts.length == 2){
                    String name = parts[0].trim();

                    List<Double> loans = new ArrayList<>();
                    String[] loanList = parts[1].split(",");

                    for(String loan : loanList){
                        loans.add(Double.parseDouble(loan.trim()));
                    }

                    loansFromFile.put(name, loans);
                }
            }
            return loansFromFile;

        } catch(IOException e){
            System.out.println("Exception while loading from file : " + e);
        }

        return null;
    }

    public static void main(String[] args){
        PromissoryNote mattisNote = new PromissoryNote();
        mattisNote.setLoan("Arto", 51.5);
        mattisNote.setLoan("Arto", 20);
        mattisNote.setLoan("Mikael", 30);

        System.out.println("Arto's debt : " + mattisNote.howMuchIsTheDebt("Arto"));
        System.out.println("Joel's debt : " + mattisNote.howMuchIsTheDebt("Joel"));
        System.out.println("Mikael's debt : " + mattisNote.howMuchIsTheDebt("Mikael"));

        mattisNote.addInterest(0.3);

        mattisNote.saveToFile("loans.txt");
        PromissoryNote loansLoadedFromFile = new PromissoryNote(loadLoansFromFile("loans.txt"));
        loansLoadedFromFile.printAllLoans();
    }
}
