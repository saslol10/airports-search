package org.lol.renue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Search {
    private final int numCol;
    private final HashMap<String, Integer> storage = new HashMap<>();


    public Search(int numCol) throws Exception {
        this.numCol = numCol;
        this.readFile();
    }

    public void readFile() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/airports.dat"))) {
            String line;
            int j = 0;
            while ((line = reader.readLine()) != null) {
                String[] arrRow = line.split(",");

                if (numCol > arrRow.length || numCol <= 0) {
                    System.out.println("Столбец вышел за пределы файла. Передел от 1 до " + arrRow.length);
                    System.exit(-1);
                }

                for (int i = 0; i < arrRow.length; i++) {
                    arrRow[i] = arrRow[i].replaceAll("\"([^\"]*)\"", "$1");
                    storage.put(arrRow[this.numCol - 1], j);
                }
                j++;
            }
        }
    }

    public TreeMap<Integer, String> searchByCol(String prefix) {
        TreeMap<Integer, String> resultOfSearch = new TreeMap<>(); //сортировка по умолчанию числа по возрастанию, буквы в алфавитном порядке
        prefix = prefix.toLowerCase();
        for (String key : this.storage.keySet()) {
            if (key.toLowerCase(Locale.ROOT).startsWith(prefix)) {
                resultOfSearch.put(this.storage.get(key), key);
            }
        }
        return resultOfSearch;
    }

    public Map<String, String> output(TreeMap<Integer, String> resultOfSearch) throws Exception {
        Map<String, String> result = new HashMap<>();
        for (Integer key : resultOfSearch.keySet()) {
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/airports.dat"))) {
                for (int i = 0; i < key; i++) {
                    reader.readLine();
                }
                result.put(resultOfSearch.get(key), reader.readLine());
            }
        }
        return result;
    }
}
