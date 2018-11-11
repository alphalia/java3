import java.util.*;
/**
 * ソートを行うクラス
 */
public class Sort extends List {
  public Sort(int n) {
    super(n);
  }
  /**
   * リストの中身の作成
   */
  @Override
  public void makeList() {
    for (int i = 0; i < size; i++) {
      add(new Cell(Integer.valueOf(new Random().nextInt(100))));
    }
  }
  /**
   * バブルソートを行う
   */
  public void bubbleSort() {
    
  }
  /**
   * マージソートを行う
   */
  public void mergeSort() {
    int center = size / 2;
    Cell curr = header.next;
    
    for (int i = 0; i < center; i++) curr = curr.next;
    
    List list1 = mergeSortRec(header.next, curr, center);
    List list2 = mergeSortRec(curr, sentinel, n - center);

    List list3 = merge(list1, list2);

    header = list3.header;
    sentinel = list3.sentinel;
  }
  /**
   * mergeSort()の本体 [再帰]
   * セルstartからセルendの一つ手前までのn個のデータをマージソートする
   */
  private List mergeSortRec(Cell start, Cell end, int n) {

  }
  /**
   * リストlist1とリストlist2をマージして一つのリストにして返す
   */
  private List merge(List list1, List list2) {

  }
}