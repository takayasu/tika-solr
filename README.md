# Apache Tikaを用いたSolrへの登録

Tikaを用いて、ファイルから本文情報を抜き出してSolrに登録するプログラムです。
この用途なら、Solr Cellというてもあるのですが諸事情からサンプルを書くことになったので、こちらで。

サンプルなのでいろいろと省略してます。

## 動かし方
引数として、file 登録するファイルパス　を指定してください。

## Solrの設定
Solrの設定は、SolrConfigクラスに設定しています。

## Solrのスキーマ

title
content
filename

を登録してください。

## 説明
SolrUtil Solr入力情報や登録を行うクラス　（TikaModelからSolrInputDocumentに変換する）
TikaUtil Tikaを用いて文書ファイルを取り扱い、情報をTikaModelに格納する
