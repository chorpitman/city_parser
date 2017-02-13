package com.lunapps.config.utils;

import com.lunapps.model.AlternativeModel;
import com.lunapps.model.RegionInfo;

import java.util.ArrayList;
import java.util.List;

public class BaseTestRegionInfoModelHelper {
    public static RegionInfo getRegionInfoModel(String regionId, int cityIndex, String regionCyrillName,
                                                String regionInterName, String featureCode) {
        RegionInfo regionInfo = new RegionInfo();
        regionInfo.setRegionId(regionId);
        regionInfo.setCityIndex(cityIndex);
        regionInfo.setRegionCyrillicName(regionCyrillName);
        regionInfo.setRegionInternationalName(regionInterName);
        regionInfo.setFeatureCode(featureCode);

        return regionInfo;
    }
}
