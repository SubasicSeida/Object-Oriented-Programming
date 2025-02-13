package Practice.ExamPrep;

import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum CaseFormatter {
    ORDINARY,
    UPPER_CASE,
    LOWER_CASE
}

enum NumberFormatter {
    COMMA,
    PERCENTAGE
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface WriteConcerns {
    CaseFormatter caseFormat() default CaseFormatter.ORDINARY;
    NumberFormatter numberFormat() default NumberFormatter.COMMA;
}

class WrongFormatException extends RuntimeException {
    public WrongFormatException(String message, Throwable cause){
        super(message, cause);
    }

    public WrongFormatException(String message){
        super(message);
    }
}

class Book {
    @WriteConcerns
    private Date date;
    @WriteConcerns(caseFormat = CaseFormatter.ORDINARY)
    private String quarter;
    private int qtr;
    private int year;
    @WriteConcerns(caseFormat = CaseFormatter.UPPER_CASE)
    private String customerId;
    @WriteConcerns(numberFormat = NumberFormatter.COMMA)
    private double totalAmount;
    @WriteConcerns(numberFormat = NumberFormatter.PERCENTAGE)
    private double profitPercentage;
    private double profitInr;
    private double costPrice;

    public Date getDate() {
        return date;
    }

    public String getQuarter() {
        return quarter;
    }

    public int getQtr() {
        return qtr;
    }

    public int getYear() {
        return year;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getProfitPercentage() {
        return profitPercentage;
    }

    public double getProfitInr() {
        return profitInr;
    }

    public double getCostPrice() {
        return costPrice;
    }

    private Book(BookBuilder builder){
        this.date = builder.date;
        this.quarter = builder.quarter;
        this.qtr = builder.qtr;
        this.year = builder.year;
        this.customerId = builder.customerId;
        this.totalAmount = builder.totalAmount;
        this.profitPercentage = builder.profitPercentage;
        this.profitInr = builder.profitInr;
        this.costPrice = builder.costPrice;
    }

    static class BookBuilder {
        Date date;
        String quarter;
        int qtr;
        int year;
        String customerId;
        double totalAmount;
        double profitPercentage;
        double profitInr;
        double costPrice;

        public BookBuilder setCostPrice(double costPrice){
            this.costPrice = costPrice;
            return this;
        }

        public BookBuilder setProfitInr(double profitInr){
            this.profitInr = profitInr;
            return this;
        }

        public BookBuilder setProfitPercentage(double profitPercentage){
            this.profitPercentage = profitPercentage;
            return this;
        }

        public BookBuilder setTotalAmount(double totalAmount){
            this.totalAmount = totalAmount;
            return this;
        }

        public BookBuilder setCustomerId(String customerId){
            this.customerId = customerId;
            return this;
        }

        public BookBuilder setYear(int year){
            this.year = year;
            return this;
        }

        public BookBuilder setQtr(int qtr){
            this.qtr = qtr;
            return this;
        }

        public BookBuilder setQuarter(String quarter){
            this.quarter = quarter;
            return this;
        }

        public BookBuilder setDate(Date date){
            this.date = date;
            return this;
        }

        public Book build(){
            return new Book(this);
        }
    }
}

class FinalPrep {
    public static List<Book> loadBooks(String filename) throws WrongFormatException {
        List<Book> books = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line = "";
            boolean isHeader = true;

            while((line = reader.readLine()) != null){
                if(isHeader){
                    isHeader = false;
                    continue;
                }
                String[] fields = line.split(";");
                if(fields[0].isEmpty() || fields[4].isEmpty() || fields[5].isEmpty()){
                    throw new WrongFormatException("Values are empty", new ClassCastException());
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
                Date date = dateFormat.parse(fields[0]);
                String quarter = fields[1];
                int qtr = Integer.parseInt(fields[2]);
                int year = Integer.parseInt(fields[3]);
                String customerId = fields[4];
                double totalAmount = Double.parseDouble(fields[5]);
                double profitPercentage = Double.parseDouble(fields[6]);
                double profitInr = Double.parseDouble(fields[7]);
                double costPrice = Double.parseDouble(fields[8]);

                Book book = new Book.BookBuilder()
                        .setDate(date)
                        .setQuarter(quarter)
                        .setQtr(qtr)
                        .setYear(year)
                        .setCustomerId(customerId)
                        .setTotalAmount(totalAmount)
                        .setProfitPercentage(profitPercentage)
                        .setProfitInr(profitInr)
                        .setCostPrice(costPrice)
                        .build();

                books.add(book);
            }

        } catch (FileNotFoundException e){
            throw new WrongFormatException("File not found", e);
        } catch (IOException | ParseException e){
            System.out.println("Exception while loading books : " + e.getMessage());
        }

        return books;
    }
}

class ReportWriter {
    public static void writeReport(String outputFile, List<Book> books){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){

            writer.write("Date;Quarter;Qtr;Year;Customer ID;\"Total_amount\";\"Profit Percentage(%)\";Profit (INR);Cost Price");

            Field[] fields = Book.class.getDeclaredFields();
            for(Book book : books){
                for(Field field : fields){

                    field.setAccessible(true);
                    if(field.isAnnotationPresent(WriteConcerns.class)){
                        WriteConcerns annotation = field.getAnnotation(WriteConcerns.class);

                        if(field.getType() == String.class){
                            CaseFormatter caseFormatter = annotation.caseFormat();

                            if(caseFormatter == CaseFormatter.LOWER_CASE){
                                String value = (String) field.get(book);
                                if(value != null) field.set(book, value.toLowerCase());
                            } else if(caseFormatter == CaseFormatter.UPPER_CASE){
                                String value = (String) field.get(book);
                                if(value != null) field.set(book, value.toUpperCase());
                            }

                        } else if(field.getType() == Double.class){
                            NumberFormatter numberFormatter = annotation.numberFormat();

                            if(numberFormatter == NumberFormatter.COMMA){
                                DecimalFormat df = new DecimalFormat("#,###.##");
                                Double value = (Double) field.get(book);
                                df.format(value);
                                field.set(book, value);
                            } else if(numberFormatter == NumberFormatter.PERCENTAGE){
                                Double value = (Double) field.get(book);
                                field.set(book, value + "%");
                            }
                        }
                    }
                }

                writer.write(book.getDate() + ";" + book.getQuarter() + ";" + book.getQtr() +
                        ";" + book.getYear() + ";" + book.getCustomerId() + ";" + book.getTotalAmount() +
                        ";" + book.getProfitPercentage() + ";" + book.getProfitInr() + ";" + book.getCostPrice() + "\n");

            }
        } catch (IOException | IllegalAccessException e){
            System.out.println("Exception while writing the report : " + e.getMessage());
        }
    }
}

class CsvUtils {
    public static int countRows(String fileName) throws IOException {
        int rowCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();
            while (br.readLine() != null) {
                rowCount++;
            }
        }
        return rowCount;
    }
}

class MainRun {
    public static void main(String[] args){
        ReportWriter.writeReport("bookReport.csv", FinalPrep.loadBooks("books.csv"));
    }
}
