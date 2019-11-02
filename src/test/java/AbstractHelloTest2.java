import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractHelloTest2 {

    /**
     * モック用に抽象クラスを具象化
     */
    class MockAbstractHello extends AbstractHello{
        @Override
        public String abstractPublicSayHello(String name) {
            return name;
        }

        @Override
        protected String abstractProtectedSayHello(String name) {
            return name;
        }

        @Override
        String abstractSayHello(String name) {
            return name;
        }
    }

    @Test
    public void publicSayHelloTest() throws Exception{
        MockAbstractHello mockAbstractHello = new MockAbstractHello();
        // 引数有りのテスト
        assertEquals("Hello,Tom!!", mockAbstractHello.publicSayHello("Tom"));
        //引数がnullのテスト
        assertEquals("Hello!!", mockAbstractHello.publicSayHello(null));
    }

    @Test
    public void protectedSayHelloTest() throws Exception{
        MockAbstractHello mockAbstractHello = new MockAbstractHello();
        // 引数有りのテスト
        assertEquals("Hello,Bob!!", mockAbstractHello.protectedSayHello("Bob"));
        //引数がnullのテスト
        assertEquals("Hello!!", mockAbstractHello.protectedSayHello(null));
    }

    @Test
    public void sayHelloTest() throws Exception{
        MockAbstractHello mockAbstractHello = new MockAbstractHello();
        // 引数有りのテスト
        assertEquals("Hello,Bob!!", mockAbstractHello.sayHello("Bob"));
        //引数がnullのテスト
        assertEquals("Hello!!", mockAbstractHello.sayHello(null));
    }
}