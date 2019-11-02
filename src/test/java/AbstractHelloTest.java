import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AbstractHelloTest {

    @Test
    public void publicSayHelloTest() throws Exception {
        //抽象クラスのモック化
        AbstractHello abstractHelloMock = Mockito.mock(AbstractHello.class, Mockito.CALLS_REAL_METHODS);
        // 引数有りのテスト
        assertEquals("Hello,Tom!!", abstractHelloMock.publicSayHello("Tom"));
        //引数がnullのテスト
        assertEquals("Hello!!", abstractHelloMock.publicSayHello(null));
    }

    @Test
    public void publicSayHelloTest2() throws Exception {
        //抽象クラスのモック化
        AbstractHello abstractHelloMock = Mockito.mock(AbstractHello.class);
        //メソッドの部分モック化
        Mockito.doCallRealMethod().when(abstractHelloMock).publicSayHello("Tom");

        // 引数が「Tom」のテスト
        assertEquals("Hello,Tom!!", abstractHelloMock.publicSayHello("Tom"));

        // 引数が「Sara」のテスト
        assertNull(abstractHelloMock.publicSayHello("Sara"));
    }

    @Test
    public void publicSayHelloVoidTest() throws Exception {
        //抽象クラスのモック化
        AbstractHello abstractHelloMock = Mockito.mock(AbstractHello.class);
        //メソッドの部分モック化
        Mockito.doCallRealMethod().when(abstractHelloMock).publicSayHelloVoid();

        // Voidメソッドのテスト
        assertEquals("Hello!!", abstractHelloMock.publicSayHelloVoid());

        // 別のメソッドpublicSayHelloの引数を「Tom」としてテスト
        // assertEquals("Hello,Tom!!", abstractHelloMock.publicSayHello("Tom"));
        assertNull(abstractHelloMock.publicSayHello("Tom"));
    }

    @Test
    public void protectedSayHelloTest() throws Exception {
        //抽象クラスのモック化
        AbstractHello abstractHelloMock = Mockito.mock(AbstractHello.class, Mockito.CALLS_REAL_METHODS);
        // 引数有りのテスト
        assertEquals("Hello,Bob!!", abstractHelloMock.protectedSayHello("Bob"));
        //引数がnullのテスト
        assertEquals("Hello!!", abstractHelloMock.protectedSayHello(null));
    }

    @Test
    public void sayHelloTest() throws Exception {
        //抽象クラスのモック化
        AbstractHello abstractHelloMock = Mockito.mock(AbstractHello.class, Mockito.CALLS_REAL_METHODS);

        // 引数有りのテスト
        assertEquals("Hello,Marry!!", abstractHelloMock.sayHello("Marry"));
        //引数がnullのテスト
        assertEquals("Hello!!", abstractHelloMock.sayHello(null));
    }

    @Test
    public void callAbstractPublicSayHelloTest() throws Exception{
        //抽象クラスのモック化
        AbstractHello abastractHello = Mockito.mock(AbstractHello.class, Mockito.CALLS_REAL_METHODS);
        //テスト対象のメソッドが呼び出す抽象メソッドのモック化
        Mockito.when(abastractHello.abstractPublicSayHello("Kevin")).thenReturn("Hi,Kevin!!");

        //引数にKevinを与えた場合のテスト
        String name = "Kevin";
        assertEquals("Hi,Kevin!!", abastractHello.callAbstractPublicSayHello(name));

        //引数にKevin以外を与えた場合のテスト
        name = "Sara";
        assertNull(abastractHello.callAbstractPublicSayHello(name));
    }
}