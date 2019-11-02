import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HelloTest {

    @Test
    public void failTest(){
        fail("Not yet implemented!!");
    }

    @Test
    public void abstractPublicSayHelloTest() {
        // テストするクラスのインスタンス化
        Hello hello = new Hello();
        // 引数有りのテスト
        assertEquals("Hello,Tom!!", hello.abstractPublicSayHello("Tom"));
        //引数がnullのテスト
        assertEquals("Hello!!", hello.abstractPublicSayHello(null));
    }

    @Test
    public void abstractProtectedSayHelloTest() {
        // テストするクラスのインスタンス化
        Hello hello = new Hello();
        // 引数有りのテスト
        assertEquals("Hello,Bob!!", hello.abstractProtectedSayHello("Bob"));
        //引数がnullのテスト
        assertEquals("Hello!!", hello.abstractProtectedSayHello(null));
    }

    @Test
    public void abstractSayHelloTest() {
        // テストするクラスのインスタンス化
        Hello hello = new Hello();
        // 引数有りのテスト
        assertEquals("Hello,Marry!!", hello.abstractSayHello("Marry"));
        //引数がnullのテスト
        assertEquals("Hello!!", hello.abstractSayHello(null));
    }

    @Test
    public void privateSayHelloTest() throws Exception {
        // テストするクラスのインスタンス化
        Hello hello = new Hello();
        Method method = Hello.class.getDeclaredMethod("privateSayHello",String.class);
        method.setAccessible(true);

        // 引数有りのテスト
        assertEquals("Hello,Kevin!!", method.invoke(hello,"Kevin"));
        //引数がnullのテスト
        //nullが入ったObjectクラスの配列を定義
        assertEquals("Hello!!", method.invoke(hello, new Object[]{null}));
        // nullのObjectクラスを定義
        Object nullObject = null;
        assertEquals("Hello!!", method.invoke(hello, nullObject));
        // nullのStringクラスを定義
        String nullString = null;
        assertEquals("Hello!!", method.invoke(hello, nullString));
    }
    @Test
    public void privateSayHello2Test() throws Exception {
        // テストするクラスのインスタンス化
        Hello hello = new Hello();
        Method method = Hello.class.getDeclaredMethod("privateSayHello2");
        //メソッドへのアクセスを許可
        method.setAccessible(true);

        // invokeの第2引数は書かない
        assertEquals("Hello!!", method.invoke(hello));
    }
}