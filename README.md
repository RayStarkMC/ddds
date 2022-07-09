# ddds

## 概要

dddsはDDDの開発補助ライブラリであり、型を使った制約により実装ミスをコンパイルエラー化する事を目的としています。
dddsという単語はdomain-driven-design-supportの略語です。  
エンティティやリポジトリ、イベント等、DDDの戦術的設計要素の実装を補助するインターフェースを提供します。

## 依存関係

dddsは永続的なデータ構造や関数を利用するために[vavr]に依存しています。

## Issue

質問、指摘、バグ報告等自由にご利用ください。重複が無い方が好ましいですが、禁止しているわけではありません。

## 導入方法

dddsはGitHubPackagesに公開されています。dddsに依存するためには[GitHubの個人アクセストークン]が必要になります。


### 事前準備

1. read:packages権限のついた[GitHubの個人アクセストークン]を取得してください。
2. ~/.gradle/gradle.propertiesを作成し、以下のプロパティを設定してください。
   - `github.username = <GitHubのユーザー名>`
   - `github.password = <生成したトークン文字列>`

### ビルドスクリプト

以下のスクリプトをbuild.gradleに記述して下さい。

```groovy
repositories {
   mavenCentral()

   maven {
      url = uri 'https://maven.pkg.github.com/raystarkmc/ddds'
      credentials {
         username = findProperty 'github.username'
         password = findProperty 'github.password'
      }
   }
}

dependencies {
   implementation 'raystark:ddds:0.1'
}
```

## ライセンス

dddsは[Apache License 2.0]の元公開されています。

[vavr]:https://www.vavr.io
[Apache License 2.0]: https://www.apache.org/licenses/LICENSE-2.0
[GitHubの個人アクセストークン]: https://docs.github.com/ja/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#authenticating-to-github-packages