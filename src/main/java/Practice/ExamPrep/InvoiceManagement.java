package Practice.ExamPrep;

import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum TextFormat {
    CAPITALIZE,
    LOWERCASE,
    PLAIN
}

enum CurrencyFormat {
    USD,
    EURO,
    INR
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface FieldFormat {
    TextFormat textFormat() default TextFormat.PLAIN;
    CurrencyFormat currencyFormat() default CurrencyFormat.USD;
}

class InvalidInvoiceException extends RuntimeException {
    public InvalidInvoiceException(String message){
        super(message);
    }

    public InvalidInvoiceException(String message, Throwable cause){
        super(message, cause);
    }
}

class Invoice {
    private Date invoiceDate;
    @FieldFormat(textFormat = TextFormat.CAPITALIZE)
    private String invoiceNumber;
    private int qtr;
    private int year;
    @FieldFormat(textFormat = TextFormat.CAPITALIZE)
    private String customerName;
    @FieldFormat(currencyFormat = CurrencyFormat.USD)
    private double totalAmount;
    @FieldFormat(currencyFormat = CurrencyFormat.EURO)
    private double taxAmount;
    private double netAmount;

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public int getQtr() {
        return qtr;
    }

    public int getYear() {
        return year;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    private Invoice(InvoiceBuilder builder){
        this.invoiceDate = builder.invoiceDate;
        this.invoiceNumber = builder.invoiceNumber;
        this.qtr = builder.qtr;
        this.year = builder.year;
        this.customerName = builder.customerName;
        this.totalAmount = builder.totalAmount;
        this.taxAmount = builder.taxAmount;
        this.netAmount = builder.netAmount;
    }

    public static class InvoiceBuilder {
        Date invoiceDate;
        String invoiceNumber;
        int qtr;
        int year;
        String customerName;
        double totalAmount;
        double taxAmount;
        double netAmount;

        public InvoiceBuilder setNetAmount(double amount){
            this.netAmount = amount;
            return this;
        }

        public InvoiceBuilder setTaxAmount(double amount){
            this.taxAmount = amount;
            return this;
        }

        public InvoiceBuilder setTotalAmount(double amount){
            this.totalAmount = amount;
            return this;
        }

        public InvoiceBuilder setCustomerName(String name){
            this.customerName = name;
            return this;
        }

        public InvoiceBuilder setYear(int year){
            this.year = year;
            return this;
        }

        public InvoiceBuilder setQtr(int qtr){
            this.qtr = qtr;
            return this;
        }

        public InvoiceBuilder setInvoiceNumber(String number){
            this.invoiceNumber = number;
            return this;
        }

        public InvoiceBuilder setInvoiceDate(Date date){
            this.invoiceDate = date;
            return this;
        }

        public Invoice build(){
            return new Invoice(this);
        }
    }
}

class InvoiceProcessor {
    public static List<Invoice> loadInvoices(String filename){
        List<Invoice> invoices = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line = reader.readLine();

            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");

                if(fields[0].isEmpty() || fields[1].isEmpty() || fields[5].isEmpty()){
                    throw new InvalidInvoiceException("Missing required fields", new ClassCastException());
                }

                Invoice invoice = new Invoice.InvoiceBuilder()
                        .setInvoiceDate(sdf.parse(fields[0]))
                        .setInvoiceNumber(fields[1])
                        .setQtr(Integer.parseInt(fields[2]))
                        .setYear(Integer.parseInt(fields[3]))
                        .setCustomerName(fields[4])
                        .setTotalAmount(Double.parseDouble(fields[5]))
                        .setTaxAmount(Double.parseDouble(fields[6]))
                        .setNetAmount(Double.parseDouble(fields[7]))
                        .build();
                invoices.add(invoice);
            }
        } catch (FileNotFoundException e){
            throw new InvalidInvoiceException("File not found", e);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        } catch (ParseException e ){
            System.out.println(e.getMessage());
        }
        return invoices;
    }
}

class CSVReportGenerator {
    public static void generateReport(String filename, List<Invoice> invoices){

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for(Invoice invoice : invoices){
                Field[] fields = Invoice.class.getDeclaredFields();
                StringBuilder line = new StringBuilder();

                for(Field field : fields){
                    field.setAccessible(true);
                    Object value = field.get(invoice);
                    if(field.isAnnotationPresent(FieldFormat.class)){
                        FieldFormat annotation = field.getAnnotation(FieldFormat.class);

                        if(value instanceof String){
                            TextFormat tf = annotation.textFormat();
                            if(tf == TextFormat.LOWERCASE){
                                value = ((String) value).toLowerCase();
                            } else if(tf == TextFormat.CAPITALIZE){
                                value = capitalize(((String) value));
                            }
                        } else if(value instanceof Double){
                            CurrencyFormat cf = annotation.currencyFormat();
                            if(cf == CurrencyFormat.USD){
                                value = "$" + value;
                            } else if(cf == CurrencyFormat.EURO){
                                value = "€" + value;
                            } else {
                                value = "₹" + value;
                            }
                        }
                    }
                    if(value instanceof Date) System.out.println(value);
                    line.append(value).append(",");
                }

                line.append("\n");
                writer.write(line.toString());
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e){
            System.out.println(e.getMessage());
        }
    }

    public static String capitalize(String input){
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();
        for(String word : words){
            result.append(word.substring(0, 1).toUpperCase())
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return result.toString().trim();
    }
}

class CSVUtils {
    public static int countLines(String filename){
        int count = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line = reader.readLine();
            while((line = reader.readLine()) != null){
                count++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return count;
    }
}

class InvoiceRun {
    public static void main(String[] args){
        CSVReportGenerator.generateReport("invoiceReport.csv", InvoiceProcessor.loadInvoices("invoices.csv"));
    }
}
