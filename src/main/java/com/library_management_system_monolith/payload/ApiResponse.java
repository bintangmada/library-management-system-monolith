package com.library_management_system_monolith.payload;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse<T> {
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private T payload;

    public ApiResponse() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
