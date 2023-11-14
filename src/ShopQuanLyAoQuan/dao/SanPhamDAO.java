/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShopQuanLyAoQuan.dao;

import ShopQuanLyAoQuan.entity.SanPham;
import com.fsm.utils.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class SanPhamDAO extends ShopAoQuanDAO<SanPham,String> {
    final String INSERT_SQL ="insert into SanPham(MaSP,MaLoai,TenSP,GiaNhap,Size,SoLuongTon,GhiChu,HinhAnh) VALUES(?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL ="UPDATE SanPham MaLoai= ?, TenSP = ?, GiaNhap= ?, Size= ?, SoLuongTon= ?, GhiChu= ?, HinhAnh= ? where MaSP = ?";
    final String DELETE_SQL ="DELETE from SanPham WHERE MaSP = ?";
    final String SELECT_ALL_SQL ="SELECT * FROM SanPham";
    final String SELECT_BY_ID_SQL ="SELECT * FROM SanPham WHERE MaSP= ?";

    @Override
    public void insert(SanPham entity) {
        jdbcHelper.Update(INSERT_SQL, entity.getMaSP(),entity.getMaLoai(),entity.getTenSP(),entity.getGiaNhap(),entity.getSize(), entity.getTonKho(),entity.getGhiChu(),entity.getHinhAnh());
    }

    @Override
    public void update(SanPham entity) {
        jdbcHelper.Update(UPDATE_SQL,entity.getMaLoai(),entity.getTenSP(),entity.getGiaNhap(),entity.getSize(), entity.getTonKho(),entity.getGhiChu(),entity.getHinhAnh(), entity.getMaSP());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.Update(DELETE_SQL,id);
    }

    @Override
    public List<SanPham> selectALl() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public SanPham selectById(String id) {
        List<SanPham> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                SanPham entity = new SanPham();
                entity.setMaSP(rs.getString("MaSP"));
                entity.setMaLoai(rs.getString("MaLoai"));
                entity.setTenSP(rs.getString("TenSP"));
                entity.setGiaNhap(rs.getFloat("GiaNhap"));
                entity.setSize(rs.getString("Size"));
                entity.setTonKho(rs.getInt("SoLuongTon"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setHinhAnh(rs.getString("HinhAnh"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
