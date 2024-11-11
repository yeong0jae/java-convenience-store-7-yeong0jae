# java-convenience-store-precourse

## 🏪 편의점

### 📝 프로그램 개요

자바 콘솔 편의점에서 상품을 주문하고, 재고 상황과 할인 혜택을 고려해 결제를 진행합니다.

---

## ✨ 주요 기능 목록

### 👐🏻 편의점 오픈

- [x] 상품 목록을 불러온다.
- [x] 프로모션 목록을 불러온다.
- [x] 주문을 받는다.
- [x] 결제를 진행한다.
    - [x] 프로모션 할인을 적용한다.
    - [x] 멤버십 할인을 적용한다.
- [x] 영수증을 안내한다.

### 🛒 주문 상품

- [x] 상품명, 주문 수량을 가진다.
    - [x] 주문 수량이 0 이하이면 예외 처리한다.
        - 🚫 message: "[ERROR] 1개 이상의 상품만 구매할 수 있습니다."
- [x] 상품명이 같은지 확인한다.

### 📞 주문

- [x] 주문 상품 목록을 가진다.
    - [x] 중복된 주문 상품이 있으면 예외 처리한다.
        - message: "[ERROR] 중복된 상품 이름을 입력하셨습니다."
- [x] 주문 상품 목록을 조회한다.
- [x] 상품명으로 주문 수량을 조회한다.
- 재고 검증
    - [x] 재고에 있는 상품인지 검증을 요청한다.
    - [x] 재고에 충분한 수량이 있는지 검증을 요청한다.

### 🛍️ 상품

- [x] 상품명, 가격, 수량, 프로모션명을 가진다.
- [x] 프로모션이 있는지 조회한다.
- [x] 상품명이 같은지 확인한다.
- [x] 상품의 재고를 차감한다.
    - [x] 재고 수량이 0이하가 되면 0으로 유지한다.

### 📦 재고

- [x] 상품 목록을 가진다.
- [x] 상품 목록을 조회한다.
- 상품 정보 조회
    - [x] 프로모션 여부를 확인한다.
    - [x] 프로모션명을 조회한다.
    - [x] 상품의 가격을 조회한다.
    - [x] 상품의 재고 수량이 충분한지 확인한다.
    - [x] 프로모션 재고 수량을 조회한다.
    - [x] 일반 재고 수량을 조회한다.
- 재고 관리
    - [x] 프로모션 재고 수량을 차감한다.
    - [x] 일반 재고 수량을 차감한다.
- 결제 가능한 상품인지 검증한다.
    - [x] 상품이 존재하지 않으면 예외 처리한다.
        - message: "[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요."
    - [x] 상품의 재고 수량이 부족하면 예외 처리한다.
        - message: "[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."

### 🎁 프로모션

- 프로모션
    - [x] 프로모션명, 프로모션 종류, 시작일, 종료일을 가진다.

- 프로모션 카탈로그
    - [x] 프로모션 목록을 갖는다.
    - [x] 프로모션 타입을 조회한다.
    - [x] 현재 프로모션 기간인지 확인한다.

- 프로모션 종류
    - [x] 1+1, 2+1 두 종류를 갖는다.

### 🧾 영수증

- [x] 구매내역, 증정상품, 프로모션 할인액, 멤버십 할인액을 갖는다.
- 결제 정보 누적
    - [x] 구매내역을 누적한다.
    - [x] 증정 상품을 누적한다.
    - [x] 프로모션 할인액을 누적한다.
    - [x] 멤버십 할인액을 누적한다.
        - [x] 최대 8000원을 유지한다.

### 💳 결제

- [x] 영수증을 생성한다.
- 프로모션 검증
    - [x] 프로모션이 없는 경우, 프로모션 할인을 적용하지 않는다.
    - [x] 프로모션 기간이 아닌 경우, 프로모션 할인을 적용하지 않는다.
    - [x] 프로모션 상품 재고가 없는 경우, 프로모션 할인을 적용하지 않는다.
- 할인 적용
    - 모든 상품에 프로모션이 적용되는 경우
        - 추가 프로모션 혜택이 가능한 경우 수락 의사를 묻는다.
            - [x] 증정 상품 개수를 계산한다.
            - [x] 프로모션 할인액을 계산한다.
            - [x] 멤버십 할인액을 계산한다.
            - [x] 재고 차감을 요청한다.
    - 일부 상품에 프로모션이 적용되는 경우
        - 프로모션이 적용되지 않는 일부 상품을 안내하고 진행 의사를 묻는다.
            - [x] 증정 상품 개수를 계산한다.
            - [x] 프로모션 할인액을 계산한다.
            - [x] 멤버십 할인액을 계산한다.
            - [x] 재고 차감을 요청한다.
- 영수증을 안내한다.

---

## 📋 입출력 기능 목록

### ⌨️ 입력

- [x] 파일 입출력을 통해 상품 목록 src/main/resources/products.md을 불러온다.
    - [x] 프로모션만 있는 상품은 기본 상품 0개로 추가한다.
- [x] 파일 입출력을 통해 프로모션 목록 src/main/resources/promotions.md을 불러온다.
- [x] 구매할 상품과 수량을 입력 받는다.
    - 상품명, 수량은 하이픈(-)으로 구분한다.
    - 개별 상품은 대괄호([])로 묶어 쉼표(,)로 구분한다.
    - ex) [콜라-10],[사이다-3]
- [x] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부를 입력받는다.
    - Y: 증정 받을 수 있는 상품을 추가한다.
    - N: 증정 받을 수 있는 상품을 추가하지 않는다.
- [x] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부를 입력받는다.
    - Y: 일부 수량에 대해 정가로 결제한다.
    - N: 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다.
- [x] 멤버십 할인 적용 여부를 입력 받는다.
    - Y: 멤버십 할인을 적용한다.
    - N: 멤버십 할인을 적용하지 않는다.
- [x] 추가 구매 여부를 입력 받는다.
    - Y: 재고가 업데이트된 상품 목록을 확인 후 추가로 구매를 진행한다.
    - N: 구매를 종료한다.

### 🖥️ 출력

- [x] 환영 인사를 출력한다.
    - ex) 안녕하세요. W편의점입니다.
- [x] 상품명, 가격, 프로모션 이름, 재고를 안내한다. 만약 재고가 0개라면 재고 없음을 출력한다.
- [x] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우, 혜택에 대한 안내 메시지를 출력한다.
    - ex) 현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
- [x] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부에 대한 안내 메시지를 출력한다.
    - ex) 현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
- [x] 멤버십 할인 적용 여부를 확인하기 위해 안내 문구를 출력한다.
    - 멤버십 할인을 받으시겠습니까? (Y/N)
- [x] 영수증을 출력한다.
    - 구매 상품 내역, 증정 상품 내역, 금액 정보를 출력한다.
- [x] 추가 구매 여부를 확인하기 위해 안내 문구를 출력한다.
    - ex) 감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
- 사용자가 잘못된 값을 입력했을 때, "[ERROR]"로 시작하는 오류 메시지와 함께 상황에 맞는 안내를 출력한다.
    - [ ] 기타 잘못된 입력의 경우:`[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.`
