/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShopQuanLyAoQuan.dao;

import ShopQuanLyAoQuan.entity.DonHangChiTiet;
import com.fsm.utils.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class DonHangChiTietDAO extends ShopAoQuanDAO<DonHangChiTiet,String> {
    final String INSERT_SQL ="insert into DonHangChiTiet(MaDHCT, MaSP, MaDH, SoLuong, DonGia, GhiChu) VALUES(?,?,?,?,?,?)";
    final String UPDATE_SQL ="UPDATE DonHangChiTiet SET MaSP = ?, MaDH= ?, SoLuong = ?, DonGia= ?, GhiChu= ? where MaDHCT = ?";
    final String DELETE_SQL ="DELETE from DonHangChiTiet WHERE MaDHCT = ?";
    final String SELECT_ALL_SQL ="SELECT * FROM DonHangChiTiet";
    final String SELECT_BY_ID_SQL ="SELECT * FROM DonHangChiTiet WHERE MaDHCT= ?";

    @Override
    public void insert(DonHangChiTiet entity) {
        jdbcHelper.Update(INSERT_SQL, entity.getMaDHCT(),entity.getMaSP(),entity.getMaDH(),entity.getSoLuong(),entity.getDonGia(),entity.getGhiChu());
    }

    @Override
    public void update(DonHangChiTiet entity) {
        jdbcHelper.Update(UPDATE_SQL,entity.getMaSP(),entity.getMaDH(),entity.getSoLuong(),entity.getDonGia(),entity.getGhiChu(),entity.getMaDHCT());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.Update(DELETE_SQL,id);
    }

    @Override
    public List<DonHangChiTiet> selectALl() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DonHangChiTiet selectById(String id) {
        List<DonHangChiTiet> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DonHangChiTiet> selectBySql(String sql, Object... args) {
        List<DonHangChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                DonHangChiTiet entity = new DonHangChiTiet();
                entity.setMaDHCT(rs.getString("MaDHCT"));
                entity.setMaSP(rs.getString("MaSP"));
                entity.setMaDH(rs.getString("MaDH"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setDonGia(rs.getFloat("DonGia"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
