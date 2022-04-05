package com.ginspiration.serverbackground;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class ServerBackgroundApplicationTests {

    @Test
    void contextLoads() {
      int i = 0;
      i=i++;
      int j = i++;
      int k = i+ ++i * i++;
        System.out.println(i);//4
        System.out.println(j);//1
        System.out.println(k);//12
    }

    public static void main(String[] args) {
        int i = 0;
        i=i++;
        int j = i++;
        int k = i+ ++i * i++;
        System.out.println(i);//4
        System.out.println(j);//1
        System.out.println(k);//12
    }

}
