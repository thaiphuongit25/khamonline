package com.mtons.Khamonline.modules.repository;

import com.mtons.Khamonline.modules.entity.Comment;
import com.mtons.Khamonline.modules.entity.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long>, JpaSpecificationExecutor<PaymentHistory> {


}
