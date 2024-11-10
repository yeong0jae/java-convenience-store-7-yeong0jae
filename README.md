# java-convenience-store-precourse

## 🏪 편의점 기능 목록

- 주문 상품
    - [x] 상품명, 수량을 가진다.
        - [x] 상품 수량이 0 이하이면 예외 처리한다.
    - [x] 상품명이 같은지 확인한다.

- 주문
    - [x] 주문 상품 목록을 가진다.
    - [x] 주문 상품의 이름 목록을 확인한다.
    - [x] 주문 수량을 확인한다.

- 상품
    - [x] 상품명, 가격, 수량, 프로모션을 가진다.
    - [x] 상품명이 같은지 확인한다.

- 재고 관리
    - [x] 상품 목록을 가진다.
    - [x] 결제 가능 여부를 확인한다.
        - [x] 구매자가 가져온 상품(상품명, 수량)을 확인한다.
            - [x] 구매자가 가져온 상품명으로 상품이 존재하는지 확인한다.
                - [x] 구매자가 가져온 상품이 존재하지 않으면 예외 처리한다.
            - [x] 요청한 상품의 수량이 재고 수량보다 작은지 확인한다.
                - [x] 상품의 재고 수량을 구한다.
                - [x] (구매자가 가져온 상품의 수량) > (상품의 재고 수량)이면 예외 처리한다.
    - [x] 상품의 가격을 확인한다.
    - [x] 존재하는 상품인지 확인한다.
    - [x] 상품의 재고 수량을 확인한다.
    - [x] 상품의 프로모션 재고 수량을 확인한다.
    - 결제 후 재고 수량을 차감한다.
        - 프로모션 재고 수량을 차감한다.
        - 일반 재고 수량을 차감한다.
    - 상품 정보를 제공한다.

- 결제
    - [x] 주문과 재고를 가진다.
    - [x] 총구매액을 계산한다.
        - [x] 총구매액 = (상품의 가격 * 주문 수량) 의 합
    - [ ] 최종 결제 금액을 계산한다.
        - [ ] 프로모션 할인 금액을 계산한다.
        - 멤버십 할인 금액을 계산한다.
    - 결제 후 영수증으로 안내한다.

- 프로모션 카탈로그
    - [x] 프로모션 목록을 갖는다.
    - [x] 상품이 프로모션 기간인지 확인한다.

- 프로모션 할인
    - [x] 프로모션 이름, 증정 조건 개수, 증정 개수, 프로모션 시작 날짜, 프로모션 종료 날짜를 가진다.
    - [x] N개 구매 시 1개 무료 증정의 형태로 진행된다.
        - [x] 1+1 또는 2+1 프로모션이 각각 지정된 상품에 적용된다.
    - [x] 하나의 상품은 하나의 프로모션만 적용된다.
    - [ ] 프로모션 할인 금액을 계산한다.
        - [x] 주문 상품이 프로모션이 있는지 확인한다.
            - [x] 프로모션이 없다면 프로모션 할인을 적용하지 않는다.
        - [x] 구매자가 가져온 상품이 프로모션 기간인지 확인한다.
            - [x] 현재 날짜와 프로모션 기간을 비교한다.
                - [ ] 프로모션 기간이 아니면 프로모션 할인을 적용하지 않는다.
        - [ ] 주문 상품 하나에 대해 할인 금액을 계산한다.
        - 구매자가 가져온 상품 개수와 프로모션 상품 개수를 비교한다.
            - case1) 구매자의 모든 상품에 프로모션 할인이 가능하다.
                - 구매자가 가져온 상품 개수 <= 프로모션 상품 개수 [바나나, 14], (바나나 20개 2+1)
            - case2) 구매자의 상품 일부만 프로모션 할인이 가능하다.
                - 구매자가 가져온 상품 개수 > 프로모션 상품 개수 [바나나, 15], (바나나 10개 2+1)
            - case3) 구매자의 상품 모두 프로모션 할인이 불가능하다.
                - 프로모션 상품 개수 == 0 [바나나, 10], (바나나 0개 2+1)
        - 증정 상품 개수와 프로모션 적용 수량을 구한다.
            - case1) 구매자의 모든 상품에 프로모션 할인이 가능하다.
                - 증정 상품 개수 = (구매자가 가져온 상품 개수) / (프로모션 buy + get 수량)
                - 나눈 나머지 = 남은 구매 개수
                    - (남은 구매 개수) == (프로모션 buy) 이면
                        - 필요한 수량을 추가로 가져오면 혜택을 받을 수 있음을 안내한다.
                            - 받을 경우 (구매자가 가져온 상품 개수) += 1, (증정 상품 개수) += 1
                            - 받지 않을 경우 남은 구매 개수는 프로모션 할인을 적용하지 않는다.
                    - (남은 구매 개수) < (프로모션 buy) 이면
                        - 남은 구매 개수는 프로모션 할인을 적용하지 않는다.
                - (프로모션 적용 수량) = (구매자가 가져온 상품 개수)
            - case2) 구매자의 상품 일부만 프로모션 할인이 가능하다.
                - 증정 상품 개수 = (프로모션 상품 개수) / (프로모션 buy + get 수량)
                - 구매자가 가져온 상품 개수 - 증정 상품 개수 = 남은 구매 개수
                    - 남은 구매 개수는 프로모션 할인을 적용하지 않는다.
                    - 일부 수량에 대해 정가로 결제하게 됨을 안내한다.
                - (프로모션 적용 수량) = (증정 상품 개수) * (프로모션 buy + get 수량)
            - case3) 구매자의 상품 모두 프로모션 할인이 불가능하다.
                - 증정 상품 개수 = 0
                - 프로모션 할인을 적용하지 않는다.
                - (프로모션 적용 수량) = 0
        - 행사 할인 금액을 구한다.
            - 행사 할인 금액 = 증정 상품별 개수 * 가격

