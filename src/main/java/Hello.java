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

    /**
     * 引数ありPrivateメソッド
     * @param name
     * @return Hello,{name}!!
     */
    private String privateSayHello(String name){
        if (Objects.isNull(name)) return "Hello!!";
        return "Hello," + name + "!!";
    }
    /**
     * 引数無しPrivateメソッド
     * @return Hello!!
     */
    private String privateSayHello2(){
        return "Hello!!";
    }
}
