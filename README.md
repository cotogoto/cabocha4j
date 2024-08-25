# CaboCha4J

CaboCha4Jは、CaboCha日本語係り受け解析器のJavaラッパーです。Javaアプリケーション内からCaboChaを実行し、解析結果を構造化されたJavaオブジェクト（`Chunk`と`Token`）として扱うことができます。柔軟な入力設定に対応し、固有表現認識を含む包括的な係り受け解析結果を出力します。

[CaboChaの詳細はこちら](https://taku910.github.io/cabocha/)

## 特徴

- **係り受け解析**: 日本語の文構造を解析
- **固有表現認識**: 文中の固有表現を抽出
- **簡単な統合**: Javaプロジェクトと簡単に統合

## インストール

[![](https://jitpack.io/v/cotogoto/cabocha4j.svg)](https://jitpack.io/#cotogoto/cabocha4j)

必ず以下の **VERSION** キーを上記の最新バージョンに置き換えてください。

Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
<dependency>
    <groupId>com.github.cotogoto</groupId>
    <artifactId>cabocha4j</artifactId>
    <version>VERSION</version>
</dependency>
```

## 使用方法

### 文の解析

```java
CaboCha cabochaWrapper = new CaboCha("C:\\Program Files (x86)\\CaboCha\\bin\\cabocha.exe", StandardCharsets.UTF_8);
Map<String, Chunk> result = cabochaWrapper.parse("太郎は花子が読んでいる本を次郎に渡した。");

for (Map.Entry<String, Chunk> entry : result.entrySet()) {
    Chunk chunk = entry.getValue();
    System.out.println("Chunk ID: " + chunk.getId() + ", Link: " + chunk.getLink());

    for (Token token : chunk.getTokenList()) {
        System.out.println("Token: " + token.getSurface() + ", Feature: " + token.getFeature() + ", NE: " + token.getNe());
    }
}
```

## コントリビューション

貢献は歓迎します！プルリクエストを提出するか、プロジェクトの改善に関する問題を開いてください。

## ライセンス

このプロジェクトはMITライセンスの下で提供されています。詳細は`LICENSE`ファイルを参照してください。
