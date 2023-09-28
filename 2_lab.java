import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        //1
        String a = problem1("abcdaaaabcde");
        System.out.println(a);
        //2
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        int[] mergedArray = problem2(arr1, arr2);
        System.out.println(Arrays.toString(mergedArray));
        //3
        int[] arr3 = {2, -5, 4, 6, -3, 7, -2, -3};
        int maxSum = problem3(arr3);
        System.out.println(maxSum);
        //4
        int[][] array4 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] rotatedArr = problem4(array4);
        for (int[] row : rotatedArr) {
            System.out.println(Arrays.toString(row));
        }
        //5
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = problem5(nums, target);
        if (result != null) {
            System.out.println(result[0] + ", " + result[1]);
        } else {
            System.out.println("Пара чисел не найдена.");
        }
        //6
        int sum = problem6(array4);
        System.out.println(sum);
        //7
        int[] maxValues = problem7(array4);
        System.out.println(Arrays.toString(maxValues));
        //8
        int[][] rotatedArr2 = problem8(array4);
        for (int i = 0; i < rotatedArr2.length; i++) {
            System.out.println(Arrays.toString(rotatedArr2[i]));
        }

    }

    public static String problem1(String str) {
        String maxSubstring = "";
        String currentSubstring = "";

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (!currentSubstring.contains(String.valueOf(c))) {
                currentSubstring += c;
            } else {
                if (currentSubstring.length() > maxSubstring.length()) {
                    maxSubstring = currentSubstring;
                }
                int index = currentSubstring.indexOf(c);
                currentSubstring = currentSubstring.substring(index + 1) + c;
            }
        }

        if (currentSubstring.length() > maxSubstring.length()) {
            maxSubstring = currentSubstring;
        }

        return maxSubstring;
    }

    public static int[] problem2(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                result[k] = arr1[i];
                i++;
            } else {
                result[k] = arr2[j];
                j++;
            }
            k++;
        }

        while (i < arr1.length) {
            result[k] = arr1[i];
            i++;
            k++;
        }

        while (j < arr2.length) {
            result[k] = arr2[j];
            j++;
            k++;
        }

        return result;
    }

    public static int problem3(int[] arr) {
        int maxSum = arr[0];
        int currentSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static int[][] problem4(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n - 1 - i] = matrix[i][j];
            }
        }
        return result;
    }

    public static int[] problem5(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{nums[i], nums[j]};
                }
            }
        }
        return null;
    }


    public static int problem6(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }

    public static int[] problem7(int[][] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int max = arr[i][0];
            for (int j = 1; j < arr[i].length; j++) {
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
            }
            result[i] = max;
        }
        return result;
    }

    public static int[][] problem8(int[][] arr) {
        int n = arr.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[n - j - 1][i] = arr[i][j];
            }
        }
        return result;
    }
}