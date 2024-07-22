import java.util.Scanner;

public class bubble {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

       
        int result = minSwapsToBeautifulArray(arr);
        System.out.println(result);

       
        scanner.close();
    }

    public static int minSwapsToBeautifulArray(int[] arr) {
        int n = arr.length;

        
        int swapsAscending = countSwaps(arr, "ascending");
        int swapsDescending = countSwaps(arr, "descending");

        
        return Math.min(swapsAscending, swapsDescending);
    }

    private static int countSwaps(int[] arr, String order) {
        int[][] elements = new int[arr.length][2];

       
        for (int i = 0; i < arr.length; i++) {
            elements[i][0] = arr[i];
            elements[i][1] = i;
        }

       
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (elements[j][0] > elements[j + 1][0]) {
                    int temp = elements[j][0];
                    elements[j][0] = elements[j + 1][0];
                    elements[j + 1][0] = temp;

                    temp = elements[j][1];
                    elements[j][1] = elements[j + 1][1];
                    elements[j + 1][1] = temp;
                }
            }
        }

      
        boolean[] visited = new boolean[arr.length];

        int swaps = 0;

        for (int i = 0; i < arr.length; i++) {
           
            if (visited[i] || elements[i][1] == i) {
                continue;
            }

            int cycleSize = 0;
            int j = i;

            while (!visited[j]) {
                visited[j] = true;
                j = elements[j][1];
                cycleSize++;
            }

           
            swaps += cycleSize - 1;
        }

        return swaps;
    }
}
