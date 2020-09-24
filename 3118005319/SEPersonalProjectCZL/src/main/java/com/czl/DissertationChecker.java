package com.czl;

import java.io.File;
import java.io.IOException;

//查重工具接口
public interface DissertationChecker {

    double getCheckedResult(File file1, File file2) throws IOException;

}
