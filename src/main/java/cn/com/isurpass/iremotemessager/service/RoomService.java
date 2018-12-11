package cn.com.isurpass.iremotemessager.service;

import cn.com.isurpass.iremotemessager.dao.RoomDao;
import cn.com.isurpass.iremotemessager.domain.Room;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoomService {
    @Resource
    private RoomDao roomDao;

    public Room findById(Integer id) {
        return roomDao.findById(id).orElse(null);
    }
}
