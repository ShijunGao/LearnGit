
public class Catalan {

    public static int answers = 0;
    public static final int n = 4;
    //请实现go函数
    public static void go(Deque from, Deque to, Stack s) {
        if (to.size() == 7){
            answers++;
            return;
        }

        if (!s.empty()){
            int val = s.pop();
            to.addLast(val);
            go(from, to, s);
            s.push(val);
            to.removeLast();
        }

        if (from.size() != 0 && s.size() <= n-1){
            int val = from.getFirst();
            from.removeFirst();
            s.push(val);
            go(from, to, s);
            from.addFirst(val);
            s.pop();
        }

    }

    public static void main(String[] args) {
        Deque from = new Deque();
        Deque to = new Deque();
        Stack s = new Stack();

        for(int i=1;i<=7;i++) {
            from.addLast(i);
        }

        go(from, to, s);

        System.out.println(answers);

    }

}