package TestNGDAO;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import ShopQuanLyAoQuan.dao.NhanVienDAO;
import ShopQuanLyAoQuan.entity.NhanVien;

import java.util.List;


public class TestNhanVienDAO {

    NhanVienDAO nhanVienDAO;
    @BeforeMethod
    public void setUp() {
        nhanVienDAO = new NhanVienDAO();
    }

    @AfterMethod
    public void tearDown() {
        nhanVienDAO = null;
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test(description = "Kiểm thử chèn với mô hình null")
    public void testInsertWithNullModel() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(null);
        exceptionRule.expect(Exception.class);
        nhanVienDAO.insert(nhanVien);
    }

    @Test(description = "Kiểm thử chèn với mô hình trống")
    public void testInsertWithEmptyModel() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(" ");
        exceptionRule.expect(Exception.class);
        nhanVienDAO.insert(nhanVien);
    }

    @Test(description = "Kiểm thử chèn với mô hình hợp lệ")
    public void testInsertWithValidModel() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV00003");
        nhanVien.setHoTen("Nguyễn Văn A");
        nhanVien.setDiaChi("Quảng Ngãi");
        nhanVien.setSDT("0534534343");
        nhanVien.setEmail("Nguyenvana@gmail.com");
        nhanVien.setLuong(6000000);
        nhanVien.setGhiChu(null);
        try {
            nhanVienDAO.insert(nhanVien);
        } catch (Exception e) {
            Assert.fail("Chèn với mô hình hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử cập nhật với mô hình null")
    public void testUpdateWithNullModel() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(null);
        exceptionRule.expect(Exception.class);
        nhanVienDAO.update(nhanVien);
    }

    @Test(description = "Kiểm thử cập nhật với mô hình trống")
    public void testUpdateWithEmptyModel() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(" ");
        exceptionRule.expect(Exception.class);
        nhanVienDAO.update(nhanVien);
    }

    @Test(description = "Kiểm thử cập nhật với mô hình hợp lệ")
    public void testUpdateWithValidModel() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV03");
        nhanVien.setHoTen("Nguyễn Văn B");
        try {
            nhanVienDAO.update(nhanVien);
        } catch (Exception e) {
            Assert.fail("Cập nhật với mô hình hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử xóa với ID null")
    public void testDeleteWithNullModel() {
        exceptionRule.expect(Exception.class);
        nhanVienDAO.delete(null);
    }

    @Test(description = "Kiểm thử xóa với ID trống")
    public void testDeleteWithEmptyModel() {
        exceptionRule.expect(Exception.class);
        nhanVienDAO.delete(" ");
    }

    @Test(description = "Kiểm thử xóa với ID hợp lệ")
    public void testDeleteWithValidModel() {
        try {
            nhanVienDAO.delete("NV03");
        } catch (Exception e) {
            Assert.fail("Xóa với ID hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử lấy tất cả")
    public void testSelectAll() {
        List<NhanVien> nhanViens = nhanVienDAO.selectALl();
        Assert.assertNotNull(nhanViens);
    }

    @Test(description = "Kiểm thử lấy theo ID")
    public void testSelectById() {
        NhanVien nhanVien = nhanVienDAO.selectById("1");
        Assert.assertNotNull(nhanVien);
    }

    @Test(description = "Kiểm thử tìm kiếm theo Mã NV hoặc Tên NV")
    public void testTimkiemByMaNVOrTenNV() {
        List<NhanVien> nhanViens = nhanVienDAO.timkiemByMaNVOrTenNV("NV01", true);
        Assert.assertNotNull(nhanViens);
    }

    @Test(description = "Kiểm thử lấy theo SDT")
    public void testSelectBySDT() {
        NhanVien nhanVien = nhanVienDAO.selectBySDT("0534534343");
        Assert.assertNotNull(nhanVien);
    }

    @Test(description = "Kiểm tra sự tồn tại của Mã NV")
    public void testMaNVExists() {
        boolean exists = nhanVienDAO.maNVExists("NV01");
        Assert.assertTrue(exists);
    }

    @Test(description = "Kiểm thử lấy theo Email")
    public void testSelectByEmail() {
        NhanVien nhanVien = nhanVienDAO.selectByEmail("Nguyenvana@gmail.com");
        Assert.assertNotNull(nhanVien);
    }
}
