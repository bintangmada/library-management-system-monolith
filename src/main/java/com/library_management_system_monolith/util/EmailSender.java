package com.library_management_system_monolith.util;

public interface EmailSender {
    void send(String to, String subject, String email) throws InterruptedException;
}
