package ezenweb.controller;

import ezenweb.model.dto.ProductDto;
import ezenweb.service.MemberService;
import ezenweb.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
