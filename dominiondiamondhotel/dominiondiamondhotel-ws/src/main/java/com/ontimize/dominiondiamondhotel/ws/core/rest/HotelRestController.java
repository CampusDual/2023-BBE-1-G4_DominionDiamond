package com.ontimize.dominiondiamondhotel.ws.core.rest;

import com.ontimize.dominiondiamondhotel.api.core.service.IHotelService;
import com.ontimize.dominiondiamondhotel.model.core.dao.HotelDao;
import com.ontimize.dominiondiamondhotel.model.core.utils.HotelUtils;
import com.ontimize.jee.common.db.SQLStatementBuilder;
import com.ontimize.jee.common.db.SQLStatementBuilder.BasicExpression;
import com.ontimize.jee.common.db.SQLStatementBuilder.BasicOperator;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotels")
public class HotelRestController extends ORestController<IHotelService> {
    @Autowired
    private IHotelService hotelService;

    @Override
    public IHotelService getService() {
        return this.hotelService;
    }

    @Autowired
    private HotelDao hotelDao;

    @GetMapping(value = "hotelByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResult hotelSearchByName(@PathVariable String name) {
        try {
            Map<String, Object> key = new HashMap<>();
            BasicExpression be = HotelUtils.searchBy(BasicOperator.LIKE_OP, HotelDao.ATTR_NAME, name);
            if (be != null) {
                key.put(SQLStatementBuilder.ExtendedSQLConditionValuesProcessor.EXPRESSION_KEY, be);
            }
            return hotelService.hotelQuery(key, List.of(HotelDao.ATTR_NAME));
        } catch (Exception e) {
            EntityResult res = new EntityResultMapImpl();
            res.setCode(EntityResult.OPERATION_WRONG);
            return res;
        }
    }

    @GetMapping(value = "hotelById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResult hotelSearchById(@PathVariable int id) {
        try {
            Map<String, Object> key = new HashMap<>();
            BasicExpression be = HotelUtils.searchBy(BasicOperator.EQUAL_OP, HotelDao.ATTR_ID, String.valueOf(id));
            if (be != null) {
                key.put(SQLStatementBuilder.ExtendedSQLConditionValuesProcessor.EXPRESSION_KEY, be);
            }
            return hotelService.hotelQuery(key, this.hotelDao.getColumns());
        } catch (Exception e) {
            EntityResult res = new EntityResultMapImpl();
            res.setCode(EntityResult.OPERATION_WRONG);
            return res;
        }
    }

    @GetMapping(value = "hotelByZip/{zip}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityResult hotelSearchByZip(@PathVariable int zip) {
        try {
            Map<String, Object> key = new HashMap<>();
            BasicExpression be = HotelUtils.searchBy(BasicOperator.EQUAL_OP, HotelDao.ATTR_ZIP_ID, String.valueOf(zip));
            if (be != null) {
                key.put(SQLStatementBuilder.ExtendedSQLConditionValuesProcessor.EXPRESSION_KEY, be);
            }
            return hotelService.hotelQuery(key, List.of(HotelDao.ATTR_NAME));
        } catch (Exception e) {
            EntityResult res = new EntityResultMapImpl();
            res.setCode(EntityResult.OPERATION_WRONG);
            return res;
        }
    }
}
