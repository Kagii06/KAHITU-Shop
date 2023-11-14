/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ShopQuanLyAoQuan.dao;

import ShopQuanLyAoQuan.entity.KhachHang;
import com.fsm.utils.jdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class KhachHangDAO extends ShopAoQuanDAO<KhachHang,String> {
    final String INSERT_SQL ="insert into KhachHang(MaKH, HoTen, DiaChi, SDT, Email) VALUES(?,?,?,?,?)";
    final String UPDATE_SQL ="UPDATE KhachHang SET HoTen = ?, DiaChi= ?, SDT = ?, Email= ? where MaKH = ?";
    final String DELETE_SQL ="DELETE from KhachHang WHERE MaKH = ?";
    final String SELECT_ALL_SQL ="SELECT * FROM KhachHang";
    final String SELECT_BY_ID_SQL ="SELECT * FROM KhachHang WHERE MaKH= ?";

    @Override
    public void insert(KhachHang entity) {
        jdbcHelper.Update(INSERT_SQL, entity.getMaKH(),entity.getHoTen(),entity.getDiaChi(),entity.getSDT(),entity.getEmail());
    }

    @Override
    public void update(KhachHang entity) {
        jdbcHelper.Update(UPDATE_SQL,entity.getHoTen(),entity.getDiaChi(),entity.getSDT(),entity.getEmail(),entity.getMaKH());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.Update(DELETE_SQL,id);
    }

    @Override
    public List<KhachHang> selectALl() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhachHang selectById(String id) {
        List<KhachHang> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getString("MaKH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setSDT(rs.getString("SDT"));
                entity.setEmail(rs.getString("Email"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
