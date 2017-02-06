package com.lunapps.sevice;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("unzipFileService")
public interface UnzipFIle {
    void unzip(String zipFilePath, String destDirectory) throws IOException;
}
