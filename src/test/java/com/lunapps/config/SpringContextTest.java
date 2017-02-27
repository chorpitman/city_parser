package com.lunapps.config;

import com.lunapps.configuration.AppConfig;
import com.lunapps.sevice.impl.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class SpringContextTest {

    @Autowired
    ScannerFileImpl scannerFile;

    @Autowired
    DownloadFileImpl downloadFile;

    @Autowired
    GoogleMapsSearchImpl googleMapsSearch;

    @Autowired
    GooglePlacesSearchImpl placesSearch;

    @Autowired
    UnzipFIleImpl unzipFIle;

    @Test
    public void should_return_scanner_file() {
        assertThat(scannerFile, instanceOf(ScannerFileImpl.class));
    }

    @Test
    public void should_return_download_file() {
        assertThat(downloadFile, instanceOf(DownloadFileImpl.class));
    }

    @Test
    public void should_return_google_search() {
        assertThat(googleMapsSearch, instanceOf(GoogleMapsSearchImpl.class));
    }

    @Test
    public void should_return_google_places() {
        assertThat(placesSearch, instanceOf(GooglePlacesSearchImpl.class));
    }

    @Test
    public void should_return_unzip() {
        assertThat(unzipFIle, instanceOf(UnzipFIleImpl.class));
        System.out.println("hello test");
    }
}

