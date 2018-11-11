import java.util.*;
/**
 * 線形リストを扱うクラス
 */
public class List {
  /** ヘッダ */
  public Cell header = new Cell("HEADER");
  /** 番兵 */
  public Cell sentinel = new Cell("");
  /** リストのサイズ */
  private int size = 0, numData = 0;
  public List(int n) {
    // セルの個数を設定
    numData = n;
    // ヘッダの次セルは，番兵を参照
    header.next = sentinel;
  }
  /**
   * リストの中身の作成
   */
  public void makeList() {
    add(new Cell("aaa"));
    add(new Cell("bbb"));
    add(new Cell("ccc"));
  }
  /**
   * リストの中身の作成2
   */
  public void makeList2() {
    Random rnd = new Random();
    for (int i = 0; i < numData; i++) {
      add(new Cell(Integer.valueOf(rnd.nextInt(100))));
    }
  }
  /**
   * 先頭にセルを加える
   * @param c セル
   */
  public void insertTop(Cell c) {
    // 挿入するセルの次セルは，今の先頭セルを参照
    c.next = header.next;
    // ヘッダの次セルは，新しく挿入するセルを参照
    header.next = c;
    size++;
  }
  /** 
   * 末尾にセルを加える
   * @param c セル
   */
  public void add(Cell c) {
    // 末尾を再帰的に探索し，その後ろに新たにセルを挿入
    Cell curr = header;
    while (curr.next != sentinel) {
      curr = curr.next;
    }
    c.next = curr.next;
    curr.next = c;
    size++;
  }
  /** 
   * add()の本体 [再帰]
   * @param c セル
   * @param curr 作業セル
   */
  private void addRec(Cell c, Cell curr) {
    // 次セルが番兵であるセル(末尾セル)を探索
    if (curr.next != sentinel) {
      addRec(c, curr.next);
    } else {
      // 末尾セルは新たに，挿入するセルを参照．
      c.next = curr.next;
      curr.next = c;
    }
  }
  /**
   * オブジェクトobjをデータとして持つ最初のセルの前にセルcを挿入
   * @param obj 探索する対象
   * @param c 挿入するセル
   */
  public void insertBefore(Object obj, Cell c) {
    // 番兵の持つデータを探索対象と同じにする
    sentinel.data = obj;
    // 先頭セルから再帰的に探索し，見つかれば新たに対象セルの前に挿入
    insertBeforeRec(obj, c, header, header.next);
    size++;
  }
  /**
   * insertBefore()の本体 [再帰]
   * @param obj 探索する対象
   * @param c 挿入セル
   * @param prev 作業セルの前セル
   * @param curr 作業セル
   */
  private void insertBeforeRec(Object obj, Cell c, Cell prev, Cell curr) {
    // 作業セルのデータが探索対象と異なれば，後ろを再帰的に探索
    if (!curr.data.equals(obj)) {
      insertBeforeRec(obj, c, curr, curr.next);
    } else {
      // 番兵でなければ対象セルが存在し，その前に新たにセルを挿入(prev->c->curr)．
      if (curr != sentinel) {
        c.next = curr;
        prev.next = c;
      } else {
        System.out.println(obj + ": NO SUCH CELL!!");
      }
    }
  }
  /**
   * オブジェクトobjをデータとして持つ最初のセルの後ろにセルcを挿入
   * @param obj 探索する対象
   * @param c 挿入するセル
   */
  public void insertAfter(Object obj, Cell c) {
    // 番兵の持つデータを探索対象と同じにする
    sentinel.data = obj;
    // 先頭セルから再帰的に探索し，見つかれば新たに対象セルの後ろに挿入
    insertAfterRec(obj, c, header.next);
    size++;
  }
  /**
   * insertAfter()の本体 [再帰]
   * @param obj 探索する対象
   * @param c 挿入セル
   * @param curr 作業セル
   */
  private void insertAfterRec(Object obj, Cell c, Cell curr) {
    // 作業セルのデータが探索文字列と異なれば，後ろを再帰的に探索
    if (!curr.data.equals(obj)) {
      insertAfterRec(obj, c, curr.next);
    } else {
      // 番兵でなければ対象セルが存在し，その後ろに新たにセルを挿入(curr->c->curr.next)．
      if (curr != sentinel) {
        c.next = curr.next;
        curr.next = c;
      } else {
        System.out.println(obj + ": NO SUCH CELL!!");
      }
    }
  }
  /**
   * オブジェクトobjをデータとして持つ最初のセルをリストから削除
   * @param obj 探索する対象
   */
  public void remove(Object obj) {
    // 番兵の持つデータを探索対象と同じにする
    sentinel.data = obj;
    // 先頭セルから再帰的に探索し，見つかればそのセルの参照を切る
    removeRec(obj, header, header.next);
    size--;
  }
  /**
   * remove()の本体 [再帰]
   * @param obj 探索する対象
   * @param prev 作業セルの前セル
   * @param curr 作業セル
   */
  private void removeRec(Object obj, Cell prev, Cell curr) {
    // 作業セルのデータが探索文字列と異なれば，後ろを再帰的に探索
    if (!curr.data.equals(obj)) {
      removeRec(obj, curr, curr.next);
    } else {
      // 番兵でなければ対象セルが存在し，そのセルの参照を切る(prev->curr.next)．
      if (curr != sentinel) {
        prev.next = curr.next;
      } else {
        System.out.println(obj + ": NO SUCH CELL!!");
      }
    }
  }
  /**
   * オブジェクトobjをデータとしてもつ最初のセルを見つけて返す
   * @param obj 探索する対象
   * @return 対象セル
   */
  public Cell findCell(Object obj) {
    // 番兵の持つデータを探索対象と同じにする
    sentinel.data = obj;
    // 先頭セルから再帰的に探索し，見つかればその対象セルの参照を返す
    return findCellRec(obj, header.next);
  }
  /**
   * findCell()の本体 [再帰]
   * @param obj 探索する対象
   * @param curr 作業セル
   * @return 対象セル
   */
  private Cell findCellRec(Object obj, Cell curr) {
    // 作業セルのデータが探索文字列と異なれば，後ろを再帰的に探索
    if (!curr.data.equals(obj)) {
      return findCellRec(obj, curr.next);
    } else {
      // 番兵でなければ対象セルが存在し，そのセルの参照を返す．
      if (curr != sentinel) {
        return curr;
      } else {
        return null;
      }
    }
  }
  /**
   * セルcの直前のセルを返す
   * @param c 作業セル
   * @return 対象セル
   */
  public Cell prev(Cell c) {
    return prevRec(c, header, header.next);
  }
  /**
   * prev()の本体 [再帰]
   * @param c 探索するセル
   * @param prev 作業セルの前セル
   * @param curr 作業セル
   * @return 対象セル
   */
  private Cell prevRec(Cell c, Cell prev, Cell curr) {
    if (curr != c) {
      return prevRec(c, curr, curr.next);
    } else {
      if (curr != sentinel) {
        return prev;
      } else {
        return null;
      }
    }
  }
  /**
   * オブジェクトobj1をデータとして持つ最初のセルと，オブジェクトobj2をデータとして持つ最初のセルを入れ替える
   * @param obj1 探索する対象1
   * @param obj2 探索する対象2
   */
  public void swap(Object obj1, Object obj2) {
    // obj1とobj2が異なる場合のみ処理(swap)を行う
    if (!obj1.equals(obj2)) {
      // swap()するための準備
      Cell obj1Cell, prevObj1Cell, obj2Cell, prevObj2Cell;

      obj1Cell = findCell(obj1);
      if (obj1Cell != null) {
        prevObj1Cell = prev(obj1Cell);
      } else {
        System.out.println(obj1 + ": NO SUCH CELL!!");
        return;
      }
      obj2Cell = findCell(obj2);
      if (obj2Cell != null) {
        prevObj2Cell = prev(obj2Cell);
      } else {
        System.out.println(obj2 + ": NO SUCH CELL!!");
        return;
      }
      // s1とs2が隣接してる場合(1)
      if (obj1Cell.next == obj2Cell) {
        obj1Cell.next = obj2Cell.next;
        obj2Cell.next = obj1Cell;
        prevObj1Cell.next = obj2Cell;
      // s1とs2が隣接してる場合(2)
      } else if (obj2Cell.next == obj1Cell) {
        obj2Cell.next = obj1Cell.next;
        obj1Cell.next = obj2Cell;
        prevObj2Cell.next = obj1Cell;
      // s1とs2が隣接してない場合
      } else {
        Cell nextObj1Cell = obj1Cell.next;
        obj1Cell.next = obj2Cell.next;
        obj2Cell.next = nextObj1Cell;
        prevObj1Cell.next = obj2Cell;
        prevObj2Cell.next = obj1Cell;
      }
    }
  }
  /**
   * セルc1とセルc2の中身を入れ替える
   * @param c1 セル1
   * @param c2 セル2
   */
  public void swapContents(Cell c1, Cell c2) {
    // c1とc2が異なる場合のみ処理(swap)を行う
    if (!c1.data.equals(c2.data)) {
      if (c1 == sentinel) {
        System.out.println(c1 + ": NO SUCH CELL!!");
      } else if (c2 == sentinel) {
        System.out.println(c2 + ": NO SUCH CELL!!");
      } else {
        Object tmp = c1.data;
        c1.data = c2.data;
        c2.data = tmp;
      }
    }
  }
  /** 
   * オブジェクトobjをデータとして持つセルがあるかどうか
   * @param obj 探索する対象
   * @return 対象セルがあるかどうか
   */
  public boolean isInList(Object obj) {
    // 番兵の持つデータを探索対象と同じにする
    sentinel.data = obj;
    // 先頭セルから再帰的に探索
    return isInListRec(obj, header.next);
  }
  /**
   * isInList()の本体 [再帰]
   * @param obj 探索する対象
   * @param curr 作業セル
   * @return 対象セルがあるかどうか
   */
  private boolean isInListRec(Object obj, Cell curr) {
    // 作業セルのデータが探索対象と異なれば，後ろを再帰的に探索
    if (!curr.data.equals(obj)) {
      return isInListRec(obj, curr.next);
    } else {
      // 番兵でなければ探索対象が存在，そうでなければ存在しない．
      if (curr != sentinel) {
        return true;
      } else {
        return false;
      }
    } 
  }
  /**
   * リストの大きさを取得
   * @return リストの大きさ
   */
  public int size() {
    return size;
  }
  /**
   * リストを表示
   * @param order 正順か逆順か
   */
  public void printList(boolean order) {
    // リストの始まりを印字
    System.out.print("[");
    // 本体の呼び出し
    if (order) {
      // 正順に再帰的に表示
      printListRec(header.next, order);
    } else {
      // 逆順に再帰的に表示
      printListRec(header.next, order);
    }
    // リストの終わりを印字
    System.out.println("]");
  }
  /**
   * printList()の本体 [再帰]
   * @param curr 作業セル
   * @param order 正順か逆順か
   */
  private void printListRec(Cell curr, boolean order) {
    if (order) {
      // リストを正順に表示
      if (curr != sentinel) {
        // 現在セルを表示
       if (curr.next != sentinel) {
         System.out.print(curr.data + "; ");
       } else {
         System.out.print(curr.data);
       }
        // 次セルに移動し，再帰
        printListRec(curr.next, order);
      }
    } else {
      // リストを逆順に表示
      if (curr != sentinel) {
        // 次セルに移動し，再帰
        printListRec(curr.next, order);
        // 現在セルを表示
        if (curr.next != sentinel) {
          System.out.print(curr.data + "; ");
        } else {
          System.out.print(curr.data);
        }
      }
    }
  }
  /**
   * バブルソートを行う
   */
  public void bubbleSort() {
    Cell prev = header.next, curr = header.next.next;
    // スワップされたかどうか
    boolean swap = false;
    // カウンタ
    int j = size;
    // 
    while (!swap) {
      prev = header.next; curr = header.next.next;
      for (int i = 0; i < j - 1; i++) {
        if (((Integer)prev.data).compareTo((Integer)curr.data) > 0) {
          swapContents(prev, curr);
          swap = true;
        }
        prev = prev.next;
        curr = curr.next;
      }
      if (!swap) break;
      swap = false; j--;
    }
  }
  /**
   * マージソートを行う
   */
  public void mergeSort() {
    // マージソートされたリストを取得する
    List list = mergeSortRec(header.next, sentinel, size);
    // このリストを参照
    header = list.header;
    sentinel = list.sentinel;
  }
  /**
   * mergeSort()の本体 [再帰]
   * セルstartからセルendの一つ手前までのn個のデータをマージソートする
   */
  private List mergeSortRec(Cell start, Cell end, int n) {
    // リストの中央位置
    int center = n / 2;
    // 作業セル
    Cell curr = start;
    // 作業セルを中央位置まで移動
    for (int i = 0; i < center; i++) curr = curr.next;
    // セルが1個なら，そのセルを返す．そうでないなら，二分割して再帰&マージ．
    if (n == 1) {
      List l = new List(0);
      l.add(new Cell(start.data));
      return l;
    } else {
      List list1 = mergeSortRec(start, curr, center), list2 = mergeSortRec(curr, end, n - center);
      return merge(list1, list2);
    }
  }
  /**
   * リストlist1とリストlist2をマージして一つのリストにして返す
   * (先頭から小さい方を順に選ぶ)
   */
  private List merge(List list1, List list2) {
    List list = new List(0);
    Cell curr1 = list1.header.next, curr2 = list2.header.next,
      sentinel1 = list1.sentinel, sentinel2 = list2.sentinel;
    // 先頭を比較して小さい方のデータを新しいリストの先頭に追加
    while (curr1 != sentinel1 || curr2 != sentinel2) {
      if (curr1 == sentinel1) {
        list.add(new Cell(curr2.data));
        curr2 = curr2.next;
      } else if (curr2 == sentinel2) {
        list.add(new Cell(curr1.data));
        curr1 = curr1.next;
      } else {
        if (((Integer)curr1.data).compareTo((Integer)curr2.data) > 0) {
          list.add(new Cell(curr2.data));
          curr2 = curr2.next;
        } else {
          list.add(new Cell(curr1.data));
          curr1 = curr1.next;
        }
      }
    }
    return list;
  }
}