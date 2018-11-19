package org.sjbanerjee.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LruCache implements Cache<String, String> {
    private static Map<String, LruQueue.Node<String, String>> map = new HashMap<>();
//    For multi threaded scenario
//    private static Map<String, LruQueue.Node<String, String>> map = new ConcurrentHashMap<>();
    private static LruQueue queue = new LruQueue();
    private static int CACHE_SIZE = 10;

    public LruCache() {

    }

    @Override
    public String get(String key) {
        //Put the most recently used at the top of the queue
        if(!map.containsKey(key)){
            return "null";
        }

        LruQueue.Node node = map.get(key);

        //No need to put on top if only element in queue
        if(queue.getSize() > 1) {
            queue.pop(node);
            queue.push(node);
        }

        return map.get(key).getData();
    }

    @Override
    public void put(String key, String value) {
        //If cache capacity reached, delete last node (tail) from map
        if(queue.getSize() >= CACHE_SIZE){
            LruQueue.Node tail = queue.getTail();
            map.remove(tail.getKey());
        }

        if(map.containsKey(key)){
            queue.pop(map.get(key));
        }

        LruQueue.Node<String, String> node = new LruQueue.Node<>(key, value);

        queue.push(node);
        map.put(key, node);
    }

    @Override
    public void purge(String key) {
        LruQueue.Node node = map.get(key);
        map.remove(key);
        queue.pop(node);
    }
}
