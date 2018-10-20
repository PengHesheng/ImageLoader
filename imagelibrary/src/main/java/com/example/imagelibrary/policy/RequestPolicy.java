package com.example.imagelibrary.policy;

import com.example.imagelibrary.data.Task;

/**
 * @author 14512 on 2018/10/20
 */
public interface RequestPolicy {

    /**
     * 根据不同的url进行不同的请求
     * @param task
     */
    void request(Task task);
}
