package TestNGDAO;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import ShopQuanLyAoQuan.dao.DonHangChiTietDAO;
import ShopQuanLyAoQuan.entity.DonHangChiTiet;

import java.util.List;

public class TestDonHangChiTietDAO {

    DonHangChiTietDAO donHangChiTietDAO;

    @BeforeMethod
    public void setUp() {
        donHangChiTietDAO = new DonHangChiTietDAO();
    }

    @AfterMethod
    public void tearDown() {
        donHangChiTietDAO = null;
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test(description = "Kiểm thử chèn với mô hình null")
    public void testInsertWithNullModel() {
        // Arrange
        DonHangChiTiet donHangChiTiet = new DonHangChiTiet();
        donHangChiTiet.setMaDH(null);

        // Act and Assert
        donHangChiTietDAO.insert(donHangChiTiet);
    }
    
    @Rule
    public ExpectedException testInsertWithEmptyModel = ExpectedException.none();
    @Test(description = "Kiểm thử chèn với mô hình trống")
    public void testInsertWithEmptyModel() {
    	 DonHangChiTiet donHangChiTiet = new DonHangChiTiet();

         donHangChiTiet.setMaDH("");

    	    // Act
    	    donHangChiTietDAO.insert(donHangChiTiet);

    }

    @Test(description = "Kiểm thử chèn với mô hình hợp lệ")
    public void testInsertWithValidModel() {
        DonHangChiTiet donHangChiTiet = new DonHangChiTiet();
        donHangChiTiet.setMaSP("SP00001");
        donHangChiTiet.setMaDH("DH00007");
        donHangChiTiet.setSoLuong(2);
        donHangChiTiet.setDonGia(150000);
        donHangChiTiet.setGhiChu("sp");
        try {
            donHangChiTietDAO.insert(donHangChiTiet);
        } catch (Exception e) {
            Assert.fail("Chèn với mô hình hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử cập nhật với mô hình null")
    public void testUpdateWithNullModel() {
        DonHangChiTiet donHangChiTiet = new DonHangChiTiet();
        donHangChiTiet.setMaDHCT(null);
        exceptionRule.expect(Exception.class);
        donHangChiTietDAO.update(donHangChiTiet);
    }

    @Test(description = "Kiểm thử cập nhật với mô hình trống")
    public void testUpdateWithEmptyModel() {
        DonHangChiTiet donHangChiTiet = new DonHangChiTiet();
        donHangChiTiet.setMaDHCT(" ");
        exceptionRule.expect(Exception.class);
        donHangChiTietDAO.update(donHangChiTiet);
    }

    @Test(description = "Kiểm thử cập nhật với mô hình hợp lệ")
    public void testUpdateWithValidModel() {
        DonHangChiTiet donHangChiTiet = new DonHangChiTiet();
        donHangChiTiet.setMaSP("SP00001");
        donHangChiTiet.setMaDH("DH00001");
        donHangChiTiet.setSoLuong(3); // Cập nhật số lượng
        donHangChiTiet.setDonGia(150000); // Cập nhật đơn giá
        donHangChiTiet.setGhiChu("sp");
        try {
            donHangChiTietDAO.update(donHangChiTiet);
        } catch (Exception e) {
            Assert.fail("Cập nhật với mô hình hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử xóa với ID null")
    public void testDeleteWithNullModel() {
        exceptionRule.expect(Exception.class);
        donHangChiTietDAO.delete(null);
    }

    @Test(description = "Kiểm thử xóa với ID trống")
    public void testDeleteWithEmptyModel() {
        exceptionRule.expect(Exception.class);
        donHangChiTietDAO.delete(" ");
    }

    @Test(description = "Kiểm thử xóa với ID hợp lệ")
    public void testDeleteWithValidModel() {
        // Tạo một mã đơn hàng hợp lệ để xoá
        String maDHCT = "1029";
        try {
            donHangChiTietDAO.delete(maDHCT);
        } catch (Exception e) {
            Assert.fail("Xóa với ID hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm tra lấy tất cả các bản ghi từ bảng hóa đơn chi tiết")
    public void testSelectAll() {
        List<DonHangChiTiet> donHangChiTiets = donHangChiTietDAO.selectALl();
        Assert.assertNotNull(donHangChiTiets);
        Assert.assertTrue(donHangChiTiets.size() > 0);
    }

    @Test(description = "Kiểm tra lấy các cột của bảng hóa đơn chi tiết theo mã đơn hàng chi tiết")
    public void testSelectById() {
        String maDHCT = "1029";
        DonHangChiTiet donHangChiTiet = donHangChiTietDAO.selectById(maDHCT);

        Assert.assertNotNull(donHangChiTiet);
        Assert.assertEquals(donHangChiTiet.getMaDHCT(), maDHCT);
    }

    @Test(description = "Kiểm tra lấy các cột của bảng hóa đơn chi tiết theo mã đơn hàng")
    public void testSelectByIdDH() {
        String maDH = "DH00001";
        List<DonHangChiTiet> donHangChiTiets = donHangChiTietDAO.selectByIdDH(maDH);

        Assert.assertNotNull(donHangChiTiets);
        Assert.assertTrue(donHangChiTiets.size() > 0);
    }
    }
