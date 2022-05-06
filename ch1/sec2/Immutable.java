package ch1.sec2;

import java.util.HashMap;
import java.util.Map;

/**
 * immutable class example
 */
public class Immutable {
    private final String name;
    private final int regNo;
    private final Map<String, String> metadata;

    public Immutable(String name, int regNo, Map<String, String> metadata) {
        this.name = name;
        this.regNo = regNo;

        Map<String, String> tempMap = new HashMap<>();
        for (Map.Entry<String, String> entry : metadata.entrySet()) {
            tempMap.put(entry.getKey(), entry.getValue());
        }
        this.metadata = tempMap;
    }

    public String getName() {
        return name;
    }

    public int getRegNo() {
        return regNo;
    }

    public Map<String, String> getMetadata() {
        Map<String, String> tempMap = new HashMap<>();
        for (Map.Entry<String, String> entry :
                this.metadata.entrySet()) {
            tempMap.put(entry.getKey(), entry.getValue());
        }
        return tempMap;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "first");
        map.put("2", "second");
        Immutable s = new Immutable("ABC", 101, map);
        System.out.println(s.getName());
        System.out.println(s.getRegNo());
        System.out.println(s.getMetadata());

        // Uncommenting below line causes error
        // s.regNo = 102;

        map.put("3", "third");
        System.out.println(s.getMetadata()); // Remains unchanged due to deep copy in constructor

        s.getMetadata().put("4", "fourth");
        System.out.println(s.getMetadata()); // Remains unchanged due to deep copy in getter

        String s1 = "hello";
        String s2 = s1;
        s1 = "world";
        System.out.println(s1);
        System.out.println(s2);
    }
}

