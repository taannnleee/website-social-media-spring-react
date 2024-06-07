package org.example.socialmedia.common.Enum;

public enum EStatus {
    PROCESSING(0),
    SHIPPED(1),
    CANCELLED(2),
    COMPLETED(3);
    private final int value;
    EStatus(int value) {
        this.value = value;
    }
}