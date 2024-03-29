package com.ontimize.dominiondiamondhotel.model.core.service;

import com.ontimize.dominiondiamondhotel.api.core.service.IHistRoomService;
import com.ontimize.dominiondiamondhotel.model.core.dao.HistRoomDao;
import com.ontimize.dominiondiamondhotel.model.core.dao.HotelDao;
import com.ontimize.dominiondiamondhotel.model.core.dao.IdDocumentTypesDao;
import com.ontimize.dominiondiamondhotel.model.core.dao.RoomDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ontimize.dominiondiamondhotel.api.core.utils.HelperUtils.INVALID_DATA;

@Lazy
@Service("HistRoomService")
public class HistRoomService implements IHistRoomService {

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HistRoomDao histRoomDao;

    @Autowired
    private RoomDao roomDao;
    @Autowired
    private RoomService roomService;

    @Autowired
    private IdDocumentTypesDao idDocumentTypesDao;

    @Override
    public EntityResult histroomQuery(Map<String, Object> filter, List<String> columns) throws OntimizeJEERuntimeException {
        EntityResult roomExists = this.roomService.roomQuery(filter, List.of(RoomDao.ATTR_ID));
        EntityResult er = new EntityResultMapImpl();
        if (((List<?>) roomExists.get(RoomDao.ATTR_ID)).get(0) != null){
            Map<String, Object> roomFilter = new HashMap<>();
            roomFilter.put(HistRoomDao.ATTR_ROOM_ID, filter.get(RoomDao.ATTR_ID));
            return this.daoHelper.query(this.histRoomDao, roomFilter,columns, "gethistroom");
        }
        er.setMessage(INVALID_DATA);
        er.setCode(EntityResult.OPERATION_WRONG);
        return er;
    }

    @Override
    public EntityResult histRoomByHotelIdQuery(Map<String, Object> attrMap, List<String> columns) throws OntimizeJEERuntimeException {
        int hotelId = Integer.parseInt(String.valueOf(attrMap.get("hotel_id")));
        Map<String, Object> hotelExistsKeyMap = new HashMap<>();
        hotelExistsKeyMap.put(HotelDao.ATTR_ID, hotelId);
        EntityResult hotelExists = hotelService.hotelQuery(hotelExistsKeyMap, List.of(HotelDao.ATTR_ID));
        if (((List<?>) hotelExists.get(HotelDao.ATTR_ID)).get(0) != null) {
            return daoHelper.query(this.histRoomDao, attrMap, columns, "viewhotelid");
        }
        EntityResult hotelNoExists = new EntityResultMapImpl();
        hotelNoExists.setCode(EntityResult.OPERATION_WRONG);
        hotelNoExists.setMessage(INVALID_DATA);
        return hotelNoExists;
    }
}
