package com.baosongle.tinywebj;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TinyWebJ tinyWebJ = new TinyWebJ(80);
        try {
            tinyWebJ.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
