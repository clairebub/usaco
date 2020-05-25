import java.io.*;
import java.util.*;

//
// In Computer Science, we operates on data, besides individual data such as number and string, we operate
// on a type of structured data we call them 'data structures'. Two most commonly used data structures are
// List and Dictionary. Python's implementation of them are called 'list' and 'dict'. Java's implementation
// are ArrayList and HashMap. This tutorial shows you how to use them in Java.
//
public class ListAndMap {
    public static void main(String[] args) {
        useArray();
        useMap();
    }

    public static void useArray() {
        // 1. First you need to create an ArrayList
        ArrayList<Integer> aList = new ArrayList<Integer>();
        // 2. add an element to the array list
        aList.add(109940);
        aList.add(200);
        aList.add(300);
        aList.add(4242);
        System.out.println("after adding elements to the list");
        for(int x : aList) {
            System.out.println(x);
        }
        // 3. get the element at the specific index
        int v0 = aList.get(0);
        int v3 = aList.get(3);
        System.out.println("x=" + v0 + ", y=" + v3);
        // 4. how many elements are in the list?
        int s = aList.size();
        System.out.println("array list's size is " + s);
        // 5. updating the element value at the specific index
        aList.set(1, 999);
        System.out.println("after updating element value");
        for(int x : aList) {
            System.out.println(x);
        }
        // 6. remove the element at a given index
        // question: if you remove element at index 1, what happens to element at index 2, 3 and so on?
        aList.remove(1);
        System.out.println("after removing element at index 1");
        for(int x : aList) {
            System.out.println(x);
        }
        // 7. combining two arrays, adding a 2nd array into the 1st array
        ArrayList<Integer> anotherList = new ArrayList();
        anotherList.add(301);
        anotherList.add(302);
        aList.addAll(anotherList);
        System.out.println("after adding another list");
        for(int x : aList) {
            System.out.println(x);
        }
    }

    public static void useMap() {
        // 1. First you need to create a map
        HashMap<String, Integer> aMap = new HashMap();
        // 2. add an element to the array list
        aMap.put("width", 100);
        aMap.put("height", 200);
        System.out.println("after adding elements to the map");
        for(String k: aMap.keySet()) {
            System.out.println(k + ", " + aMap.get(k));
        }
        // 3. get the element at the specific key
        int w = aMap.get("width");
        int h = aMap.get("height");
        System.out.println("width=" + w + ", height=" + h);
        // 4. how many elements are in the list?
        int s = aMap.size();
        System.out.println("size of the map is " + s);
        // 5. updating the element value for the given key
        aMap.put("width", 999);
        System.out.println("after updating element value");
        for(String k : aMap.keySet()) {
            System.out.println(k + ", " + aMap.get(k));
        }
        // 6. remove the element for a given key
        aMap.remove("width");
        System.out.println("after removing key of 'width'");
        for(String k : aMap.keySet()) {
            System.out.println(k + ", " + aMap.get(k));
        }
        // 7. combining two maps, adding a 2nd map to the 1st map
        // question: what happened to the value of key "width" in the first map?
        HashMap<String, Integer> anotherMap = new HashMap();
        anotherMap.put("width", 1001);
        anotherMap.put("area", 303);
        aMap.putAll(anotherMap);
        System.out.println("after combining two maps");
        for(String k : aMap.keySet()) {
            System.out.println(k + ", " + aMap.get(k));
        }
    }
}

