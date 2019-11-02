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
}