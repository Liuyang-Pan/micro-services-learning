package org.feign.clients;

import org.feign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/19 16:52
 */
@FeignClient("basic-user")
public interface UserClient {

    @GetMapping("/user/getUser/{id}")
    User findById(@PathVariable(value = "id") String id);
}
