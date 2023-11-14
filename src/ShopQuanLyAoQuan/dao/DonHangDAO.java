/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShopQuanLyAoQuan.dao;

import ShopQuanLyAoQuan.entity.DonHang;
import com.fsm.utils.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class DonHangDAO extends ShopAoQuanDAO<DonHang,String> {
    final String INSERT_SQL ="insert into DonHang(MaDH, MaKH, MaNV, NgayLap, TongTien, GhiChu) VALUES(?,?,?,?,?,?)";
    final String UPDATE_SQL ="UPDATE DonHang SET MaKH = ?, MaNV= ?, NgayLap = ?, TongTien= ?, GhiChu=? where MaDH = ?";
    final String DELETE_SQL ="DELETE from DonHang WHERE MaDH = ?";
    final String SELECT_ALL_SQL ="SELECT * FROM DonHang";
    final String SELECT_BY_ID_SQL ="SELECT * FROM DonHang WHERE MaDH= ?";

    @Override
    public void insert(DonHang entity) {
        jdbcHelper.Update(INSERT_SQL, entity.getMaDH(),entity.getMaKH(),entity.getMaNV(),entity.getNgayLap(),entity.getTongTien(),entity.getGhiChu());
    }

    @Override
    public void update(DonHang entity) {
        jdbcHelper.Update(UPDATE_SQL,entity.getMaKH(),entity.getMaNV(),entity.getNgayLap(),entity.getTongTien(),entity.getGhiChu(),entity.getMaDH());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.Update(DELETE_SQL,id);
    }

    @Override
    public List<DonHang> selectALl() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DonHang selectById(String id) {
        List<DonHang> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DonHang> selectBySql(String sql, Object... args) {
        List<DonHang> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                DonHang entity = new DonHang();
                entity.setMaDH(rs.getString("MaDH"));
                entity.setMaKH(rs.getString("MaKH"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayLap(rs.getDate("NgayLap"));
                entity.setTongTien(rs.getFloat("TongTien"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
