package com.company.streams;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Streams {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(100);
        Random random = new Random();
        for (int i =0; i < 100; i++){
            byte[] chars = new byte[random.nextInt(20)];
            random.nextBytes(chars);
            list.add(new String(chars, Charset.forName("UTF-8")));
        }
        list.forEach(System.out::println);
    }
}
