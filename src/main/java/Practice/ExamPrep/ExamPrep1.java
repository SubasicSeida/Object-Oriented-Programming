package Practice.ExamPrep;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

enum CaseFormatter {
    ORDINARY,
    LOWER_CASE,
    UPPER_CASE
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

    public Book(BookBuilder builder){
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
        private Date date;
        private String quarter;
        private int qtr;
        private int year;
        private String customerId;
        private double totalAmount;
        private double profitPercentage;
        private double profitInr;
        private double costPrice;

        public BookBuilder setDate(Date date){
            this.date = date;
            return this;
        }
        public BookBuilder setQuarter(String quarter){
            this.quarter = quarter;
            return this;
        }
        public BookBuilder setQtr(int qtr){
            this.qtr = qtr;
            return this;
        }
        public BookBuilder setYear(int year){
            this.year = year;
            return this;
        }
        public BookBuilder setCustomerId(String customerId){
            this.customerId = customerId;
            return this;
        }



    }
}
