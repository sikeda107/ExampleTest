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

    public String publicSayHelloVoid() {
        return "Hello!!";
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
    /**
     * 抽象メソッドを呼び出す実装メソッド
     */
    public String callAbstractPublicSayHello(String name){
        // Stringを引数に、抽象メソッドを呼び出し、戻り値をStringへ格納
        String str = abstractPublicSayHello(name);
        return str;
    }
}
