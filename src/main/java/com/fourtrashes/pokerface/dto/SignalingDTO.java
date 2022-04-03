package com.fourtrashes.pokerface.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignalingDTO {
    private String from;
    private String to;
    private String type;
    private Object sdp;
    private Object candidate;

    public SignalingDTO(String from, String type) {
        this.from = from;
        this.type = type;
    }

    @Override
    public String toString() {
        return "SignalingDTO{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", type='" + type + '\'' +
                ", sdp=" + sdp +
                ", candidate=" + candidate +
                '}';
    }
}