- 멤버십 할인
    - 멤버십 적용 대상 금액 = 총 구매액 - (프로모션 적용 수량 * 금액)
    - 멤버십 할인 금액을 계산한다.
        - 멤버십 할인 금액 = 멤버십 적용 대상 금액 * 30%
    - 멤버십 할인 금액 > 8,000 이면 8,000원만 할인한다.
    - 멤버십 할인 금액 <= 8,000 이면 멤버십 할인 금액만큼 할인한다.

- 영수증
    - 구매 상품 내역: 구매한 상품명, 수량, 가격을 안내한다.
    - 증정 상품 내역: 프로모션에 따라 무료로 제공된 증정 상품의 목록을 안내한다.
    - 금액 정보를 안내한다.
        - 총구매액: 구매한 상품의 총 수량과 총 금액
        - 행사할인: 프로모션에 의해 할인된 금액
        - 멤버십할인: 멤버십에 의해 추가로 할인된 금액
        - 내실돈: 최종 결제 금액

- 추가 구매를 여부를 선택한다.

- 사용자가 잘못된 값을 입력할 경우`IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
    - `Exception`이 아닌`IllegalArgumentException`,`IllegalStateException`등과 같은 명확한 유형을 처리한다.

---

- 입력
    - [x] 파일 입출력을 통해 상품 목록 src/main/resources/products.md을 불러온다.
        - [x] 프로모션만 있는 상품은 기본 상품 0개로 추가한다.
    - [x] 파일 입출력을 통해 프로모션 목록 src/main/resources/promotions.md을 불러온다.
    - 두 파일 모두 내용의 형식을 유지한다면 값은 수정할 수 있다.
    - 구매할 상품과 수량을 입력 받는다.
        - 상품명, 수량은 하이픈(-)으로 구분한다.
        - 개별 상품은 대괄호([])로 묶어 쉼표(,)로 구분한다.
        - ex) [콜라-10],[사이다-3]
    - 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부를 입력받는다.
        - Y: 증정 받을 수 있는 상품을 추가한다.
        - N: 증정 받을 수 있는 상품을 추가하지 않는다.
    - 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부를 입력받는다.
        - Y: 일부 수량에 대해 정가로 결제한다.
        - N: 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다.
    - 멤버십 할인 적용 여부를 입력 받는다.
        - Y: 멤버십 할인을 적용한다.
        - N: 멤버십 할인을 적용하지 않는다.
    - 추가 구매 여부를 입력 받는다.
        - Y: 재고가 업데이트된 상품 목록을 확인 후 추가로 구매를 진행한다.
        - N: 구매를 종료한다.

- 출력
    - 환영 인사를 출력한다.
        - ex) 안녕하세요. W편의점입니다.
    - 상품명, 가격, 프로모션 이름, 재고를 안내한다. 만약 재고가 0개라면 재고 없음을 출력한다.
    - 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우, 혜택에 대한 안내 메시지를 출력한다.
        - ex) 현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
    - 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부에 대한 안내 메시지를 출력한다.
        - ex) 현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
    - 멤버십 할인 적용 여부를 확인하기 위해 안내 문구를 출력한다.
        - 멤버십 할인을 받으시겠습니까? (Y/N)
    - 영수증을 출력한다.
        - 구매 상품 내역, 증정 상품 내역, 금액 정보를 출력한다.
    - 추가 구매 여부를 확인하기 위해 안내 문구를 출력한다.
        - ex) 감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
    - 사용자가 잘못된 값을 입력했을 때, "[ERROR]"로 시작하는 오류 메시지와 함께 상황에 맞는 안내를 출력한다.
        - 구매할 상품과 수량 형식이 올바르지 않은 경우:`[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.`
        - [x] 존재하지 않는 상품을 입력한 경우:`[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.`
        - [x] 구매 수량이 재고 수량을 초과한 경우:`[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.`
        - 기타 잘못된 입력의 경우:`[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.`
