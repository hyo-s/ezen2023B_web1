package ezenweb.service;

import ezenweb.model.dao.ProductDao;
import ezenweb.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private FileService fileService;

// ======================= 1. 등록 서비스 / 기능처리 요청 ======================= //
    public boolean postProductRegister(ProductDto productDto){
        System.out.println("ProductService.postProductRegister");

        // 1. 첨부파일 업로드 처리
        List<String> list = new ArrayList<>();
        productDto.getUploadFiles().forEach((uploadFiles)->{
            String fileName = fileService.fileUpload(uploadFiles);
            if(fileName != null){
                list.add(fileName);
            }
        });
        productDto.setPimg(list);
        return productDao.postProductRegister(productDto);
    }
// ======================= 2. 제품 [지도에 출력할] 요청 ======================= //
    public List<ProductDto> getProductList(){
        System.out.println("ProductService.getProductList");
        return productDao.getProductList();
    }
// ======================= 3. 해당 제품의 찜하기 등록 ======================= //
    public boolean getPlikeWrite(int pno, int mno){
        System.out.println("ProductService.getPlikeWrite");
        return productDao.getPlikeWrite(pno, mno);
    }

// ======================= 4. 해당 제품의 찜하기 상태 출력 ======================= //
    public boolean getPlikeView(int pno, int mno){
        System.out.println("ProductService.getPlikView");
        return productDao.getPlikeView(pno, mno);
    }
// ======================= 5. 해당 제품의 찜하기 취소 삭제 ======================= //
    public boolean getPlikeDelete(int pno, int mno){
        System.out.println("ProductService.getPlikeDelete");
        return productDao.getPlikeDelete(pno, mno);
    }

}
