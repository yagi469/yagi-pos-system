# Yagi POS System

ビジネス向けのPOS（Point of Sale）システムのバックエンドアプリケーションです。Spring Boot 3.4.1 と Java 21 を使用して構築されており、堅牢なセキュリティと決済機能の統合を提供します。

## プロジェクト構成

現在のプロジェクト構成は以下の通りです。

```
yagi-pos-system/
├── yagi-pos-backend/       # バックエンドアプリケーション（Spring Boot）
└── README.md               # プロジェクトドキュメント
```

## 技術スタック

### コアテクノロジー
- **Java**: 21
- **Framework**: Spring Boot 3.4.1
- **Build Tool**: Maven

### データベース & ORM
- **Database**: MySQL
- **ORM**: Spring Data JPA (Hibernate)

### セキュリティ & 認証
- **Security**: Spring Security
- **Authentication**: JWT (JSON Web Token)

### 決済機能
- **Razorpay**: 決済処理の統合
- **Stripe**: 決済処理の統合

### その他
- **Lombok**: ボイラープレートコードの削減
- **Validation**: Spring Boot Starter Validation

## セットアップと実行

### 前提条件
- Java 21 Development Kit (JDK)
- MySQL Server

### 1. リポジトリのクローン
```bash
git clone <repository-url>
cd yagi-pos-system
```

### 2. データベース設定
MySQLでデータベースを作成し、`yagi-pos-backend/src/main/resources/application.properties` の設定を環境に合わせて更新してください。

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/yagi_pos
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. アプリケーションの実行
バックエンドディレクトリに移動し、Mavenラッパーを使用してアプリケーションを起動します。

```bash
cd yagi-pos-backend
./mvnw spring-boot:run
```

アプリケーションはデフォルトで `http://localhost:5000` で起動します。

## 主な機能 (API)

- **認証 (Auth)**: ユーザー登録、ログイン、JWT発行
- **店舗管理 (Store)**: 店舗の作成、更新、取得
- **商品管理 (Product)**: 商品の登録、在庫管理
- **カテゴリー管理 (Category)**: 商品カテゴリーの管理
- **決済 (Payment)**: RazorpayおよびStripeを使用した決済処理

## ライセンス

MIT License
