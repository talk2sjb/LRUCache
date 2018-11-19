package org.sjbanerjee;

import org.sjbanerjee.cache.LruCache;

public class Main {
    public static LruCache cache = new LruCache();

    public static void main(String[] args){
        cache.put("key1", "val1");

        System.out.println(cache.get("key1"));//output should be val1
        System.out.println(cache.get("key2"));//output should be null

        for(int i = 2; i <= 10; i++){
            cache.put("key" + i, "val" + i);
        }

        System.out.println(cache.get("key5"));//output should be val5

        cache.put("key11", "val11");
        System.out.println(cache.get("key1"));//output should be null
    }
}
