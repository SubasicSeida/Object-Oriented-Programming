package Week6;

import java.util.HashMap;

public class PromissoryNote {
    private HashMap<String, Double> loans;

    public PromissoryNote(){
        loans = new HashMap<>();
    }

    public void setLoan(String toWhom, double loan){
        loans.put(toWhom, loan);
    }

    public double howMuchIsTheDebt(String whose){
        if(loans.get(whose) == null) {
            return 0.0;
        } else {
            return loans.get(whose);
        }
    }

    public static void main(String[] args){
        PromissoryNote mattisNote = new PromissoryNote();
        mattisNote.setLoan("Arto", 51.5);
        mattisNote.setLoan("Mikael", 30);
        System.out.println(mattisNote.howMuchIsTheDebt("Arto"));
        // System.out.println(mattisNote.howMuchIsTheDebt("Joel"));
        mattisNote.setLoan("Arto", 10.5);
        System.out.println(mattisNote.howMuchIsTheDebt("Arto"));
    }
}
