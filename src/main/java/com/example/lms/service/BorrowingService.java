package com.example.lms.service;

import com.example.lms.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingService implements IBorrowingService{
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
}
