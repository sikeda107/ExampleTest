import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Helloクラスのインスタンス化
        Hello hello = new Hello();
        // 挨拶を格納するリストをインスタンス化
        List<String> greetings = new ArrayList<String>();
        //挨拶をリストへ格納する
        //※呼び出せないためprivateSayHello() は省略
        greetings.add(hello.abstractPublicSayHello("AbstractPublic"));
        greetings.add(hello.abstractProtectedSayHello("AbstractProtected"));
        greetings.add(hello.abstractSayHello("AbstractNone"));
        greetings.add(hello.publicSayHello("RealPublic"));
        greetings.add(hello.protectedSayHello("RealProtected"));
        greetings.add(hello.sayHello("RealNone"));
        // 挨拶をする(※forEachとメソッド参照を使う)
        greetings.forEach(System.out::println);
    }
}
