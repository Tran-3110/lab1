package main;

import java.util.Arrays;

public class MyArray {
	private int[] array;

	public MyArray(int[] array) {
		this.array = array;
	}

	public int[] mirror() {
		int[] result = new int[array.length * 2];
		int count = result.length - 1;
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i];
			result[count] = array[i];
			count--;
		}

		return result;
	}
	public int[] removeDuplicates() {
		int[] array2 = array.clone();
		int[] result = new int[array.length];
		int count = 0;
		for (int i = 1; i < array2.length; i++) {
			int[] arr = new int[i];
			System.arraycopy(array2, 0, arr, 0, i);
			if (haveDuplicate(arr, array2[i])) {
				array2[i] = 0;
			}
		}
		int index0 = index0(array);
		for (int i = 0; i < array2.length; i++) {
			if (array2[i] != 0 || i == index0) {
				result[count] = array2[i];
				count++;
			}
		}
		int[] resultFinal = new int[count];
		System.arraycopy(result, 0, resultFinal, 0, count);
		return resultFinal;
	}

	public boolean haveDuplicate(int[] arr, int value) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == value) {
				return true;
			}
		}
		return false;
	}

	public int index0(int[] arr) {
		int result = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				return i;
			}
		}
		return result;
	}
	public int[] getMissingValues() {
		int[] arr = array.clone();
		int[] result = new int[array.length];
		int countIndex =0;
		int count = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == count) {
				continue;
			}
			count++;
			if (count != arr[i]) {
				result[countIndex] = count;
				countIndex++;
				i--;
			}
		}
		int[] resultFinal = new int[countIndex++];
		for (int i = 0; i < resultFinal.length; i++) {
			resultFinal[i] = result[i];
		}
		return resultFinal;
	}

	public int[] fillMissingValues(int k) {
		if (k >= array.length || k <= 0)
			return array;
		int[] arr = array.clone();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == -1) {
				int aver1 = avergare(arr, i, k/2, k/2 + k%2, k);
				System.out.println(aver1);
				int aver2 = avergare(arr, i, k/2 + k%2, k/2, k);
				arr[i] = Math.min(aver1, aver2);
				System.out.println(aver2);
			}
		}
		return arr;
	}
	
	public int avergare(int[] arr, int i, int k1, int k2, int k) {
		if(k1>i) {
			k2 += k1-i;
			k1 = i;
		}
		if(k2> arr.length-1 - i) {
			k1 += k2-(arr.length-1 - i);
			k2 = arr.length-1 - i;
		}
		System.out.println(k1 +" "+k2);
		double aver = 0;
		while (k1 >0) {
			aver += arr[i-k1];
			k1--;
		}
		while (k2 >0) {
			aver += arr[i+k2];
			k2--;
		}
		if ((aver/k) % 1 >= 0.5) {
			aver = (aver/k)+1;
		} else {
			aver = aver/k;
		}
		arr[i] = (int) aver;
		return arr[i];
	}
	public static void main(String[] args) {
		MyArray myArr = new MyArray(new int[] {10, 11, 12, 13, 14, -1, 17, 19});
		//System.out.println(Arrays.toString(myArr.mirror()));
		//System.out.println(Arrays.toString(myArr.removeDuplicates()));
		//System.out.println(Arrays.toString(myArr.getMissingValues()));
		System.out.println(Arrays.toString(myArr.fillMissingValues(45)));
	}
}
