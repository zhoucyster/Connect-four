package four;

import java.util.HashMap;
import java.util.Map;

public class ColumnTracker {
    static Map<Integer, Integer> columnBottom = new HashMap<>();
    {
        columnBottom.put(0,5);
        columnBottom.put(1,5);
        columnBottom.put(2,5);
        columnBottom.put(3,5);
        columnBottom.put(4,5);
        columnBottom.put(5,5);
        columnBottom.put(6,5);

    }
    static Map<String, Integer> letterSequence = Map.of(
            "A",0,
            "B",1,
            "C",2,
            "D",3,
            "E",4,
            "F",5,
            "G",6
    );
    public void resetColumnTracker(){
        for (Integer s: columnBottom.keySet()) {
            columnBottom.put(s,5);
        }
    }
}
