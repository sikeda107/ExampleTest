import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

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