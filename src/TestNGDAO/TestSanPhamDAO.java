package TestNGDAO;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ShopQuanLyAoQuan.dao.SanPhamDAO;
import ShopQuanLyAoQuan.entity.SanPham;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.List;

public class TestSanPhamDAO {

    SanPhamDAO sanPhamDAO;

    @BeforeMethod
    public void setUp() {
        sanPhamDAO = new SanPhamDAO();
    }

    @AfterMethod
    public void tearDown() {
        sanPhamDAO = null;
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test(description = "Kiểm thử chèn với mô hình null")
    public void testInsertWithNullModel() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP(null);
        exceptionRule.expect(Exception.class);
        sanPhamDAO.insert(sanPham);
    }

    @Test(description = "Kiểm thử chèn với mô hình trống")
    public void testInsertWithEmptyModel() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP(" ");
        exceptionRule.expect(Exception.class);
        sanPhamDAO.insert(sanPham);
    }

    @Test(description = "Kiểm thử chèn với mô hình hợp lệ")
    public void testInsertWithValidModel() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP("SP00003");
        sanPham.setMaLoai("LOAI01");
        sanPham.setTenSP("Áo thun nam");
        sanPham.setGiaNhap(250000);
        sanPham.setSoLuongNhap(100);
        sanPham.setGhiChu(null);
        sanPham.setHinhAnh("image.jpg");
        try {
            sanPhamDAO.insert(sanPham);
        } catch (Exception e) {
            Assert.fail("Chèn với mô hình hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử cập nhật với mô hình null")
    public void testUpdateWithNullModel() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP(null);
        exceptionRule.expect(Exception.class);
        sanPhamDAO.update(sanPham);
    }

    @Test(description = "Kiểm thử cập nhật với mô hình trống")
    public void testUpdateWithEmptyModel() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP(" ");
        exceptionRule.expect(Exception.class);
        sanPhamDAO.update(sanPham);
    }

    @Test(description = "Kiểm thử cập nhật với mô hình hợp lệ")
    public void testUpdateWithValidModel() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP("SP03");
        sanPham.setMaLoai("LOAI02");
        sanPham.setTenSP("Áo khoác nữ");
        try {
            sanPhamDAO.update(sanPham);
        } catch (Exception e) {
            Assert.fail("Cập nhật với mô hình hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử xóa với ID null")
    public void testDeleteWithNullModel() {
        exceptionRule.expect(Exception.class);
        sanPhamDAO.delete(null);
    }

    @Test(description = "Kiểm thử xóa với ID trống")
    public void testDeleteWithEmptyModel() {
        exceptionRule.expect(Exception.class);
        sanPhamDAO.delete(" ");
    }

    @Test(description = "Kiểm thử xóa với ID hợp lệ")
    public void testDeleteWithValidModel() {
        try {
            sanPhamDAO.delete("SP03");
        } catch (Exception e) {
            Assert.fail("Xóa với ID hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử lấy tất cả")
    public void testSelectAll() {
        List<SanPham> sanPhams = sanPhamDAO.selectALl();
        Assert.assertNotNull(sanPhams);
    }

    @Test(description = "Kiểm thử lấy theo ID")
    public void testSelectById() {
        SanPham sanPham = sanPhamDAO.selectById("1");
        Assert.assertNotNull(sanPham);
    }

    @Test(description = "Kiểm thử tìm kiếm theo Mã SP hoặc Tên SP")
    public void testTimKiemByMaSPOrTenSP() {
        List<SanPham> sanPhams = sanPhamDAO.timKiem("SP01", true);
        Assert.assertNotNull(sanPhams);
    }

    @Test(description = "Kiểm thử lấy theo Tên SP")
    public void testSelectByTenSP() {
        SanPham sanPham = sanPhamDAO.selectByTenSP("Áo thun nam");
        Assert.assertNotNull(sanPham);
    }
}
