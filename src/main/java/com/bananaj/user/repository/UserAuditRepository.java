package com.bananaj.user.repository;

import com.bananaj.user.domain.User;
import com.bananaj.user.domain.UserAudit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by cio on 21/02/15.
 */
public interface UserAuditRepository extends CrudRepository<UserAudit, String> {


    List<UserAudit> findTop10ByUserOrderByDateDesc(User user);
}
