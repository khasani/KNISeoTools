package com.kniapps.seotools.dao;

import java.util.Date;
import java.util.List;

import com.kniapps.seotools.model.Position;

public interface IPositionDao {

    public List<Position> find(long siteID, Date date_start, Date date_end);

}
