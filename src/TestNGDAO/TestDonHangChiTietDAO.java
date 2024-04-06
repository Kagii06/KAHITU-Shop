package TestNGDAO;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ShopQuanLyAoQuan.dao.DonHangChiTietDAO;
import ShopQuanLyAoQuan.entity.DonHangChiTiet;

public class TestDonHangChiTietDAO {
    
    private DonHangChiTietDAO donHangChiTietDAO;
    
    @BeforeClass
    public void setUp() {
        donHangChiTietDAO = new DonHangChiTietDAO();
    }
    
    @AfterClass
    public void tearDown() {
        donHangChiTietDAO = null;
    }
    
    @Test
    public void testInsertWithNullModel() {
        // Kiểm tra insert với đối tượng null
        try {
            donHangChiTietDAO.insert(null);
            // Nếu không có exception nào được ném, kiểm tra không thành công
            Assert.fail("Phương thức insert không ném ra ngoại lệ NullPointerException với đối tượng null.");
        } catch (NullPointerException e) {
            // Ngoại lệ được ném thành công, test thành công
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void testInsertWithEmptyModel() {
        // Kiểm tra insert với đối tượng rỗng
        try {
            donHangChiTietDAO.insert(new DonHangChiTiet());
            // Nếu không có exception nào được ném, kiểm tra không thành công
            Assert.fail("Phương thức insert không ném ra ngoại lệ với đối tượng rỗng.");
        } catch (Exception e) {
            // Ngoại lệ được ném thành công, test thành công
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void testInsertWithValidModel() {
        // Tạo một đối tượng hợp lệ để insert
        DonHangChiTiet validModel = new DonHangChiTiet();
        validModel.setMaSP("SP01");
        validModel.setMaDH("DH01");
        validModel.setSoLuong(2);
        validModel.setDonGia(150000);
        validModel.setGhiChu("sp");
        
        // Kiểm tra insert với đối tượng hợp lệ
        try {
            donHangChiTietDAO.insert(validModel);
            // Nếu không có exception nào được ném, test thành công
            Assert.assertTrue(true);
        } catch (Exception e) {
            // Nếu có exception được ném, test không thành công
            Assert.fail("Phương thức insert ném ra ngoại lệ với đối tượng hợp lệ.");
        }
    }
    
    @Test
    public void testUpdateWithNullModel() {
        // Kiểm tra cập nhật với đối tượng null
        try {
            donHangChiTietDAO.update(null);
            // Nếu không có exception nào được ném, kiểm tra không thành công
            Assert.fail("Phương thức update không ném ra ngoại lệ NullPointerException với đối tượng null.");
        } catch (NullPointerException e) {
            // Ngoại lệ được ném thành công, test thành công
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateWithEmptyModel() {
        // Kiểm tra cập nhật với đối tượng rỗng
        try {
            donHangChiTietDAO.update(new DonHangChiTiet());
            // Nếu không có exception nào được ném, kiểm tra không thành công
            Assert.fail("Phương thức update không ném ra ngoại lệ với đối tượng rỗng.");
        } catch (Exception e) {
            // Ngoại lệ được ném thành công, test thành công
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void testUpdateWithValidModel() {
        // Tạo một đối tượng hợp lệ để cập nhật
        DonHangChiTiet validModel = new DonHangChiTiet();
        validModel.setMaSP("SP01");
        validModel.setMaDH("DH01");
        validModel.setSoLuong(3); // Cập nhật số lượng
        validModel.setDonGia(150000); // Cập nhật đơn giá
        validModel.setGhiChu("sp");
        
        // Kiểm tra cập nhật với đối tượng hợp lệ
        try {
            donHangChiTietDAO.update(validModel);
            // Nếu không có exception nào được ném, test thành công
            Assert.assertTrue(true);
        } catch (Exception e) {
            // Nếu có exception được ném, test không thành công
            Assert.fail("Phương thức update ném ra ngoại lệ với đối tượng hợp lệ.");
        }
    }
    
    @Test
    public void testDeleteWithNullModel() {
        // Kiểm tra xoá với mã đơn hàng null
        try {
            donHangChiTietDAO.delete(null);
            // Nếu không có exception nào được ném, kiểm tra không thành công
            Assert.fail("Phương thức delete không ném ra ngoại lệ NullPointerException với mã đơn hàng null.");
        } catch (NullPointerException e) {
            // Ngoại lệ được ném thành công, test thành công
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void testDeleteWithEmptyModel() {
        // Kiểm tra xoá với mã đơn hàng rỗng
        try {
            donHangChiTietDAO.delete("");
            // Nếu không có exception nào được ném, kiểm tra không thành công
            Assert.fail("Phương thức delete không ném ra ngoại lệ với mã đơn hàng rỗng.");
        } catch (Exception e) {
            // Ngoại lệ được ném thành công, test thành công
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void testDeleteWithValidModel() {
        // Tạo một mã đơn hàng hợp lệ để xoá
        String maDH = "DH01";
        
        // Kiểm tra xoá với mã đơn hàng hợp lệ
        try {
            donHangChiTietDAO.delete(maDH);
            // Nếu không có exception nào được ném, test thành công
            Assert.assertTrue(true);
        } catch (Exception e) {
            // Nếu có exception được ném, test không thành công
            Assert.fail("Phương thức delete ném ra ngoại lệ với mã đơn hàng hợp lệ.");
        }
    }

    
    
    
}



