package com.czl;

import java.io.File;
import java.io.IOException;

/**
 * @author CZL
 * @date 2020 09 22
 */
public interface DissertationChecker {

    /**
     * 查重方法
     * @param file1 文件
     * @param file2 文件
     * @return 查重率
     * @throws IOException
     */
    double getCheckedResult(File file1, File file2) throws IOException;

}
