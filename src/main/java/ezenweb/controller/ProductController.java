package ezenweb.controller;

import ezenweb.model.dto.ProductDto;
import ezenweb.service.MemberService;
import ezenweb.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;

// ======================= 1. 등록 서비스 / 기능처리 요청 ======================= //
    @PostMapping("/register.do")
    @ResponseBody
    public boolean postProductRegister(ProductDto productDto){
        System.out.println("ProductController.postProductRegister");

        // 1. 작성자 처리
        Object sessionObj = request.getSession().getAttribute("loginDto");
        if(sessionObj == null){
            return false;
        }
        productDto.setMno(memberService.doGetLoginInfo((String)sessionObj).getNo());
        return productService.postProductRegister(productDto);
    }
// ======================= 2. 제품 [지도에 출력할] 요청 ======================= //
    @GetMapping("/list.do")
    @ResponseBody
    public List<ProductDto> getProductList(){
        System.out.println("ProductController.getProductList");
        return productService.getProductList();
    }

// ======================= 3. 해당 제품의 찜하기 등록 ======================= //
    @PostMapping("/plike.do")
    @ResponseBody
    public boolean getPlikeWrite(int pno){
        System.out.println("ProductController.getPlikeWrite");
        Object sessionObj = request.getSession().getAttribute("loginDto");
        if(sessionObj == null){
            return false;
        }
        int mno = memberService.doGetLoginInfo((String)sessionObj).getNo();
        return productService.getPlikeWrite(pno, mno);
    }

// ======================= 4. 해당 제품의 찜하기 상태 출력 ======================= //
    @GetMapping("/plike.do")
    @ResponseBody
    public boolean getPlikeView(int pno){
        System.out.println("ProductController.getPlikView");
        Object sessionObj = request.getSession().getAttribute("loginDto");
        if(sessionObj == null){
            return false;
        }
        int mno = memberService.doGetLoginInfo((String)sessionObj).getNo();
        return productService.getPlikeView(pno, mno);
    }
// ======================= 5. 해당 제품의 찜하기 취소 삭제 ======================= //
    @DeleteMapping("/plike.do")
    @ResponseBody
    public boolean getPlikeDelete(int pno){
        System.out.println("ProductController.getPlikeDelete");
        Object sessionObj = request.getSession().getAttribute("loginDto");
        if(sessionObj == null){
            return false;
        }
        int mno = memberService.doGetLoginInfo((String)sessionObj).getNo();
        return productService.getPlikeDelete(pno, mno);
    }


// ======================= 1. 등록 페이지/화면/뷰 요청 ======================= //
    @GetMapping("/register")
    public String productRegister(){
        System.out.println("ProductController.getProductList");
        return "ezenweb/product/register";
    }
// ======================= 2. 제품 지도 페이지/화면/뷰/요청 ======================= //
    @GetMapping("/list")
    public String productList(){
        System.out.println("ProductController.productList");
        return "ezenweb/product/list";
    }


}
