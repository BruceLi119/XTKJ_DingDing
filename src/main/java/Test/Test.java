package Test;

public class Test {
    public static void main(String[] args) {
      /*  int i=1,j=10;
        do{
            if(i++ > --j) continue;
        }while(i<5);
        System.out.println(i);
        System.out.println(j);*/

  /*    String a = "ABCD";
      String b = a.toLowerCase();
      b.replace('a','d');
      b.replace('d','c');
        System.out.println(b);*/

      /*  boolean flag = false;
        int i = 0;
        do {
            System.out.print(i++);
            flag = i < 10;
            continue;
        } while (flag);*/

        //3. 分割字符串
        String[] arr = new String("219,23,9,10,32").split(",");
        for (int i=0; i<arr.length;i++){
            System.out.println(arr[i]);
        }
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int a = Integer.parseInt(arr[i]);
            newArr[i] = a;
            System.out.println(newArr[i]);
        }

//        System.out.println();

    }
}
