package ru.vlad.NauJava.service;

public interface TransactionService {
    void executeBookingTransaction(Long sessionId, Long userId, int row, int seat);
}