package com.lunapps.sevice;

import org.springframework.stereotype.Service;

import java.io.IOException;
@Service("downLoadFileService")
public interface DownloadFile {
    void downloadFile(String fileURL, String saveDir) throws IOException;
}
