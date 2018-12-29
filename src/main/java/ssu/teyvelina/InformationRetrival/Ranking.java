package ssu.teyvelina.InformationRetrival;

import java.util.*;

public class Ranking {
    private static List<Double> DCGList;

    private static List<Double> IDCGResList;

    private static double IDCG(Map<Integer, Integer> respMap) {
        IDCGResList = new ArrayList<>();
        List<Integer> IDCGList = new ArrayList<>();

        for (Map.Entry<Integer, Integer> map : respMap.entrySet()) {
            IDCGList.add(map.getValue());
        }

        Collections.sort(IDCGList, (o1, o2) -> {
            if (o1 > o2) {
                return -1;
            } else if (o1 < o2) {
                return 1;
            }
            return 0;
        });

        int j = 1;
        for (int i = 0; i < IDCGList.size(); i++) {
            IDCGResList.add(fun(j, IDCGList.get(i)));
            j++;
        }

        double sum = 0;
        for (int i = 0; i < IDCGResList.size(); i++) {
            sum += IDCGResList.get(i);
        }

        return sum;
    }

    private static double DCG(Map<Integer, Integer> respMap) {
        DCGList = new ArrayList<>();
        int k = 1;
        for (Map.Entry<Integer, Integer> map : respMap.entrySet()) {
            DCGList.add(fun(k, map.getValue()));
            k++;
        }

        double sum = 0;
        for (int i = 0; i < DCGList.size(); i++) {
            sum += DCGList.get(i);
        }

        return sum;
    }

    public static double getNDCG(Map<Integer, Integer> respMap) {
        double AVG = DCG(respMap) / IDCG(respMap);


        return AVG;
    }



    private static double fun(int k, double rel) {
        return rel / (Math.log(k + 1) / Math.log(2));
    }
}