## Background
  ※特に意味はないセクションです。
  テストコードをちゃんと書いたことがないなと。ソフトウェア工学で単体テストとか結合テストとか色々学んだけど、テストコードは書かなかった。Excelポチポチのテストに意味がないとはそこまで思わないけど、何回もテストしたり、寝てる間に自動でテストしてくれたりとかそういうのはできないよねって。あとは単体レベルのリグレッションとかちゃんとテストコード書けば、コマンド一発できるんだから積極的に書い行くべきだと思ったり。考慮漏れとかあっても、テストコード追加してコマンド叩けばいいよね。（テストコードが正しいかテストする方法がないのが辛いけど。）コードレビューとか正直、単体テストレベル通らないコードをレビューするのはレビュー担当者にとって相当な負担なんじゃないかと。

## Introduction
JunitでJavaのテストコードを書きました。流れは[参考文献](https://www.codeflow.site/ja/article/junit-test-abstract-class)のパクリで、記事は自分の頭で整理する用です。普通のクラスのテストはそれほど大変ではなないけども、抽象クラスってどうやってテストするのかな？ってことでこの記事を参考にしました。後々、Kotlinでも書きたい…。開発環境は[IntelliJ IDEA CE](https://www.jetbrains.com/idea/)です。ビルドツールは[Gradle](https://gradle.org/)を使いました。[Eclipse](https://mergedoc.osdn.jp/)は重たいってのと、個人的な趣味でIntelliJを使ってます。

## References
- [CodeFlow JUnitで抽象クラスをテストする Testing Mockito JUnit 5](https://www.codeflow.site/ja/article/junit-test-abstract-class)
- [テストを作成する - 公式ヘルプ | IntelliJ IDEA](https://pleiades.io/help/idea/create-tests.html)
- 「分かりそう」で「分からない」でも「分かった」気になれるIT用語辞典
  - [システムテスト (ST)とは](https://wa3.i-3-i.info/word15915.html)
  - [単体テスト (UT)とは](https://wa3.i-3-i.info/word15916.html)
  - [結合テスト (IT)とは](https://wa3.i-3-i.info/word15917.html)
  - [ドライバ【テスト】 (driver)とは](https://wa3.i-3-i.info/word14934.html)
  - [スタブ【テスト】 (stub)とは](https://wa3.i-3-i.info/word14933.html)

## Description
今回のコードはGithubの[このリポジトリ](https://github.com/sikeda107/ExampleTest)にあげてあります。
プロジェクトの構成で、

- 実装用コードのディレクトリのパスは `Example/src/main/java/`
- テスト用コードのディレクトリのパスは`Example/src/test/java/`
です。

リポジトリを落としてきて、`gradle test`を打てば、テストが実行できます。Gradleの使い方はとかは、公式とかを参考にしてください。今回に関しては↓だけ覚えておけばいいかもしれません。

- ビルド `gradle build`
- テスト実行 `gradle test`
- ビルド結果の削除 `gradle clean`

## Description of Main Class
テスト対象のクラスの説明です。クラスは３つあります。

- 抽象クラス `AbstractHello`
- 実装クラス `Hello`
- 実装クラスを実行するためのクラス `Main`

各クラスの詳細は、後述します。`Hello`は`AbstractHello`を継承しています。`Main`では、`Hello`のメソッドを使って、処理を行なっていきます。
各クラスのメソッドは基本的に、`Hello,{名前}!!`という文字列を返すだけです。

### Abstract Helloクラス

抽象クラスです。

- 3つの抽象メソッドと、
  - abstractPublicSayHello
  - abstractProtectedSayHello
  - abstractSayHello

- 名前を引数として取るアクセス修飾子が異なるメソッドが4つと、
  - publicSayHello
  - protectedSayHello
  - privateSayHello
  - sayHello

- 引数を取らないメソッドが1つ、
  - publicSayHelloVoid

- 自身の抽象メソッドを呼び出すメソッドが1つ、
  - callAbstractPublicSayHello

あります。
各メソッドでやっていることやりたいことは基本的にこれです。

```Java
public String publicSayHello(String name) {
  if (Objects.isNull(name)) return "Hello!!";
  return "Hello," + name + "!!";
}
```

引数として、Stringの`name`を受け取り、nameが`null`の時は挨拶だけし、そうでない時は名前付きで挨拶を返します。引数を取らないメソッドは挨拶だけ返します。抽象メソッドを呼び出すメソッドは、結果をStringへ一度格納して、それを返します。

```Java
// 引数を取らないメソッド
public String publicSayHelloVoid() {
  return "Hello!!";
}
//抽象メソッドを呼びだすメソッド
public String callAbstractPublicSayHello(String name){
  String str = abstractPublicSayHello(name);
  return str;
}
```

### Helloクラス
`AbstractHello`を継承しています。各メソッドは抽象クラスと同じことをしています。抽象メソッドをオーバーライドして、中身を同じように実装しています。

- abstractPublicSayHello
- abstractProtectedSayHello
- abstractSayHello

```Java
@Override
public String abstractPublicSayHello(String name) {
  if (Objects.isNull(name)) return "Hello!!";
  return "Hello," + name + "!!";
}
```

テスト用に同じ処理をするプライベートメソッドも定義してあります。

- privateSayHello
- privateSayHello2

1と2の違いは、1には引数があり、2にはないということです。
メソッドの処理の説明は割愛します。


### Mainクラス
このクラスは別に作る必要はないです。mainメソッドで、Helloクラスを使って処理行なっています。mainメソッドでは次のことをも行います。Helloクラスのインスタンスを作って、各メソッドを呼び出し、その戻り値をリストへ挿入していきます。最後に、リストの中身を標準出力します。

```Java
public static void main(String[] args) {
  // Helloクラスのインスタンス化
  Hello hello = new Hello();
  // 挨拶を格納するリストをインスタンス化
  List<String> greetings = new ArrayList<String>();
  //挨拶をリストへ格納する
  greetings.add(hello.各メソッド);
  // 挨拶をする(※forEachとメソッド参照を使う)
  greetings.forEach(System.out::println);
}
```

## Description of Test Class

今回の目的のテスト用のクラスです。クラスは３つあります。

- AbstractHelloTest
- AbstractHelloTest2
- HelloTest

テスト対象のクラスにTestをつけたクラスを作成します。IntelliJだと、クラス名の上にマウスオーバーすると黄色の電球が出てきて、それをクリックするとメニューが出てくるので
、そのメニューから`Create Test`をクリックして作成できます。詳細は公式の[こちら](https://pleiades.io/help/idea/create-tests.html)を確認ください。

### 抽象クラスのテスト

抽象クラス用のテストを2つ作ってあります。それぞれの違いは次の通りです。

- AbstractHelloTest
  - Mockito によるモック化
- AbstractHelloTest2
  - 抽象クラスを継承したインナークラスを作成

まず思いついたのが、二つ目のインナークラスを作成する方です。特に新しいこともなく、問題もなさそうです。正直、これのデメリットがいまいちわかってないです。ただ、せっかくなのでMockitoを使いなあと思いました。それで作成したテストが一つ目です。二つ目はおまけです。

インナークラスのテストはこう書きました。

```Java
public class AbstractHelloTest2 {
  class MockAbstractHello extends AbstractHello{
    @Override
    いろんなメソッド{}
  }
  @Test
  public void テストメソッド() throws Exception {
  // インナークラスのインスタンス化
  MockAbstractHello mockAbstractHello = new MockAbstractHello();
  //実行結果の比較
  assertEquals(期待値, mockAbstractHello.メソッド());
  }
}
```

これを[mockito](https://site.mockito.org/)を使って、書き換えていきます。[公式ドキュメント 3.1.0 ](https://javadoc.io/static/org.mockito/mockito-core/3.1.0/org/mockito/Mockito.html)に導入方法や使い方があるので、ちゃんとやりたい人はそちらを読んでください。
mockitoを使う場合は、インナークラスはいらないです。代わりに、`Mockito.mock()`を使います。そうするとこんな感じになります。

```Java
public class AbstractHelloTest {
  @Test
  public void テストメソッド() throws Exception {
    //抽象クラスのモック化
    AbstractHello abstractHelloMock = Mockito.mock(AbstractHello.class, Mockito.CALLS_REAL_METHODS);
    //実行結果の比較
    assertEquals(期待値, abstractHelloMock.メソッド());
  }
}
```
だいたいこれだけ知っておけば、もうテストコード書くときに困りそうにないんじゃないかと思っています。メソッドの説明は後述します。

あとは、抽象メソッドを具象メソッドが呼び出している`callAbstractPublicSayHello()`のテストです。この場合、呼び出している抽象メソッド何かしらでモック化してあげる必要があります。今回はこのようにしてあります。[when](https://javadoc.io/static/org.mockito/mockito-core/3.1.0/org/mockito/Mockito.html#when-T-)というメソッドで、メソッドのスタブを作っています。そのメソッドが呼ばれたときに何を返すかは[thenReturn](https://javadoc.io/static/org.mockito/mockito-core/3.1.0/org/mockito/stubbing/OngoingStubbing.html#thenReturn-T-)メソッドで指定しています。ただし、今回の実装だと「Kevin」が与えられた時のスタブを作っているので、他の引数(例えば、「Sara」)を与えたときに`null`が返ってきます。

```Java
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
```

### mockメソッド
[公式](https://javadoc.io/static/org.mockito/mockito-core/3.1.0/org/mockito/Mockito.html#mock-java.lang.Class-org.mockito.stubbing.Answer-) から

> public static <T> T mock(Class<T> classToMock, Answer defaultAnswer)
Parameters:
classToMock - class or interface to mock
defaultAnswer - default answer for unstubbed methods
Returns:
mock object

第1引数に、モック化したいクラスを指定します。第2引数には、[Answer](https://javadoc.io/static/org.mockito/mockito-core/3.1.0/org/mockito/stubbing/Answer.html)を指定します。アンサーの定義は `Interface Answer<T>`です。アンサーで、モックに対してメソッドを実行した時の返答を指定します。
今回は、[CALLS_REAL_METHODS](https://javadoc.io/static/org.mockito/mockito-core/3.1.0/org/mockito/Mockito.html#CALLS_REAL_METHODS) を指定しています。これを指定することで抽象メソッドの実際のメソッドを使うことができます。

### whenメソッドとthenReturnメソッド
[公式](https://javadoc.io/static/org.mockito/mockito-core/3.1.0/org/mockito/Mockito.html#when-T-)

>public static <T> OngoingStubbing<T> when(T methodCall)
Parameters:
methodCall - method to be stubbed
Returns:
OngoingStubbing object used to stub fluently. Do not create a reference to this returned object.

[公式](https://javadoc.io/static/org.mockito/mockito-core/3.1.0/org/mockito/stubbing/OngoingStubbing.html#thenReturn-T-)

>OngoingStubbing<T> thenReturn(T value)
Parameters:
value - return value
Returns:
object that allows stubbing consecutive calls

whenメソッドの引数にスタブを作りたいメソッドを指定(許可)します。そのまま、[Interface OngoingStubbing < T >](https://javadoc.io/static/org.mockito/mockito-core/3.1.0/org/mockito/stubbing/OngoingStubbing.html)の一つであるthenReturnメソッドを繋げて、書きます。thenReturnメソッドの引数には、スタブしたメソッドの戻り値を渡します。これで、メソッドのスタブが作られました。

### 余談: メソッドの部分モック化
mockメソッドで、Answerを指定せずに部分的にモック化する方法です。それは[doCallRealMethod](https://javadoc.io/static/org.mockito/mockito-core/3.1.0/org/mockito/Mockito.html#doCallRealMethod--)を使います。調べたので書きますが、使いどころはなさそうです。
公式も
> However, there are rare cases when partial mocks come handy: dealing with code you cannot change easily.

とのことです。ただ、引数がvoidのメソッドをテストするときなどには使えそうです。このような感じで使います。

```Java
@Test
public void publicSayHelloVoidTest() throws Exception {
  //抽象クラスのモック化
  AbstractHello abstractHelloMock = Mockito.mock(AbstractHello.class);
  //メソッドの部分モック化
  Mockito.doCallRealMethod().when(abstractHelloMock).publicSayHelloVoid();
  // Voidメソッドのテスト
  assertEquals("Hello!!", abstractHelloMock.publicSayHelloVoid());
  // 別のメソッドpublicSayHelloの引数を「Tom」としてテスト
  assertNull(abstractHelloMock.publicSayHello("Tom"));
}
```

### 具象クラスのテスト
普通のテストです。インナークラスを用いた抽象クラスのテストからインナークラスを定義する部分を抜けばいいです。

```Java
@Test
public void テストメソッド() {
  // テストするクラスのインスタンス化
  Hello hello = new Hello();
  // 引数有りのテスト
  assertEquals(期待値, hello.メソッド(引数));
  //引数がnullのテスト
  assertEquals(期待値), hello.メソッド(null));
}
```

#### プロテクトメソッドやプライベートメソッドのテスト

プライベートならそれを読んでいるメソッドをテストすればいいので、テストする必要はなさそうですが、一応やってみます。テスト対象のメソッドにアクセスできるように設定して、実行してあげればいいです。

```Java

@Test
public void プライベートメソッドのテスト() throws Exception {
  // テストするクラスのインスタンス化
  Hello hello = new Hello();
  //メソッドの取得
  Method method = Hello.class.getDeclaredMethod("メソッド名",引数のクラス名.class);
  //アクセス権の付与
  method.setAccessible(true);

  // 期待値と比較
  assertEquals(期待値, method.invoke(hello,引数));
}
```

#### getDeclaredMethodメソッド
[公式 JavaSE13 & JDK13](https://docs.oracle.com/javase/jp/13/docs/api/java.base/java/lang/Class.html#getDeclaredMethod(java.lang.String,java.lang.Class...\))

>public Method getDeclaredMethod​(String name, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException
パラメータ:
name - メソッドの名前
parameterTypes - パラメータ配列
戻り値:
このクラスの指定された名前とパラメータと一致するメソッドのMethodオブジェクト

Classオブジェクトで定義されているこのメソッドで、メソッドのオブジェクトを取得します。

#### setAccessibleメソッド
[公式 JavaSE13 & JDK13](https://docs.oracle.com/javase/jp/13/docs/api/java.base/java/lang/reflect/Method.html#setAccessible(boolean\))

>public void setAccessible​(boolean flag)
パラメータ:
flag - accessibleフラグの新しい値
例外:
InaccessibleObjectException - アクセスを有効にできない場合
SecurityException - リクエストがセキュリティ・マネージャによって拒否された場合

getDeclaredMethodメソッドで取得したMethodオブジェクトに対して、アクセス権を設定できます。戻り値はvoidのようです。

#### invokeメソッド
[公式 JavaSE13 & JDK13](https://docs.oracle.com/javase/jp/13/docs/api/java.base/java/lang/reflect/Method.html#invoke(java.lang.Object,java.lang.Object...\) )

>public Object invoke​(Object obj, Object... args)
パラメータ:
obj - 基本となるメソッドの呼出し元のオブジェクト
args - メソッド呼出しに使用される引数
戻り値:
このオブジェクトが表すメソッドを、パラメータargsを使用してobjにディスパッチした結果

Methodオブジェクトを実行するときに使います。第1引数にインスタンス化したオブジェクトを、第2引数以降にメソッドに渡す引数を、invokeメソッドに渡してあげます。

## 強制失敗
テストコードを自動生成した際に、だいたいこんな感じのコードが生成されます。

```Java
@Test
public void failTest(){
  fail("Not yet implemented!!");
}
```

failメソッドでテストが失敗したことを通知させています。`gradle test`をすると、結果として次のような物をGradleが返します。どこで失敗しているのかとか、わかりやすいですね。Gradleはテスト結果をHTMLとして、出力してくれるのですがいつもパスを忘れます。テストを強制的に失敗させると、「失敗したテストがあるよ。このレポートをみてね。パス」というメッセージを出してくれるので、わかりやすいです。ブラウザで、`file:///任意/Example/build/reports/tests/test/index.html`とかを開いてください。
ここでテスト結果を確認できます。

```Gradle
> Task :test FAILED

HelloTest > failTest FAILED
    java.lang.AssertionError at HelloTest.java:12

13 tests completed, 1 failed

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':test'.
> There were failing tests. See the report at: file:///任意/Example/build/reports/tests/test/index.html

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 2s
5 actionable tasks: 5 executed

```

## Conclusion

抽象クラスのテスト方法とアクセス権のないメソッドのテスト方法を試してみました。今後は、[H2DB](https://www.h2database.com/html/main.html)での、DBを使ったテストを書いてみようと思います。あとはKotlinでも書きたい…。
「[TDD is dead. Long live testing. (DHH)](https://dhh.dk/2014/tdd-is-dead-long-live-testing.html)」そんなことはないと思っています。どれくらいコストかけるかは考える必要はあると思いますが。