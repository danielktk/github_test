import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**s
 * Created by rudan on 05/01/2020.
 */
public class Main {
    static int[] dp;

    static int minimumCalculator(int number) {
        if (number == 1)
            return 0;test

        if (dp[number] > 0)
            return dp[number];

        int devide3 = Integer.MAX_VALUE;
        int devide2 = Integer.MAX_VALUE;
        int minus1 = Integer.MAX_VALUE;

        if (0 == number % 3) {
            devide3 = minimumCalculator(number / 3) + 1;
        } else if (0 == number % 2) {
            devide2 = minimumCalculator(number / 2) + 1;
        }
        minus1 = minimumCalculator(number - 1) + 1;

        return dp[number] = Math.min(devide3, Math.min(devide2, minus1));
    }

    public static void main(String[] str) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int goal = Integer.parseInt(br.readLine());

        boolean[] visit = new boolean[goal+1];

//		int[] dp = new int[goal+1];
//		dp[2]=1; //2일경우 1이됨
//		dp[3]=1; //3일경우 1이됨

        Queue<Integer> queue = new LinkedList<Integer>();

        queue.offer(goal);
        visit[goal]=true;

        int result = -1;
        while(!queue.isEmpty()) {
            result++;
            int queueSize = queue.size();
            for(int i=0;i<queueSize;i++) {

                int thisVal = queue.poll();
                int top = 0;
                int bottom = 0;
                if(thisVal==1) {
                    queue.clear();
                    break;
                }//if end

                top = thisVal/2;
                bottom = thisVal%2;
                if(bottom==0 && !visit[top]) {
                    queue.offer(thisVal/2);
                    visit[top]=true;
                }//if end

                top = thisVal/3;
                bottom = thisVal%3;
                if(bottom==0 && !visit[top]) {
                    queue.offer(thisVal/3);
                    visit[top]=true;
                }//if end

                int nextVal = thisVal-1;
                if(!visit[nextVal]) {
                    queue.offer(nextVal);
                    visit[nextVal]=true;
                }//if end
            }//for end
        }//while end


        System.out.println(result);

        br.close();
        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        dp = new int[X + 1];

        System.out.println(minimumCalculator(X));

        br.close();*/
        /* find smaller number
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer1 = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tokenizer1.nextToken());
        int x = Integer.parseInt(tokenizer1.nextToken());
        StringTokenizer tokenizer2 = new StringTokenizer(br.readLine());
        StringBuilder result = new StringBuilder();

        for (int i=0; i < n; i++) {
            int number = Integer.parseInt(tokenizer2.nextToken());
            if (number < x)
                result.append(number).append(" ");
        }

        System.out.println(result);

        br.close();*/

        /*
        DeliverySugar
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalWeight = Integer.parseInt(br.readLine());
        int result = totalWeight / 5;
        int remainsWeight = totalWeight % 5;

        if (totalWeight == 4 || totalWeight == 7) {
            result = -1;
        } else {
            if (remainsWeight == 1 || remainsWeight == 3) {
                result++;
            } else if (remainsWeight == 2 || remainsWeight == 4) {
                result += 2;
            }
        }
        System.out.println(result);
        br.close();*/



        /*int[] arr1 = {46, 33, 33 ,22, 31, 50};
        int[] arr2 = {27 ,56, 19, 14, 14, 10};
        test1(6,arr1, arr2);*/
        /*deleverySugar(10);
        deleverySugar(11);
        deleverySugar(12);
        deleverySugar(13);
        deleverySugar(14);
        deleverySugar(15);
        deleverySugar(16);
        deleverySugar(17);
        deleverySugar(18);
        deleverySugar(19);
        deleverySugar(20);*/


        //test2("1D2S#10S");

       /*System.out.println(test3(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
       System.out.println(test3(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
       System.out.println(test3(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
       System.out.println(test3(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
       System.out.println(test3(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}));
       System.out.println(test3(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));*/

    }

    public static void deleverySugar(int totalWeight) {
        int result = 0;
        int remainsWeight = 0;

        if (totalWeight > 5 && totalWeight % 5 != 0) {
            for (int i = totalWeight / 5; i > -1; i--) {
                remainsWeight = totalWeight - (i * 5);
                result = i;

                if (remainsWeight % 3 == 0) {
                    result += remainsWeight / 3;
                    break;
                }
            }
        } else if (totalWeight > 5 && totalWeight % 5 == 0) {
            result = totalWeight / 5;
        } else if (totalWeight > 3 && totalWeight % 3 == 0) {
            result = totalWeight / 3;
        }
        System.out.println(result == 0 ? -1 : result);
    }

    private static int test3(int cacheSize, String[] cities) {
        int result = 0;

        // Cache Size Zero is not save city name in cache
        if (cacheSize == 0) return cities.length * 5;

        // Simple LRU Map
        LinkedHashMap<String, Integer> lruMap = new LinkedHashMap<String, Integer>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return cacheSize > 0 ? size() > cacheSize : false;
            }
        };

        // Cache Check
        for (String city : cities) {
            if (lruMap.containsKey(city.toLowerCase())) {
                result += 1;
                lruMap.remove(city.toLowerCase());
            } else {
                result += 5;
            }
            lruMap.put(city.toLowerCase(), 1);
        }

        return result;
    }

    private static int test2(String dartResult) {
        int result = 0;
        int prePoint = 0;

        for (int i = 0; i < dartResult.length(); i++) {
            int point = Character.getNumericValue(dartResult.charAt(i));
            char bonus = i + 1 < dartResult.length() ? dartResult.charAt(i + 1) : ' ';
            char option = i + 2 < dartResult.length() ? dartResult.charAt(i + 2) : ' ';

            if (point == 1 && bonus == '0') {
                point = Integer.parseInt(Integer.toString(point) + bonus);
                i++;
                bonus = i + 1 < dartResult.length() ? dartResult.charAt(i + 1) : ' ';
                option = i + 2 < dartResult.length() ? dartResult.charAt(i + 2) : ' ';
            }

            if (bonus == 'D') {
                point = (int)Math.pow(point, 2);
            } else if (bonus == 'T') {
                point = (int)Math.pow(point, 3);
            }
            i++;

            if (option == '*') {
                point = point * 2;
                result += prePoint + point;
                i++;
            } else if (option == '#') {
                point = point * (-1);
                result += point;
                i++;
            } else {
                result += point;
            }

            prePoint = point;
        }

        System.out.println(result);
        return result;
    }

    private static String[] test1(int n, int[] arr1, int[] arr2) {

        String[] result = new String[n];

        for (int i = 0; i < n; i++) {
            int arr = arr1[i] | arr2[i];
            String binary = Integer.toBinaryString(arr);
            if (n > binary.length()) {
                for (int j = n - binary.length(); j > 0; j--) {
                    binary = "0" + binary;
                }
            }
            binary = binary.replaceAll("1", "#");
            binary = binary.replaceAll("0", " ");
            result[i] = binary;
            System.out.println(binary);
        }
        //["#####","# # #", "### #", "# ##", "#####"]
        return result;

    }
}
