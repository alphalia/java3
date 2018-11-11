import java.util.Random;

/**
 * セルを表すクラス
 */
public class Cell {
  /** 次のセルの参照 */
  public Cell next;
  /** セルの内容 */
  public Object data;
  /** コンストラクタ */
  Cell(Object obj) {
    // 次のセルはない(このセルが末尾)
    next = null;
    // このセルにデータを代入
    data = obj;
  }
  /**
   * セルの文字列表現を返す(デバッグ専用)
   * @return セルの文字列表現
   */
  @Override
  public String toString() {
    if (next != null) {
      return "[next: Cell(" + next.data.toString() + ")" + ", data: " + data.toString() + "]";
    } else {
      return "[next: null, data: " + data.toString() + "]";
    }
  }
  /**
   * セルの同値判定を行う
   * @param obj 比較対象オブジェクト
   * @return 同値かどうか
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Cell) {
      Cell c = (Cell)obj;
      if ((c.next == next) && (c.data == data)) {
        return true;
      } else {
        return false;
      } 
    } else {
      return false;
    }
  }
}