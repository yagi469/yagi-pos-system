# Yagi POS System

ビジネス向けのPOS（Point of Sale）システムです。Spring Bootによる堅牢なバックエンドと、React/Viteによるモダンなフロントエンドで構成されています。

## プロジェクト構成

```
yagi-pos-system/
├── yagi-pos-backend/       # バックエンドアプリケーション（Spring Boot）
├── yagi-pos-frontend/      # フロントエンドアプリケーション（React + Vite）
└── README.md               # プロジェクトドキュメント
```

## 技術スタック

### バックエンド (Backend)
- **Language**: Java 21
- **Framework**: Spring Boot 3.4.1
- **Build Tool**: Maven
- **Database**: MySQL
- **ORM**: Spring Data JPA (Hibernate)
- **Security**: Spring Security (JWT)
- **Payment**: Razorpay, Stripe

### フロントエンド (Frontend)
- **Language**: TypeScript
- **Framework**: React
- **Build Tool**: Vite
- **Package Manager**: pnpm (推奨) / npm

## セットアップと実行

### 前提条件
- Java 21 Development Kit (JDK)
- Node.js (LTS推奨)
- MySQL Server
- pnpm (推奨)

### 1. リポジトリのクローン
```bash
git clone <repository-url>
cd yagi-pos-system
```

### 2. バックエンドのセットアップ

**データベース設定**
MySQLでデータベースを作成し、`yagi-pos-backend/src/main/resources/application.properties` を編集します。

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/yagi_pos
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**実行**
```bash
cd yagi-pos-backend
./mvnw spring-boot:run
```
バックエンドは `http://localhost:5000` で起動します。

### 3. フロントエンドのセットアップ

**依存関係のインストール**
```bash
cd yagi-pos-frontend
pnpm install
```

**実行**
```bash
pnpm run dev
```
フロントエンドは通常 `http://localhost:5173` で起動します。

## 主な機能

- **認証 (Auth)**: ユーザー登録、ログイン
- **店舗管理 (Store)**: 店舗の作成、更新、取得
- **商品管理 (Product)**: 商品の登録、在庫管理
- **カテゴリー管理 (Category)**: 商品カテゴリーの管理
- **決済 (Payment)**: RazorpayおよびStripeを使用した決済処理

## ライセンス

MIT License
