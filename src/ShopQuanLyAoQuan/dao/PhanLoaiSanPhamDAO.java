/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShopQuanLyAoQuan.dao;

import ShopQuanLyAoQuan.entity.PhanLoaiSanPham;
import com.fsm.utils.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class PhanLoaiSanPhamDAO extends ShopAoQuanDAO<PhanLoaiSanPham,String> {
    final String INSERT_SQL ="insert into PhanLoaiSanPham(MaLoai,TenLoai) VALUES(?,?)";
    final String UPDATE_SQL ="UPDATE PhanLoaiSanPham SET TenLoai = ? where MaLoai = ?";
    final String DELETE_SQL ="DELETE from PhanLoaiSanPham WHERE MaLoai = ?";
    final String SELECT_ALL_SQL ="SELECT * FROM PhanLoaiSanPham";
    final String SELECT_BY_ID_SQL ="SELECT * FROM PhanLoaiSanPham WHERE MaLoai= ?";

    @Override
    public void insert(PhanLoaiSanPham entity) {
        jdbcHelper.Update(INSERT_SQL, entity.getMaLoai(),entity.getTenLoai());
    }

    @Override
    public void update(PhanLoaiSanPham entity) {
        jdbcHelper.Update(UPDATE_SQL,entity.getTenLoai(), entity.getMaLoai());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.Update(DELETE_SQL,id);
    }

    @Override
    public List<PhanLoaiSanPham> selectALl() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public PhanLoaiSanPham selectById(String id) {
        List<PhanLoaiSanPham> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<PhanLoaiSanPham> selectBySql(String sql, Object... args) {
        List<PhanLoaiSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                PhanLoaiSanPham entity = new PhanLoaiSanPham();
                entity.setMaLoai(rs.getString("MaLoai"));
                entity.setTenLoai(rs.getString("TenLoai"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
