package com.mashibing.dm.facade;


import lombok.Data;

@Data
public class ConfigModel {

    //是否生成表现层
    private boolean isGenPresentation;

    //是否生成逻辑层
    private boolean isGenBusiness;

    //是否生成DAO
    private boolean isGenDAO;

}
