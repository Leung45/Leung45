package com.czl;

import com.czl.exception.EmptyInputException;
import org.junit.jupiter.api.Test;

/**
 * @author CZL
 * @date 2020 09 24
 */
public class ExceptionTest {

    /**
     * 空输入异常测试
     */
    @Test
    public void testException(){
        try {
            throw new EmptyInputException("Input field is empty!");
        } catch (EmptyInputException e) {
            e.printStackTrace();
        }
    }
}

