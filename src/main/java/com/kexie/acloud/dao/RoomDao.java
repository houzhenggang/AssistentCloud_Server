package com.kexie.acloud.dao;

import com.kexie.acloud.domain.Room;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import javax.annotation.Resource;

/**
 * Created : wen
 * DateTime : 2017/5/12 10:42
 * Description :
 */
@Repository
public class RoomDao extends HibernateDaoSupport implements IRoomDao {

    @Resource
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public Room getRoom(int roomId) {
        return getHibernateTemplate().get(Room.class, roomId);
    }

    @Override
    public Integer addRoom(Room room) {
        Serializable save = getHibernateTemplate().save(room);
        return (Integer) save;
    }

    @Override
    public void updateRoom(Room room) {
        getHibernateTemplate().update(room);
    }

    @Override
    public void deleteRoom(int roomId) {
        Room room = new Room();
        room.setRoomId(roomId);
        getHibernateTemplate().delete(room);
    }
}
