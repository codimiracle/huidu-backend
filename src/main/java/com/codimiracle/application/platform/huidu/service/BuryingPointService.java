package com.codimiracle.application.platform.huidu.service;

public interface BuryingPointService {
    void forTag(String userId, String tagId);
    void forTag(String userId, String tagId, Float score);

    void forBookDetails(String userId, String bookId);

    void forReading(String userId, String bookId);
}
