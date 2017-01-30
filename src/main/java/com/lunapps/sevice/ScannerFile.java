package com.lunapps.sevice;

import com.lunapps.model.Model;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public interface ScannerFile {
    List<Model> scanPath(String filepath);

    void print(List<?> modelList);
}
