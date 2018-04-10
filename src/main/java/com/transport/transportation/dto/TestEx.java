package com.transport.transportation.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestEx {

    public static void main(String[] args) {
        List<Integer> lists = new ArrayList<>();
        lists.add(110);lists.add(210);lists.add(310);lists.add(410);lists.add(510);lists.add(610);lists.add(710);
        lists.add(1110);lists.add(1033);lists.add(12022);lists.add(1440);lists.add(3410);lists.add(150);lists.add(160);
        lists.add(2210);lists.add(1022);lists.add(10333);lists.add(1330);lists.add(14460);lists.add(110);lists.add(120);
        lists.add(3310);lists.add(1033);lists.add(1022);lists.add(1066);lists.add(1320);lists.add(120);lists.add(1220);
        lists.add(4410);lists.add(4210);lists.add(1011);lists.add(1555);lists.add(1340);lists.add(1230);lists.add(1012);
        for(int i = 0; i < lists.size(); i++) {
            Integer in = lists.get(new Random().nextInt(lists.size()));
            System.out.println(in);
        }
    }
}
