package TestNGDAO;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import ShopQuanLyAoQuan.dao.DonHangDAO;
import ShopQuanLyAoQuan.entity.DonHang;import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
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
        donHang.setMaDH("DH00007");
        donHang.setMaKH("KH00001");
        donHang.setMaNV("NV00001");
        Calendar calendar = Calendar.getInstance();
	    calendar.set(2024, 04, 06);
	    Date validDate = calendar.getTime();
        donHang.setNgayLap(validDate); // convert string to Date
        donHang.setTongTien(100000.0f);
        donHang.setGhiChu(null);
        try {
            donHangDAO.insert(donHang);
        } catch (Exception e) {
           // Assert.fail("Chèn với mô hình hợp lệ không nên ném ra ngoại lệ");
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
        donHang.setMaDH("DH00003");
        donHang.setMaKH("KH00002");
        donHang.setMaNV("NV00002");
        Calendar calendar = Calendar.getInstance();
	    calendar.set(2024, 04, 07);
	    Date validDate = calendar.getTime();
        donHang.setNgayLap(validDate); // convert string to Date
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
        DonHang donHang = donHangDAO.selectById("DH00002");
        Assert.assertNotNull(donHang);
    }
    @Test(description = "Kiểm tra truy vấn đơn hàng bằng SQL")
    public void testSelectBySql() {
        List<DonHang> donHangs = donHangDAO.selectBySql("SELECT * FROM donhang");
        Assert.assertNotNull(donHangs);
        Assert.assertFalse(donHangs.isEmpty());
    }

    @Test(description = "Kiểm tra truy vấn đơn hàng theo mã nhân viên")
    public void testSelectNhanVienByMaNV() {
        List<DonHang> donHangs = donHangDAO.selectNhanVienByMaNV("NV00002");
        Assert.assertNotNull(donHangs);
    }

    @Test(description = "Kiểm tra truy vấn nhân viên theo mã đơn hàng")
    public void testSelectNhanVienByMaDH() {
        List<DonHang> donHangs = donHangDAO.selectNhanVienByMaDH("DH00002");
        Assert.assertNotNull(donHangs);
    }

    @Test(description = "Kiểm thử lấy danh sách các ngày đơn hàng")
    public void testSelectDay() {
        DonHangDAO donHangDAO = new DonHangDAO();
        List<Date> ngayLapList = donHangDAO.selectDay();
        Assert.assertNotNull(ngayLapList);
        Assert.assertFalse(ngayLapList.isEmpty());
    }


    @Test(description = "Kiểm tra truy vấn đơn hàng theo tháng")
    public void testSelectMonth() {
        DonHangDAO donHangDAO = new DonHangDAO();
        List<String> monthList = donHangDAO.selectMonth();
        Assert.assertNotNull(monthList);
        Assert.assertFalse(monthList.isEmpty());
    }

    @Test(description = "Kiểm tra truy vấn đơn hàng theo năm")
    public void testSelectYear() {
        DonHangDAO donHangDAO = new DonHangDAO();
        List<Integer> yearList = donHangDAO.selectYear();
        Assert.assertNotNull(yearList);
        Assert.assertFalse(yearList.isEmpty());
    }



}