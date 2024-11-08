package Week6;

import java.util.Random;

public class NightSky {
    private double density;
    private int width;
    private int height;
    private int starsInLastPrint;

    public NightSky(double density){
        this.density = density;
        width = 20;
        height = 10;
        starsInLastPrint = 0;
    }

    public NightSky(int width, int height){
        density = 0.1;
        this.width = width;
        this.height = height;
        starsInLastPrint = 0;
    }

    public NightSky(double density, int width, int height){
        this.density = density;
        this.height = height;
        this.width = width;
        starsInLastPrint = 0;
    }

    public void printLine(){
        Random rand = new Random();
        int starsPrinted = 0;
        for(int i = 0; i < width; i++){
            if(rand.nextDouble() < density){
                System.out.print("*");
                starsPrinted++;
            }else {
                System.out.print(" ");
            }
        }
        setStarsInLastPrint(starsPrinted);
        System.out.println();
    }

    public void print(){
        for(int i = 0; i < height; i++){
            printLine();
        }
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getStarsInLastPrint() {
        return starsInLastPrint;
    }

    public void setStarsInLastPrint(int starsInLastPrint) {
        this.starsInLastPrint += starsInLastPrint;
    }
}
