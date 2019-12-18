package com.mygdx.game;

import jdk.nashorn.internal.runtime.Debug;

import java.util.Random;

/**
 * Created by ita49 on 26.01.2017.
 */
public class CodeGenerator {

    public static int firstDigit = 0;
    public static int secondDigit = 0;
    public static int thirdDigit = 0;
    public static int fourthDigit = 0;

    public static String computerPassword = "root";

    public static void init() {
        Random ran = new Random();

        firstDigit = ran.nextInt(10) + 1;
        secondDigit = ran.nextInt(10)+ 1;
        thirdDigit = ran.nextInt(10)+ 1;
        fourthDigit = ran.nextInt(10)+ 1;

        while(secondDigit == firstDigit)
            secondDigit = ran.nextInt(10)+ 1;

        while(thirdDigit == firstDigit || thirdDigit == secondDigit)
            thirdDigit = ran.nextInt(10)+ 1;

        while(fourthDigit == firstDigit || fourthDigit == secondDigit ||fourthDigit == thirdDigit)
            fourthDigit = ran.nextInt(10)+ 1;

        computerPassword = firstDigit + "" + secondDigit + "" + thirdDigit + "" + fourthDigit;

        System.out.println("pw: " + computerPassword);
    }


}
