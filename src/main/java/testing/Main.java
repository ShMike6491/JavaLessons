package testing;

import java.util.ArrayList;

public class Main {

    public Integer[] arrayMaker(int[] arr) throws RuntimeException {
        if(arr.length <= 0) throw new RuntimeException
                ("Array must have at least one element in it");

        ArrayList<Integer> newArr = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 4) newArr.clear();
            else newArr.add(arr[i]);
        }

        if(newArr.size() == arr.length) throw new RuntimeException("At least one number 4 must be included inside array");

        return newArr.toArray(new Integer[0]);
    }

    public boolean arrayContains(int[] arr) {
        boolean checkOnes = false;
        boolean checkFours = false;

        for (int num:arr) {
            if(num != 1 && num != 4) return false;

            if(num == 1) checkOnes = true;
            if(num == 4) checkFours = true;
        }

        return (checkOnes && checkFours);
    }
}
