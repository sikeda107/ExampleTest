# Example Test

## Background
  ※特に意味はないセクションです。
  テストコードをちゃんと書いたことがないなと。ソフトウェア工学で単体テストとか結合テストとか色々学んだけど、テストコードは書かなかった。Excelポチポチのテストに意味がないとはそこまで思わないけど、何回もテストしたり、寝てる間に自動でテストしてくれたりとかそういうのはできないよねって。あとは単体レベルのリグレッションとかちゃんとテストコード書けば、コマンド一発できるんだから積極的に書い行くべきだと思ったり。考慮漏れとかあっても、テストコード追加してコマンド叩けばいいよね。（テストコードが正しいかテストする方法がないのが辛いけど。）コードレビューとか正直、単体テストレベル通らないコードをレビューするのはレビュー担当者にとって相当な負担なんじゃないかと。

## Introduction
JunitでJavaのテストコードを書きました。流れは[参考文献](https://www.codeflow.site/ja/article/junit-test-abstract-class)のパクリで、記事は自分の頭で整理する用です。普通のクラスのテストはそれほど大変ではなないけども、抽象クラスってどうやってテストするのかな？ってことでこの記事を参考にした。後々、Kotlinでも書きたい…。開発環境は[IntelliJ IDEA CE](https://www.jetbrains.com/idea/)です。ビルドツールは[Gradle](https://gradle.org/)を使いました。[Eclipse](https://mergedoc.osdn.jp/)は重たいってのと、個人的な趣味でIntelliJを使ってます。

## References
- [CodeFlow JUnitで抽象クラスをテストする Testing Mockito JUnit 5](https://www.codeflow.site/ja/article/junit-test-abstract-class)
- [テストを作成する - 公式ヘルプ | IntelliJ IDEA](https://pleiades.io/help/idea/create-tests.html)

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

