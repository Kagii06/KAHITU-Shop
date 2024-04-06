package TestNGDAO;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import ShopQuanLyAoQuan.dao.DonHangDAO;
import ShopQuanLyAoQuan.entity.DonHang;

import java.util.List;

public class TestDonHangDAO {

    DonHangDAO donHangDAO;

    @BeforeMethod
    public void setUp() {
        donHangDAO = new DonHangDAO();
    }

    @AfterMethod
    public void tearDown() {
        donHangDAO = null;
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test(description = "Kiểm thử chèn với mô hình null")
    public void testInsertWithNullModel() {
        DonHang donHang = new DonHang();
        donHang.setMaDH(null);
        exceptionRule.expect(Exception.class);
        donHangDAO.insert(donHang);
    }

    @Test(description = "Kiểm thử chèn với mô hình trống")
    public void testInsertWithEmptyModel() {
        DonHang donHang = new DonHang();
        donHang.setMaDH(" ");
        exceptionRule.expect(Exception.class);
        donHangDAO.insert(donHang);
    }

    @Test(description = "Kiểm thử chèn với mô hình hợp lệ")
    public void testInsertWithValidModel() {
        DonHang donHang = new DonHang();
        donHang.setMaDH("DH00003");
        donHang.setMaKH("KH01");
        donHang.setMaNV("NV01");
        //donHang.setNgayLap("2024-04-06");
        donHang.setTongTien(100000.0f);
        donHang.setGhiChu(null);
        try {
            donHangDAO.insert(donHang);
        } catch (Exception e) {
            Assert.fail("Chèn với mô hình hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử cập nhật với mô hình null")
    public void testUpdateWithNullModel() {
        DonHang donHang = new DonHang();
        donHang.setMaDH(null);
        exceptionRule.expect(Exception.class);
        donHangDAO.update(donHang);
    }

    @Test(description = "Kiểm thử cập nhật với mô hình trống")
    public void testUpdateWithEmptyModel() {
        DonHang donHang = new DonHang();
        donHang.setMaDH(" ");
        exceptionRule.expect(Exception.class);
        donHangDAO.update(donHang);
    }

    @Test(description = "Kiểm thử cập nhật với mô hình hợp lệ")
    public void testUpdateWithValidModel() {
        DonHang donHang = new DonHang();
        donHang.setMaDH("DH03");
        donHang.setMaKH("KH02");
        donHang.setMaNV("NV02");
        try {
            donHangDAO.update(donHang);
        } catch (Exception e) {
            Assert.fail("Cập nhật với mô hình hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử xóa với ID null")
    public void testDeleteWithNullModel() {
        exceptionRule.expect(Exception.class);
        donHangDAO.delete(null);
    }

    @Test(description = "Kiểm thử xóa với ID trống")
    public void testDeleteWithEmptyModel() {
        exceptionRule.expect(Exception.class);
        donHangDAO.delete(" ");
    }

    @Test(description = "Kiểm thử xóa với ID hợp lệ")
    public void testDeleteWithValidModel() {
        try {
            donHangDAO.delete("DH03");
        } catch (Exception e) {
            Assert.fail("Xóa với ID hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử lấy tất cả")
    public void testSelectAll() {
        List<DonHang> donHangs = donHangDAO.selectALl();
        Assert.assertNotNull(donHangs);
    }

    @Test(description = "Kiểm thử lấy theo ID")
    public void testSelectById() {
        DonHang donHang = donHangDAO.selectById("1");
        Assert.assertNotNull(donHang);
    }

}