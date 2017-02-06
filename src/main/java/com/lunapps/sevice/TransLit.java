package com.lunapps.sevice;

import com.lunapps.model.Model;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: 2/2/17
@Service("transLitService")
public interface TransLit {
    void transLitCyr2Ukr(List<Model> modelList);
}
