import java.util.*;
/**
 * サンプルリスト1 [add()の振る舞い確認]
 */
public class SampleList {
  public static void main(String[] args) {
    List l = new List(0);

    HashMap<String, Integer> method = new HashMap<String, Integer>();
    method.put("add", 1);
    method.put("insertTop", 2);
    method.put("isInList", 3);
    method.put("insertAfter", 4);
    method.put("insertBefore", 5);
    method.put("remove", 6);
    method.put("swap", 7);
    method.put("swapContents", 8);
    method.put("findCell", 9);
    
    try {
      switch (method.get(args[0])) {
        // add
        case 1:
          l.makeList();
          l.printList(true);
          l.add(new Cell("ddd"));
          l.printList(true);
          break;
        // insertTop
        case 2:
          l.makeList();
          l.printList(true);
          l.insertTop(new Cell("ddd"));
          l.printList(true);
          break;
        // isInList
        case 3:
          l.makeList();
          l.printList(true);
          System.out.println(l.isInList("bbb"));
          break;
        // insertAfter
        case 4:
          l.makeList();
          l.printList(true);
          l.insertAfter("aaa", new Cell("ddd"));
          l.printList(true);
          l.insertAfter("aaa", new Cell("eee"));
          l.printList(true);
          l.insertAfter("eee", new Cell("fff"));
          l.printList(true);
          break;
        // insertBefore
        case 5:
          l.makeList();
          l.printList(true);
          l.insertBefore("aaa", new Cell("ddd"));
          l.printList(true);
          l.insertBefore("aaa", new Cell("eee"));
          l.printList(true);
          l.insertBefore("eee", new Cell("fff"));
          l.printList(true);
          break;
        // remove
        case 6:
          l.makeList();
          l.printList(true);
          l.remove("ccc");
          l.printList(true);
          l.remove("aaa");
          l.printList(true);
          l.remove("bbb");
          l.printList(true);
          l.remove("aaa");
          break;
        // swap
        case 7:
          l.makeList();
          l.printList(true);
          l.swap("bbb", "ccc");
          l.printList(true);
          l.swap("aaa", "bbb");
          l.printList(true);
          l.swap("ccc", "ccc");
          l.printList(true);
          l.swap("ddd", "aaa");
          l.printList(true);
          break;
        // swapContents
        case 8:
          l.makeList();
          l.printList(true);
          l.swapContents("bbb", "ccc");
          l.printList(true);
          l.swapContents("aaa", "bbb");
          l.printList(true);
          l.swapContents("ccc", "ccc");
          l.printList(true);
          l.swapContents("ddd", "aaa");
          l.printList(true);
          break;
        case 9:
          l.makeList();
          l.printList(true);
          System.out.println(l.findCell("aaa").toString());
          break;
        // otherwise
        default:
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}