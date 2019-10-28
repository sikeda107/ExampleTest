import java.util.Objects;

abstract class AbstractHello {
    /**
     * アクセス修飾子ごとの抽象メソッド
     * (privateは宣言できないため省略)
     */
    abstract public String abstractPublicSayHello(String name);

    abstract protected String abstractProtectedSayHello(String name);

    abstract String abstractSayHello(String name);

    /**
     * アクセス修飾子ごとのメソッド
     */
    public String publicSayHello(String name) {
        if (Objects.isNull(name)) return "Hello!!";
        return "Hello," + name + "!!";
    }

    protected String protectedSayHello(String name) {
        if (Objects.isNull(name)) return "Hello!!";
        return "Hello," + name + "!!";
    }

    private String privateSayHello(String name) {
        if (Objects.isNull(name)) return "Hello!!";
        return "Hello," + name + "!!";
    }

    String sayHello(String name) {
        if (Objects.isNull(name)) return "Hello!!";
        return "Hello," + name + "!!";
    }
}
