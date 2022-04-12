package com.fourtrashes.pokerface.dto;

import lombok.Data;

@Data
public class SelectCardDTO {
    //우선적으로 index가 아닌 카드 번호를 전달하는 것으로 구현한다고 가정 예) 하트4 => 34
    private int dump;
    private int openCard;
}
