import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BalancedIntercept {
    private Map<String,Integer> countMap;
    private int methodSum;
    private List<Integer> sumList = new ArrayList<>();
    public BalancedIntercept(){
        countMap = new LinkedHashMap<>();
    }
    static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e2.getValue().compareTo(e1.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
    private  Map<Integer,List<String>>  classIntercept( int bucket){
        Map<Integer,List<String>> buckets = new HashMap<>();
        SortedSet<Map.Entry<String,Integer>> sortMapSet = entriesSortedByValues(countMap);
        System.out.println(sortMapSet);
        countMap = new LinkedHashMap<>();
        for(Map.Entry<String,Integer> entry: sortMapSet){
            countMap.put(entry.getKey(),entry.getValue());
        }
        int sum = 0;
        int smallestIndex;
        Map<Integer,Integer> bucketSum = new HashMap<>();
        for(int i = 0; i < bucket; i++)
            bucketSum.put(i,0);

        for(Map.Entry<String,Integer> entry: countMap.entrySet()) {
            List<String> list = new ArrayList<>();
            sum += entry.getValue();
            smallestIndex = findSmallestSum(bucketSum);
            if(buckets.containsKey(smallestIndex))
                list = buckets.get(smallestIndex);
            list.add(entry.getKey());
            buckets.put(smallestIndex,list);

            bucketSum.put(smallestIndex,bucketSum.get(smallestIndex)+ entry.getValue());
        }

        int avg = sum / bucket;
        System.out.println("sum "+ sum );
        System.out.println("Average: "+ avg);
        System.out.println(bucketSum);
        return buckets;
    }
    private int findSmallestSum(Map<Integer,Integer>bucketSum){
        int smallest = bucketSum.get(0) ;
        int smallestIndex = 0;
        int i = 0;
        for (Map.Entry<Integer,Integer> entry:bucketSum.entrySet() ){
            if (entry.getValue() < smallest) {
                smallest = entry.getValue();
                smallestIndex = i;
            }
            i++;
        }
        System.out.println(bucketSum);
        System.out.println("The smallest bucket is " + smallestIndex);
        return smallestIndex;
    }

    public static void main(String[] args){
        BalancedIntercept t = new BalancedIntercept();
        Map<String,Integer> task =new HashMap<>();
        List<String> bucket = new ArrayList<>();
//        t.countMap.put("b1",157);
//        t.countMap.put("b2",123);
//        t.countMap.put("b3",122);
//        t.countMap.put("b4",111);
//        t.countMap.put("b5",102);
//        t.countMap.put("c1",100);
//        t.countMap.put("c2",78);
//        t.countMap.put("c3",77);
//        t.countMap.put("c4",58);
//        t.countMap.put("c5",45);
//        t.countMap.put("a1",67);
//        t.countMap.put("a2",98);
//        t.countMap.put("a3",56);
//        t.countMap.put("a4",45);
//        t.countMap.put("a5",36);
        t.countMap.put("b1",3);
        t.countMap.put("b2",8);
        t.countMap.put("b3",20);
        t.countMap.put("b4",15);
        t.countMap.put("b5",60);
        t.countMap.put("b6",1);
        t.countMap.put("b7",32);
        bucket.add("u1");
        bucket.add("u2");
        System.out.println(t.classIntercept( 3));

    }
}
