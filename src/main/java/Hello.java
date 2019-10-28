import java.util.Objects;

public class Hello extends AbstractHello {
    /**
     * アクセス修飾子ごとの抽象メソッドの実装
     */
    @Override
    public String abstractPublicSayHello(String name) {
        if (Objects.isNull(name)) return "Hello!!";
        return "Hello," + name + "!!";
    }

    @Override
    protected String abstractProtectedSayHello(String name) {
        if (Objects.isNull(name)) return "Hello!!";
        return "Hello," + name + "!!";
    }

    @Override
    String abstractSayHello(String name) {
        if (Objects.isNull(name)) return "Hello!!";
        return "Hello," + name + "!!";
    }
}
