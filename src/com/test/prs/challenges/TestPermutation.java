package com.test.prs.challenges;

import java.util.Arrays;
import java.util.Collections;

public class TestPermutation {
    static void permute(String str,int l,int r){// repeated arrangements for AAC etc. O(n*n!)
        if(l==r)
            System.out.println(str);
        for(int i=l;i<=r;i++){
            str=swap(str,l,i);
            permute(str,l+1,r);
            str=swap(str,l,i);// backtrack
        }
    }

    static void permute1(char[] s){// distinct O(n2*n!)tc;O(1)
        int size = s.length;
        Arrays.sort(s);
        boolean isFinished=false;
        int x = 1;
        while(!isFinished){
            System.out.println(x++ +" "+String.valueOf(s).replaceAll("\\[|\\]|\\,|\"",""));
            int i=0;
            for(i=size-2;i>=0;--i){// rightmost char (s[i])smaller than its next char(s[i+1]);;'first char'
                if(s[i]<s[i+1])
                    break;
            }
            if(i==-1)
                isFinished=true;
            else{
                int ceilIndex = findCeilIndex(s,s[i],i+1,size-1);
                swap(s,i,ceilIndex);
               Arrays.sort(s,i+1,size);//sort from right of first O(n^2*n!)
//                reverseArray(s,i+1,size-1);// subarray is already in non-increasing order.. reverseing it is more efficient
            }
        }
    }

    private static void reverseArray(char[] s, int l, int h) {
        while(l<=h){
            swap(s,l++,h--);
        }
    }

    private static void swap(char[] charArr, int l, int i) {
        char temp = charArr[l];
        charArr[l]=charArr[i];
        charArr[i]=temp;
    }

    private static int findCeilIndex(char[] s, char c, int l, int h) {
        int ceilIndex = l;
        for(int i=l+1;i<=h;i++)
            if(s[i]>c&&s[i]<s[ceilIndex])// ceil of a char is smallest char greater than it
                ceilIndex=i;
        return ceilIndex;
    }

    private static String swap(String str, int l, int i) {
        char[] charArr = str.toCharArray();
        char temp = charArr[l];
        charArr[l]=charArr[i];
        charArr[i]=temp;
        return String.valueOf(charArr);
    }

    public static void main(String[] args) {
        String str = "abac";
//        TestPermutation.permute(str,0,str.length()-1);
//        TestPermutation.permute1(str.toCharArray());
        TestPermutation.permute3(str, 0, str.length());
    }


    public static void permute3(String str, int index, int n){
        if (index>=n){
            System.out.println(str);
        }
        for (int i = index; i < n; i++) {
            if(shouldSwap(str, index, i)){// only swap if values are different
                str = swap(str,index,i);
                permute3(str,index+1,n);
                str = swap(str,index,i);
            }
        }
    }

    private static boolean shouldSwap(final String str, final int start, final int curr) {
        for (int k = start; k < curr; k++) {
            if (str.charAt(k)==str.charAt(curr))
                return false;
        }
        return true;
    }

}
