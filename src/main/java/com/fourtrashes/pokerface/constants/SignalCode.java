package com.fourtrashes.pokerface.constants;

public interface SignalCode {
    enum Message {
        JOIN("join"),
        OFFER("offer"),
        ANSWER("answer"),
        ICE("ice");

        private String value;
        Message(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        public void setValue() {
            this.value = value;
        }
    }

    enum Action {

    }
}
