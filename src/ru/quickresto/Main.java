package ru.quickresto;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        int[] resolve = new Resolver().resolve(new int[]{1, 2, 3, 0, 5, 7, 4, 6});
//        int[] resolve = new Resolver().resolve(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        int[] resolve = new Resolver().resolve(new int[]{2, 1, 3, 4, 0, 5, 6, 7});
        System.out.println(Arrays.toString(resolve));
    }

}
