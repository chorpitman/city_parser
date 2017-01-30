package com.lunapps.sevice;

import java.io.IOException;

public interface UnzipFIle {
    void unzip(String zipFilePath, String destDirectory) throws IOException;
}
