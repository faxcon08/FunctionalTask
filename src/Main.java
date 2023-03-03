import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    private static void printStatistic(String inputString) {
        Map<String, Integer> map;
        map=Arrays.stream(inputString.split("\\s")).collect(HashMap::new,(m,c)->{
            if(m.containsKey(c))
                m.put(c,m.get(c)+1);
            else
                m.put(c, 1);
        },HashMap::putAll);
        System.out.println("Output:\nВ тексте "+map.entrySet().stream().count()+" слов\nTOP10:");
        //System.out.println(map);
        Map<Integer,List<Map.Entry<String,Integer>>> map2;
        map2=map.entrySet().stream().collect(Collectors.groupingBy(m -> m.getValue()));
        //System.out.println(map2);
        //System.out.println("------------------------\n");
        List <Map.Entry<String,Integer>> list;
        list=map2.entrySet().stream().map(x->x.getValue()).sorted((x,y)->{
            if(x.get(0).getValue()<y.get(0).getValue())
                return 1;
            if(x.get(0).getValue()>y.get(0).getValue())
                return -1;
            return 0;
        }).map(x->x.stream().sorted((z,k)->{
            return z.getKey().compareTo(k.getKey());
        }).collect(Collectors.toList())).flatMap(x->x.stream()).collect(Collectors.toList());
        list.stream().limit(10).forEach(x->System.out.println(x.getValue()+" - "+x.getKey()));
    }

    private static void runInput() {
        String inputString;
        try (Scanner scanner = new Scanner(System.in)) {
            boolean flag = true;
            //System.out.println("Введите строку для подсчета совпадений: (для выхода введите exit)");
            do {
                System.out.println("Введите строку для подсчета совпадений: (для выхода введите exit)");
                inputString=scanner.nextLine();
                if (inputString.isBlank()) {
                    System.out.println("Error: введена пустая строка. Попробуйте еще или введите exit");
                    continue;
                }
                if (inputString.trim().toLowerCase().compareTo("exit")==0){
                    System.out.println("EXIT confirm");
                    flag=false;
                    break;
                }else {
                    printStatistic(inputString);
                    System.out.println("----------------------------------------------");
                }
            }while (true);
        }
    }
    public static void main(String[] args) {
        runInput();
    }// main

}