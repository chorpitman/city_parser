package com.lunapps.sevice;

import java.io.IOException;

public interface DownloadFile {
    void downloadFile(String fileURL, String saveDir) throws IOException;
}
