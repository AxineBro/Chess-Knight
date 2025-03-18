package basicFiles;

import Module.AxineQueue.SolutionChessKnightAxine;
import Module.JavaQueue.SolutionChessKnightJava;
import VC.*;
import Utils.*;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            if (args[0].toLowerCase().equals("input")) {
                if (args.length == 3) {
                    TypeImplementation type = null;
                    if(args[3].toLowerCase().equals("axine")){
                        type = TypeImplementation.AXINE;
                    } else if (args[3].toLowerCase().equals("java")) {
                        type = TypeImplementation.JAVA;
                    }
                    inputOneFile(args[2], type);
                } else if (args.length == 4) {
                    //inputTwoFiles(args[1], args[2]);
                }
            } else if (args[0].toLowerCase().equals("window")) {
                TypeImplementation type = null;
                if(args[1].toLowerCase().equals("axine")){
                    type = TypeImplementation.AXINE;
                } else if (args[1].toLowerCase().equals("java")) {
                    type = TypeImplementation.JAVA;
                }
                View view = new View();
                new Controller(view, type);
                view.setVisible(true);
            } else {
                showHelpMessage();
            }
        }catch (Exception e){
            showHelpMessage();
        }

    }

    private static void showHelpMessage(){
        System.out.print("Для работы с программой, пожалуйста, введите аргумент:\n " +
                "\thelp — выводит данное сообщение\n" +
                "\tinput file (Java/Axine)* — выводит в консоль путь коня от первой клетки ко второй\n" +
                "\tinput file1 file2 (Java/Axine)* — сохраняет в file2.txt путь коня из от первой клетки ко второй\n" +
                "\twindow (Java/Axine)* — открывает оконное приложение\n" +
                "\t\t(Java/Axine) — обозначает выбор, чем будет реализовываться очередь\n" +
                "\t\tJava — базовой библиотекой Java\n" +
                "\t\tAxine — очередь, разработанная AxineIT\n" +
                "Пример ввода: window axine");
    }

    private static void inputTwoFiles(String path1, String path2, TypeImplementation type){
        Solution solution = null;
        String[] input = String.valueOf(new Utils.Files().readFile(new File(path1))).split(" ");
        String knight = input[0];
        String target = input[1];

        switch (type){
            case JAVA:
                solution = new SolutionChessKnightJava(knight.charAt(0), Integer.parseInt(String.valueOf(knight.charAt(1))));
                break;
            case AXINE:
                solution = new SolutionChessKnightAxine(knight.charAt(0), Integer.parseInt(String.valueOf(knight.charAt(1))));
                break;
        }

        solution.solveParams(target.charAt(0), Integer.parseInt(String.valueOf(target.charAt(1))));

        List<?> answer = solution.getAnswer().getList();
        Object[] result = new Object[answer.size()];
        Object prefPosition = null;
        for (int i = 0; i < answer.size(); i++) {
            Object position = answer.get(i);
            if(prefPosition == null) {
                prefPosition = position;
                continue;
            }
            result[i] = (String.valueOf(prefPosition) + "-" + String.valueOf(position));
            prefPosition = position;
        }
        new Utils.Files().writeFile(new File(path2), result);
    }

    private static void inputOneFile(String path, TypeImplementation type){
        Solution solution = null;
        String[] input = String.valueOf(new Utils.Files().readFile(new File(path))).split(" ");
        String knight = input[0];
        String target = input[1];

        switch (type){
            case JAVA:
                solution = new SolutionChessKnightJava(knight.charAt(0), Integer.parseInt(String.valueOf(knight.charAt(1))));
                break;
            case AXINE:
                solution = new SolutionChessKnightAxine(knight.charAt(0), Integer.parseInt(String.valueOf(knight.charAt(1))));
                break;
        }

        solution.solveParams(target.charAt(0), Integer.parseInt(String.valueOf(target.charAt(1))));

        List<?> answer = solution.getAnswer().getList();
        Object prefPosition = null;
        for (Object position : answer) {
            if(prefPosition == null) {
                prefPosition = position;
                continue;
            }
            System.out.println(String.valueOf(prefPosition) + "-" +String.valueOf(position));
            prefPosition = position;
        }
    }
}