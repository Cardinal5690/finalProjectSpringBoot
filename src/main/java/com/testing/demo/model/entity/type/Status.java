package com.testing.demo.model.entity.type;

public enum Status {
    BLOCKED(0),
    UNBLOCKED(1);

    private final int STATUS;

    Status(int STATUS) {
        this.STATUS = STATUS;
    }

    public int getSTATUS() {
        return STATUS;
    }
}
