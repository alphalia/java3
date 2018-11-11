public class SampleSort {
  public static void main(String[] args) {
    try {
      List l = new List(Integer.parseInt(args[1])); // 引数はデータの個数
      l.makeList2();
      l.printList(true);

      long t1 = System.currentTimeMillis(); // ソート前の時刻の保持(ms)
      if (args[0].equals("bubble")) {
        l.bubbleSort();
      } else if (args[0].equals("merge")) {
        l.mergeSort();
      } else {
        System.err.println("Invalid argument: " + args[0]);
      }
      long t2 = System.currentTimeMillis(); // ソート後の時刻の保持(ms)
      System.out.println((t2 - t1) + "ms");
      l.printList(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}