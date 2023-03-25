package org.lol.renue;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {

        if (args.length == 1) {

            int numCol = Integer.parseInt(args[0]);
            System.out.println("����� �� �������: " + numCol + ".");
            Search s = new Search(numCol);
            boolean flag = true;
            while (flag) {
                System.out.println("������� ������:");

                Scanner console = new Scanner(System.in);
                String line = console.nextLine();

                if (line.equals("!quit")) {
                    flag = false;
                } else {

                    long startTime = System.currentTimeMillis();
                    TreeMap<Integer, String> resultOfSearch = s.searchByCol(line); //�����
                    long endTime = System.currentTimeMillis();

                    Map<String, String> result = s.output(resultOfSearch); //����� �����
                    if (result.size() > 0) {
                        for (String key : result.keySet()) {
                            System.out.println("\"" + key + "\" [" + result.get(key) + "]");
                        }
                    }
                    System.out.println("���������� ��������� �����: " + result.size() + ", ����������� ����� �� �����: " + (endTime - startTime) + " ��");
                }
            }
        } else {
            System.out.println("������������ ���� ����������. ");
        }
        System.out.println("��������� ���������.");
    }
}